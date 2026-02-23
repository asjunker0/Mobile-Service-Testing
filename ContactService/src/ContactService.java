import java.util.HashMap;
import java.util.Map;

public class ContactService {
	// Creates hash map of contacts keyed by ID
	private final Map<String, Contact> contacts = new HashMap<>();
	
	// Add a contact to map
	public void addContact(Contact contact) {
        if (contact == null || contact.getContactID() == null) {
            throw new IllegalArgumentException("Contact and contact ID are required");
        }

        if (contacts.containsKey(contact.getContactID())) {
            throw new IllegalArgumentException("Contact ID already exists");
        }

        contacts.put(contact.getContactID(), contact);
	}

    // Retrieve a contact by ID
    public Contact getContact(String contactID) {
        return contacts.get(contactID);
    }
	
	// Delete a contact from hash map
	public void deleteContact(String contactID) {
        if (contacts.remove(contactID) == null) {
            throw new IllegalArgumentException("Contact not found");
        }
	}
	
	// Update firstName
	public void updateFirstName(String contactID, String newFirstName) {
		Contact contact = getContact(contactID);
		contact.setFirstName(newFirstName);
	}
	
	// Update lastName
	public void updateLastName(String contactID, String newLastName) {
		Contact contact = getContact(contactID);
		contact.setLastName(newLastName);
	}
	
	// Update number
	public void updateNumber(String contactID, String newNumber) {
		Contact contact = getContact(contactID);
		contact.setNumber(newNumber);
	}
	
	// Update address
	public void updateAddress(String contactID, String newAddress) {
		Contact contact = getContact(contactID);
		contact.setAddress(newAddress);
	}

}
