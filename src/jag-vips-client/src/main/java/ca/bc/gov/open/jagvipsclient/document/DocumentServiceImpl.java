package ca.bc.gov.open.jagvipsclient.document;

import ca.bc.gov.open.jag.ordsvipsclient.api.DocumentApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsDocumentOrdsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


/**
 * Document Service Implementation using ORDS services.
 *
 * @author carolcarpenterjustice
 */
public class DocumentServiceImpl implements DocumentService {

    private final DocumentApi documentApi;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public DocumentServiceImpl(DocumentApi documentApi) {
        this.documentApi = documentApi;
    }

    @Override
    public VipsDocumentResponse vipsDocument(String typeCode, String metadata, String mimeType, String mimeSubType, String authGuid, File body) {

        try {
            VipsDocumentOrdsResponse response = this.documentApi.vipsDocumentPost(typeCode, sanitizeBase64(metadata), mimeType, mimeSubType, authGuid, body);
            return  VipsDocumentResponse.successResponse(response.getDocumentId(), response.getStatusCode(), response.getStatusMessage());

        } catch (ApiException ex) {

            logger.error("Document Service did throw exception: " + ex.getMessage(), ex);
            return VipsDocumentResponse.errorResponse(ex.getMessage());
        }
    }
    
    
	@Override
	public VipsDocumentResponse storeDocument(String typeCode, String mimeType, String mimeSubType, String authGuid,
			String noticeTypeCode, String noticeSubjectCode, int pageCount, File body) {
		try {
			
			VipsDocumentOrdsResponse response = this.documentApi.storeDocumentPost(typeCode, mimeType, mimeSubType,
					authGuid, body, noticeTypeCode, noticeSubjectCode, pageCount);
			
			return VipsDocumentResponse.successResponse(response.getDocumentId(), response.getStatusCode(),
					response.getStatusMessage());

		} catch (ApiException ex) {

			logger.error("Document Service did throw exception: " + ex.getMessage(), ex);
			return VipsDocumentResponse.errorResponse(ex.getMessage());
		}
	}

    private String sanitizeBase64(String value) {
        return value
                .replace('/', '_')
                .replace('+', '-')
                .replaceAll("\r\n", "");
    }



}
