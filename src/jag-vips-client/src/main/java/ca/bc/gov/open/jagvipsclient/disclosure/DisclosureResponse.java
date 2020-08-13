package ca.bc.gov.open.jagvipsclient.disclosure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import ca.bc.gov.open.jagvipsclient.VipsOrdsClientConstants;

/**
 *
 * Represents the Disclosure service Response
 *
 * @author sivakaruna
 * 
 */
@JacksonXmlRootElement(localName = "disclosureResponse")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DisclosureResponse {

	@JsonProperty("document")
	private DocumentInfo documentInfo;

	@JsonProperty("respCode")
	private int respCode;

	@JsonProperty("respMsg")
	private String respMsg;

	@JsonProperty("updatedTime")
	private String updatedTime;

	private DisclosureResponse(DocumentInfo documentInfo, int respCode, String respMsg) {
		this.documentInfo = documentInfo;
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	private DisclosureResponse(String updatedTime, int respCode, String respMsg) {
		this.updatedTime = updatedTime;
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	private DisclosureResponse(int respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public DocumentInfo getDocumentInfo() {
		return documentInfo;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public int getRespCode() {
		return respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public static DisclosureResponse errorResponse(String errorMessage) {
		return new DisclosureResponse(VipsOrdsClientConstants.SERVICE_FAILURE_CD, errorMessage);
	}

	public static DisclosureResponse successResponse(String updatedTime, String respCodeStr, String respMsg) {
		return new DisclosureResponse(updatedTime, Integer.parseInt(respCodeStr), respMsg);
	}

	public static DisclosureResponse successDocumentResponse(DocumentInfo documentInfo, String respCodeStr,
			String respMsg) {
		return new DisclosureResponse(documentInfo, Integer.parseInt(respCodeStr), respMsg);
	}
}
