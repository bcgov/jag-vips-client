package ca.bc.gov.open.jagvipsclient.prohibition;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.bc.gov.open.jag.ordsvipsclient.api.ProhibitionStatusApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsProhibitionStatusOrdsResponse;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsProhibitionStatusOrdsResponseDisclosureInner;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsProhibitionStatusOrdsResponseReviewsInner;

/**
 *
 * Collection of services for accessing VIPS prohibition related data.
 *
 * @author shaunmillargov
 *
 */
public class ProhibitionServiceImpl implements ProhibitionService {

	private final ProhibitionStatusApi prohibStatusApi;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public ProhibitionServiceImpl(ProhibitionStatusApi prohibStatusApi) {
		this.prohibStatusApi = prohibStatusApi;
	}

	@Override
	public VipsProhibitionStatusResponse getVipsProhibitionStatus(String prohibitionNoticeNo, String correlationId) {

		try {

			// Here what we're doing is a conversion from the actual response to a properly
			// camelCased JSON response.
			VipsProhibitionStatusOrdsResponse response = this.prohibStatusApi
					.prohibitionStatusNoticeNoGet(prohibitionNoticeNo);

			ProhibitionStatus status = new ProhibitionStatus();

			status.setNoticeTypeCd(response.getNoticeTypeCd());
			status.setNoticeServedDt(response.getNoticeServedDt());
			status.setReviewFormSubmittedYn(response.getReviewFormSubmittedYn());
			status.setReviewCreatedYn(response.getReviewCreatedYn());
			status.setOriginalCause(response.getOriginalCause());
			status.setSurnameNm(response.getSurnameNm());
			status.setDriverLicenceSeized(response.getDriverLicenceSeizedYn());

			List<DocumentDisclosureInfo> disclosureList = new ArrayList<>();

			if (null != response.getDisclosure()) {

				for (VipsProhibitionStatusOrdsResponseDisclosureInner element : response.getDisclosure()) {
					disclosureList.add(new DocumentDisclosureInfo(element.getDocumentId(), element.getDisclosedDtm()));
				}
				status.setDisclosure(disclosureList);
			}

			List<ReviewInfo> reviewList = new ArrayList<>();

			if (null != response.getReviews()) {
				for (VipsProhibitionStatusOrdsResponseReviewsInner element : response.getReviews()) {
					reviewList.add(new ReviewInfo(element.getApplicationId(), element.getReviewStatus(), element.getReviewStartDtm(), element.getReviewEndDtm(), element.getReceiptNumberTxt(), element.getReviewId()));
				}
				status.setReviews(reviewList);
			}

			logger.info("Processed get prohibition info request: ORDS returned code: {} and message: {} ",
					response.getStatusCode(), response.getStatusMessage());

			return VipsProhibitionStatusResponse.successResponse(status, response.getStatusCode(),
					response.getStatusMessage());

		} catch (ApiException ex) {
			logger.error("Prohibition Service threw an exception: " + ex.getMessage(), ex);
			return VipsProhibitionStatusResponse.errorResponse(ex.getMessage());
		}
	}

}
