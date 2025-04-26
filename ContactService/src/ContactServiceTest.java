import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
	ContactService service;
	Contact contact;
	
	@BeforeEach
	void setup() {
		service = new ContactService();
		contact = new Contact("123", "Firstname", "Lastname", "0123456789", "123 Address Ln.");
		service.addContact(contact);
	}
	
	@Test
	void testAddContact() {
		service.addContact(new Contact("000", "Firstname", "Lastname", "0123456789", "123 Address Ln."));
	}
	
	@Test
	void testDeleteContact() {
		service.deleteContact(contact.getContactID());
	}
	
	@Test
	void testUpdateFirstname() {
		service.updateFirstName("123", "new First");
		assertEquals("new First", contact.getFirstName());
	}
	
	@Test
	void testUpdateLastname() {
		service.updateLastName("123", "new Last");
		assertEquals("new Last", contact.getLastName());
	}
	
	@Test
	void testUpdateNumber() {
		service.updateNumber("123", "0000000000");
		assertEquals("0000000000", contact.getNumber());
	}
	
	@Test
	void testUpdateAddress() {
		service.updateAddress("123", "000 Address Dr.");
		assertEquals("000 Address Dr.", contact.getAddress());
	}
}
