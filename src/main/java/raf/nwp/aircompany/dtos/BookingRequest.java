package raf.nwp.aircompany.dtos;

public record BookingRequest(
        String username,
        Long ticketId,
        Integer count
) {
}
