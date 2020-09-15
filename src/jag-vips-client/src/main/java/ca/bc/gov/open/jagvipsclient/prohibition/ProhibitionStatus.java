package ca.bc.gov.open.jagvipsclient.prohibition;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author shaunmillargov
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "noticeTypeCd", "noticeServedDt", "reviewFormSubmittedYn", "reviewCreatedYn", "originalCause",
		"surnameNm", "driverLicenceSeized", "reviewStartDtm", "reviewEndDtm", "receiptNumberTxt", "disclosure" })
public class ProhibitionStatus {
	
	@JsonProperty("noticeTypeCd")
	private String noticeTypeCd;
	@JsonProperty("noticeServedDt")
	private String noticeServedDt;
	@JsonProperty("reviewFormSubmittedYn")
	private String reviewFormSubmittedYn;
	@JsonProperty("reviewCreatedYn")
	private String reviewCreatedYn;
	@JsonProperty("originalCause")
	private String originalCause;
	@JsonProperty("surnameNm")
	private String surnameNm;
	@JsonProperty("driverLicenceSeizedYn")
	private String driverLicenceSeized;
	@JsonProperty("reviewStartDtm")
	private String reviewStartDtm;
	@JsonProperty("reviewEndDtm")
	private String reviewEndDtm;
	@JsonProperty("receiptNumberTxt")
	private String receiptNumberTxt;
	@JsonProperty("applicationId")
	private String applicationId;
	@JsonProperty("disclosure")
	private List<DocumentDisclosureInfo> disclosure = null;

	@JsonProperty("noticeTypeCd")
	public String getNoticeTypeCd() {
		return noticeTypeCd;
	}

	@JsonProperty("noticeTypeCd")
	public void setNoticeTypeCd(String noticeTypeCd) {
		this.noticeTypeCd = noticeTypeCd;
	}

	@JsonProperty("noticeServedDt")
	public String getNoticeServedDt() {
		return noticeServedDt;
	}

	@JsonProperty("noticeServedDt")
	public void setNoticeServedDt(String noticeServedDt) {
		this.noticeServedDt = noticeServedDt;
	}

	@JsonProperty("reviewFormSubmittedYn")
	public String getReviewFormSubmittedYn() {
		return reviewFormSubmittedYn;
	}

	@JsonProperty("reviewFormSubmittedYn")
	public void setReviewFormSubmittedYn(String reviewFormSubmittedYn) {
		this.reviewFormSubmittedYn = reviewFormSubmittedYn;
	}

	@JsonProperty("reviewCreatedYn")
	public String getReviewCreatedYn() {
		return reviewCreatedYn;
	}

	@JsonProperty("reviewCreatedYn")
	public void setReviewCreatedYn(String reviewCreatedYn) {
		this.reviewCreatedYn = reviewCreatedYn;
	}

	@JsonProperty("originalCause")
	public String getOriginalCause() {
		return originalCause;
	}

	@JsonProperty("originalCause")
	public void setOriginalCause(String originalCause) {
		this.originalCause = originalCause;
	}

	@JsonProperty("surnameNm")
	public String getSurnameNm() {
		return surnameNm;
	}

	@JsonProperty("surnameNm")
	public void setSurnameNm(String surnameNm) {
		this.surnameNm = surnameNm;
	}
	
	public String getDriverLicenceSeized() {
		return driverLicenceSeized;
	}

	public void setDriverLicenceSeized(String driverLicenceSeized) {
		this.driverLicenceSeized = driverLicenceSeized;
	}

	@JsonProperty("reviewStartDtm")
	public String getReviewStartDtm() {
		return reviewStartDtm;
	}

	@JsonProperty("reviewStartDtm")
	public void setReviewStartDtm(String reviewStartDtm) {
		this.reviewStartDtm = reviewStartDtm;
	}

	@JsonProperty("reviewEndDtm")
	public String getReviewEndDtm() {
		return reviewEndDtm;
	}

	@JsonProperty("reviewEndDtm")
	public void setReviewEndDtm(String reviewEndDtm) {
		this.reviewEndDtm = reviewEndDtm;
	}

	@JsonProperty("receiptNumberTxt")
	public String getReceiptNumberTxt() {
		return receiptNumberTxt;
	}

	@JsonProperty("receiptNumberTxt")
	public void setReceiptNumberTxt(String receiptNumberTxt) {
		this.receiptNumberTxt = receiptNumberTxt;
	}
	
	@JsonProperty("applicationId")
	public String getApplicationId() {
		return applicationId;
	}
	
	@JsonProperty("applicationId")
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@JsonProperty("disclosure")
	public List<DocumentDisclosureInfo> getDisclosure() {
		if (null == this.disclosure) 
			this.disclosure = new ArrayList<>();
		return disclosure;
	}
	
	@JsonProperty("disclosure")
	public void setDisclosure(List<DocumentDisclosureInfo> disclosure) {
		this.disclosure = disclosure;
	}

}
