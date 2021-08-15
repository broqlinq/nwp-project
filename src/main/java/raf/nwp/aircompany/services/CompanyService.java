package raf.nwp.aircompany.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raf.nwp.aircompany.dtos.CompanyDto;
import raf.nwp.aircompany.exceptions.ExistingResourceException;
import raf.nwp.aircompany.exceptions.NotFoundException;
import raf.nwp.aircompany.repositories.CompanyRepository;
import raf.nwp.aircompany.repositories.TicketRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final TicketRepository ticketRepository;

    public CompanyService(CompanyRepository companyRepository, TicketRepository ticketRepository) {
        this.companyRepository = companyRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<CompanyDto> findAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(Mappers::companyToDto)
                .toList();
    }

    public Optional<CompanyDto> findCompanyByName(String name) {
        return companyRepository.findCompanyByName(name)
                .map(Mappers::companyToDto);
    }

    public CompanyDto createCompany(CompanyDto companyDto) {
        var company = companyRepository.findCompanyByName(companyDto.name());
        if (company.isPresent())
            throw new ExistingResourceException("Company with name `" + companyDto.name() + "` already exists");

        var newCompany = Mappers.dtoToCompany(companyDto);
        companyRepository.save(newCompany);
        return companyDto;
    }

    public CompanyDto updateCompany(CompanyDto companyDto) {
        var company = companyRepository.findById(companyDto.id())
                .orElseThrow(() -> new NotFoundException("No company with name `" + companyDto.name() + "` was found"));

        if (company.getName().equals(companyDto.name())) {
            return companyDto;
        }

        company.setName(companyDto.name());
        companyRepository.save(company);
        return companyDto;
    }

    @Transactional
    public CompanyDto deleteCompany(Long id) {
        var company = companyRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("No company with id `" + id + "` was found"));

        this.ticketRepository.deleteAllByCompany(company);

        companyRepository.delete(company);
        return Mappers.companyToDto(company);
    }

    @Transactional
    public CompanyDto deleteCompany(CompanyDto companyDto) {
        var company = companyRepository
                .findCompanyByName(companyDto.name())
                .orElseThrow(() -> new NotFoundException("No company with name `" + companyDto.name() + "` was found"));

        this.ticketRepository.deleteAllByCompany(company);

        companyRepository.delete(company);
        return companyDto;
    }
}
