package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.Plan;
import by.training.provider.service.PlanService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PlansCommand implements Command {

    private PlanService service;

    public PlansCommand(PlanService service) {
        this.service = service;
    }

    @Override
    public PageResponse execute(HttpServletRequest request) {

        List<Plan> planList;
        try {
            planList = service.getAllPlans();
        } catch (DataException e) {
            return new PageResponse(ResponseMethod.FORWARD, PageEnum.ERROR);
        }

        request.setAttribute(ParamNames.PLAN_LIST, planList);

        return new PageResponse(ResponseMethod.FORWARD, PageEnum.PLANS);
    }
}
