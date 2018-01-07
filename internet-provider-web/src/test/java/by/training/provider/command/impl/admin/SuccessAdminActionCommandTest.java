package by.training.provider.command.impl.admin;

import by.training.provider.command.enums.PageEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.service.CustomerService;
import by.training.provider.service.PlanService;
import by.training.provider.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SuccessAdminActionCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private UserService userService;
    @Mock
    private PlanService planService;
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private SuccessAdminActionCommand command;

    @Before
    public void setup() throws DataException {
        when(userService.getAllUsersWithPlan()).thenReturn(new ArrayList<>());
        when(planService.getAllPlans()).thenReturn(new ArrayList<>());
        when(customerService.getAllCustomersWithPlan()).thenReturn(new ArrayList<>());
    }

    @Test
    public void shouldReturnForwardAdminWhenValidData() {
        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.ADMIN, response.getPageUrl());
    }

    @Test
    public void shouldReturnForwardErrorWhenDataExceptionOnUserService() throws DataException {
        when(userService.getAllUsersWithPlan()).thenThrow(new DataException());
        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.ERROR, response.getPageUrl());
    }

    @Test
    public void shouldReturnForwardErrorWhenDataExceptionOnPlanService() throws DataException {
        when(planService.getAllPlans()).thenThrow(new DataException());
        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.ERROR, response.getPageUrl());
    }

    @Test
    public void shouldReturnForwardErrorWhenDataExceptionOnCustomerService() throws DataException {
        when(customerService.getAllCustomersWithPlan()).thenThrow(new DataException());
        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.ERROR, response.getPageUrl());
    }
}
