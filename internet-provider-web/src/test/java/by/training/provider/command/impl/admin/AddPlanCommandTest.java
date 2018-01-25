package by.training.provider.command.impl.admin;

import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.controller.ResponseMethod;
import by.training.provider.controller.UrlResponse;
import by.training.provider.dao.exception.DataException;
import by.training.provider.service.PlanService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddPlanCommandTest {

    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String D_SPEED = "50";
    private static final String U_SPEED = "50";
    private static final String PRICE = "1";
    @Mock
    private HttpServletRequest request;
    @Mock
    private PlanService service;
    @InjectMocks
    private AddPlanCommand command;

    @Before
    public void setup() {
        when(request.getParameter(ParamNames.NAME)).thenReturn(NAME);
        when(request.getParameter(ParamNames.DESCRIPTION)).thenReturn(DESCRIPTION);
        when(request.getParameter(ParamNames.DOWNLOAD_SPEED)).thenReturn(D_SPEED);
        when(request.getParameter(ParamNames.UPLOAD_SPEED)).thenReturn(U_SPEED);
        when(request.getParameter(ParamNames.PRICE)).thenReturn(PRICE);
    }

    @Test
    public void shouldReturnRedirectSuccessAdminWhenValidData() throws DataException {
        doNothing().when(service).insertPlan(any());

        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.REDIRECT, response.getMethod());
        Assert.assertEquals(UrlEnum.SUCCESS_ADMIN_ACTION_COMMAND, response.getUrl());
    }

    @Test
    public void shouldReturnForwardSetPlanWhenInvalidData() {
        when(request.getParameter(ParamNames.NAME)).thenReturn(null);

        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.SET_PLAN, response.getUrl());
    }

    @Test
    public void shouldReturnForwardErrorWhenDataException() throws DataException {
        doThrow(new DataException()).when(service).insertPlan(any());

        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.ERROR, response.getUrl());
    }
}
