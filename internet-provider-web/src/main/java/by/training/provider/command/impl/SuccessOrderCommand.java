package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.dto.UrlResponse;
import by.training.provider.dto.ResponseMethod;

import javax.servlet.http.HttpServletRequest;

public class SuccessOrderCommand implements Command {

    /**
     * Returns success order page url.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    @Override
    public UrlResponse execute(HttpServletRequest request) {
        return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.SUCCESS_ORDER);
    }
}
