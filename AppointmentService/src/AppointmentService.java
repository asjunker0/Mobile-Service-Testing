import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentService {
	// Creates list of appointments
	private final List<Appointment> appointments = new ArrayList<>();
	
	// Add an appointment to list
	public void addAppointment(Appointment appointment) {
		// Verifies that ID is unique
		for (Appointment a : appointments) {
			if (a.getAppointmentID().equals(appointment.getAppointmentID())) {
				throw new IllegalArgumentException("Appointment ID already exists");
			}
		}
		appointments.add(appointment);
	}

	// Delete an appointment from list
	public void deleteAppointment(String appointmentID) {
		boolean removed = appointments.removeIf(a -> a.getAppointmentID().equals(appointmentID));
		if (!removed) {
			throw new IllegalArgumentException("Appointment not found");
		}
	}

    // Update an appointment date and/or description
    public void updateAppointment (String appointmentID, Date newDate, String newDescription){
        if (appointmentID == null || appointmentID.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment ID is required");
        }
        // Find the appointment by ID
        Appointment appointmentToUpdate = null;
        for (Appointment a : appointments) {
            if (a.getAppointmentID().equals(appointmentID)) {
                appointmentToUpdate = a;
                break;
            }
        }

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
