package model.dao;

import model.utility.ConnectionManager;
import model.entity.Flight;
import model.entity.FlightStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {
    public static final FlightDao INSTANCE = new FlightDao();

    private static final String GET_ALL = "SELECT * FROM flight";
    @Override
    public List<Flight> getAll() {
        try (var connection = ConnectionManager.getConnection();
             var statement = connection.prepareStatement(GET_ALL)) {
            var result = statement.executeQuery();
            List<Flight> flights = new ArrayList<>();
            while (result.next()) {
                flights.add(buildFlight(result));
            }
            return flights;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private FlightDao() {
    }

    public static FlightDao getInstance(){
        return INSTANCE;
    }

    @Override
    public Optional<Flight> getById() {
        return Optional.empty();
    }

    @Override
    public int delete(Flight flight) {
        return 0;
    }

    @Override
    public int update(Flight flight) {
        return 0;
    }

    @Override
    public int insert(Flight flight) {
        return 0;
    }

    private Flight buildFlight(ResultSet result) {
        try {
            return new Flight(
                    result.getLong("id"),
                    result.getString("flight_no"),
                    result.getObject("departure_date", Timestamp.class).toLocalDateTime(),
                    result.getString("departure_airport_code"),
                    result.getObject("arrival_date", Timestamp.class).toLocalDateTime(),
                    result.getString("arrival_airport_code"),
                    result.getLong("aircraft_id"),
                    FlightStatus.valueOf(result.getString("status")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
