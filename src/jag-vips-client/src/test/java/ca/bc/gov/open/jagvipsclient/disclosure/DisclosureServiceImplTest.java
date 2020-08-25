package ca.bc.gov.open.jagvipsclient.disclosure;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.bc.gov.open.jag.ordsvipsclient.api.DisclosureApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsDisclosureSentOrdsResponse;
import ca.bc.gov.open.jagvipsclient.VipsOrdsClientConstants;

/**
 * Disclosure service tests
 *
 * @author sivakaruna
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DisclosureServiceImplTest {

	public static final String API_EXCEPTION = "api exception";
	public static final String SUCCESS_RESPONSE = "success";
	public static final String ERROR_RESPONSE = "error";

	private DisclosureService service;

	@Mock
	private DisclosureApi disclosureApi;

	@BeforeAll
	public void setup() throws ApiException {
		MockitoAnnotations.initMocks(this);

		
		/*
		 * VipsDisclosureDocumentOrdsResponse disclosureDocumentResponse = new
		 * VipsDisclosureDocumentOrdsResponse();
		 * disclosureDocumentResponse.setStatusCode(String.valueOf(
		 * VipsOrdsClientConstants.SERVICE_SUCCESS_CD));
		 * disclosureDocumentResponse.setStatusMessage(SUCCESS_RESPONSE);
		 * disclosureDocumentResponse.setDocument("123");
		 * disclosureDocumentResponse.setMimeType("PDF");
		 * 
		 * 
		 * VipsDisclosureSentOrdsResponse disclosureResponse = new
		 * VipsDisclosureSentOrdsResponse();
		 * disclosureResponse.setUpdDtm("2019-01-02 17:30:00 -08:00");
		 * disclosureResponse.setStatusCode(String.valueOf(VipsOrdsClientConstants.
		 * SERVICE_SUCCESS_CD)); disclosureResponse.setStatusMessage(SUCCESS_RESPONSE);
		 * 
		 * Mockito.when(disclosureApi.disclosureDocumentIdAuthGuidGet(eq("1"), any()))
		 * .thenReturn(disclosureDocumentResponse);
		 * Mockito.when(disclosureApi.disclosureDocumentIdAuthGuidGet(eq("2"), any()))
		 * .thenThrow(new ApiException(ERROR_RESPONSE));
		 * 
		 * 
		 * Mockito.when(disclosureApi.
		 * disclosureNoticeNoDocumentIdDisclosedDtmAuthGuidPatch(eq("1"), any(), any(),
		 * any())) .thenReturn(disclosureResponse); Mockito.when(disclosureApi.
		 * disclosureNoticeNoDocumentIdDisclosedDtmAuthGuidPatch(eq("2"), any(), any(),
		 * any())) .thenThrow(new ApiException(ERROR_RESPONSE));
		 */
		 
		service = new DisclosureServiceImpl(disclosureApi);
	}
	
	// TODO Uncomment tests
	
	
	/*
	 * @Test public void getDisclosureDocumentSuccess() { DisclosureResponse
	 * response = service.getDisclosureDocument("1", "authGuid", "correlationId");
	 * 
	 * Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_SUCCESS_CD,
	 * response.getRespCode()); Assertions.assertEquals(SUCCESS_RESPONSE,
	 * response.getRespMsg()); Assertions.assertEquals("123",
	 * response.getDocumentInfo().getDocument()); Assertions.assertEquals("PDF",
	 * response.getDocumentInfo().getMimeType()); }
	 * 
	 * @Test public void getDisclosureDocumentException() { DisclosureResponse
	 * response = service.getDisclosureDocument("2", "authGuid", "correlationId");
	 * 
	 * Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_FAILURE_CD,
	 * response.getRespCode()); Assertions.assertEquals(ERROR_RESPONSE,
	 * response.getRespMsg()); }
	 * 
	 * 
	 * @Test public void setDisclosureSentSuccess() { DisclosureResponse response =
	 * service.setDisclosureSent("1", "documentId", "disclosedDtm", "authGuid",
	 * "correlationId");
	 * 
	 * Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_SUCCESS_CD,
	 * response.getRespCode()); Assertions.assertEquals(SUCCESS_RESPONSE,
	 * response.getRespMsg()); }
	 * 
	 * @Test public void setDisclosureSentException() { DisclosureResponse response
	 * = service.setDisclosureSent("2", "documentId", "disclosedDtm", "authGuid",
	 * "correlationId");
	 * 
	 * Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_FAILURE_CD,
	 * response.getRespCode()); Assertions.assertEquals(ERROR_RESPONSE,
	 * response.getRespMsg()); }
	 */

}
