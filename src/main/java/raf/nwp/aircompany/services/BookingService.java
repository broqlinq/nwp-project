package raf.nwp.aircompany.services;

import org.springframework.stereotype.Service;
import raf.nwp.aircompany.dtos.BookingDto;
import raf.nwp.aircompany.exceptions.NotFoundException;
import raf.nwp.aircompany.models.Booking;
import raf.nwp.aircompany.models.Ticket;
import raf.nwp.aircompany.repositories.BookingRepository;
import raf.nwp.aircompany.repositories.TicketRepository;
import raf.nwp.aircompany.repositories.UserRepository;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    private final UserRepository userRepository;

    private final TicketRepository ticketRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, TicketRepository ticketRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    public BookingDto simulateBuyTickets(Long id) {
        var booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No booking with id `" + id + "` was found"));

        var ticketCount = booking.getCount();
        var ticket = booking.getTicket();

        if (ticket.getCount() < ticketCount)
            throw new IllegalStateException("Failed to buy tickets; Amount of available tickets is less then booked tickets.");

        Integer newCount = ticket.getCount() - ticketCount;
        ticket.setCount(newCount);
        ticketRepository.save(ticket);

        bookingRepository.delete(booking);
        return Mappers.bookingToDto(booking);
    }

    public List<BookingDto> simulateBuyTickets(List<Long> ids) {
        var bookings = bookingRepository.findAllById(ids);

        var allValid = bookings.stream()
                .allMatch(booking -> {
                    var ticketCount = booking.getCount();
                    var ticket = booking.getTicket();
                    return ticket.getCount() < ticketCount;
                });

        if (!allValid)
            throw new IllegalStateException("Failed to buy tickets; Amount of available tickets is less then booked tickets.");

        bookings.forEach(booking -> {
            var ticket = booking.getTicket();
            Integer newCount = ticket.getCount() - booking.getCount();
            ticket.setCount(newCount);
            ticketRepository.save(ticket);
            bookingRepository.save(booking);
        });

        return bookings.stream()
                .map(Mappers::bookingToDto)
                .toList();
    }

    public BookingDto deleteBooking(Long id) {
        var booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No booking with id `" + id + "` was found"));

        var departureDate = booking.getTicket().getDepartureDate();
        var now = OffsetDateTime.now();
        var hours = Duration.between(departureDate, now).toHours();

        if (hours < 24)
            throw new IllegalStateException("Delete booking failed; Booking can be deleted at least 24 hours before time of departure.");

        bookingRepository.delete(booking);
        return Mappers.bookingToDto(booking);
    }

    public List<BookingDto> findBookingsByUser(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("No user with username `" + username + "` was found"));

        var bookings = bookingRepository.findAllByUser(user);
        bookings.forEach(booking -> {
            var now = OffsetDateTime.now();
            var departs = booking.getTicket().getDepartureDate();
            booking.setAvailable(now.isBefore(departs));
            bookingRepository.save(booking);
        });

        return bookings.stream()
                .map(Mappers::bookingToDto)
                .toList();
    }

    public BookingDto createBookingForUser(String username, Long ticketId, Integer count) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("No user with username `" + username + "` was found"));

        var ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("No ticket with id `" + ticketId + "` was found"));

        var booking = bookingRepository.findByUserAndTicket(user, ticket);

        Booking b;
        if (booking.isPresent()) {
            b = booking.get();
            var countSum = b.getCount() + count;
            Integer available = ticket.getCount();
            b.setCount(Math.min(countSum, available));
            bookingRepository.save(b);
        } else {
            b = new Booking(null, true, Math.min(ticket.getCount(), count), ticket, user);
            b = bookingRepository.save(b);
        }
        return Mappers.bookingToDto(b);
    }
}
