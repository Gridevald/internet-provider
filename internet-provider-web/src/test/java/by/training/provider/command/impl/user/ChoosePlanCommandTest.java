package by.training.provider.command.impl.user;

import by.training.provider.command.enums.UrlEnum;
import by.training.provider.controller.ResponseMethod;
import by.training.provider.controller.UrlResponse;
import by.training.provider.dao.exception.DataException;
import by.training.provider.service.PlanService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChoosePlanCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private PlanService service;
    @InjectMocks
    private ChoosePlanCommand command;

    @Test
    public void shouldReturnForwardChoosePlanWhenNoExceptions() {
        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.CHOOSE_PLAN, response.getUrl());
    }

    @Test
    public void shouldReturnForwardErrorWhenDataException() throws DataException {
        when(service.getAllPlans()).thenThrow(new DataException());

        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.ERROR, response.getUrl());
    }
}
