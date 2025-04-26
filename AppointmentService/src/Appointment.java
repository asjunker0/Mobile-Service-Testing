import java.util.Date;

public class Appointment {
	// Attributes
	private final String appointmentID;
	private Date date;
	private String description;
	
	// Constructor
	public Appointment(String appointmentID, Date date, String description){
		// Constraints
		if (appointmentID == null || appointmentID.length() > 10) {
			throw new IllegalArgumentException("Invalid appointment ID, cannot be null or greater than 10 characters");
		}
		if (date == null || date.before(new Date())) {
			throw new IllegalArgumentException("Invalid date, cannot be in the past");
		}
		if (description == null || description.length() > 50) {
			throw new IllegalArgumentException("Invalid description, cannot be null or greater than 50 characters");
		}
		
		this.appointmentID = appointmentID;
		this.date = date;
		this.description = description;
	}
	
	// Only getter, its unable to be edited
	// appintmentID
	public String getAppointmentID() {
		return appointmentID;
	}
	
	// Getters and setters
	// date
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date newDate) {
		if (newDate == null || newDate.before(new Date())) {
			throw new IllegalArgumentException("Invalid date, cannot be in the past");
		}
		this.date = newDate;
	}
	
	// description
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String newDescription) {
		if (newDescription == null || newDescription.length() > 50) {
			throw new IllegalArgumentException("Invalid description, cannot be null or greater than 50 characters");
		}
		this.description = newDescription;
	}
}
