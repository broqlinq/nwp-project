package raf.nwp.aircompany.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import raf.nwp.aircompany.dtos.*;
import raf.nwp.aircompany.models.*;

import java.util.List;

public class Mappers {

    public static UserDto userToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getType());
    }

    public static User formToUser(UserRegisterForm registerForm, PasswordEncoder passwordEncoder) {
        return new User(
                null,
                registerForm.username(),
                passwordEncoder.encode(registerForm.password()),
                registerForm.type(),
                (registerForm.type() == User.Type.REGULAR) ? List.of() : null);
    }

    public static CompanyDto companyToDto(Company company) {
        return new CompanyDto(company.getId(), company.getName());
    }

    public static Company dtoToCompany(CompanyDto companyDto) {
        return new Company(null, companyDto.name());
    }

    public static FlightDto flightToDto(Flight flight) {
        var tickets = flight.getTickets()
                .stream()
                .map(Mappers::ticketToFlightTicketDto)
                .toList();
        return new FlightDto(
                flight.getId(),
                flight.getOrigin().getName(),
                flight.getDestination().getName(),
                tickets);
    }

    private static FlightTicketDto ticketToFlightTicketDto(Ticket ticket) {
        return new FlightTicketDto(
                ticket.getCompany().getName(),
                ticket.getDepartureDate(),
                ticket.getReturnDate(),
                ticket.getCount());
    }

    public static TicketDto ticketToDto(Ticket ticket) {
        var company = ticket.getCompany().getName();
        var flight = flightToTicketFlightDto(ticket.getFlight());
        return new TicketDto(
                ticket.getId(),
                ticket.getDepartureDate(),
                ticket.getReturnDate(),
                flight,
                company,
                ticket.getCount());
    }

    private static TicketFlightDto flightToTicketFlightDto(Flight flight) {
        return new TicketFlightDto(
                flight.getId(),
                flight.getOrigin().getName(),
                flight.getDestination().getName());
    }

    public static BookingDto bookingToDto(Booking booking) {
        return new BookingDto(
                booking.getId(),
                booking.getAvailable(),
                booking.getCount(),
                ticketToDto(booking.getTicket()),
                userToDto(booking.getUser()));
    }
}
