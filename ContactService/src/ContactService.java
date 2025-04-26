import java.util.ArrayList;
import java.util.List;

public class ContactService {
	// Creates list of contacts
	private List<Contact> contacts = new ArrayList<>();
	
	// Add a contact to list
	public void addContact(Contact contact) {
		// Verifies that ID is unique
		for (Contact c : contacts) {
			if (c.getContactID().equals(contact.getContactID())) {
				throw new IllegalArgumentException("Contact ID already exists");
			}
		}
		contacts.add(contact);
	}
	
	// Delete a contact from list
	public void deleteContact(String contactID) {
		boolean removed = contacts.removeIf(c -> c.getContactID().equals(contactID));
		if (!removed) {
			throw new IllegalArgumentException("Contact not found");
		}
	}
	
	// Update firstName
	public void updateFirstName(String contactID, String newFirstName) {
		Contact contact = findContactByID(contactID);
		contact.setFirstName(newFirstName);
	}
	
	// Update lastName
	public void updateLastName(String contactID, String newLastName) {
		Contact contact = findContactByID(contactID);
		contact.setLastName(newLastName);
	}
	
	// Update number
	public void updateNumber(String contactID, String newNumber) {
		Contact contact = findContactByID(contactID);
		contact.setNumber(newNumber);
	}
	
	// Update address
	public void updateAddress(String contactID, String newAddress) {
		Contact contact = findContactByID(contactID);
		contact.setAddress(newAddress);
	}
	
	// Method to lookup contact by ID
	private Contact findContactByID(String contactID) {
		for (Contact c : contacts) {
			if(c.getContactID().equals(contactID)) {
				return c;
			}
		}
		throw new IllegalArgumentException("Contact ID not found");
	}
}
