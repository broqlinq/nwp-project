package raf.nwp.aircompany.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.nwp.aircompany.dtos.CompanyDto;
import raf.nwp.aircompany.exceptions.ExistingResourceException;
import raf.nwp.aircompany.exceptions.NotFoundException;
import raf.nwp.aircompany.services.CompanyService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "company")
@CrossOrigin("*")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(path = "all")
    public ResponseEntity<?> getAllCompanies() {
        var allCompanies = companyService.findAllCompanies();
        return ResponseEntity.ok(allCompanies);
    }

    @GetMapping(path = "get")
    public ResponseEntity<?> getCompanyByName(@RequestParam(name = "name") String name) {
        var company = companyService.findCompanyByName(name);
        return ResponseEntity.of(company);
    }

    @PostMapping
    public ResponseEntity<?> createCompany(@Valid @RequestBody CompanyDto companyDto) {
        try {
            return ResponseEntity.ok(companyService.createCompany(companyDto));
        } catch (ExistingResourceException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCompany(@Valid @RequestBody CompanyDto companyDto) {
        try {
            return ResponseEntity.ok(companyService.updateCompany(companyDto));
        } catch (NotFoundException e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCompany(@Valid @RequestBody CompanyDto companyDto) {
        try {
            return ResponseEntity.ok(companyService.deleteCompany(companyDto));
        } catch (NotFoundException e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
