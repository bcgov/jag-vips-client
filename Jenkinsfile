timeout(time: 25, unit: 'MINUTES') {
    try {
        timeout(time: 20, unit: 'MINUTES') {
            node {
                stage("Initialize") {
                    project = env.BUILD
                }
            }

            node('maven') {
                stage("Checkout") {
                    git url: "${GIT_SOURCE_URL}", branch: "${GIT_SOURCE_REF}"
                }
                stage("Build JARs") {
                    sh "mvn clean package -f./src -P all"
                }

                stage('Push to Nexus'){
                    // Read POM xml file using 'readMavenPom' step , this step 'readMavenPom' is included in: https://plugins.jenkins.io/pipeline-utility-steps
                    pom = readMavenPom file: "./src/pom.xml";
                    // Find built artifact under target folder
                    filesByGlob = findFiles(glob: "**/target/*.jar");
                    pomsByGlob = findFiles(glob: "**/target/*.pom");
                
                    echo "Found ${filesByGlob.length} JARS"
                    echo "Found ${filesByGlob.length} POMS"
                    for(file in filesByGlob){
                        // Print some info from the artifact found
                        echo "${file.name} ${file.path} ${file.directory} ${file.length} ${file.lastModified}"
                    }
                    for(file in pomsByGlob){
                        // Print some info from the artifact found
                        echo "${file.name} ${file.path} ${file.directory} ${file.length} ${file.lastModified}"
                    }
                    // Promote all the JARS
                    filesByGlob.each {
                        echo "${it.name} ${it.path} ${it.directory} ${it.length} ${it.lastModified}"
                        // Extract the path from the File found
                        artifactPath = it.path;
                        // Assign to a boolean response verifying If the artifact name exists
                        artifactExists = fileExists artifactPath;
                        if(artifactExists) {
                            pomPath = it.path.replace("target/${it.name}", "pom.xml")
                            pomExists = fileExists pomPath;
                            if(!pomExists){
                                echo "POM Does not exist!!!"
                            }
                            artifactPom = readMavenPom file: pomPath;
                            echo "*** File: ${artifactPath}, group: ${artifactPom.groupId}, packaging: ${artifactPom.packaging}, version ${artifactPom.version}";
                            if (artifactPom.version.matches("(.*)SNAPSHOT")){
                                artifactRepo = NEXUS_SNAPSHOT_REPOSITORY
                            }else{
                                artifactRepo = NEXUS_RELEASE_REPOSITORY
                            }
                            echo "***UPLOADING pom AT: ${pomPath}";
                            nexusArtifactUploader(
                                nexusVersion: NEXUS_VERSION,
                                protocol: NEXUS_PROTOCOL,
                                nexusUrl: NEXUS_URL,
                                groupId: artifactPom.groupId,
                                version: artifactPom.version,
                                repository: artifactRepo,
                                credentialsId: NEXUS_CREDENTIAL_ID,
                                artifacts: [
                                    // Artifact generated such as .jar, .ear and .war files.
                                    [artifactId: artifactPom.artifactId,
                                    classifier: '',
                                    file: artifactPath,
                                    type: 'jar'],
                                    // Lets upload the pom.xml file for additional information for Transitive dependencies
                                    [
                                        artifactId: artifactPom.artifactId,
                                        classifier: '',
                                        file: pomPath,
                                        type: "pom"
                                    ]
                                ]
                            );
                        } else {
                            error "*** File: ${artifactPath}, could not be found";
                        }
                    }
                    // Set the upload repo for parent pom
                    if (pom.version.matches("(.*)SNAPSHOT")){
                        artifactRepo = NEXUS_SNAPSHOT_REPOSITORY
                    }else{
                        artifactRepo = NEXUS_RELEASE_REPOSITORY
                    }
                }
            }
        }
    } 
    catch (err) {
        echo "in catch block"
        currentBuild.result = 'FAILURE'
        env.ERR = "${err}"
        node {
            sh "curl -XPOST '${LIFECYCLE_WEBHOOK}' -H 'Content-type: application/json' --data '{\"username\":\"The Pipe Lion\",\"icon_emoji\":\":lion_face:\",\"text\": \"${APP_NAME} Pipeline Failure - ${ERR}\"}'"
        }
        echo "Caught: ${err}"
        throw err
    }
}
