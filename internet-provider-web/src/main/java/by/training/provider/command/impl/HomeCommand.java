package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.controller.ResponseMethod;
import by.training.provider.controller.UrlResponse;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand implements Command {

    /**
     * Returns home page url.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    @Override
    public UrlResponse execute(HttpServletRequest request) {
        return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.HOME);
    }
}
