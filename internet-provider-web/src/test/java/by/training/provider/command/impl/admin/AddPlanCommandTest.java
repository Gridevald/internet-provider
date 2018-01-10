package by.training.provider.command.impl.admin;

import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

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

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.REDIRECT, response.getMethod());
        Assert.assertEquals(PageEnum.SUCCESS_ADMIN_ACTION_COMMAND, response.getPageUrl());
    }

    @Test
    public void shouldReturnForwardSetPlanWhenInvalidData() {
        when(request.getParameter(ParamNames.NAME)).thenReturn(null);

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.SET_PLAN, response.getPageUrl());
    }

    @Test
    public void shouldReturnForwardErrorWhenDataException() throws DataException {
        doThrow(new DataException()).when(service).insertPlan(any());

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.ERROR, response.getPageUrl());
    }
}
