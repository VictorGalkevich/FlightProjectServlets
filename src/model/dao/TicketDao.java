package model.dao;

import model.utility.ConnectionManager;
import model.entity.Ticket;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Long, Ticket>{

    private static final TicketDao INSTANCE = new TicketDao();

    public static final String GET_ALL_BY_FLIGHT_ID = "SELECT * FROM ticket WHERE flight_id = ?";

    private TicketDao(){}

    public static TicketDao getInstance(){
        return INSTANCE;
    }

    public List<Ticket> getAllByFlightId(Long flightId){
        try(var connection = ConnectionManager.getConnection();
            var statement = connection.prepareStatement(GET_ALL_BY_FLIGHT_ID)) {
            statement.setLong(1, flightId);

            var resultSet = statement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                tickets.add(buildTicket(resultSet));
            }
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Ticket buildTicket(ResultSet resultSet) throws SQLException {
        return new Ticket(
                resultSet.getLong("id"),
                resultSet.getString("passenger_no"),
                resultSet.getString("passenger_name"),
                resultSet.getLong("flight_id"),
                resultSet.getString("seat_no"),
                resultSet.getObject("cost", BigDecimal.class)
        );
    }

    @Override
    public List<Ticket> getAll() {
        return null;
    }

    @Override
    public Optional<Ticket> getById() {
        return Optional.empty();
    }

    @Override
    public int delete(Ticket ticket) {
        return 0;
    }

    @Override
    public int update(Ticket ticket) {
        return 0;
    }

    @Override
    public int insert(Ticket ticket) {
        return 0;
    }
}
