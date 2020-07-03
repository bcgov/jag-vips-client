package ca.bc.gov.open.jagvipsclient.prohibition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.bc.gov.open.jag.ordsvipsclient.api.ProhibitionStatusApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsProhibitionStatusOrdsResponse;

/**
 * 
 * Collection of services for accessing VIPS prohibition related data.
 * 
 * @author shaunmillargov
 *
 */
public class ProhibitionServiceImpl implements ProhibitionService{

	private final ProhibitionStatusApi prohibStatusApi;
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ProhibitionServiceImpl(ProhibitionStatusApi prohibStatusApi) {
        this.prohibStatusApi = prohibStatusApi;
    }

	@Override
	public VipsProhibitionStatusResponse getVipsProhibitionStatus(Long prohibitionNoticeNo) {
		
		 try {
			 
			 String noticeNo = Long.toString(prohibitionNoticeNo);
			 
			 // Here what we're doing is a conversion from the actual response to a properly camelCased JSON response.  
			 VipsProhibitionStatusOrdsResponse response = this.prohibStatusApi.prohibitionInfoNoticeNoGet(noticeNo);
			 
			 ProhibitionStatus status = new ProhibitionStatus();
			 status.setEffectiveDate(response.getEffectiveDate());
			 status.setDriverLicenceSeizedYn(response.getDriverLicenceSeizedYn());
			 status.setDriverLastName(response.getDriverLastName());
			 status.setReviewStatus(response.getReviewStatus());
			 status.setCancelledYn(response.getCancelledYn());
			 
	         return  VipsProhibitionStatusResponse.successResponse(status, response.getStatusCode(), response.getStatusMessage());

	        } catch (ApiException ex) {
	        	ex.printStackTrace();
	            logger.error("Prohibition Service threw an exception: " + ex.getMessage(), ex);
	            return VipsProhibitionStatusResponse.errorResponse(ex.getMessage());
	        }
	}

}
