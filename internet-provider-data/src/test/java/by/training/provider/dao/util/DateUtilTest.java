package by.training.provider.dao.util;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilTest {

    @Test
    public void sqlToJavaDateTest() {
        Class expected = java.util.Date.class;
        Class actual = DateUtil.sqlToJavaDate(new java.sql.Date(0)).getClass();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void javaToSqlDateTest() {
        Class expected = java.sql.Date.class;
        Class actual = DateUtil.javaToSqlDate(new java.util.Date()).getClass();
        Assert.assertEquals(expected, actual);
    }
}
