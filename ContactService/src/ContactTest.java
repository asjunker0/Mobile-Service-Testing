import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactTest {
	
    private Contact contact;
	
	@BeforeEach
	void setup() {
		contact = new Contact("123", "First name", "Last name", "1234567890", "Address");
	}
	
	// Constructor and getter tests
	@Test
	void testValidContactCreation() {
		assertEquals("123", contact.getContactID());
		assertEquals("First name", contact.getFirstName());
		assertEquals("Last name", contact.getLastName());
		assertEquals("1234567890", contact.getNumber());
		assertEquals("Address", contact.getAddress());
	}
	
	//Setter tests
	@Test
	void testSetterHappyPaths() {
	    contact.setFirstName("Anna");
	    contact.setLastName("Smith");
	    contact.setNumber("0987654321");
	    contact.setAddress("456 New Place Blvd.");
	}
	
	// First name
	@Test
	void testSetFirstName() {
		contact.setFirstName("New First");
		assertEquals("New First", contact.getFirstName());
	}
	
	@Test
	void testSetFirstNameNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setFirstName(null);
		});
	}
	
	@Test
	void testSetFirstNameTooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setFirstName("ThisIsWayTooLong");
		});
	}
	
	// Last name
	@Test
	void testSetLastName() {
		contact.setLastName("New Last");
		assertEquals("New Last", contact.getLastName());
	}
	
	@Test
	void testSetLastNameNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setLastName(null);
		});
	}
	
	@Test
	void testSetLastNameTooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setLastName("TooLongLastName");
		});
	}
	
	// Number
	@Test
	void testSetNumber() {
		contact.setNumber("New Number");
		assertEquals("New Number", contact.getNumber());
	}
	
	@Test
	void testSetNumberNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setNumber(null);
		});
	}
	
	@Test
	void testSetNumberTooShort() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setNumber("123456789");
		});
	}
	
	@Test
	void testSetNumberTooLong() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        contact.setNumber("12345678901"); // 11 digits
	    });
	}
	
	// Address
	@Test
	void testSetAddress() {
		contact.setAddress("New Address");
		assertEquals("New Address", contact.getAddress());
	}
	
	@Test
	void testSetAddressNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setAddress(null);
		});
	}
	
	@Test
	void testSetAddressTooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setAddress("This address string is way too long to be valid for the Contact object");
		});
	}
	
	// ContactId failure paths
	@Test
	void testContactIdTooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
	        new Contact("12345678901", "First", "Last", "1234567890", "123 Main St");
	    });
	}
	
	@Test
	void testContactIdNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(null, "First name", "Last name", "1234567890", "Address");
		});
	}
	
	// First name failure paths
	@Test
	void testFirstNameTooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123", "First name is too long", "Last name", "1234567890", "Address");
		});
	}
	
	@Test
	void testFirstNameNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123", null, "Last name", "1234567890", "Address");
		});
	}
	
	// Last name failure paths
	@Test
	void testLastNameTooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123", "First name", "Last name is too long", "1234567890", "Address");
		});
	}
	
	@Test
	void testLastNameNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123", "First name", null, "1234567890", "Address");
		});
	}
	
	// Last name failure paths
	@Test
	void testNumberNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123", "First name", "Last name", null, "Address");
		});
	}
	
	// Address failure paths
	@Test
	void testAddressTooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123", "First name", "Last name", "12345678901", "This address is definitely more than thirty characters");
		});
	}
	
	@Test
	void testAddressNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123", "First name", "Last name", "1234567890", null);
		});
	}
}
