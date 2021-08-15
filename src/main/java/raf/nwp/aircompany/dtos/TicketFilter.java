package raf.nwp.aircompany.dtos;

import java.time.OffsetDateTime;

public record TicketFilter(
        String origin,
        String destination,
        OffsetDateTime departureDate,
        OffsetDateTime returnDate,
        String company,
        Boolean oneWay,
        int page,
        int count
) {
}
