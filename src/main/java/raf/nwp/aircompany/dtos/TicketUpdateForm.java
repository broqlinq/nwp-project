package raf.nwp.aircompany.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

public record TicketUpdateForm(
        @NotNull
        Long id,
        @NotNull
        OffsetDateTime departureDate,
        OffsetDateTime returnDate,
        @NotNull
        Long flightId,
        @NotNull
        String companyName,
        @NotNull
        @Min(value = 1, message = "Ticket count must be positive integer")
        Long count
) {
}
