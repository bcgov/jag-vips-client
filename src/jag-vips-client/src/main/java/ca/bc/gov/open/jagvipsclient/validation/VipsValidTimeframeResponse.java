package ca.bc.gov.open.jagvipsclient.validation;

import ca.bc.gov.open.jagvipsclient.VipsOrdsClientConstants;
import ca.bc.gov.open.jagvipsclient.prohibition.ProhibitionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


/**
 *
 * Represents the VIPS Valid Timeframe Response
 *
 *
 */
@JacksonXmlRootElement(localName = "VipsValidTimeframeResponse")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VipsValidTimeframeResponse {

	private String valid;
	private int respCode;
	private String respMsg;

	public VipsValidTimeframeResponse(String valid, int respCode, String respMsg) {
		this.valid = valid;
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public VipsValidTimeframeResponse(int respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public String getValid() {
		return valid;
	}

	public int getRespCode() {
		return respCode;
	}
	
	public String getRespMsg() {
		return respMsg;
	}
	
	public static VipsValidTimeframeResponse errorResponse(String errorMessage) {
		return new VipsValidTimeframeResponse(VipsOrdsClientConstants.SERVICE_FAILURE_CD, errorMessage);
	}

	public static VipsValidTimeframeResponse successResponse(String valid, String respCodeStr, String respMsg) {
		return new VipsValidTimeframeResponse(valid, Integer.parseInt(respCodeStr), respMsg);
	}

}
