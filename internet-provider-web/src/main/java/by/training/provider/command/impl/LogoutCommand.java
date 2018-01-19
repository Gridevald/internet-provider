package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.command.enums.RoleEnum;
import by.training.provider.dto.UrlResponse;
import by.training.provider.dto.ResponseMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    /**
     * Sets role to 'guest' and returns home page url.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    @Override
    public UrlResponse execute(HttpServletRequest request) {

        HttpSession session = request.getSession();

        session.setAttribute(ParamNames.ROLE, RoleEnum.GUEST.getRole());
        session.setAttribute(ParamNames.PERSON, null);

        return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.HOME);
    }
}
