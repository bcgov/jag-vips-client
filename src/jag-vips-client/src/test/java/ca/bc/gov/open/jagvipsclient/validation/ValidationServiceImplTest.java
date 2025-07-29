package ca.bc.gov.open.jagvipsclient.validation;

import ca.bc.gov.open.jag.ordsvipsclient.api.ValidationApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsValidationOrdsResponse;
import ca.bc.gov.open.jagvipsclient.VipsOrdsClientConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Validation service tests
 *
 * @author jdosil
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ValidationServiceImplTest {

	public static final String API_EXCEPTION = "api exception";
	public static final String SUCCESS_RESPONSE = "success";
	public static final String ERROR_RESPONSE = "Message: error";

	private ValidationService service;

	@Mock
	private ValidationApi validationApiMock;

	@BeforeAll
	public void setup() throws ApiException {
		MockitoAnnotations.initMocks(this);

		VipsValidationOrdsResponse validationOrdsResponse = new VipsValidationOrdsResponse();
		validationOrdsResponse.setStatusCode(String.valueOf(VipsOrdsClientConstants.SERVICE_SUCCESS_CD));
		validationOrdsResponse.setStatusMessage(SUCCESS_RESPONSE);
		validationOrdsResponse.setValid("Y");

		Calendar cldr = Calendar.getInstance();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String nowFormattedDate = formatter.format(cldr.getTime());

		cldr.add(Calendar.DATE, -4);
		String fourDaysAgoFormattedDate = formatter.format(cldr.getTime());

		Mockito.when(validationApiMock.validationsWithinTimeframeGet(nowFormattedDate, BigDecimal.valueOf(3)))
				.thenReturn(validationOrdsResponse);
		Mockito.when(validationApiMock.validationsWithinTimeframeGet(fourDaysAgoFormattedDate,BigDecimal.valueOf(2)))
				.thenThrow(new ApiException(ERROR_RESPONSE));

		service = new ValidationServiceImpl(validationApiMock);
	}

	@Test
	public void getSuccess() {
		Calendar cldr = Calendar.getInstance();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String nowFormattedDate = formatter.format(cldr.getTime());

		VipsValidTimeframeResponse response = service.getWithinTimeframe(nowFormattedDate, BigDecimal.valueOf(3));

		Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_SUCCESS_CD, response.getRespCode());
		Assertions.assertEquals(SUCCESS_RESPONSE, response.getRespMsg());
		Assertions.assertEquals("Y", response.getValid());
	}

	@Test
	public void getException() {
		Calendar cldr = Calendar.getInstance();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		cldr.add(Calendar.DATE, -4);
		String fourDaysAgoFormattedDate = formatter.format(cldr.getTime());
		VipsValidTimeframeResponse response = service.getWithinTimeframe(fourDaysAgoFormattedDate, BigDecimal.valueOf(2));

		Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_FAILURE_CD, response.getRespCode());
		Assertions.assertTrue(response.getRespMsg().contains(ERROR_RESPONSE));
	}

}
