package ca.bc.gov.open.jagvipsclient.prohibition;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "effectiveDate", "driverLicenceSeizedYn", "driverLastName", "reviewStatus", "cancelledYn",
		"resultCode", "resultMessage" })
public class ProhibitionStatus {

	@JsonProperty("effectiveDate")
	private String effectiveDate;
	@JsonProperty("driverLicenceSeizedYn")
	private String driverLicenceSeizedYn;
	@JsonProperty("driverLastName")
	private String driverLastName;
	@JsonProperty("reviewStatus")
	private String reviewStatus;
	@JsonProperty("cancelledYn")
	private String cancelledYn;
	@JsonProperty("resultCode")
	private String resultCode;
	@JsonProperty("resultMessage")
	private String resultMessage;

	@JsonProperty("effectiveDate")
	public String getEffectiveDate() {
		return effectiveDate;
	}

	@JsonProperty("effectiveDate")
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@JsonProperty("driverLicenceSeizedYn")
	public String getDriverLicenceSeizedYn() {
		return driverLicenceSeizedYn;
	}

	@JsonProperty("driverLicenceSeizedYn")
	public void setDriverLicenceSeizedYn(String driverLicenceSeizedYn) {
		this.driverLicenceSeizedYn = driverLicenceSeizedYn;
	}

	@JsonProperty("driverLastName")
	public String getDriverLastName() {
		return driverLastName;
	}

	@JsonProperty("driverLastName")
	public void setDriverLastName(String driverLastName) {
		this.driverLastName = driverLastName;
	}

	@JsonProperty("reviewStatus")
	public String getReviewStatus() {
		return reviewStatus;
	}

	@JsonProperty("reviewStatus")
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	@JsonProperty("cancelledYn")
	public String getCancelledYn() {
		return cancelledYn;
	}

	@JsonProperty("cancelledYn")
	public void setCancelledYn(String cancelledYn) {
		this.cancelledYn = cancelledYn;
	}

	@JsonProperty("resultCode")
	public String getResultCode() {
		return resultCode;
	}

	@JsonProperty("resultCode")
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	@JsonProperty("resultMessage")
	public String getResultMessage() {
		return resultMessage;
	}

	@JsonProperty("resultMessage")
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

}
