import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppointmentService {
	// Creates hash map of appointments keyed by ID
	private final Map<String, Appointment> appointments = new HashMap<>();
    private final String databaseUrl;

    // Default constructor keeps existing in-memory behavior
    public AppointmentService() {
        this.databaseUrl = null;
    }

    // Database-backed constructor
    public AppointmentService(String databaseURL){
        if (databaseURL == null || databaseURL.isBlank()){
            throw new IllegalArgumentException("Database URL is required");
        }
    }

	// Add an appointment to map
	public void addAppointment(Appointment appointment) {
        if (appointment == null || appointment.getAppointmentID() == null) {
            throw new IllegalArgumentException("Appointment and appointment ID are required");
        }

        if (appointments.containsKey(appointment.getAppointmentID())) {
            throw new IllegalArgumentException("Appointment ID already exists");
        }

        appointments.put(appointment.getAppointmentID(), appointment);
        saveAppointment(appointment);
    }

    // Retrieve an appointment by ID
    public Appointment getAppointment(String appointmentID) {
        Appointment cached = appointments.get(appointmentID);
        if (cached != null) {
            return cached;
        }

        Appointment stored = getAppointmentById(appointmentID);
        if (stored != null) {
            appointments.put(stored.getAppointmentID(), stored);
        }
        return stored;
    }

	// Delete an appointment from cache and database
	public void deleteAppointment(String appointmentID) {
        Appointment removed = appointments.remove(appointmentID);
        boolean dbDeleted = deleteAppointmentFromDatabase(appointmentID);

		if (removed ==null && !dbDeleted) {
            throw new IllegalArgumentException("Appointment not found");
        }
	}

    // Update an appointment date and/or description and persist when DB mode is enabled
    public void updateAppointment(String appointmentID, Date newDate, String newDescription) {
        if (appointmentID == null || appointmentID.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment ID is required");
        }
        if (newDate == null && (newDescription == null || newDescription.trim().isEmpty())) {
            throw new IllegalArgumentException("Nothing to update");
        }

        Appointment appointmentToUpdate = getAppointment(appointmentID);
        if (appointmentToUpdate == null) {
            throw new IllegalArgumentException("Appointment not found");
        }

        if (newDate != null) {
            appointmentToUpdate.setDate(newDate);
        }
        if (newDescription != null && !newDescription.trim().isEmpty()) {
            appointmentToUpdate.setDescription(newDescription);
        }

        saveAppointment(appointmentToUpdate);
    }

    // Create appointment table when DB mode is enabled
    private void createAppointmentsTable() {
        if (!isDatabaseEnabled()) {
            return;
        }

        String sql = "CREATE TABLE IF NOT EXISTS appointment ("
                + "id TEXT PRIMARY KEY,"
                + "date TEXT NOT NULL,"
                + "description TEXT NOT NULL"
                + ")";

        try (Connection connection = connectToDatabase(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to create appointment table", e);
        }
    }

    // Save appointment into database when configured
    private void saveAppointment(Appointment appointment) {
        if (!isDatabaseEnabled()) {
            return;
        }

        String sql = "INSERT OR REPLACE INTO appointment(id, date, description) VALUES (?, ?, ?)";

        try (Connection connection = connectToDatabase();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, appointment.getAppointmentID());
            statement.setString(2, String.valueOf(appointment.getDate().getTime()));
            statement.setString(3, appointment.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to save appointment", e);
        }
    }

    // Retrieve appointment by ID directly from database
    private Appointment getAppointmentById(String appointmentID) {
        if (!isDatabaseEnabled() || appointmentID == null) {
            return null;
        }

        String sql = "SELECT id, date, description FROM appointment WHERE id = ?";

        try (Connection connection = connectToDatabase(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, appointmentID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }

                String id = resultSet.getString("id");
                long millis = Long.parseLong(resultSet.getString("date"));
                String description = resultSet.getString("description");
                return new Appointment(id, new java.util.Date(millis), description);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to retrieve appointment", e);
        }
    }

    // Delete appointment from database by ID
    private boolean deleteAppointmentFromDatabase(String appointmentID) {
        if (!isDatabaseEnabled() || appointmentID == null) {
            return false;
        }

        String sql = "DELETE FROM appointment WHERE id = ?";

        try (Connection connection = connectToDatabase(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, appointmentID);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to delete appointment", e);
        }
    }

    private Connection connectToDatabase() throws SQLException {
        return DriverManager.getConnection(databaseUrl);
    }

    private boolean isDatabaseEnabled() {
        return databaseUrl != null;
    }
}
