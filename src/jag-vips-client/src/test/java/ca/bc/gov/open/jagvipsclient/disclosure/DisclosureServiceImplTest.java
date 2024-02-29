package ca.bc.gov.open.jagvipsclient.disclosure;

import static org.mockito.ArgumentMatchers.eq;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.ArgumentMatchers.any;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
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
	public static final String ERROR_RESPONSE = "Message: error";

	public static final Path resourcesPath = Paths.get("src", "test", "resources");

	public static final File successFile = new File(resourcesPath.toString(), "SuccessTest");
	public static final File errorFile = new File(resourcesPath.toString(), "ErrorTest");

	private DisclosureService service;

	@Mock
	private DisclosureApi disclosureApi;

	@BeforeAll
	public void setup() throws ApiException {
		MockitoAnnotations.initMocks(this);

		VipsDisclosureSentOrdsResponse disclosureResponse = new VipsDisclosureSentOrdsResponse();
		disclosureResponse.setUpdDtm("2019-01-02 17:30:00 -08:00");
		disclosureResponse.setStatusCode(String.valueOf(VipsOrdsClientConstants.SERVICE_SUCCESS_CD));
		disclosureResponse.setStatusMessage(SUCCESS_RESPONSE);

		Mockito.when(disclosureApi.disclosureDocumentIdAuthGuidGet(eq("1"), any())).thenReturn(successFile);
		Mockito.when(disclosureApi.disclosureDocumentIdAuthGuidGet(eq("2"), any())).thenReturn(null);
		Mockito.when(disclosureApi.disclosureDocumentIdAuthGuidGet(eq("3"), any())).thenReturn(errorFile);
		Mockito.when(disclosureApi.disclosureDocumentIdAuthGuidGet(eq("4"), any()))
				.thenThrow(new ApiException(ERROR_RESPONSE));

		Mockito.when(disclosureApi.disclosureDocumentIdAuthGuidPatch(any(), eq("1"), any()))
				.thenReturn(disclosureResponse);
		Mockito.when(disclosureApi.disclosureDocumentIdAuthGuidPatch(any(), eq("2"), any()))
				.thenThrow(new ApiException(ERROR_RESPONSE));

		service = new DisclosureServiceImpl(disclosureApi);
	}

	@Test
	public void getDisclosureDocumentSuccess() throws IOException {
		DisclosureResponse response = service.getDisclosureDocument("1", "authGuid", "correlationId");

		// Decode file
		byte[] outputArray = Base64.decodeBase64(response.getDocumentInfo().getDocument());
		// Save output to resources to verify manually
		FileUtils.writeByteArrayToFile(new File(resourcesPath.toString(), "TestOutput.pdf"), outputArray);

		Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_SUCCESS_CD, response.getRespCode());
		Assertions.assertEquals(SUCCESS_RESPONSE, response.getRespMsg());
		Assertions.assertEquals("application/pdf", response.getDocumentInfo().getMimeType());
		Assertions.assertEquals(FileUtils.readFileToString(successFile, StandardCharsets.UTF_8),
				new String(outputArray, StandardCharsets.UTF_8));

	}

	@Test
	public void getDisclosureDocumentNullFileError() {
		DisclosureResponse response = service.getDisclosureDocument("2", "authGuid", "correlationId");

		Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_FAILURE_CD, response.getRespCode());
		Assertions.assertEquals("File not found", response.getRespMsg());
	}

	@Test
	public void getDisclosureDocumentError() {
		DisclosureResponse response = service.getDisclosureDocument("3", "authGuid", "correlationId");

		Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_FAILURE_CD, response.getRespCode());
		Assertions.assertEquals("Record not found", response.getRespMsg());
	}

	@Test
	public void getDisclosureDocumentException() {
		DisclosureResponse response = service.getDisclosureDocument("4", "authGuid", "correlationId");

		Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_FAILURE_CD, response.getRespCode());
		Assertions.assertTrue(response.getRespMsg().contains(ERROR_RESPONSE));
	}

	@Test
	public void setDisclosureSentSuccess() {
		DisclosureResponse response = service.setDisclosureSent("1", "disclosedDtm", "userId", "authGuid",
				"correlationId");

		Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_SUCCESS_CD, response.getRespCode());
		Assertions.assertEquals(SUCCESS_RESPONSE, response.getRespMsg());
	}

	@Test
	public void setDisclosureSentException() {
		DisclosureResponse response = service.setDisclosureSent("2", "disclosedDtm", "userId", "authGuid",
				"correlationId");

		Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_FAILURE_CD, response.getRespCode());
		Assertions.assertTrue(response.getRespMsg().contains(ERROR_RESPONSE));
	}

}
