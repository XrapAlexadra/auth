package edu.epam.auth.util;

import java.sql.Date;
import java.time.LocalDate;

public class DaoUtil {

    public static Date localDateToDate(LocalDate localDate) {
        Date result = localDate == null ? null : Date.valueOf(localDate);
        return result;
    }
}
