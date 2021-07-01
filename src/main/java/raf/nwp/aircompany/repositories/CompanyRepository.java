package raf.nwp.aircompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raf.nwp.aircompany.models.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findCompanyByName(String name);
}
