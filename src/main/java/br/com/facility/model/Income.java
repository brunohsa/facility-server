package br.com.facility.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "INCOME")
public class Income extends Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
