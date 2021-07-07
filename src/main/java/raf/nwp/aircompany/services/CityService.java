package raf.nwp.aircompany.services;

import org.springframework.stereotype.Service;
import raf.nwp.aircompany.models.City;
import raf.nwp.aircompany.repositories.CityRepository;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAllCities() {
        return cityRepository.findAll();
    }
}
