package ca.bc.gov.open.jagvipsclient.payment;

import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsPaymentPostRequest;

/**
 * 
 * Collection of services for accessing VIPS payment related data.
 * 
 * @author sivakaruna
 *
 */
public interface PaymentService {

	VipsPaymentResponse getVipsPaymentStatus(Long noticeNo);
	
	VipsPaymentResponse postVipsPaymentDetails(Long noticeNo, VipsPaymentPostRequest request);

}
