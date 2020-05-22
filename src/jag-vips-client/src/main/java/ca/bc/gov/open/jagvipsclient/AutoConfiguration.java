package ca.bc.gov.open.jagvipsclient;

import ca.bc.gov.open.jag.ordsvipsclient.api.DocumentApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.HealthApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiClient;
import ca.bc.gov.open.jagvipsclient.document.DocumentService;
import ca.bc.gov.open.jagvipsclient.document.DocumentServiceImpl;
import ca.bc.gov.open.jagvipsclient.health.HealthService;
import ca.bc.gov.open.jagvipsclient.health.HealthServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(VipsClientProperties.class)
public class AutoConfiguration {

    private final VipsClientProperties vipsClientProperties;


    public AutoConfiguration(VipsClientProperties vipsClientProperties) {
        this.vipsClientProperties = vipsClientProperties;
    }

    @Bean(name = "vipsApiClient")
    public ApiClient apiClient() {
        ApiClient apiClient = new ApiClient();

        apiClient.setBasePath(vipsClientProperties.getBasePath());

        if(StringUtils.isNotBlank(vipsClientProperties.getUsername()))
            apiClient.setUsername(vipsClientProperties.getUsername());

        if(StringUtils.isNotBlank(vipsClientProperties.getPassword()))
            apiClient.setPassword(vipsClientProperties.getPassword());

        return apiClient;
    }

    @Bean
    public DocumentApi documentApi(ApiClient apiClient) {
        return new DocumentApi(apiClient);
    }

    @Bean
    public DocumentService documentService(DocumentApi documentApi) {
        return new DocumentServiceImpl(documentApi);
    }

    @Bean
    public HealthApi healthApi(ApiClient apiClient) {
        return new HealthApi(apiClient);
    }

    @Bean
    public HealthService healthService(HealthApi healthApi) {
        return new HealthServiceImpl(healthApi);
    }

}
