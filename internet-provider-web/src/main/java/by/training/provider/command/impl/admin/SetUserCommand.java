package by.training.provider.command.impl.admin;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.UrlResponse;
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

    /**
     * Returns set user page url.
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
            customer = service.getEagerCustomerById(customerId);
        } catch (DataException e) {
            LOGGER.error(e.getMessage());
            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.ERROR);
        }

        request.setAttribute(ParamNames.CUSTOMER, customer);

        return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.SET_USER);
    }
}
