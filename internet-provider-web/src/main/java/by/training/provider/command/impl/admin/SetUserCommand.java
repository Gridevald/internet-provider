package by.training.provider.command.impl.admin;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.Customer;
import by.training.provider.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SetUserCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private CustomerService service;

    public SetUserCommand(CustomerService service) {
        this.service = service;
    }

    @Override
    public PageResponse execute(HttpServletRequest request) {

        String customerIdStr = request.getParameter(ParamNames.CUSTOMER_TO_APPROVE);
        Integer customerId = Integer.valueOf(customerIdStr);
        Customer customer;
        try {
            customer = service.getEagerCustomerById(customerId);
        } catch (DataException e) {
            LOGGER.error(e.getMessage());
            return new PageResponse(ResponseMethod.FORWARD, PageEnum.ERROR);
        }

        request.setAttribute(ParamNames.CUSTOMER, customer);

        return new PageResponse(ResponseMethod.FORWARD, PageEnum.SET_USER);
    }
}
