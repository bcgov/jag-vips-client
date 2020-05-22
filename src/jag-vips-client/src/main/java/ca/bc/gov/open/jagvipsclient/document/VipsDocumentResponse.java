package ca.bc.gov.open.jagvipsclient.document;

import ca.bc.gov.open.jagvipsclient.VipsOrdsClientConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.text.MessageFormat;

/**
 *
 * Represents the VIPS document Response
 *
 * @author carolcarpenterjustice
 *
 */
@JacksonXmlRootElement(localName = "vipsDocumentResponse")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VipsDocumentResponse {

    private String documentId;
    private int respCode;
    private String respMsg;

    private VipsDocumentResponse(int respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

    private VipsDocumentResponse(String documentId, int respCode, String respMsg) {
        this(respCode, respMsg);
        this.documentId = documentId;
    }

    public String getDocumentId() { return documentId; }

    public void setDocumentId(String documentId) { this.documentId = documentId; }

    public int getRespCode() {
        return respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public static VipsDocumentResponse errorResponse(String errorMessage) {
        return new VipsDocumentResponse(
                VipsOrdsClientConstants.SERVICE_FAILURE_CD,
                errorMessage);
    }

    public static VipsDocumentResponse successResponse(String documentIdStr, String respCodeStr, String respMsg) {

        return new VipsDocumentResponse(documentIdStr, Integer.parseInt(respCodeStr), respMsg);
    }

    @Override
    public String toString() {
        return MessageFormat.format("VipsDocumentResponse: documentId [{0}], respCode [{1}], respMsg [{2}]", this.documentId, this.respCode, this.respMsg);
    }

}
