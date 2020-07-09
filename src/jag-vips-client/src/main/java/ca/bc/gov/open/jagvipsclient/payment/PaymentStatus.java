package ca.bc.gov.open.jagvipsclient.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * Payment Status object
 * 
 * @author sivakaruna
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "amountOwing" })
public class PaymentStatus {

	@JsonProperty("amountOwing")
	private String amountOwing;
	@JsonProperty("resultCode")
	private String resultCode;
	@JsonProperty("resultMessage")
	private String resultMessage;

	@JsonProperty("amountOwing")
	public String getAmountOwing() {
		return amountOwing;
	}

	@JsonProperty("amountOwing")
	public void setAmountOwing(String amountOwing) {
		this.amountOwing = amountOwing;
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
