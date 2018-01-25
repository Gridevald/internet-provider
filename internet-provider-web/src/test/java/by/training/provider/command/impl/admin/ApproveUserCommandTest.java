package by.training.provider.command.impl.admin;

import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.controller.ResponseMethod;
import by.training.provider.controller.UrlResponse;
import by.training.provider.dao.exception.DataException;
import by.training.provider.service.ApproveUserService;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApproveUserCommandTest {

    private static final String ID = "1";
    private static final String VALID_CONTRACT = "555555555";
    private static final String INVALID_CONTRACT = "5555";
    @Mock
    private HttpServletRequest request;
    @Mock
    private ApproveUserService approveUserService;
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private ApproveUserCommand command;

    @Before
    public void setup() {
        when(request.getParameter(ParamNames.CUSTOMER_TO_APPROVE)).thenReturn(ID);
        when(request.getParameter(ParamNames.CONTRACT)).thenReturn(VALID_CONTRACT);
    }

    @Test
    public void shouldReturnForwardErrorWhenDataExceptionOnCustomerService() throws DataException {
        when(customerService.getEagerCustomerById(any())).thenThrow(new DataException());

        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.ERROR, response.getUrl());
    }

    @Test
    public void shouldReturnForwardSetUserWhenInvalidContract() {
        when(request.getParameter(ParamNames.CONTRACT)).thenReturn(INVALID_CONTRACT);

        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.SET_USER, response.getUrl());
    }

    @Test
    public void shouldReturnForwardErrorWhenDataExceptionOnApproveService() throws DataException {
        doThrow(new DataException()).when(approveUserService).moveCustomerToUser(any(), any());

        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.ERROR, response.getUrl());
    }

    @Test
    public void shouldReturnRedirectSuccessAdminWhenValidData() {
        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.REDIRECT, response.getMethod());
        Assert.assertEquals(UrlEnum.SUCCESS_ADMIN_ACTION_COMMAND, response.getUrl());
    }
}
