package by.training.provider.command.impl.user;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
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

    @Override
    public PageResponse execute(HttpServletRequest request) {

        String planIdToSetStr = request.getParameter(ParamNames.PLAN_TO_SET);
        Integer planToSet = Integer.valueOf(planIdToSetStr);

        User user = (User) request.getSession().getAttribute(ParamNames.PERSON);
        user.setPlanId(planToSet);

        try {
            service.updateUser(user);
        } catch (DataException e) {
            LOGGER.error(e.getMessage());
            return new PageResponse(ResponseMethod.FORWARD, PageEnum.ERROR);
        }

        return new PageResponse(ResponseMethod.REDIRECT, PageEnum.SUCCESS_USER_ACTION_COMMAND);
    }
}
