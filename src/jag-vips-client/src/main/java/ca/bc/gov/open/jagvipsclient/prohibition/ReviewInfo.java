package ca.bc.gov.open.jagvipsclient.prohibition;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * Review info object
 * 
 * @author smillargov
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "applicationId", "reviewStatus", "reviewStartDtm", "reviewEndDtm", "receiptNumberTxt", "reviewId"})
public class ReviewInfo {

	@JsonProperty("applicationId")
	private String applicationId;
	
	@JsonProperty("reviewStatus")
	private String reviewStatus;
	
	@JsonProperty("reviewStartDtm")
	private String reviewStartDtm;
	
	@JsonProperty("reviewEndDtm")
	private String reviewEndDtm;
	
	@JsonProperty("receiptNumberTxt")
	private String receiptNumberTxt;
	
	@JsonProperty("reviewId")
	private String reviewId;
	
	public ReviewInfo(String applicationId, String reviewStatus, String reviewStartDtm, String reviewEndDtm, String receiptNumberTxt, String reviewId) {
		this.applicationId = applicationId;
		this.reviewStatus = reviewStatus;
		this.reviewStartDtm = reviewStartDtm;
		this.reviewEndDtm = reviewEndDtm;
		this.receiptNumberTxt = receiptNumberTxt;
		this.reviewId = reviewId;
	}

	@JsonProperty("applicationId")
	public String getApplicationId() {
		return applicationId;
	}

	@JsonProperty("applicationId")
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@JsonProperty("reviewStatus")
	public String getReviewStatus() {
		return reviewStatus;
	}

	@JsonProperty("reviewStatus")
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
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

	@JsonProperty("reviewId")
	public String getReviewId() {
		return reviewId;
	}

	@JsonProperty("reviewId")
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

}
