package br.com.zitrus.procedurecontrol.model;

import br.com.zitrus.procedurecontrol.enums.AuthorizationEnum;
import br.com.zitrus.procedurecontrol.enums.GenderEnum;

public class Authorization {

    private Long id;
    private String name;
    private Proceduresql proceduresql;
    private AuthorizationEnum authorization;
    private int age;
    private GenderEnum gender;

    public Authorization(Long id, String name, Proceduresql proceduresql, AuthorizationEnum authorization, int age, GenderEnum gender) {
        this.id = id;
        this.name = name;
        this.proceduresql = proceduresql;
        this.authorization = authorization;
        this.age = age;
        this.gender = gender;
    }

    public Authorization(String name, Proceduresql proceduresql, AuthorizationEnum authorization, int age, GenderEnum gender) {
        this.name = name;
        this.proceduresql = proceduresql;
        this.authorization = authorization;
        this.age = age;
        this.gender = gender;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Proceduresql getProcedure() {
        return proceduresql;
    }

    public void setProcedure(Proceduresql proceduresql) {
        this.proceduresql = proceduresql;
    }

    public AuthorizationEnum getAuthorization() {
        return authorization;
    }

    public void setAuthorization(AuthorizationEnum authorization) {
        this.authorization = authorization;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
}
