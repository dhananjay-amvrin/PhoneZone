package phonezoneproject;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;


public class UserTest extends beforeandafterclassTest {
	@Test
	public void AddUserTest() {
		User user = new User(wd);
		assertEquals("User added successfully.", user.addUser("Test", "Seven", "Test7@datagenius.co.nz", "qwerty123", "23234334", "Staff", "", "Active"));	
	}
}

	