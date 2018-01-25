package by.training.provider.command.impl.user;

import by.training.provider.command.Command;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.controller.ResponseMethod;
import by.training.provider.controller.UrlResponse;

import javax.servlet.http.HttpServletRequest;

public class SetPaymentCommand implements Command {

    /**
     * Returns set payment page url.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    @Override
    public UrlResponse execute(HttpServletRequest request) {
        return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.SET_PAYMENT);
    }
}
