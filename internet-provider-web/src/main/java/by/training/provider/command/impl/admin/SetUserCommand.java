package by.training.provider.command.impl.admin;

import by.training.provider.command.Command;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.Customer;
import by.training.provider.service.CustomerService;

import javax.servlet.http.HttpServletRequest;

public class SetUserCommand implements Command {

    private CustomerService service;

    public SetUserCommand(CustomerService service) {
        this.service = service;
    }

    @Override
    public PageResponse execute(HttpServletRequest request) {

        String customerIdStr = request.getParameter("customerToApprove");
        Integer customerId = Integer.valueOf(customerIdStr);
        Customer customer;
        try {
            customer = service.getEagerCustomerById(customerId);
        } catch (DataException e) {
            return new PageResponse(ResponseMethod.FORWARD, PageEnum.ERROR);
        }

        request.setAttribute("customer", customer);

        return new PageResponse(ResponseMethod.FORWARD, PageEnum.SET_USER);
    }
}
