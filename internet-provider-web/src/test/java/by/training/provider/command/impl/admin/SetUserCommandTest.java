package by.training.provider.command.impl.admin;

import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.Customer;
import by.training.provider.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SetUserCommandTest {

    private static final String VALID_ID = "1";
    @Mock
    private HttpServletRequest request;
    @Mock
    private CustomerService service;
    @Mock
    private Customer customer;
    @InjectMocks
    private SetUserCommand command;

    @Before
    public void setup() {
        when(request.getParameter(ParamNames.CUSTOMER_TO_APPROVE)).thenReturn(VALID_ID);
    }

    @Test
    public void shouldReturnForwardErrorWhenDataException() throws DataException {
        when(service.getEagerCustomerById(any())).thenThrow(new DataException());

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.ERROR, response.getPageUrl());
    }

    @Test
    public void shouldReturnForwardSetUserWhenValidData() throws DataException {
        when(service.getEagerCustomerById(any())).thenReturn(customer);

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.SET_USER, response.getPageUrl());
    }
}
