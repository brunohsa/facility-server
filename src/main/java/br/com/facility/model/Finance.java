package br.com.facility.model;

import br.com.facility.model.enuns.FinanceSituation;
import br.com.facility.model.enuns.PaymentType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "FINANCE")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VALUE", nullable = false)
    @NotNull
    private BigDecimal value;

    @Column(name = "RELEASE_DATE", nullable = false)
    @NotNull
    private LocalDateTime releaseDate;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_FINANCE"))
    @NotNull
    private User user;

    @Column(name = "DESCRIPTION", nullable = false)
    @NotNull
    private String description;

    @Column(name = "OBSERVATION")
    private String observation;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_TYPE", nullable = false)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    @NotNull
    private FinanceSituation situation;

    public Finance() {
    }

    public Finance(BigDecimal value, User user, String description, String observation, PaymentType paymentType,
            FinanceSituation situation) {
        this.value = value;
        this.releaseDate = LocalDateTime.now();
        this.user = user;
        this.description = description;
        this.observation = observation;
        this.paymentType = paymentType;
        this.situation = situation;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public FinanceSituation getSituation() {
        return situation;
    }

    public void setSituation(FinanceSituation situation) {
        this.situation = situation;
    }

    public boolean isPayd() {
        return this.situation.equals(FinanceSituation.PAID);
    }
}
