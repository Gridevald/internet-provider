package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements Command {

    @Override
    public PageResponse execute(HttpServletRequest request) {

        String langToSet = request.getParameter("langToSet");
        HttpSession session = request.getSession(true);
        session.setAttribute("uiLang", langToSet);

        return new PageResponse(ResponseMethod.FORWARD, PageEnum.HOME);
    }
}
