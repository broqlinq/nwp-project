package raf.nwp.aircompany.models;

import javax.persistence.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean available;

    @Column(nullable = false)
    private Integer count;

    @OneToOne(optional = false)
    private Ticket ticket;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn
    private User user;

    public Booking() {
    }

    public Booking(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", available=" + available +
                ", count=" + count +
                ", ticket=" + ticket +
                ", user=" + user.getId() +
                '}';
    }
}
