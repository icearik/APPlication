/**
 * 
 */
package src.Database.DataObjects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author mccar130
 *
 */
public class UserInformationTest {

	
	UserInformation ui1;
	UserInformation ui2;
	@Before
	public void setUp() {
		ui1 = new UserInformation();
		ui2 = new UserInformation();
	}

	/**
	 * Test method for {@link DataObjects.UserInformation#getUserID()}.
	 */
	@Test
	public void testGetUserID() {
		assertEquals("guest", ui1.getUserID());
	}

	/**
	 * Test method for {@link DataObjects.UserInformation#getPassword()}.
	 */
	@Test
	public void testGetPassword() {
		assertEquals("guest", ui1.getPassword());
	}

	/**
	 * Test method for {@link DataObjects.UserInformation#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals("guest", ui1.getName());
	}

	/**
	 * Test method for {@link DataObjects.UserInformation#getEmail()}.
	 */
	@Test
	public void testGetEmail() {
		assertEquals("guest", ui1.getEmail());
	}

	/**
	 * Test method for {@link DataObjects.UserInformation#getUserStatus()}.
	 */
	@Test
	public void testGetUserStatus() {
		assertEquals("guest", ui1.getUserStatus());
	}

	/**
	 * Test method for {@link DataObjects.UserInformation#getDate()}.
	 */
	@Test
	public void testGetDate() {
		assertEquals("guest", ui1.getDate());
	}

	/**
	 * Test method for {@link DataObjects.UserInformation#setUserID(java.lang.String)}.
	 */
	@Test
	public void testSetUserID() {
		assertEquals("guest", ui1.getUserID());
		ui1.setUserID("cmccarthy");
		assertEquals("cmccarthy", ui1.getUserID());
	}

	/**
	 * Test method for {@link DataObjects.UserInformation#setPassword(java.lang.String)}.
	 */
	@Test
	public void testSetPassword() {
		assertEquals("guest", ui1.getPassword());
		ui1.setPassword("123Cat");
		assertEquals("123Cat", ui1.getPassword());
	}

	/**
	 * Test method for {@link DataObjects.UserInformation#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		assertEquals("guest", ui1.getName());
		ui1.setName("Carson");
		assertEquals("Carson", ui1.getName());
	}

	/**
	 * Test method for {@link DataObjects.UserInformation#setEmail(java.lang.String)}.
	 */
	@Test
	public void testSetEmail() {
		assertEquals("guest", ui1.getEmail());
		ui1.setEmail("mccar130@miamioh.edu");
		assertEquals("mccar130@miamioh.edu", ui1.getEmail());
	}

	/**
	 * Test method for {@link DataObjects.UserInformation#setUserStatus(java.lang.String)}.
	 */
	@Test
	public void testSetUserStatus() {
		assertEquals("guest", ui1.getUserStatus());
		ui1.setUserStatus("user");
		assertEquals("user", ui1.getUserStatus());
	}

	/**
	 * Test method for {@link DataObjects.UserInformation#setDate(java.lang.String)}.
	 */
	@Test
	public void testSetDate() {
		assertEquals("guest", ui1.getDate());
		ui1.setDate("08/04/2000");
		assertEquals("08/04/2000", ui1.getDate());
	}

	/**
	 * Test method for {@link DataObjects.UserInformation#getUserString()}.
	 */
	@Test
	public void testGetUserString() {
		ui2.setDate("1/1/2000");
		ui2.setEmail("test@dummy.com");
		ui2.setName("dummy");
		ui2.setPassword("Dummy1!");
		ui2.setUserID("dummy01");
		ui2.setUserStatus("admin");
		assertEquals("userID: guest\npassword: guest\nName: guest\nEmail: guest\nUserStatus: guest", ui1.getUserString());
		assertEquals("userID: dummy01\npassword: Dummy1!\nName: dummy\nEmail: test@dummy.com\nUserStatus: admin", ui2.getUserString());
	}

}
