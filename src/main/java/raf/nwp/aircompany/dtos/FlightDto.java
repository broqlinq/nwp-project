package raf.nwp.aircompany.dtos;

import java.util.List;

public record FlightDto(
    Long id,
    String origin,
    String destination,
    List<FlightTicketDto> tickets
) {
}
