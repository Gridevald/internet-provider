package by.training.provider.command.impl.admin;

import by.training.provider.command.enums.UrlEnum;
import by.training.provider.controller.ResponseMethod;
import by.training.provider.controller.UrlResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

@RunWith(MockitoJUnitRunner.class)
public class SetPlanCommandTest {

    @Mock
    private HttpServletRequest request;

    @Test
    public void shouldReturnForwardSetPlan() {
        SetPlanCommand command = new SetPlanCommand();
        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.SET_PLAN, response.getUrl());
    }
}
