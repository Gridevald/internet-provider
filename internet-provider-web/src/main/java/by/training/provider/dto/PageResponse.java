package by.training.provider.dto;

import by.training.provider.command.enums.PageEnum;

public class PageResponse {

    private ResponseMethod method;
    private PageEnum pageUrl;

    public PageResponse() {
    }

    public PageResponse(ResponseMethod method, PageEnum pageUrl) {
        this.method = method;
        this.pageUrl = pageUrl;
    }

    public ResponseMethod getMethod() {
        return method;
    }

    public void setMethod(ResponseMethod method) {
        this.method = method;
    }

    public PageEnum getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(PageEnum pageUrl) {
        this.pageUrl = pageUrl;
    }
}
