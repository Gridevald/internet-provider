package by.training.provider.command.impl.admin;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.command.util.ContractValidator;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.Customer;
import by.training.provider.service.ApproveUserService;
import by.training.provider.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ApproveUserCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private ApproveUserService approveUserService;
    private CustomerService customerService;

    public ApproveUserCommand(ApproveUserService approveUserService,
                              CustomerService customerService) {
        this.approveUserService = approveUserService;
        this.customerService = customerService;
    }

    @Override
    public PageResponse execute(HttpServletRequest request) {

        String customerIdStr = request.getParameter(ParamNames.CUSTOMER_TO_APPROVE);
        Integer customerId = Integer.valueOf(customerIdStr);

        Customer customer;
        try {
            customer = customerService.getEagerCustomerById(customerId);
        } catch (DataException e) {
            LOGGER.error(e.getMessage());
            return new PageResponse(ResponseMethod.FORWARD, PageEnum.ERROR);
        }

        String contractStr = request.getParameter(ParamNames.CONTRACT);
        Integer contract = Integer.valueOf(contractStr);

        if (ContractValidator.isContractValid(contract)) {
            try {
                approveUserService.moveCustomerToUser(customer, contract);
            } catch (DataException e) {
                LOGGER.error(e.getMessage());
                return new PageResponse(ResponseMethod.FORWARD, PageEnum.ERROR);
            }
        } else {
            request.setAttribute(ParamNames.CUSTOMER, customer);
            request.setAttribute(ParamNames.CONTRACT_ERROR, 1);
            return new PageResponse(ResponseMethod.FORWARD, PageEnum.SET_USER);
        }

        return new PageResponse(ResponseMethod.REDIRECT, PageEnum.SUCCESS_ADMIN_ACTION_COMMAND);
    }
}
