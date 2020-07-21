package ca.bc.gov.open.jagvipsclient.payment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import ca.bc.gov.open.jag.ordsvipsclient.api.PaymentApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.PaymentStatusApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsPaymentOrdsResponse;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsPaymentPostRequest;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsPaymentStatusOrdsResponse;
import ca.bc.gov.open.jagvipsclient.VipsOrdsClientConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaymentServiceImplTest {

	public static final String API_EXCEPTION = "api exception";
	public static final String SUCCESS_RESPONSE = "success";
	public static final String ERROR_RESPONSE = "error";

	private PaymentService service;

	@Mock
	private PaymentApi paymentApiMock;

	@Mock
	private PaymentStatusApi paymentStatusApiMock;

	@BeforeAll
	public void setup() throws ApiException {
		MockitoAnnotations.initMocks(this);

		VipsPaymentOrdsResponse paymentOrdsResponseSuccess = new VipsPaymentOrdsResponse();
		paymentOrdsResponseSuccess.setStatusCode(String.valueOf(VipsOrdsClientConstants.SERVICE_SUCCESS_CD));
		paymentOrdsResponseSuccess.setStatusMessage(SUCCESS_RESPONSE);

		VipsPaymentOrdsResponse paymentOrdsResponseError = new VipsPaymentOrdsResponse();
		paymentOrdsResponseError.setStatusCode(String.valueOf(VipsOrdsClientConstants.SERVICE_FAILURE_CD));
		paymentOrdsResponseError.setStatusMessage(ERROR_RESPONSE);

		VipsPaymentStatusOrdsResponse paymentStatusOrdsResponseSuccess = new VipsPaymentStatusOrdsResponse();
		paymentStatusOrdsResponseSuccess.setResultCode(String.valueOf(VipsOrdsClientConstants.SERVICE_SUCCESS_CD));
		paymentStatusOrdsResponseSuccess.setResultMessage(SUCCESS_RESPONSE);
		paymentStatusOrdsResponseSuccess.setAmountOwing("1");

		VipsPaymentStatusOrdsResponse paymentStatusOrdsResponseError = new VipsPaymentStatusOrdsResponse();
		paymentStatusOrdsResponseError.setResultCode(String.valueOf(VipsOrdsClientConstants.SERVICE_FAILURE_CD));
		paymentStatusOrdsResponseError.setResultMessage(ERROR_RESPONSE);

		Mockito.when(paymentApiMock.paymentNoticeNoPost(eq("1"), any())).thenReturn(paymentOrdsResponseSuccess);
		Mockito.when(paymentApiMock.paymentNoticeNoPost(eq("2"), any())).thenThrow(new ApiException(ERROR_RESPONSE));
		Mockito.when(paymentStatusApiMock.paymentStatusNoticeNoGet(eq("1")))
				.thenReturn(paymentStatusOrdsResponseSuccess);
		Mockito.when(paymentStatusApiMock.paymentStatusNoticeNoGet(eq("2")))
				.thenThrow(new ApiException(ERROR_RESPONSE));

		service = new PaymentServiceImpl(paymentStatusApiMock, paymentApiMock);
	}

	@Test
	public void postSuccess() {
		VipsPaymentResponse response = service.postVipsPaymentDetails(1L, new VipsPaymentPostRequest());

		Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_SUCCESS_CD, response.getRespCode());
		Assertions.assertEquals(SUCCESS_RESPONSE, response.getRespMsg());
	}

	@Test
	public void postException() {
		VipsPaymentResponse response = service.postVipsPaymentDetails(2L, new VipsPaymentPostRequest());

		Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_FAILURE_CD, response.getRespCode());
		Assertions.assertEquals(ERROR_RESPONSE, response.getRespMsg());
	}

	@Test
	public void getSuccess() {
		VipsPaymentResponse response = service.getVipsPaymentStatus(1L);

		Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_SUCCESS_CD, response.getRespCode());
		Assertions.assertEquals(SUCCESS_RESPONSE, response.getRespMsg());
	}

	@Test
	public void getException() {
		VipsPaymentResponse response = service.getVipsPaymentStatus(2L);

		Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_FAILURE_CD, response.getRespCode());
		Assertions.assertEquals(ERROR_RESPONSE, response.getRespMsg());
	}

}
