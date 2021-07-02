package raf.nwp.aircompany.dtos;

import raf.nwp.aircompany.models.User;

public record UserJwtResponse(String token, String username, User.Type type) {
}
