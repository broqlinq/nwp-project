package raf.nwp.aircompany.services;

import org.springframework.stereotype.Service;
import raf.nwp.aircompany.dtos.FlightDto;
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
        return flightRepository.filterFlightsByOriginAndDestination(origin, destination)
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
}
