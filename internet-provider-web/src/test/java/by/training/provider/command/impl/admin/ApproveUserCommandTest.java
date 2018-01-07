package by.training.provider.command.impl.admin;

import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
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

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.ERROR, response.getPageUrl());
    }

    @Test
    public void shouldReturnForwardSetUserWhenInvalidContract() {
        when(request.getParameter(ParamNames.CONTRACT)).thenReturn(INVALID_CONTRACT);

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.SET_USER, response.getPageUrl());
    }

    @Test
    public void shouldReturnForwardErrorWhenDataExceptionOnApproveService() throws DataException {
        doThrow(new DataException()).when(approveUserService).moveCustomerToUser(any(), any());

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.ERROR, response.getPageUrl());
    }

    @Test
    public void shouldReturnRedirectSuccessAdminWhenValidData() {
        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.REDIRECT, response.getMethod());
        Assert.assertEquals(PageEnum.SUCCESS_ADMIN_ACTION_COMMAND, response.getPageUrl());
    }
}
