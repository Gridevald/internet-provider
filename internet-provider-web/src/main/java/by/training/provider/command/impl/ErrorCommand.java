package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.command.enums.PageEnum;

import javax.servlet.http.HttpServletRequest;

public class ErrorCommand implements Command {

    @Override
    public PageResponse execute(HttpServletRequest request) {
        return new PageResponse(ResponseMethod.FORWARD, PageEnum.ERROR);
    }
}
