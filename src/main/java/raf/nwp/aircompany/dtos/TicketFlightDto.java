package raf.nwp.aircompany.dtos;

import javax.validation.constraints.NotNull;

public record TicketFlightDto(
        @NotNull
        String origin,
        @NotNull
        String destination
) {
}
