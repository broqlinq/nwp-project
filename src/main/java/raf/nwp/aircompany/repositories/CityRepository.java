package raf.nwp.aircompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raf.nwp.aircompany.models.City;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findCityByName(String name);
}
