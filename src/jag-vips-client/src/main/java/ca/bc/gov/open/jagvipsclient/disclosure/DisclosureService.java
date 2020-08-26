package ca.bc.gov.open.jagvipsclient.disclosure;

/**
 * 
 * Collection of services for accessing VIPS disclosure related data.
 * 
 * @author sivakaruna
 *
 */
public interface DisclosureService {

	DisclosureResponse getDisclosureDocument(String documentId, String authGuid, String correlationId);

	DisclosureResponse setDisclosureSent(String documentId, String disclosedDtm, String authGuid, String userId,
			String correlationId);

}
