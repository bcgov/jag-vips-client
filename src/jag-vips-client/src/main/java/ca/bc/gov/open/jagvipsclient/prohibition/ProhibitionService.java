package ca.bc.gov.open.jagvipsclient.prohibition;

/**
 * 
 * Collection of services for accessing VIPS prohibition related data.
 * 
 * @author shaunmillargov
 *
 */
public interface ProhibitionService {

     VipsProhibitionStatusResponse getVipsProhibitionStatus(String prohibitionId, String correlationId);

}
