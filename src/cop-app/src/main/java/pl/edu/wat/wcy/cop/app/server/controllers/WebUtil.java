package pl.edu.wat.wcy.cop.app.server.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import pl.edu.wat.wcy.cop.app.shared.response.AbstractResponse;
import pl.edu.wat.wcy.cop.app.shared.response.ErrorCode;
import pl.edu.wat.wcy.cop.app.shared.response.ErrorResponse;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
// Provides web utilities.

public class WebUtil {
    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private static final String X_REQUESTED_WITH = "X-Requested-With";

    private static final String CONTENT_TYPE = "Content-type";
    private static final String CONTENT_TYPE_JSON = "application/json";

    public static boolean isAjax(HttpServletRequest request) {
        return XML_HTTP_REQUEST.equals(request.getHeader(X_REQUESTED_WITH));
    }

    public static ResponseEntity<?> createResponseEntity(HttpServletRequest request, AbstractResponse response) {
        return ResponseEntity.status(response.getStatus()).body(response);

    }

    public static <T> ResponseEntity<?> createOkEntity(T content) {
        return ResponseEntity.status(HttpStatus.OK).body(new OkResponse<T>(content));
    }

    public static <T> ResponseEntity<?> createErrorEntity(HttpStatus status, ErrorCode errorCode, String message) {
        return ResponseEntity.status(status).body(new ErrorResponse(message, errorCode, status.value()));
    }

    public static ResponseEntity<?> createExportDataAsCsvResponseEntity(HttpServletRequest request, String fileName,
                                                                        String data) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body(data);
    }

    public static <T> T getKeyFromRequestAttribute(HttpServletRequest request, String param, Class<T> cls,
                                                   T defaultValue) {
        T value = (T) request.getAttribute(param);
        if (value == null)
            value = defaultValue;
        return value;
    }

    /**
     * @param response
     * @param fileName
     * @param text
     * @return
     * @throws IOException
     */
    public static void exportDataAsCsv(HttpServletResponse response, String fileName, String text) throws IOException {
        response.setContentType("text/plain; charset=utf-8");
        response.setHeader("Content-Disposition:", "attachment;filename=" + "\"" + fileName + "\"");
        response.setHeader("x-filename", fileName);
        response.setBufferSize(1024);
        response.getWriter().println(text);
    }

    public static String getClientIpAddress(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        if (remoteAddr.equals("0:0:0:0:0:0:0:1")) {
            InetAddress localip;
            try {
                localip = java.net.InetAddress.getLocalHost();
                remoteAddr = localip.getHostAddress();
            } catch (UnknownHostException e) {
            }
        }
        return remoteAddr;
    }
}
