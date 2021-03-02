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
@JsonPropertyOrder({ "applicationId", "status", "receiptNumberTxt", "reviewStartDtm", "reviewEndDtm", "reviewId"})
public class ReviewInfo {

	@JsonProperty("applicationId")
	private String applicationId;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("reviewStartDtm")
	private String reviewStartDtm;
	
	@JsonProperty("reviewEndDtm")
	private String reviewEndDtm;
	
	@JsonProperty("receiptNumberTxt")
	private String receiptNumberTxt;
	
	@JsonProperty("reviewId")
	private String reviewId;
	
	public ReviewInfo(String applicationId, String status, String reviewStartDtm, String reviewEndDtm, String receiptNumberTxt, String reviewId) {
		this.applicationId = applicationId;
		this.status = status;
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

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
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
