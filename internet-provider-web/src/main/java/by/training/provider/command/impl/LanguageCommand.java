package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements Command {

    @Override
    public PageResponse execute(HttpServletRequest request) {

        String langToSet = request.getParameter(ParamNames.LANG_TO_SET);
        HttpSession session = request.getSession();
        session.setAttribute(ParamNames.UI_LANG, langToSet);

        return new PageResponse(ResponseMethod.FORWARD, PageEnum.HOME);
    }
}
