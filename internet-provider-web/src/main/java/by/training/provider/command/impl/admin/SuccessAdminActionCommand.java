package by.training.provider.command.impl.admin;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.Customer;
import by.training.provider.entity.Plan;
import by.training.provider.entity.User;
import by.training.provider.service.CustomerService;
import by.training.provider.service.PlanService;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SuccessAdminActionCommand implements Command {

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

    @Override
    public PageResponse execute(HttpServletRequest request) {

        try {
            List<User> userList = userService.getAllUsersWithPlan();
            request.setAttribute(ParamNames.USER_LIST, userList);

            List<Plan> planList = planService.getAllPlans();
            request.setAttribute(ParamNames.PLAN_LIST, planList);

            List<Customer> customerList = customerService.getAllCustomersWithPlan();
            request.setAttribute(ParamNames.CUSTOMER_LIST, customerList);
        } catch (DataException e) {
            return new PageResponse(ResponseMethod.FORWARD, PageEnum.ERROR);
        }

        return new PageResponse(ResponseMethod.FORWARD, PageEnum.ADMIN);
    }
}
