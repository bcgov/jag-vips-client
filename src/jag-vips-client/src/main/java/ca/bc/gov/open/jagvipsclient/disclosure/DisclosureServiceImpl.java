package ca.bc.gov.open.jagvipsclient.disclosure;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.bc.gov.open.jag.ordsvipsclient.api.DisclosureApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsDisclosureSentOrdsRequest;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsDisclosureSentOrdsResponse;
import ca.bc.gov.open.jagvipsclient.VipsOrdsClientConstants;

/**
 * 
 * Collection of services for accessing VIPS disclosure related data.
 * 
 * @author sivakaruna
 *
 */
public class DisclosureServiceImpl implements DisclosureService {

	private static final String MIME_TYPE_PDF = "application/pdf";

	private final DisclosureApi disclosureApi;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public DisclosureServiceImpl(DisclosureApi disclosureApi) {
		this.disclosureApi = disclosureApi;
	}

	@Override
	public DisclosureResponse getDisclosureDocument(String documentId, String authGuid, String correlationId) {

		// Initialize variables
		File file = null;
		String fileString = null;
		byte[] fileArray = null;

		try {
			// Retrieve response file object
			file = this.disclosureApi.disclosureDocumentIdAuthGuidGet(documentId, authGuid);
			
			if (null == file) {
				logger.error("Disclosure Service threw an exception: Null file returned from ORDS");
				return DisclosureResponse.errorResponse("File not found");
			}
			
			// Read file as a String to check error status code
			fileString = FileUtils.readFileToString(file, StandardCharsets.UTF_8.name());
			// Read file as byte array to encode if success status
			fileArray = FileUtils.readFileToByteArray(file);

			// Parse file as JSON to extract status code and status message
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(fileString);
			String errorMessage = json.get("status_message").toString();
			logger.error("Disclosure Service could not find document: {} ", errorMessage);

			// If ParseException is not thrown, it is a error response
			return DisclosureResponse.errorResponse(errorMessage);

		} catch (ApiException ex) {
			logger.error("Disclosure Service threw an exception: " + ex.getMessage(), ex);
			return DisclosureResponse.errorResponse(ex.getMessage());
		} catch (IOException ex) {
			logger.error("Disclosure Service threw an exception: Could not read file " + ex.getMessage(), ex);
			return DisclosureResponse.errorResponse(ex.getMessage());
		} catch (ParseException e) {
			// Log success message as parse exception is thrown only when valid PDF is returned
			logger.info("Processed Get Disclosure Document request: ORDS returned file: {} ", file.getName());
		}

		// Encode the PDF array as String
		String base64EncodedData = Base64.encodeBase64String(fileArray);

		DocumentInfo documentInfo = new DocumentInfo(MIME_TYPE_PDF, base64EncodedData);

		return DisclosureResponse.successDocumentResponse(documentInfo,
				String.valueOf(VipsOrdsClientConstants.SERVICE_SUCCESS_CD), "success");

	}

	@Override
	public DisclosureResponse setDisclosureSent(String documentId, String disclosedDtm, String authGuid, String userId,
			String correlationId) {

		try {
			VipsDisclosureSentOrdsRequest request = new VipsDisclosureSentOrdsRequest();
			request.setDisclosureDtm(disclosedDtm);
			request.setUserId(userId);
			VipsDisclosureSentOrdsResponse response = this.disclosureApi.disclosureDocumentIdAuthGuidPatch(authGuid,
					documentId, request);

			logger.info("Processed Set Disclosure Document as sent request: ORDS returned code: {} and message: {} ",
					response.getStatusCode(), response.getStatusMessage());

			return DisclosureResponse.successResponse(response.getUpdDtm(), response.getStatusCode(),
					response.getStatusMessage());

		} catch (ApiException ex) {
			logger.error("Disclosure Service threw an exception: " + ex.getMessage(), ex);
			return DisclosureResponse.errorResponse(ex.getMessage());
		}

	}

}
