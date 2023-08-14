package model.service;

import lombok.SneakyThrows;
import model.dao.UserDao;
import model.dto.CreateUserDto;
import model.dto.UserDto;
import model.exception.ValidationException;
import model.mapper.CreateUserMapper;
import model.mapper.UserMapper;
import model.validator.CreateUserValidator;

import java.util.Optional;

public class UserService {
    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator validator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    private UserService(){}

    public Optional<UserDto> login(String email, String password) {
        return userDao.getByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);
    }

    @SneakyThrows
    public Long create(CreateUserDto dto) {
        var validationResult = validator.isValid(dto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var user = createUserMapper.mapFrom(dto);
        imageService.upload(user.getImage(), dto.getImage().getInputStream());
        userDao.insert(user);
        return user.getId();
    //validation
    //map
    //userDao.insert()
    //return id
    }

    public static UserService getInstance(){
        return INSTANCE;
    }
}
