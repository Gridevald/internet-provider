package by.training.provider.command.impl.user;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.controller.ResponseMethod;
import by.training.provider.controller.UrlResponse;
import by.training.provider.dao.exception.DataException;
import by.training.provider.entity.Plan;
import by.training.provider.service.PlanService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChoosePlanCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private PlanService service;

    public ChoosePlanCommand(PlanService service) {
        this.service = service;
    }

    /**
     * Returns choose plan page url.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    @Override
    public UrlResponse execute(HttpServletRequest request) {

        List<Plan> planList;
        try {
            planList = service.getAllPlans();
        } catch (DataException e) {
            LOGGER.error(e.getMessage());
            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.ERROR);
        }

        request.setAttribute(ParamNames.PLAN_LIST, planList);

        return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.CHOOSE_PLAN);
    }
}
