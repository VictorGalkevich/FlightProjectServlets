package model.dao;

import lombok.SneakyThrows;
import model.entity.Gender;
import model.entity.Role;
import model.entity.User;
import model.utility.ConnectionManager;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Long, User> {
    private static final UserDao INSTANCE = new UserDao();
    private static final String SAVE_THE_USER = "INSERT INTO users(name, birthday, email, password, role, gender, image) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE email = ? AND password = ?";

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Optional<User> getById() {
        return Optional.empty();
    }

    @Override
    public int delete(User user) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    @SneakyThrows
    public int insert(User user) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(SAVE_THE_USER)) {
            preparedStatement.setObject(1, user.getName());
            preparedStatement.setObject(2, user.getBirthday());
            preparedStatement.setObject(3, user.getEmail());
            preparedStatement.setObject(4, user.getPassword());
            preparedStatement.setObject(5, user.getRole().name());
            preparedStatement.setObject(6, user.getGender().name());
            preparedStatement.setObject(7, user.getImage());
            return preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public Optional<User> getByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            var resultSet = preparedStatement.executeQuery();
            User user = null;

            if (resultSet.next()) {
                user = buildEntity(resultSet);
            }

            return Optional.empty();
        }
    }

    @SneakyThrows
    private static User buildEntity(ResultSet resultSet) {
        return User.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .birthday(LocalDate.parse(resultSet.getObject("birthday", Date.class).toLocaleString()))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .image(resultSet.getString("image"))
                .role(Role.find(resultSet.getObject("role", String.class)).orElse(null))
                .gender(Gender.valueOf(resultSet.getObject("gender", String.class)))
                .build();
    }


    public static UserDao getInstance(){
        return INSTANCE;
    }
}
