package ca.bc.gov.open.jagvipsclient.validation;

import java.math.BigDecimal;

/**
 * 
 * Collection of services for accessing VIPS validations.
 * 
 * @author jdosil
 *
 */
public interface ValidationService {

     VipsValidTimeframeResponse getWithinTimeframe(String startDate, BigDecimal intervalDays);
     VipsValidExpiryDateResponse getValidExpiryDate(String startDate, BigDecimal intervalDays);

}
