package raf.nwp.aircompany.dtos;

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
        String companyName
) {
}
