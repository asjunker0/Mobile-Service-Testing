
public class Contact {
	// Attributes
	private final String contactID;
	private String firstName;
	private String lastName;
	private String number;
	private String address;
	
	// Constructor
	public Contact(String contactID, String firstName, String lastName, String number, String address){
		// Constraints
		if (contactID == null || contactID.length() > 10) {
			throw new IllegalArgumentException("Invalid contact ID, cannot be null or greater than 10 characters");
		}
		if (firstName == null || firstName.length() > 10) {
			throw new IllegalArgumentException("Invalid first name, cannot be null or greater than 10 characters");
		}
		if (lastName == null || lastName.length() > 10) {
			throw new IllegalArgumentException("Invalid last name, cannot be null or greater than 10 characters");
		}
		if (number == null || number.length() != 10) {
			throw new IllegalArgumentException("Invalid number, must be 10 characters");
		}
		if (address == null || address.length() > 30) {
			throw new IllegalArgumentException("Invalid address, cannot be null or greater than 30 characters");
		}
		
		this.contactID = contactID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.address = address;
	}
	
	// Only getter, its unable to be edited
	public String getContactID() {
		return contactID;
	}
	
	// Getters and setters
	// firstName
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		if(firstName == null || firstName.length() > 10) {
			throw new IllegalArgumentException("Invalid first name, cannot be null or greater than 10 characters");
		}
		this.firstName = firstName;
	}
	// lastName
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		if(lastName == null || lastName.length() > 10) {
			throw new IllegalArgumentException("Invalid last name, cannot be null or greater than 10 characters");
		}
		this.lastName = lastName;
	}	
	// number
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		if(number == null || number.length() != 10) {
			throw new IllegalArgumentException("Invalid number , must be 10 characters");
		}
		this.number = number;
	}	
	// address
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		if(address == null || address.length() > 30) {
			throw new IllegalArgumentException("Invalid address, cannot be null or greater than 30 characters");
		}
		this.address = address;
	}
}
