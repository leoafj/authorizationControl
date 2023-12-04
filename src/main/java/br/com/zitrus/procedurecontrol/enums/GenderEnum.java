package br.com.zitrus.procedurecontrol.enums;

import java.util.Arrays;

public enum GenderEnum {

    MALE("M","MALE"),
    FEMALE("F","FEMALE");


    GenderEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    private final String value;
    private final String description;

    public String getValue(){
        return value;
    }

    public String getDescription(){
        return description;
    }

    public static GenderEnum fromValue(String value) {
        return Arrays.stream(GenderEnum.values())
                .filter(g -> g.value.equals(value))
                .findFirst()
                .orElse(null);
    }

}
