package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.command.util.CustomerValidator;
import by.training.provider.command.util.PasswordCoder;
import by.training.provider.controller.ResponseMethod;
import by.training.provider.controller.UrlResponse;
import by.training.provider.dao.exception.DataException;
import by.training.provider.entity.Customer;
import by.training.provider.entity.Plan;
import by.training.provider.service.CustomerService;
import by.training.provider.service.PlanService;
import by.training.provider.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MakeOrderCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int PASS_NOT_EQUALS_CODE = 1;
    private static final int EMAIL_CODE = 2;
    private CustomerService customerService;
    private PlanService planService;

    public MakeOrderCommand(CustomerService customerService, PlanService planService) {
        this.customerService = customerService;
        this.planService = planService;
    }

    /**
     * Validates given data and returns success order command url if data is
     * correct and register page url otherwise.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    @Override
    public UrlResponse execute(HttpServletRequest request) {

        CustomerValidator validator = new CustomerValidator(new UserService());

        String passwordF = request.getParameter(ParamNames.PASSWORD_F);
        String passwordS = request.getParameter(ParamNames.PASSWORD_S);

        if (!validator.isPasswordsEquals(passwordF, passwordS)) {
            return handleWithError(PASS_NOT_EQUALS_CODE, request);
        }

        Customer customer = new Customer();

        String firstName = request.getParameter(ParamNames.FIRST_NAME);
        customer.setFirstName(firstName);

        String middleName = request.getParameter(ParamNames.MIDDLE_NAME);
        customer.setMiddleName(middleName);

        String lastName = request.getParameter(ParamNames.LAST_NAME);
        customer.setLastName(lastName);

        String city = request.getParameter(ParamNames.CITY);
        customer.setCity(city);

        String street = request.getParameter(ParamNames.STREET);
        customer.setStreet(street);

        String building = request.getParameter(ParamNames.BUILDING);
        customer.setBuilding(building);

        String apartmentsStr = request.getParameter(ParamNames.APARTMENTS);
        Integer apartments = Integer.valueOf(apartmentsStr);
        customer.setApartments(apartments);

        String email = request.getParameter(ParamNames.EMAIL);
        customer.setEmail(email);

        String passwordHash = PasswordCoder.hashSha(passwordF);
        customer.setPassword(passwordHash);

        String planIdStr = request.getParameter(ParamNames.SELECT_PLAN);
        Integer planId = Integer.valueOf(planIdStr);
        customer.setPlanId(planId);

        try {
            if (validator.isValidCustomer(customer)) {
                customerService.addCustomer(customer);
            } else {
                return handleWithError(EMAIL_CODE, request);
            }
        } catch (DataException e) {
            LOGGER.error(e.getMessage());
            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.ERROR);
        }

        return new UrlResponse(ResponseMethod.REDIRECT, UrlEnum.SUCCESS_ORDER_COMMAND);
    }

    /**
     * Sets appropriate message and plan list to request.
     *
     * @param errorCode code of error to ser
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    private UrlResponse handleWithError(int errorCode, HttpServletRequest request) {
        List<Plan> planList;
        try {
            planList = planService.getAllPlans();
        } catch (DataException e) {
            LOGGER.error(e.getMessage());
            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.ERROR);
        }

        request.setAttribute(ParamNames.PLAN_LIST, planList);
        request.setAttribute(ParamNames.REGISTER_ERROR, errorCode);

        return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.REGISTER);
    }
}
