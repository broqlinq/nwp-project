package raf.nwp.aircompany.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import raf.nwp.aircompany.dtos.FlightDto;
import raf.nwp.aircompany.dtos.PageResponse;
import raf.nwp.aircompany.exceptions.NotFoundException;
import raf.nwp.aircompany.models.Flight;
import raf.nwp.aircompany.repositories.CityRepository;
import raf.nwp.aircompany.repositories.FlightRepository;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    private final CityRepository cityRepository;

    public FlightService(FlightRepository flightRepository, CityRepository cityRepository) {
        this.flightRepository = flightRepository;
        this.cityRepository = cityRepository;
    }

    public List<FlightDto> findAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(Mappers::flightToDto)
                .toList();
    }

    public List<FlightDto> findAllFlightsBy(String origin, String destination) {
        return flightRepository.findFlightsByOriginAndDestination(origin, destination)
                .stream()
                .map(Mappers::flightToDto)
                .toList();
    }

    public FlightDto createFlight(FlightDto flightDto) {
        var origin = cityRepository.findCityByName(flightDto.origin())
                .orElseThrow(() -> new NotFoundException("No city with name `" + flightDto.origin() + "` was found"));

        var destination = cityRepository.findCityByName(flightDto.destination())
                .orElseThrow(() -> new NotFoundException("No city with name `" + flightDto.destination() + "` was found"));

        var flight = new Flight(null, origin, destination, List.of());
        flight = flightRepository.save(flight);
        return Mappers.flightToDto(flight);
    }

    public FlightDto deleteFlight(Long id) {
        var flight = flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No flight with id `" + id + "` was found"));

        flightRepository.delete(flight);
        return Mappers.flightToDto(flight);
    }

    public PageResponse<FlightDto> findFlights(int page, int count) {
        var pageable = PageRequest.of(page, count);
        var flights= flightRepository.findAll(pageable)
                .map(Mappers::flightToDto);
        return PageResponse.of(flights);
    }

    public PageResponse<FlightDto> findFlightsByOriginAndDestination(int page, int count, String origin, String destination) {
        var pageable = PageRequest.of(page, count);
        var flights = flightRepository.findFlightsByOriginAndDestination(origin, destination, pageable)
                .map(Mappers::flightToDto);
        return PageResponse.of(flights);
    }
}
