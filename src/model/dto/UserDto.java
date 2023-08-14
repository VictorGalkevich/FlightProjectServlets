package model.dto;

import lombok.Builder;
import lombok.Value;
import model.entity.Gender;
import model.entity.Role;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    Long id;
    String name;
    String email;
    String image;
    LocalDate birthday;
    Role role;
    Gender gender;
}
