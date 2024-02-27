package br.com.zitrus.procedurecontrol.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
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


    public static AuthorizationEnum fromValue(String value) {
        return Arrays.stream(AuthorizationEnum.values())
                .filter(a -> a.value.equals(value))
                .findFirst()
                .orElse(null);
    }
}
