package ca.bc.gov.open.jagvipsclient.payment;

/**
 * 
 * Collection of services for accessing VIPS payment related data.
 * 
 * @author sivakaruna
 *
 */
public interface PaymentService {

	VipsPaymentStatusResponse getVipsPaymentStatus(Long noticeNo);

}
