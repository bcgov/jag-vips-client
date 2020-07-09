package ca.bc.gov.open.jagvipsclient.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import ca.bc.gov.open.jagvipsclient.VipsOrdsClientConstants;

/**
 *
 * Represents the VIPS Payment status Response
 *
 * @author sivakaruna
 * 
 */
@JacksonXmlRootElement(localName = "vipsPaymentStatusResponse")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VipsPaymentStatusResponse {

	private PaymentStatus status;
	private int respCode;
	private String respMsg;

	private VipsPaymentStatusResponse(PaymentStatus status, int respCode, String respMsg) {
		this.status = status;
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	private VipsPaymentStatusResponse(int respCode, String respMsg) {
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

	public static VipsPaymentStatusResponse errorResponse(String errorMessage) {
		return new VipsPaymentStatusResponse(VipsOrdsClientConstants.SERVICE_FAILURE_CD, errorMessage);
	}

	public static VipsPaymentStatusResponse successResponse(PaymentStatus status, String respCodeStr, String respMsg) {
		return new VipsPaymentStatusResponse(status, Integer.parseInt(respCodeStr), respMsg);
	}

}
