package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.command.enums.RoleEnum;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    @Override
    public PageResponse execute(HttpServletRequest request) {

        HttpSession session = request.getSession();

        session.setAttribute(ParamNames.ROLE, RoleEnum.GUEST.getRole());
        session.setAttribute(ParamNames.PERSON, null);

        return new PageResponse(ResponseMethod.FORWARD, PageEnum.HOME);
    }
}
