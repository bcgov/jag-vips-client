package ca.bc.gov.open.jagvipsclient.health;

import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;

public interface HealthService {

        HealthResponse health() throws ApiException, ApiException;
}
