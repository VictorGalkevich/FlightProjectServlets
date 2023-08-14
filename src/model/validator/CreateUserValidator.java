package model.validator;

import model.dto.CreateUserDto;
import model.entity.Gender;
import model.utility.LocalDateFormatter;

public class CreateUserValidator implements Validator<CreateUserDto>{
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();
    @Override
    public ValidationResult isValid(CreateUserDto object) {
        var result = new ValidationResult();
        if (!LocalDateFormatter.isValid(object.getBirthday())) {
            result.add(Error.of("invalid.birthday", "Bithday date is invalid"));
        }
        if (object.getGender() == null || Gender.valueOf(object.getGender()) == null) {
            result.add(Error.of("invalid.gender", "Gender is invalid"));
        }
        return result;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
