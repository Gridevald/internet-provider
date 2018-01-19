package by.training.provider.command.impl.admin;

import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.UrlResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.User;
import by.training.provider.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BlockUnblockUserCommandTest {

    private static final String VALID_INT_STR = "1";
    @Mock
    private HttpServletRequest request;
    @Mock
    private UserService userService;
    @Mock
    private User user;
    @InjectMocks
    private BlockUnblockUserCommand command;

    @Before
    public void setup() throws DataException {
        when(request.getParameter(ParamNames.USER_TO_BLOCK)).thenReturn(VALID_INT_STR);
        when(userService.getUserById(any())).thenReturn(user);
        when(request.getParameter(ParamNames.BLOCK)).thenReturn(VALID_INT_STR);
    }

    @Test
    public void shouldReturnForwardErrorWhenDataException() throws DataException {
        doThrow(new DataException()).when(userService).updateUser(user);

        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.ERROR, response.getUrl());
    }

    @Test
    public void shouldReturnRedirectSuccessAdminWhenValidData() throws DataException {
        doNothing().when(userService).updateUser(user);

        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.REDIRECT, response.getMethod());
        Assert.assertEquals(UrlEnum.SUCCESS_ADMIN_ACTION_COMMAND, response.getUrl());
    }
}
