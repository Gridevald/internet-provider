package by.training.provider.command.impl;

import by.training.provider.command.Command;
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

        session.setAttribute("role", RoleEnum.GUEST.getRole());
        session.setAttribute("person", null);

        return new PageResponse(ResponseMethod.FORWARD, PageEnum.HOME);
    }
}
