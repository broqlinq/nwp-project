package raf.nwp.aircompany.models;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

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
    private Integer count;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY)
    private List<Booking> booking;

    public Ticket() {
    }

    public Ticket(Long id, Company company, Flight flight, OffsetDateTime departureDate, OffsetDateTime returnDate, Boolean oneWay, Integer count, List<Booking> booking) {
        this.id = id;
        this.company = company;
        this.flight = flight;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.oneWay = oneWay;
        this.count = count;
        this.booking = booking;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> booking) {
        this.booking = booking;
    }
}
