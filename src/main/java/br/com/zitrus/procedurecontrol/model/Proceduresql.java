package br.com.zitrus.procedurecontrol.model;

public class Proceduresql {

    private Long id;
    private String name;

    public Proceduresql(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
