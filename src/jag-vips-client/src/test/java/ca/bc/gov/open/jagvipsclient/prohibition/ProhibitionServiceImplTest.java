package ca.bc.gov.open.jagvipsclient.prohibition;

import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.bc.gov.open.jag.ordsvipsclient.api.ProhibitionStatusApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsProhibitionStatusOrdsResponse;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsProhibitionStatusOrdsResponseDisclosure;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsProhibitionStatusOrdsResponseReviews;
import ca.bc.gov.open.jagvipsclient.VipsOrdsClientConstants;

/**
 * Prohibition service tests
 *
 * @author sivakaruna
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProhibitionServiceImplTest {

	public static final String API_EXCEPTION = "api exception";
	public static final String SUCCESS_RESPONSE = "success";
	public static final String ERROR_RESPONSE = "error";

	private ProhibitionService service;

	@Mock
	private ProhibitionStatusApi prohibitionApiMock;

	@BeforeAll
	public void setup() throws ApiException {
		MockitoAnnotations.initMocks(this);
		
		List<VipsProhibitionStatusOrdsResponseDisclosure> disclosureList = new ArrayList<>();
		VipsProhibitionStatusOrdsResponseDisclosure disclosure = new VipsProhibitionStatusOrdsResponseDisclosure();
		disclosure.setDisclosedDtm("2019-01-02 17:30:00 -08:00");
		disclosure.setDocumentId("456");
		
		disclosureList.add(disclosure);
		
		List<VipsProhibitionStatusOrdsResponseReviews> reviewsList = new ArrayList<>();
		VipsProhibitionStatusOrdsResponseReviews review = new VipsProhibitionStatusOrdsResponseReviews();
		review.setApplicationId("123456");
		review.setReviewStartDtm("2021-01-02 17:30:00 -08:00");
		review.setReviewEndDtm("2021-01-02 19:30:00 -08:00");
		review.setReceiptNumberTxt("567");
		review.setReviewId("9990");
		review.setReviewStatus("complete-success");
		
		reviewsList.add(review);

		VipsProhibitionStatusOrdsResponse prohibitionOrdsResponse = new VipsProhibitionStatusOrdsResponse();
		prohibitionOrdsResponse.setStatusCode(String.valueOf(VipsOrdsClientConstants.SERVICE_SUCCESS_CD));
		prohibitionOrdsResponse.setStatusMessage(SUCCESS_RESPONSE);
		prohibitionOrdsResponse.setDisclosure(disclosureList);
		prohibitionOrdsResponse.setReviews(reviewsList);
		

		Mockito.when(prohibitionApiMock.prohibitionStatusNoticeNoGet(eq("1"))).thenReturn(prohibitionOrdsResponse);
		Mockito.when(prohibitionApiMock.prohibitionStatusNoticeNoGet(eq("2")))
				.thenThrow(new ApiException(ERROR_RESPONSE));

		service = new ProhibitionServiceImpl(prohibitionApiMock);
	}

	@Test
	public void getSuccess() {
		VipsProhibitionStatusResponse response = service.getVipsProhibitionStatus("1", "correlationId");

		Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_SUCCESS_CD, response.getRespCode());
		Assertions.assertEquals(SUCCESS_RESPONSE, response.getRespMsg());
		Assertions.assertEquals("2019-01-02 17:30:00 -08:00", response.getStatus().getDisclosure().get(0).getDisclosedDtm());
		Assertions.assertEquals("456", response.getStatus().getDisclosure().get(0).getDocumentId());
		
		Assertions.assertEquals("2021-01-02 17:30:00 -08:00", response.getStatus().getReviews().get(0).getReviewStartDtm());
		Assertions.assertEquals("2021-01-02 19:30:00 -08:00", response.getStatus().getReviews().get(0).getReviewEndDtm());
		Assertions.assertEquals("123456", response.getStatus().getReviews().get(0).getApplicationId());
		Assertions.assertEquals("567", response.getStatus().getReviews().get(0).getReceiptNumberTxt());
		Assertions.assertEquals("9990", response.getStatus().getReviews().get(0).getReviewId());
		Assertions.assertEquals("complete-success", response.getStatus().getReviews().get(0).getStatus());
		
	}

	@Test
	public void getException() {
		VipsProhibitionStatusResponse response = service.getVipsProhibitionStatus("2", "correlationId");

		Assertions.assertEquals(VipsOrdsClientConstants.SERVICE_FAILURE_CD, response.getRespCode());
		Assertions.assertEquals(ERROR_RESPONSE, response.getRespMsg());
	}

}
