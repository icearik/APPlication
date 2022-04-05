package src.Database.DataObjects;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author mccar130
 *
 */
public class AppInformationTest {

	
	AppInformation ai1;

	@Before
	public void startUp() {
		ai1 = new AppInformation();
	}

	/**
	 * Test method for getAppID and setAppID
	 */
	@Test
	public void testGetSetAppID() {
		ai1.setAppID("TestID");
		assertEquals(ai1.getAppID(), "TestID");
	}
	
	/**
	 * Test method for getCompanyID and setCompanyID.
	 */
	@Test
	public void testGetSetCompanyID() {
		ai1.setCompanyID("TestID");
		assertEquals(ai1.getCompanyID(), "TestID");
	}
	
	/**
	 * Test method for getImageID and setImageID.
	 */
	@Test
	public void testGetSetImageID() {
		ai1.setImageID("TestID");
		assertEquals(ai1.getImageID(), "TestID");
	}
	
	/**
	 * Test method for getBriefDesc and setBriefDesc
	 */
	@Test
	public void testGetSetBriefDec() {
		ai1.setBriefDesc("Test description");
		assertEquals(ai1.getBriefDesc(), "Test description");
	}
	
	/**
	 * Test method for getDetailedDesc and setDetailedDesc
	 */
	@Test
	public void testGetSetDetailedDec() {
		ai1.setDetailedDesc("Test description");
		assertEquals(ai1.getDetailedDesc(), "Test description");
	}
	
	/**
	 * Test method for getVersion and setVersion
	 */
	@Test
	public void testGetSetVersion() {
		ai1.setVersion("1.0");
		assertEquals(ai1.getVersion(), "1.0");
	}
	
	/**
	 * Test method for getDate and setDate
	 */
	@Test
	public void testGetSetDate() {
		ai1.setDate("09/19/2020");
		assertEquals(ai1.getDate(), "09/19/2020");
	}
	
	/**
	 * Test method for getPrice and setPrice
	 */
	@Test
	public void testGetSetPrice() {
		ai1.setPrice(999.99);
		assertEquals(ai1.getPrice(), 999.99, 0.001);
	}
	
	/**
	 * Test method for getDownloads and setDownloads
	 */
	@Test
	public void testGetSetDownloads() {
		ai1.setDownloads(24);
		assertEquals(ai1.getDownloads(), 24);
	}
	
	/**
	 * Test method for getRatingAvg and setRatingAvg
	 */
	@Test
	public void testGetSetRatingAvg() {
		ai1.setRatingAvg(5.5);
		assertEquals(ai1.getRatingAvg(), 5.5, 0.001);
	}
	
	/**
	 * Test method for getBriefDesc and setBriefDesc
	 */
	@Test
	public void testGetSetRatingCount() {
		ai1.setRatingCount(2);
		assertEquals(ai1.getRatingCount(), 2);
	}
	
	
}