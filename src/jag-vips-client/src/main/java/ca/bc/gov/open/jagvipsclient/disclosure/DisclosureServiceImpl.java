package ca.bc.gov.open.jagvipsclient.disclosure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.bc.gov.open.jag.ordsvipsclient.api.DisclosureApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsDisclosureDocumentOrdsResponse;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsDisclosureSentOrdsResponse;

/**
 * 
 * Collection of services for accessing VIPS disclosure related data.
 * 
 * @author sivakaruna
 *
 */
public class DisclosureServiceImpl implements DisclosureService {

	private final DisclosureApi disclosureApi;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public DisclosureServiceImpl(DisclosureApi disclosureApi) {
		this.disclosureApi = disclosureApi;
	}

	@Override
	public DisclosureResponse getDisclosureDocument(String documentId, String authGuid, String correlationId) {
		
		//TODO Uncomment code and remove dummy response
		
		/*
		 * try {
		 * 
		 * VipsDisclosureDocumentOrdsResponse response = this.disclosureApi
		 * .vipsDisclosureDocumentIdAuthGuidGet(documentId, authGuid);
		 * 
		 * logger.
		 * info("Processed Get Disclosure Document request: ORDS returned code: {} and message: {} "
		 * , response.getStatusCode(), response.getStatusMessage());
		 * 
		 * DocumentInfo documentInfo = new DocumentInfo(response.getMimeType(),
		 * response.getDocument());
		 * 
		 * return DisclosureResponse.successDocumentResponse(documentInfo,
		 * response.getStatusCode(), response.getStatusMessage());
		 * 
		 * } catch (ApiException ex) {
		 * logger.error("Disclosure Service threw an exception: " + ex.getMessage(),
		 * ex); return DisclosureResponse.errorResponse(ex.getMessage()); }
		 */
		
		VipsDisclosureDocumentOrdsResponse response = new VipsDisclosureDocumentOrdsResponse();
		response.setStatusCode("0");
		response.setStatusMessage("success");
		response.setMimeType("mimeType");
		response.setDocument("document");
		
		logger.info("Processed Get Disclosure Document request: ORDS returned code: {} and message: {} ",
				response.getStatusCode(), response.getStatusMessage());

		DocumentInfo documentInfo = new DocumentInfo(response.getMimeType(), response.getDocument());

		return DisclosureResponse.successDocumentResponse(documentInfo, response.getStatusCode(),
				response.getStatusMessage());
		
	}

	@Override
	public DisclosureResponse setDisclosureSent(String noticeNo, String documentId, String disclosedDtm,
			String authGuid, String correlationId) {
		
		//TODO Uncomment code and remove dummy response
		
		/*
		 * try { VipsDisclosureSentOrdsResponse response = this.disclosureApi
		 * .vipsDisclosureNoticeNoDocumentIdDisclosedDtmAuthGuidPatch(noticeNo,
		 * documentId, disclosedDtm, authGuid);
		 * 
		 * logger.
		 * info("Processed Set Disclosure Document as sent request: ORDS returned code: {} and message: {} "
		 * , response.getStatusCode(), response.getStatusMessage());
		 * 
		 * return DisclosureResponse.successResponse(response.getUpdDtm(),
		 * response.getStatusCode(), response.getStatusMessage());
		 * 
		 * } catch (ApiException ex) {
		 * logger.error("Disclosure Service threw an exception: " + ex.getMessage(),
		 * ex); return DisclosureResponse.errorResponse(ex.getMessage()); }
		 */
		
		VipsDisclosureSentOrdsResponse response = new VipsDisclosureSentOrdsResponse();
		response.setStatusCode("0");
		response.setStatusMessage("success");
		response.setUpdDtm("updDtm");

		logger.info("Processed Set Disclosure Document as sent request: ORDS returned code: {} and message: {} ",
				response.getStatusCode(), response.getStatusMessage());

		return DisclosureResponse.successResponse(response.getUpdDtm(), response.getStatusCode(),
				response.getStatusMessage());
		
	}

}
