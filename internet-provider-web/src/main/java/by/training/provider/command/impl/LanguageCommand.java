package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.dto.UrlResponse;
import by.training.provider.dto.ResponseMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements Command {
    /**
     * Changes UI language and returns home page url.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    @Override
    public UrlResponse execute(HttpServletRequest request) {

        String langToSet = request.getParameter(ParamNames.LANG_TO_SET);
        HttpSession session = request.getSession();
        session.setAttribute(ParamNames.UI_LANG, langToSet);

        return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.HOME);
    }
}
