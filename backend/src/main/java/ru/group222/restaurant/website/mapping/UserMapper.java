package ru.group222.restaurant.website.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.group222.restaurant.website.dto.request.UserRegisterDto;
import ru.group222.restaurant.website.config.MapstructConfig;
import ru.group222.restaurant.website.dto.response.UserDto;
import ru.group222.restaurant.website.model.User;

import java.util.Locale;

@Mapper(config = MapstructConfig.class, imports = {Locale.class})
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", expression = "java(userRegisterDto.email().toLowerCase(Locale.ROOT))")
    @Mapping(target = "passwordHash", expression = "java(passwordEncoder.encode(userRegisterDto.password()))")
    @Mapping(target = "phoneNumber", ignore = true)
    User userRegisterDtoToUser(UserRegisterDto userRegisterDto, PasswordEncoder passwordEncoder);

    @Mapping(target = "password", source = "passwordHash")
    UserDto userToUserDto(User user);
}
