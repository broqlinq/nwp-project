package raf.nwp.aircompany.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private City origin;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private City destination;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    public Flight() {
    }

    public Flight(Long id, City origin, City destination, List<Ticket> tickets) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.tickets = tickets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", origin=" + origin +
                ", destination=" + destination +
                '}';
    }
}
