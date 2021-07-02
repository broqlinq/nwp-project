package raf.nwp.aircompany.models;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Company company;

    @ManyToOne(optional = false)
    private Flight flight;

    @Column(nullable = false)
    private OffsetDateTime departureDate;

    private OffsetDateTime returnDate;

    @Column(nullable = false)
    private Boolean oneWay;

    @Column(nullable = false)
    private Long count;

    public Ticket() {
    }

    public Ticket(Long id, Company company, Flight flight, OffsetDateTime departureDate, OffsetDateTime returnDate, Boolean oneWay, Long count) {
        this.id = id;
        this.company = company;
        this.flight = flight;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.oneWay = oneWay;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public OffsetDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(OffsetDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public OffsetDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(OffsetDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getOneWay() {
        return oneWay;
    }

    public void setOneWay(Boolean oneWay) {
        this.oneWay = oneWay;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
