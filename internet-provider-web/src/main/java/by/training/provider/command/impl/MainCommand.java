package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.command.enums.RoleEnum;
import by.training.provider.command.impl.exception.InvalidPasswordException;
import by.training.provider.command.util.PasswordCoder;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.*;
import by.training.provider.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MainCommand implements Command {

    private static final int WRONG_PASSWORD_CODE = 1;
    private static final int NOT_REGISTERED_CODE = 2;
    private static final int NOT_ACTIVE_CODE = 3;

    private AdminService adminService;
    private UserService userService;
    private CustomerService customerService;
    private PlanService planService;

    public MainCommand(AdminService adminService,
                       UserService userService,
                       CustomerService customerService,
                       PlanService planService) {

        this.adminService = adminService;
        this.userService = userService;
        this.customerService = customerService;
        this.planService = planService;
    }

    @Override
    public PageResponse execute(HttpServletRequest request) {

        String role = (String) request.getSession().getAttribute(ParamNames.ROLE);
        if (RoleEnum.USER.getRole().equals(role)) {
            return new PageResponse(ResponseMethod.REDIRECT, PageEnum.SUCCESS_USER_ACTION_COMMAND);
        }
        if (RoleEnum.ADMIN.getRole().equals(role)) {
            return new PageResponse(ResponseMethod.REDIRECT, PageEnum.SUCCESS_ADMIN_ACTION_COMMAND);
        }

        String email = request.getParameter(ParamNames.EMAIL);
        String password = request.getParameter(ParamNames.PASSWORD);

        if (email == null || password == null) {
            return new PageResponse(ResponseMethod.REDIRECT, PageEnum.LOGIN_COMMAND);
        }

        String passwordHash = PasswordCoder.hashSha(password);

        try {
            if (isPersonExists(adminService, email, passwordHash)) {
                return new AdminHandleRequest().handle(request, email);
            }

            if (isPersonExists(userService, email, passwordHash)) {
                return new UserHandleRequest().handle(request, email);
            }

            if (isPersonExists(customerService, email, passwordHash)) {
                return new CustomerHandleRequest().handle(request, email);
            }
        } catch (DataException e) {
            return new PageResponse(ResponseMethod.FORWARD, PageEnum.ERROR);
        } catch (InvalidPasswordException e1) {
            return handlePasswordException(request);
        }

        request.setAttribute(ParamNames.LOGIN_ERROR, NOT_REGISTERED_CODE);
        return new PageResponse(ResponseMethod.FORWARD, PageEnum.LOGIN);
    }

    private boolean isPersonExists(PersonService service, String email, String password)
            throws DataException, InvalidPasswordException {

        Person person = service.getPersonByEmail(email);
        if (person != null) {
            if (password.equals(person.getPassword())) {
                return true;
            } else {
                throw new InvalidPasswordException();
            }
        }
        return false;
    }

    private PageResponse handlePasswordException(HttpServletRequest request) {
        request.setAttribute(ParamNames.LOGIN_ERROR, WRONG_PASSWORD_CODE);
        return new PageResponse(ResponseMethod.FORWARD, PageEnum.LOGIN);
    }

    private interface HandleRequest {
        PageResponse handle(HttpServletRequest request, String email) throws DataException;
    }

    private class AdminHandleRequest implements HandleRequest {

        @Override
        public PageResponse handle(HttpServletRequest request, String email)
                throws DataException {

            Admin admin = adminService.getByUnique(email);

            HttpSession session = request.getSession();

            session.setAttribute(ParamNames.ROLE, RoleEnum.ADMIN.getRole());
            session.setAttribute(ParamNames.PERSON, admin);

            List<User> userList = userService.getAllUsersWithPlan();
            request.setAttribute(ParamNames.USER_LIST, userList);

            List<Plan> planList = planService.getAllPlans();
            request.setAttribute(ParamNames.PLAN_LIST, planList);

            List<Customer> customerList = customerService.getAllCustomersWithPlan();
            request.setAttribute(ParamNames.CUSTOMER_LIST, customerList);

            return new PageResponse(ResponseMethod.FORWARD, PageEnum.ADMIN);
        }
    }

    private class UserHandleRequest implements HandleRequest {

        @Override
        public PageResponse handle(HttpServletRequest request, String email)
                throws DataException {

            User user = userService.getUserByUnique(email);

            HttpSession session = request.getSession();

            session.setAttribute(ParamNames.ROLE, RoleEnum.USER.getRole());

            Integer userId = user.getId();
            user = userService.getEagerUser(userId);

            session.setAttribute(ParamNames.PERSON, user);

            return new PageResponse(ResponseMethod.FORWARD, PageEnum.USER);
        }
    }

    private class CustomerHandleRequest implements  HandleRequest {

        @Override
        public PageResponse handle(HttpServletRequest request, String email)
                throws DataException {

            request.setAttribute(ParamNames.LOGIN_ERROR, NOT_ACTIVE_CODE);

            return new PageResponse(ResponseMethod.FORWARD, PageEnum.LOGIN);
        }
    }
}
