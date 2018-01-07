package by.training.provider.command.impl;

import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.service.AdminService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainCommandTest {
    @Mock
    private HttpSession session;

    @Mock
    private HttpServletRequest request;

    @Mock
    private AdminService service;

    @InjectMocks
    private MainCommand command;

    @Before
    public void setUp() throws DataException{

        when(request.getSession()).thenReturn(session);

        when(service.getPersonByEmail(anyString())).thenReturn(null);
    }

    @Test
    public void testExecute() {
        when(session.getAttribute(ParamNames.ROLE)).thenReturn("admin");

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.REDIRECT, response.getMethod());
        Assert.assertEquals(PageEnum.SUCCESS_ADMIN_ACTION_COMMAND, response.getPageUrl());
    }
}
