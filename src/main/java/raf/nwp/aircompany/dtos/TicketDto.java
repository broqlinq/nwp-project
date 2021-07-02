package raf.nwp.aircompany.dtos;

import java.time.OffsetDateTime;

public record TicketDto(
        Long id,
        OffsetDateTime departureDate,
        OffsetDateTime returnDate,
        TicketFlightDto flight,
        String company,
        Long count
) {
}
