package br.com.zitrus.procedurecontrol.model;

import br.com.zitrus.procedurecontrol.enums.GenderEnum;

public class Rule {

    private Long id;
    private Proceduresql proceduresql;
    private int age;
    private GenderEnum gender;

    public Rule(Long id, Proceduresql proceduresql, int age, GenderEnum gender) {
        this.id = id;
        this.proceduresql = proceduresql;
        this.age = age;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Proceduresql getProcedure() {
        return proceduresql;
    }

    public void setProcedure(Proceduresql proceduresql) {
        this.proceduresql = proceduresql;
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
