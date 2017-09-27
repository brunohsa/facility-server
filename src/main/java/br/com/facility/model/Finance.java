package br.com.facility.model;

import br.com.facility.model.enuns.PaymentType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "FINANCE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VALUE", nullable = false)
    @NotNull
    private BigDecimal value;

    @Column(name = "RELEASE_DATE", nullable = false)
    @NotNull
    private LocalDateTime releaseDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @NotNull
    private User user;

    @Column(name = "DESCRIPTION", nullable = false)
    @NotNull
    private String description;

    @Column(name = "OBSERVATION")
    private String observation;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_TYPE", nullable = false)
    @NotNull
    private PaymentType paymentType;

    public Finance() {
    }

    public Finance(BigDecimal value, LocalDateTime releaseDate, User user, String description, String observation, PaymentType paymentType) {
        this.value = value;
        this.releaseDate = releaseDate;
        this.user = user;
        this.description = description;
        this.observation = observation;
        this.paymentType = paymentType;
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
}
