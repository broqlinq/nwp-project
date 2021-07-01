package raf.nwp.aircompany.dtos;

import java.time.OffsetDateTime;

public record FlightTicketDto(
    String company,
    OffsetDateTime departureDate,
    OffsetDateTime returnDate,
    Long count
) {
}
