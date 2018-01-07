package by.training.provider.dao.util;

/**
 * Converts java.sql.Date to java.util.Date and vice versa.
 */
public class DateUtil {

    /**
     * Converts java.sql.Date to java.util.Date.
     *
     * @param dateSql to convert.
     * @return java.util.Date.
     */
    public static java.util.Date sqlToJavaDate(java.sql.Date dateSql) {
        long time = dateSql.getTime();
        return new java.util.Date(time);
    }

    /**
     * Converts java.util.Date to java.sql.Date.
     *
     * @param dateJava to convert.
     * @return java.sql.Date.
     */
    public static java.sql.Date javaToSqlDate(java.util.Date dateJava) {
        long time = dateJava.getTime();
        return new java.sql.Date(time);
    }
}
