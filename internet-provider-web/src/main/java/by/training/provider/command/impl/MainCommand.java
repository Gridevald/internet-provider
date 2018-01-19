package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.command.enums.RoleEnum;
import by.training.provider.command.impl.exception.InvalidPasswordException;
import by.training.provider.command.util.PasswordCoder;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.UrlResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.*;
import by.training.provider.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MainCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
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

    /**
     * If visitor's role is not 'guest', then returns appropriate main page url.
     * Otherwise checks if email and password correct. If so, then returns
     * appropriate main page url, otherwise returns login page url with error
     * message.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    @Override
    public UrlResponse execute(HttpServletRequest request) {

        String role = (String) request.getSession().getAttribute(ParamNames.ROLE);
        if (RoleEnum.USER.getRole().equals(role)) {
            return new UrlResponse(ResponseMethod.REDIRECT, UrlEnum.SUCCESS_USER_ACTION_COMMAND);
        }
        if (RoleEnum.ADMIN.getRole().equals(role)) {
            return new UrlResponse(ResponseMethod.REDIRECT, UrlEnum.SUCCESS_ADMIN_ACTION_COMMAND);
        }

        String email = request.getParameter(ParamNames.EMAIL);
        String password = request.getParameter(ParamNames.PASSWORD);

        if (email == null || password == null) {
            return new UrlResponse(ResponseMethod.REDIRECT, UrlEnum.LOGIN_COMMAND);
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
            LOGGER.error(e.getMessage());
            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.ERROR);
        } catch (InvalidPasswordException e1) {
            return handlePasswordException(request);
        }

        request.setAttribute(ParamNames.LOGIN_ERROR, NOT_REGISTERED_CODE);
        return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.LOGIN);
    }

    /**
     * Checks if person with given email and password exists.
     *
     * @param service Service according to role.
     * @param email to find.
     * @param password to check.
     * @return true when person with given email exists, false otherwise.
     * @throws DataException when exception occurred on service execution.
     * @throws InvalidPasswordException when given password not equals to
     * password in DB.
     */
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

    /**
     * Returns login page url if incorrect password.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    private UrlResponse handlePasswordException(HttpServletRequest request) {
        request.setAttribute(ParamNames.LOGIN_ERROR, WRONG_PASSWORD_CODE);
        return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.LOGIN);
    }

    /**
     * Person handler. Executes when person exists.
     */
    private interface HandleRequest {
        UrlResponse handle(HttpServletRequest request, String email) throws DataException;
    }

    /**
     * Admin handler.
     * Sets necessary parameters to session and request
     * and returns admin main page url.
     */
    private class AdminHandleRequest implements HandleRequest {

        @Override
        public UrlResponse handle(HttpServletRequest request, String email)
                throws DataException {

            Admin admin = adminService.getAdminByEmail(email);

            HttpSession session = request.getSession();

            session.setAttribute(ParamNames.ROLE, RoleEnum.ADMIN.getRole());
            session.setAttribute(ParamNames.PERSON, admin);

            List<User> userList = userService.getAllUsersWithPlan();
            request.setAttribute(ParamNames.USER_LIST, userList);

            List<Plan> planList = planService.getAllPlans();
            request.setAttribute(ParamNames.PLAN_LIST, planList);

            List<Customer> customerList = customerService.getAllCustomersWithPlan();
            request.setAttribute(ParamNames.CUSTOMER_LIST, customerList);

            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.ADMIN);
        }
    }

    /**
     * User handler.
     * Sets eager loaded user to session and returns user main page url.
     */
    private class UserHandleRequest implements HandleRequest {

        @Override
        public UrlResponse handle(HttpServletRequest request, String email)
                throws DataException {

            User user = userService.getUserByEmail(email);

            HttpSession session = request.getSession();

            session.setAttribute(ParamNames.ROLE, RoleEnum.USER.getRole());

            Integer userId = user.getId();
            user = userService.getEagerUser(userId);

            session.setAttribute(ParamNames.PERSON, user);

            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.USER);
        }
    }

    /**
     * Customer handler
     * Returns login page url with message.
     */
    private class CustomerHandleRequest implements  HandleRequest {

        @Override
        public UrlResponse handle(HttpServletRequest request, String email)
                throws DataException {

            request.setAttribute(ParamNames.LOGIN_ERROR, NOT_ACTIVE_CODE);

            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.LOGIN);
        }
    }
}
