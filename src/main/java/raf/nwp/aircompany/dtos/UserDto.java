package raf.nwp.aircompany.dtos;

import raf.nwp.aircompany.models.User;

public record UserDto(Long id, String username, User.Type type) {
}
