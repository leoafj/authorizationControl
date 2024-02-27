package br.com.zitrus.procedurecontrol.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum GenderEnum {

    MALE("M","MALE"),
    FEMALE("F","FEMALE");


    GenderEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    private final String value;
    private final String description;

    public static GenderEnum fromValue(String value) {
        return Arrays.stream(GenderEnum.values())
                .filter(g -> g.value.equals(value))
                .findFirst()
                .orElse(null);
    }

}
