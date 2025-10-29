package ca.bc.gov.open.jagvipsclient.validation;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.bc.gov.open.jag.ordsvipsclient.api.ValidationApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsValidationExpiryDateResponse;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsValidationOrdsResponse;

/**
 *
 * Collection of services for accessing VIPS validations.
 *
 * @author jdosil
 *
 */
public class ValidationServiceImpl implements ValidationService {

	private final ValidationApi validationApi;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public ValidationServiceImpl(ValidationApi validationApi) {
		this.validationApi = validationApi;
	}

	@Override
	public VipsValidTimeframeResponse getWithinTimeframe(String startDate, BigDecimal intervalDays) {

		try {

			// Here what we're doing is a conversion from the actual response to a properly
			// camelCased JSON response.
			VipsValidationOrdsResponse response = this.validationApi.validationsWithinTimeframeGet(startDate, intervalDays);

			logger.info("Processed get getWithinTimeframe: ORDS returned code: {} and message: {} ",
					response.getStatusCode(), response.getStatusMessage());

			String valid = String.valueOf("Y".equals(response.getValid()));
			
			return VipsValidTimeframeResponse.successResponse(valid, response.getStatusCode(),
					response.getStatusMessage());

		} catch (ApiException ex) {
			logger.error("getWithinTimeframe Service threw an exception: " + ex.getMessage(), ex);
			return VipsValidTimeframeResponse.errorResponse(ex.getMessage());
		}
	}

	@Override
	public VipsValidExpiryDateResponse getValidExpiryDate(String startDate, BigDecimal intervalDays) {

		try {

			VipsValidationExpiryDateResponse response = this.validationApi.validationsValidExpiryDateGet(startDate, intervalDays);

			logger.info("Processed get getValidExpiryDate: ORDS returned code: {} and message: {} ",
					response.getStatusCode(), response.getStatusMessage());
			
			return VipsValidExpiryDateResponse.successResponse(response.getExpiryDate(), response.getStatusCode(),
					response.getStatusMessage());

		} catch (ApiException ex) {
			logger.error("getValidExpiryDate Service threw an exception: " + ex.getMessage(), ex);
			return VipsValidExpiryDateResponse.errorResponse(ex.getMessage());
		}
	}
}
