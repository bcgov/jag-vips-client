package ca.bc.gov.open.jagvipsclient.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.bc.gov.open.jag.ordsvipsclient.api.PaymentStatusApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsPaymentStatusOrdsResponse;

/**
 * 
 * Collection of services for accessing VIPS payment related data.
 * 
 * @author sivakaruna
 *
 */
public class PaymentServiceImpl implements PaymentService {

	private final PaymentStatusApi paymentApi;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public PaymentServiceImpl(PaymentStatusApi paymentApi) {
		this.paymentApi = paymentApi;
	}

	@Override
	public VipsPaymentStatusResponse getVipsPaymentStatus(Long noticeNo) {

		try {
			String noticeNoStr = Long.toString(noticeNo);
			VipsPaymentStatusOrdsResponse response = this.paymentApi.paymentStatusNoticeNoGet(noticeNoStr);

			PaymentStatus status = new PaymentStatus();
			status.setAmountOwing(response.getAmountOwing());

			return VipsPaymentStatusResponse.successResponse(status, response.getResultCode(),
					response.getResultMessage());
		} catch (ApiException ex) {
			logger.error("Payment Service threw an exception: " + ex.getMessage(), ex);
			return VipsPaymentStatusResponse.errorResponse(ex.getMessage());
		}
	}
}
