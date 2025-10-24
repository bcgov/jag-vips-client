package ca.bc.gov.open.jagvipsclient.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import ca.bc.gov.open.jagvipsclient.VipsOrdsClientConstants;


/**
 *
 * Represents the VIPS Valid Expiry Date Response
 *
 *
 */
@JacksonXmlRootElement(localName = "VipsValidExpiryDateResponse")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VipsValidExpiryDateResponse {

	private String expiryDate;
	private int respCode;
	private String respMsg;

	public VipsValidExpiryDateResponse(String expiryDate, int respCode, String respMsg) {
		this.expiryDate = expiryDate;
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public VipsValidExpiryDateResponse(int respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public int getRespCode() {
		return respCode;
	}
	
	public String getRespMsg() {
		return respMsg;
	}
	
	public static VipsValidExpiryDateResponse errorResponse(String errorMessage) {
		return new VipsValidExpiryDateResponse(VipsOrdsClientConstants.SERVICE_FAILURE_CD, errorMessage);
	}

	public static VipsValidExpiryDateResponse successResponse(String expiryDate, String respCodeStr, String respMsg) {
		return new VipsValidExpiryDateResponse(expiryDate, Integer.parseInt(respCodeStr), respMsg);
	}

}
