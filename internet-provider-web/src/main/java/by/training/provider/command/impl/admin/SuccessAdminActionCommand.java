package by.training.provider.command.impl.admin;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.controller.ResponseMethod;
import by.training.provider.controller.UrlResponse;
import by.training.provider.dao.exception.DataException;
import by.training.provider.entity.Customer;
import by.training.provider.entity.Plan;
import by.training.provider.entity.User;
import by.training.provider.service.CustomerService;
import by.training.provider.service.PlanService;
import by.training.provider.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SuccessAdminActionCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private UserService userService;
    private PlanService planService;
    private CustomerService customerService;

    public SuccessAdminActionCommand(UserService userService,
                                     PlanService planService,
                                     CustomerService customerService) {

        this.userService = userService;
        this.planService = planService;
        this.customerService = customerService;
    }

    /**
     * Sets user list, plan list, customer list and returns admin main page url.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    @Override
    public UrlResponse execute(HttpServletRequest request) {

        try {
            List<User> userList = userService.getAllUsersWithPlan();
            request.setAttribute(ParamNames.USER_LIST, userList);

            List<Plan> planList = planService.getAllPlans();
            request.setAttribute(ParamNames.PLAN_LIST, planList);

            List<Customer> customerList = customerService.getAllCustomersWithPlan();
            request.setAttribute(ParamNames.CUSTOMER_LIST, customerList);
        } catch (DataException e) {
            LOGGER.error(e.getMessage());
            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.ERROR);
        }

        return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.ADMIN);
    }
}
