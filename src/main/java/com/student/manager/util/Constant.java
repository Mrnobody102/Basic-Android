package com.student.manager.util;

import com.student.manager.model.Account;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Constant {
    public static String NAME_DATABASE = "student_management_center_english.sqlite_versiontwo";

    public static String ROLE_ADMIN = "admin";
    public static String ROLE_STAFF = "staff";
    public static String ROLE_LECTURER = "lecturer";
    public static String ROLE_STUDENT = "student";


    public static Account accountAdmin01 = new Account(0, "a", "a", ROLE_ADMIN, "okla@gmail.com");

    public static String SLOT_DAY_1 = "Morning - Mon, Tue, Thu, Fri ";
    public static String SLOT_DAY_2 = "Evening - Mon, Wed, Thu, Fri";
    public static String SLOT_DAY_3 = "Afternoon - Mon, Tue, Thu | Morning - Wed";
    public static String SLOT_DAY_4 = "Morning, Evening - Saturday and Sunday";

    public static String startDate = "2023-03-19";
    public static String endDate = "2023-06-19";
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static Date START_DATE;
    public static Date END_DATE;

    static {
        try {
            START_DATE = dateFormat.parse(startDate);
            END_DATE = dateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public Constant() throws ParseException {
    }
}
