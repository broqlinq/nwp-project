package raf.nwp.aircompany.dtos;

import javax.validation.constraints.NotNull;

public record TicketFlightDto(
        Long id,
        @NotNull
        String origin,
        @NotNull
        String destination
) {
}
