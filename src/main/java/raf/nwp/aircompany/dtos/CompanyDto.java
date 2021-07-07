package raf.nwp.aircompany.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record CompanyDto(
        Long id,
        @NotNull
        @Length(max = 100, message = "Company name length cannot exceed more than 100 characters")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*)[A-Za-z]*$")
        String name
) {
}
