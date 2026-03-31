import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppointmentService {
	// Creates hash map of appointments keyed by ID
	private final Map<String, Appointment> appointments = new HashMap<>();
	
	// Add an appointment to map
	public void addAppointment(Appointment appointment) {
        if (appointment == null || appointment.getAppointmentID() == null) {
            throw new IllegalArgumentException("Appointment and appointment ID are required");
        }

        if (appointments.containsKey(appointment.getAppointmentID())) {
            throw new IllegalArgumentException("Appointment ID already exists");
        }

        appointments.put(appointment.getAppointmentID(), appointment);
    }

    // Retrieve an appointment by ID
    public Appointment getAppointment(String appointmentID) {
        return appointments.get(appointmentID);
    }

	// Delete an appointment from list
	public void deleteAppointment(String appointmentID) {
		if (appointments.remove(appointmentID) == null) {
            throw new IllegalArgumentException("Appointment not found");
        }
	}

    // Update an appointment date and/or description
    public void updateAppointment (String appointmentID, Date newDate, String newDescription){
        if (appointmentID == null || appointmentID.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment ID is required");
        }
        // Find the appointment by ID
        Appointment appointmentToUpdate = appointments.get(appointmentID);

        // If appointment not found, throw exception
        if (appointmentToUpdate == null) {
            throw new IllegalArgumentException("Appointment not found");
        }

        // Update date
        if (newDate != null) {
            appointmentToUpdate.setDate(newDate);
        }

        // Update description
        if (newDescription != null) {
            appointmentToUpdate.setDescription(newDescription);
        }

        // If fields are null
        if (newDate == null && (newDescription == null || newDescription.trim().isEmpty())) {
            throw new IllegalArgumentException("Nothing to update");
        }
    }
}
