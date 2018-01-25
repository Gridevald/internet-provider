package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.RoleEnum;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.controller.ResponseMethod;
import by.training.provider.controller.UrlResponse;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    /**
     * Returns main page url according to visitor role.
     * If visitor is 'guest', then returns login page url.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    @Override
    public UrlResponse execute(HttpServletRequest request) {

        String role = (String) request.getSession().getAttribute(ParamNames.ROLE);

        UrlEnum replyPage = null;

        if (RoleEnum.GUEST.getRole().equals(role)) {
            replyPage = UrlEnum.LOGIN;
        }

        if (RoleEnum.USER.getRole().equals(role)) {
            replyPage = UrlEnum.USER;
        }

        if (RoleEnum.ADMIN.getRole().equals(role)) {
            return new UrlResponse(ResponseMethod.REDIRECT, UrlEnum.SUCCESS_ADMIN_ACTION_COMMAND);
        }

        return new UrlResponse(ResponseMethod.FORWARD, replyPage);
    }
}
