package br.com.zitrus.procedurecontrol.model;

import br.com.zitrus.procedurecontrol.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rule {

    private Long id;
    private Proceduresql operation;
    private int age;
    private GenderEnum gender;
}
