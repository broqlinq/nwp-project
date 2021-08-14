package raf.nwp.aircompany.dtos;

public record BookingDto(
        Long id,
        Boolean available,
        Integer count,
        TicketDto ticket,
        UserDto user
) {
}
