package br.com.zitrus.procedurecontrol.model;

import br.com.zitrus.procedurecontrol.enums.AuthorizationEnum;
import br.com.zitrus.procedurecontrol.enums.GenderEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
}
