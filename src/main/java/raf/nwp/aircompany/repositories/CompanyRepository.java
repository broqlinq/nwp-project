package raf.nwp.aircompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raf.nwp.aircompany.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
