# jag-vips-client

VIPS client as part of DPS Interface Modernization project for Ministry of AG/PSSG

## Usage

Add the following dependency to your spring boot application

```xml

    <dependencies>

        <dependency>
            <groupId>ca.bc.gov.open</groupId>
            <artifactId>jag-vips-client</artifactId>
            <version>0.1.1</version>
        </dependency>

    </dependencies>

```

Configure the following properties in your `application.properties`

```
vips.client.basePath=path-to-order
vips.client.username=username
vips.client.password=password
```
## Openshift and Nexus Integration

After implementing your Openshift pipeline, you'll want to add the following env vars:

```
    - name: NEXUS_VERSION
      value: nexus3
    - name: NEXUS_PROTOCOL
      value: http
    - name: NEXUS_URL
      value: 'nexus:8081'
    - name: NEXUS_SNAPSHOT_REPOSITORY
      value: ${NAME_OF_YOUR_NEXUS_SNAPSHOT_REPOSITORY}
    - name: NEXUS_RELEASE_REPOSITORY
      value: ${NAME_OF_YOUR_NEXUS_RELEASE_REPOSITORY}
    - name: NEXUS_CREDENTIAL_ID
      value: nexus-credentials
```

Make sure to replace the values for NEXUS_SNAPSHOT_REPOSITORY and NEXUS_RELEASE_REPOSITORY.

And then replace any stock Jenkins pipeline with the Jenkinsfile found in this repository.

After running the Openshift pipeline, you should fine something similar in either the Snapshot or Release Nexus repository:

![snapsnot-example](/images/snapshot.PNG)\