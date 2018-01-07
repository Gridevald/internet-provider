package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.command.enums.RoleEnum;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    @Override
    public PageResponse execute(HttpServletRequest request) {

        String role = (String) request.getSession().getAttribute(ParamNames.ROLE);

        PageEnum replyPage = null;

        if (RoleEnum.GUEST.getRole().equals(role)) {
            replyPage = PageEnum.LOGIN;
        }

        if (RoleEnum.USER.getRole().equals(role)) {
            replyPage = PageEnum.USER;
        }

        if (RoleEnum.ADMIN.getRole().equals(role)) {
            return new PageResponse(ResponseMethod.REDIRECT, PageEnum.SUCCESS_ADMIN_ACTION_COMMAND);
        }

        return new PageResponse(ResponseMethod.FORWARD, replyPage);
    }
}
