package by.training.provider.command.impl.admin;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.command.util.ContractValidator;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.UrlResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.Customer;
import by.training.provider.service.ApproveUserService;
import by.training.provider.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ApproveUserCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int ERROR_FLAG = 1;
    private ApproveUserService approveUserService;
    private CustomerService customerService;

    public ApproveUserCommand(ApproveUserService approveUserService,
                              CustomerService customerService) {
        this.approveUserService = approveUserService;
        this.customerService = customerService;
    }

    /**
     * Approves new user and returns success admin action command url.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    @Override
    public UrlResponse execute(HttpServletRequest request) {

        String customerIdStr = request.getParameter(ParamNames.CUSTOMER_TO_APPROVE);
        Integer customerId = Integer.valueOf(customerIdStr);

        Customer customer;
        try {
            customer = customerService.getEagerCustomerById(customerId);
        } catch (DataException e) {
            LOGGER.error(e.getMessage());
            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.ERROR);
        }

        String contractStr = request.getParameter(ParamNames.CONTRACT);
        Integer contract = Integer.valueOf(contractStr);

        if (ContractValidator.isContractValid(contract)) {
            try {
                approveUserService.moveCustomerToUser(customer, contract);
            } catch (DataException e) {
                LOGGER.error(e.getMessage());
                return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.ERROR);
            }
        } else {
            request.setAttribute(ParamNames.CUSTOMER, customer);
            request.setAttribute(ParamNames.CONTRACT_ERROR, ERROR_FLAG);
            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.SET_USER);
        }

        return new UrlResponse(ResponseMethod.REDIRECT, UrlEnum.SUCCESS_ADMIN_ACTION_COMMAND);
    }
}
