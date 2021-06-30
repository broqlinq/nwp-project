package raf.nwp.aircompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raf.nwp.aircompany.models.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
