package by.training.provider.command.impl;

import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.command.enums.RoleEnum;
import by.training.provider.command.util.PasswordCoder;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.Admin;
import by.training.provider.entity.Customer;
import by.training.provider.entity.Person;
import by.training.provider.entity.User;
import by.training.provider.service.AdminService;
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
import javax.servlet.http.HttpSession;

import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainCommandTest {

    private static final String EMAIL = "mail@gigabit.by";
    private static final String PASSWORD = "password";
    private static final String PASSWORD_HASH = PasswordCoder.hashSha(PASSWORD);
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private AdminService adminService;
    @Mock
    private UserService userService;
    @Mock
    private CustomerService customerService;
    @Mock
    private PlanService planService;
    @InjectMocks
    private MainCommand command;

    @Before
    public void setUp() throws DataException{
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(ParamNames.EMAIL)).thenReturn(EMAIL);
        when(request.getParameter(ParamNames.PASSWORD)).thenReturn(PASSWORD);
    }

    @Test
    public void shouldReturnRedirectSuccessUserActionWhenRoleUser() {
        when(session.getAttribute(ParamNames.ROLE)).thenReturn(RoleEnum.USER.getRole());

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.REDIRECT, response.getMethod());
        Assert.assertEquals(PageEnum.SUCCESS_USER_ACTION_COMMAND, response.getPageUrl());
    }

    @Test
    public void shouldReturnRedirectSuccessAdminWhenRoleAdmin() {
        when(session.getAttribute(ParamNames.ROLE)).thenReturn(RoleEnum.ADMIN.getRole());

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.REDIRECT, response.getMethod());
        Assert.assertEquals(PageEnum.SUCCESS_ADMIN_ACTION_COMMAND, response.getPageUrl());
    }

    @Test
    public void shouldReturnRedirectLoginWhenNullEmail() {
        when(request.getParameter(ParamNames.EMAIL)).thenReturn(null);

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.REDIRECT, response.getMethod());
        Assert.assertEquals(PageEnum.LOGIN_COMMAND, response.getPageUrl());
    }

    @Test
    public void shouldReturnRedirectLoginWhenNullPassword() {
        when(request.getParameter(ParamNames.PASSWORD)).thenReturn(null);

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.REDIRECT, response.getMethod());
        Assert.assertEquals(PageEnum.LOGIN_COMMAND, response.getPageUrl());
    }

    @Test
    public void shouldReturnForwardAdmin() throws DataException {
        Person person = new Admin();
        person.setEmail(EMAIL);
        person.setPassword(PASSWORD_HASH);
        when(adminService.getPersonByEmail(anyString())).thenReturn(person);
        when(userService.getAllUsersWithPlan()).thenReturn(Collections.emptyList());
        when(planService.getAllPlans()).thenReturn(Collections.emptyList());
        when(customerService.getAllCustomersWithPlan()).thenReturn(Collections.emptyList());

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.ADMIN, response.getPageUrl());
    }

    @Test
    public void shouldReturnForwardUser() throws DataException {
        User user = new User();
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD_HASH);
        when(adminService.getPersonByEmail(anyString())).thenReturn(null);
        when(userService.getPersonByEmail(anyString())).thenReturn(user);
        when(userService.getUserByUnique(anyString())).thenReturn(user);
        when(userService.getEagerUser(any())).thenReturn(user);

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.USER, response.getPageUrl());
    }

    @Test
    public void shouldReturnForwardCustomer() throws DataException {
        Customer customer = new Customer();
        customer.setEmail(EMAIL);
        customer.setPassword(PASSWORD_HASH);
        when(adminService.getPersonByEmail(anyString())).thenReturn(null);
        when(userService.getPersonByEmail(anyString())).thenReturn(null);
        when(customerService.getPersonByEmail(anyString())).thenReturn(customer);

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.LOGIN, response.getPageUrl());
    }

    @Test
    public void shouldReturnForwardErrorWhenDataExceptionThrown() throws DataException {
        when(adminService.getPersonByEmail(anyString())).thenThrow(new DataException());

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.ERROR, response.getPageUrl());
    }

    @Test
    public void shouldReturnForwardLoginWhenUserDoesNotExist() throws DataException {
        when(adminService.getPersonByEmail(anyString())).thenReturn(null);
        when(userService.getPersonByEmail(anyString())).thenReturn(null);
        when(customerService.getPersonByEmail(anyString())).thenReturn(null);

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.LOGIN, response.getPageUrl());
    }
}
