package br.com.zitrus.procedurecontrol.enums;

import java.util.Arrays;

public enum AuthorizationEnum {

    ALLOWED("A", "ALLOWED"),
    NOT_ALLOWED("N", "NOT ALLOWED"),
    CANCELLED("C","CANCELLED");

    private final String value;
    private final String description;

    AuthorizationEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue(){
        return value;
    }

    public String getDescription(){
        return description;
    }


    public static AuthorizationEnum fromValue(String value) {
        return Arrays.stream(AuthorizationEnum.values())
                .filter(a -> a.value.equals(value))
                .findFirst()
                .orElse(null);
    }
}
