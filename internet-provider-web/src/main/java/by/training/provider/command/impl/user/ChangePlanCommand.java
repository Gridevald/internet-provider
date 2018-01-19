package by.training.provider.command.impl.user;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.UrlResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.User;
import by.training.provider.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangePlanCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private UserService service;

    public ChangePlanCommand(UserService service) {
        this.service = service;
    }

    /**
     * Updates user with new plan and returns success user action command url.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    @Override
    public UrlResponse execute(HttpServletRequest request) {

        String planIdToSetStr = request.getParameter(ParamNames.PLAN_TO_SET);
        Integer planToSet = Integer.valueOf(planIdToSetStr);

        User user = (User) request.getSession().getAttribute(ParamNames.PERSON);
        user.setPlanId(planToSet);

        try {
            service.updateUser(user);
        } catch (DataException e) {
            LOGGER.error(e.getMessage());
            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.ERROR);
        }

        return new UrlResponse(ResponseMethod.REDIRECT, UrlEnum.SUCCESS_USER_ACTION_COMMAND);
    }
}
