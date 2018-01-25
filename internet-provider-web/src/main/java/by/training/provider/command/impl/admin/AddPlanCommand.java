package by.training.provider.command.impl.admin;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.command.util.PlanValidator;
import by.training.provider.controller.ResponseMethod;
import by.training.provider.controller.UrlResponse;
import by.training.provider.dao.exception.DataException;
import by.training.provider.entity.Plan;
import by.training.provider.service.PlanService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class AddPlanCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int ERROR_FLAG = 1;
    private PlanService service;

    public AddPlanCommand(PlanService service) {
        this.service = service;
    }

    /**
     * Adds new plan and returns success admin action command url.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    @Override
    public UrlResponse execute(HttpServletRequest request) {

        Plan plan = new Plan();

        String name =  request.getParameter(ParamNames.NAME);
        plan.setName(name);

        String description = request.getParameter(ParamNames.DESCRIPTION);
        plan.setDescription(description);

        String downloadSpeedStr = request.getParameter(ParamNames.DOWNLOAD_SPEED);
        Integer downloadSpeed = Integer.valueOf(downloadSpeedStr);
        plan.setDownloadSpeed(downloadSpeed);

        String uploadSpeedStr = request.getParameter(ParamNames.UPLOAD_SPEED);
        Integer uploadSpeed = Integer.valueOf(uploadSpeedStr);
        plan.setUploadSpeed(uploadSpeed);

        String priceStr = request.getParameter(ParamNames.PRICE);
        BigDecimal price = new BigDecimal(priceStr);
        plan.setPrice(price);

        if (PlanValidator.isValidPlan(plan)) {
            try {
                service.insertPlan(plan);
            } catch (DataException e) {
                LOGGER.error(e.getMessage());
                return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.ERROR);
            }
        } else {
            request.setAttribute(ParamNames.PLAN_ERROR, ERROR_FLAG);
            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.SET_PLAN);
        }

        return new UrlResponse(ResponseMethod.REDIRECT, UrlEnum.SUCCESS_ADMIN_ACTION_COMMAND);
    }
}
