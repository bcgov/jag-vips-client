package ca.bc.gov.open.jagvipsclient.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import ca.bc.gov.open.jagvipsclient.VipsOrdsClientConstants;

/**
 *
 * Represents the VIPS Payment service Response
 *
 * @author sivakaruna
 * 
 */
@JacksonXmlRootElement(localName = "vipsPaymentResponse")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VipsPaymentResponse {

	private PaymentStatus status;
	private int respCode;
	private String respMsg;

	private VipsPaymentResponse(PaymentStatus status, int respCode, String respMsg) {
		this.status = status;
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	private VipsPaymentResponse(int respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public int getRespCode() {
		return respCode;
	}

	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public static VipsPaymentResponse errorResponse(String errorMessage) {
		return new VipsPaymentResponse(VipsOrdsClientConstants.SERVICE_FAILURE_CD, errorMessage);
	}
	
	public static VipsPaymentResponse successResponse(String respCodeStr, String respMsg) {
		return new VipsPaymentResponse(Integer.parseInt(respCodeStr), respMsg);
	}

	public static VipsPaymentResponse successStatusResponse(PaymentStatus status, String respCodeStr, String respMsg) {
		return new VipsPaymentResponse(status, Integer.parseInt(respCodeStr), respMsg);
	}
}
