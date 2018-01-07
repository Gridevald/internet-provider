package by.training.provider.command.impl.admin;

import by.training.provider.command.Command;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;

import javax.servlet.http.HttpServletRequest;

public class SetPlanCommand implements Command {

    @Override
    public PageResponse execute(HttpServletRequest request) {
        return new PageResponse(ResponseMethod.FORWARD, PageEnum.SET_PLAN);
    }
}
