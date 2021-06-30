package raf.nwp.aircompany.services;

import raf.nwp.aircompany.dtos.UserDto;
import raf.nwp.aircompany.models.User;

public class Mappers {

    public static UserDto userToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getType());
    }
}
