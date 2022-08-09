package ca.bc.gov.open.jagvipsclient.document;

import java.io.File;

/**
 * Collection of services for documents.
 *
 * @author carolcarpenterjustice
 */
public interface DocumentService {

     VipsDocumentResponse vipsDocument(String typeCode, String metadata, String mimeType, String mimeSubType,
                                       String authGuid, File body);
     
     VipsDocumentResponse storeDocument(String typeCode, String mimeType, String mimeSubType, String authGuid, 
    		 							String noticeTypeCode, String noticeSubjectCode, int pageCount, File body);

}
