package by.training.provider.command.impl.admin;

import by.training.provider.command.Command;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.command.util.PlanValidator;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.Plan;
import by.training.provider.service.PlanService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class AddPlanCommand implements Command {

    private PlanService service;

    public AddPlanCommand(PlanService service) {
        this.service = service;
    }

    @Override
    public PageResponse execute(HttpServletRequest request) {

        Plan plan = new Plan();

        String name =  request.getParameter("name");
        plan.setName(name);

        String description = request.getParameter("description");
        plan.setDescription(description);

        String downloadSpeedStr = request.getParameter("downloadSpeed");
        Integer downloadSpeed = Integer.valueOf(downloadSpeedStr);
        plan.setDownloadSpeed(downloadSpeed);

        String uploadSpeedStr = request.getParameter("uploadSpeed");
        Integer uploadSpeed = Integer.valueOf(uploadSpeedStr);
        plan.setUploadSpeed(uploadSpeed);

        String priceStr = request.getParameter("price");
        BigDecimal price = new BigDecimal(priceStr);
        plan.setPrice(price);

        if (PlanValidator.isValidPlan(plan)) {
            try {
                service.inserPlan(plan);
            } catch (DataException e) {
                return new PageResponse(ResponseMethod.FORWARD, PageEnum.ERROR);
            }
        } else {
            request.setAttribute("planError", 1);
            return new PageResponse(ResponseMethod.FORWARD, PageEnum.SET_PLAN);
        }

        return new PageResponse(ResponseMethod.REDIRECT, PageEnum.SUCCESS_ADMIN_ACTION_COMMAND);
    }
}
