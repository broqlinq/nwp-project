package raf.nwp.aircompany.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

public record TicketForm(
        @NotNull
        OffsetDateTime departureDate,
        OffsetDateTime returnDate,
        @Min(value = 1, message = "Ticket count must be positive integer")
        Integer count,
        @NotNull
        Long companyId,
        @NotNull
        Long flightId
) {
}
