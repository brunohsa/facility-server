package br.com.facility.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "INCOME")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "FINANCE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_INCOME_FINANCE"))
    @NotNull
    private Finance finance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Finance getFinance() {
        return finance;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
    }
}
