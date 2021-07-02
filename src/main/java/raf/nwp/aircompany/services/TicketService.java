package raf.nwp.aircompany.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import raf.nwp.aircompany.dtos.PageResponse;
import raf.nwp.aircompany.dtos.TicketDto;
import raf.nwp.aircompany.dtos.TicketForm;
import raf.nwp.aircompany.exceptions.NotFoundException;
import raf.nwp.aircompany.models.Ticket;
import raf.nwp.aircompany.repositories.CityRepository;
import raf.nwp.aircompany.repositories.CompanyRepository;
import raf.nwp.aircompany.repositories.FlightRepository;
import raf.nwp.aircompany.repositories.TicketRepository;

import javax.validation.Valid;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final CityRepository cityRepository;

    private final FlightRepository flightRepository;

    private final CompanyRepository companyRepository;

    public TicketService(TicketRepository ticketRepository, CityRepository cityRepository, FlightRepository flightRepository, CompanyRepository companyRepository) {
        this.ticketRepository = ticketRepository;
        this.cityRepository = cityRepository;
        this.flightRepository = flightRepository;
        this.companyRepository = companyRepository;
    }

    public TicketDto createTicket(@Valid TicketForm form) {
        if (form.returnDate().isBefore(form.departureDate()))
            throw new IllegalArgumentException("Return date must be after departure date");

        var company = companyRepository
                .findById(form.companyId())
                .orElseThrow(() -> new NotFoundException("No company with id `" + form.companyId() + "` was found"));

        var flight = flightRepository
                .findById(form.flightId())
                .orElseThrow(() -> new NotFoundException("No flight with id `" + form.flightId() + "` was found"));

        var ticket = new Ticket(null, company, flight, form.departureDate(), form.returnDate(), form.returnDate() == null, form.count());
        ticket = ticketRepository.save(ticket);
        return Mappers.ticketToDto(ticket);
    }

    public PageResponse<TicketDto> findAllTickets(int page, int count) {
        var pageable = PageRequest.of(page, count);
        var tickets = ticketRepository.findAll(pageable)
                .map(Mappers::ticketToDto);
        return PageResponse.of(tickets);
    }

    public PageResponse<TicketDto> findTicketsByCompanyName(String companyName, int page, int count) {
        var pageable = PageRequest.of(page, count);
        var tickets = ticketRepository.findTicketsByCompanyName(companyName, pageable)
                .map(Mappers::ticketToDto);
        return PageResponse.of(tickets);
    }

    public PageResponse<TicketDto> filterTickets(String companyName, Boolean oneWay, int page, int count) {
        var pageable = PageRequest.of(page, count);
        var tickets = ticketRepository
                .findTicketsByCompanyNameAAndOneWay(companyName, oneWay, pageable)
                .map(Mappers::ticketToDto);
        return PageResponse.of(tickets);
    }
}
