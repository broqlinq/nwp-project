package raf.nwp.aircompany.dtos;

import raf.nwp.aircompany.models.User;

public record UserDto(Long id, String username, User.Type type) {

    public static UserDto fromUser(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getType());
    }
}
