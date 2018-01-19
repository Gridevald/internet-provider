package by.training.provider.dto;

import by.training.provider.command.enums.UrlEnum;

/**
 * Information object about servlet response.
 * Contains URL and response method.
 */
public class UrlResponse {

    private ResponseMethod method;
    private UrlEnum url;

    public UrlResponse() {
    }

    public UrlResponse(ResponseMethod method, UrlEnum url) {
        this.method = method;
        this.url = url;
    }

    public ResponseMethod getMethod() {
        return method;
    }

    public void setMethod(ResponseMethod method) {
        this.method = method;
    }

    public UrlEnum getUrl() {
        return url;
    }

    public void setUrl(UrlEnum url) {
        this.url = url;
    }
}
