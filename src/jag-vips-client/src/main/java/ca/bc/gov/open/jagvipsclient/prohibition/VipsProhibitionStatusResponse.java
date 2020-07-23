package ca.bc.gov.open.jagvipsclient.prohibition;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import ca.bc.gov.open.jagvipsclient.VipsOrdsClientConstants;


/**
 *
 * Represents the VIPS Prohibition status Response
 *
 *
 */
@JacksonXmlRootElement(localName = "vipsProhibitionStatusResponse")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VipsProhibitionStatusResponse {

	private ProhibitionStatus status;
	private int respCode;
	private String respMsg;
	
	public VipsProhibitionStatusResponse(ProhibitionStatus status, int respCode, String respMsg) {
		this.status = status; 
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public VipsProhibitionStatusResponse(int respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public ProhibitionStatus getStatus() {
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

	public static VipsProhibitionStatusResponse errorResponse(String errorMessage) {
		return new VipsProhibitionStatusResponse(VipsOrdsClientConstants.SERVICE_FAILURE_CD, errorMessage);
	}

	public static VipsProhibitionStatusResponse successResponse(ProhibitionStatus status, String respCodeStr, String respMsg) {
		return new VipsProhibitionStatusResponse(status, Integer.parseInt(respCodeStr), respMsg);
	}

}
