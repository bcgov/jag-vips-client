package ca.bc.gov.open.jagvipsclient.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.bc.gov.open.jag.ordsvipsclient.api.PaymentApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.PaymentStatusApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsPaymentOrdsResponse;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsPaymentPostRequest;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsPaymentStatusOrdsResponse;

/**
 * 
 * Collection of services for accessing VIPS payment related data.
 * 
 * @author sivakaruna
 *
 */
public class PaymentServiceImpl implements PaymentService {

	private final PaymentStatusApi paymentStatusApi;

	private final PaymentApi paymentApi;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public PaymentServiceImpl(PaymentStatusApi paymentStatusApi, PaymentApi paymentApi) {
		this.paymentStatusApi = paymentStatusApi;
		this.paymentApi = paymentApi;
	}

	@Override
	public VipsPaymentResponse getVipsPaymentStatus(Long noticeNo) {

		try {
			String noticeNoStr = Long.toString(noticeNo);
			VipsPaymentStatusOrdsResponse response = this.paymentStatusApi.paymentStatusNoticeNoGet(noticeNoStr);

			PaymentStatus status = new PaymentStatus();
			status.setAmountOwing(response.getAmountOwing());

			return VipsPaymentResponse.successStatusResponse(status, response.getResultCode(),
					response.getResultMessage());
		} catch (ApiException ex) {
			logger.error("Payment Service threw an exception: " + ex.getMessage(), ex);
			return VipsPaymentResponse.errorResponse(ex.getMessage());
		}
	}

	@Override
	public VipsPaymentResponse postVipsPaymentDetails(Long noticeNo, VipsPaymentPostRequest request) {
		try {
			String noticeNoStr = Long.toString(noticeNo);
			VipsPaymentOrdsResponse response = this.paymentApi.paymentNoticeNoPost(noticeNoStr, request);

			return VipsPaymentResponse.successResponse(response.getStatusCode(), response.getStatusMessage());
		} catch (ApiException ex) {
			logger.error("Payment Service threw an exception: " + ex.getMessage(), ex);
			return VipsPaymentResponse.errorResponse(ex.getMessage());
		}
	}
}
