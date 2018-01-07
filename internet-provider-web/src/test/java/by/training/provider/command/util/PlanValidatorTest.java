package by.training.provider.command.util;

import by.training.provider.entity.Plan;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlanValidatorTest {

    private static final String EMPTY = "";
    private static final String NOT_EMPTY = "string";
    private static final Integer SPEED_LESS_THAN_ONE = 0;
    private static final Integer SPEED_MORE_THAN_HUNDRED = 101;
    private static final Integer SPEED_IN_BOUNDS = 50;
    private static final BigDecimal PRICE_LESS_THAN_ZERO = new BigDecimal(-1);
    private static final BigDecimal PRICE_EQUALS_ZERO = BigDecimal.ZERO;
    private static final BigDecimal PRICE_MORE_THAN_ZERO = BigDecimal.ONE;

    @Mock
    private Plan plan;

    @Before
    public void setup() {
        when(plan.getName()).thenReturn(NOT_EMPTY);
        when(plan.getDescription()).thenReturn(NOT_EMPTY);
        when(plan.getDownloadSpeed()).thenReturn(SPEED_IN_BOUNDS);
        when(plan.getUploadSpeed()).thenReturn(SPEED_IN_BOUNDS);
    }

    @Test
    public void shouldReturnFalseWhenNullName() {
        when(plan.getName()).thenReturn(null);
        Assert.assertFalse(PlanValidator.isValidPlan(plan));
    }

    @Test
    public void shouldReturnFalseWhenEmptyName() {
        when(plan.getName()).thenReturn(EMPTY);
        Assert.assertFalse(PlanValidator.isValidPlan(plan));
    }

    @Test
    public void shouldReturnFalseWhenNullDescription() {
        when(plan.getDescription()).thenReturn(null);
        Assert.assertFalse(PlanValidator.isValidPlan(plan));
        verify(plan).getName();
    }

    @Test
    public void shouldReturnFalseWhenEmptyDescription() {
        when(plan.getDescription()).thenReturn(EMPTY);
        Assert.assertFalse(PlanValidator.isValidPlan(plan));
        verify(plan).getName();
    }

    @Test
    public void shouldReturnFalseWhenDownloadSpeedLessThanOne() {
        when(plan.getDownloadSpeed()).thenReturn(SPEED_LESS_THAN_ONE);
        Assert.assertFalse(PlanValidator.isValidPlan(plan));
        verify(plan).getName();
        verify(plan).getDescription();
    }

    @Test
    public void shouldReturnFalseWhenDownloadSpeedMoreThanHundred() {
        when(plan.getDownloadSpeed()).thenReturn(SPEED_MORE_THAN_HUNDRED);
        Assert.assertFalse(PlanValidator.isValidPlan(plan));
        verify(plan).getName();
        verify(plan).getDescription();
    }

    @Test
    public void shouldReturnFalseWhenUploadSpeedLessThanOne() {
        when(plan.getUploadSpeed()).thenReturn(SPEED_LESS_THAN_ONE);
        Assert.assertFalse(PlanValidator.isValidPlan(plan));
        verify(plan).getName();
        verify(plan).getDescription();
        verify(plan).getDownloadSpeed();
    }

    @Test
    public void shouldReturnFalseWhenUploadSpeedMoreThanHundred() {
        when(plan.getUploadSpeed()).thenReturn(SPEED_MORE_THAN_HUNDRED);
        Assert.assertFalse(PlanValidator.isValidPlan(plan));
        verify(plan).getName();
        verify(plan).getDescription();
        verify(plan).getDownloadSpeed();
    }

    @Test
    public void shouldReturnFalseWhenPriceLessThanZero() {
        when(plan.getPrice()).thenReturn(PRICE_LESS_THAN_ZERO);
        Assert.assertFalse(PlanValidator.isValidPlan(plan));
        verify(plan).getName();
        verify(plan).getDescription();
        verify(plan).getDownloadSpeed();
        verify(plan).getUploadSpeed();
    }

    @Test
    public void shouldReturnFalseWhenPriceIsZero() {
        when(plan.getPrice()).thenReturn(PRICE_EQUALS_ZERO);
        Assert.assertFalse(PlanValidator.isValidPlan(plan));
        verify(plan).getName();
        verify(plan).getDescription();
        verify(plan).getDownloadSpeed();
        verify(plan).getUploadSpeed();
    }

    @Test
    public void shouldReturnTrueWhenAllParametersCorrect() {
        when(plan.getPrice()).thenReturn(PRICE_MORE_THAN_ZERO);
        Assert.assertTrue(PlanValidator.isValidPlan(plan));
        verify(plan).getName();
        verify(plan).getDescription();
        verify(plan).getDownloadSpeed();
        verify(plan).getUploadSpeed();
    }
}
