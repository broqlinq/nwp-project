package raf.nwp.aircompany.dtos;

import java.time.OffsetDateTime;

public record TicketDto(
        OffsetDateTime departureDate,
        OffsetDateTime returnDate,
        TicketFlightDto flight,
        String company,
        Long count
) {
}
