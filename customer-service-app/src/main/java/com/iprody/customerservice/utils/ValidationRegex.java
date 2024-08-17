package com.iprody.customerservice.utils;

public final class ValidationRegex {

    public static final String EMAIL_REGEX = "^(?=.{1,256})(?=.{1,64}@.{1,255}$)(?=.{1,63}"
            + "\\.[A-Za-z]{2,63}$)(?!.*[.]{2})[A-Za-z0-9+_.-]+"
            + "@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public static final String TELEGRAM_ID_REGEX = "^@[a-zA-Z0-9_]{4,31}$";

    public static final String COUNTRY_CODE_REGEX = "^[A-Z]{3}$";
}
