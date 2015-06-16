package manifesto.atm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AtmCommonTest {

	AtmCommon atmCommon;

	@Before
	public void setUp() {
		atmCommon = new AtmCommon();
	}

	@Test
	public void test_doesAtmHaveFundsAvailable_false() throws Exception {

		int atmBalance = 10;
		int withdrawalAmount = 20;

		assertFalse(atmCommon.doesAtmHaveFundsAvailable(atmBalance,
				withdrawalAmount));

	}

	@Test
	public void test_doesAtmHaveFundsAvailable_true() throws Exception {

		int atmBalance = 20;
		int withdrawalAmount = 10;

		assertTrue(atmCommon.doesAtmHaveFundsAvailable(atmBalance,
				withdrawalAmount));

	}

	@Test
	public void test_customerPinCorrect_true() throws Exception {

		String testString = "12341234 1234 1234";

		assertTrue(atmCommon.customerPinCorrect(testString));

	}

	@Test
	public void test_customerPinCorrect_false() throws Exception {

		String testString = "12341234 1234 4321";

		assertFalse(atmCommon.customerPinCorrect(testString));

	}

	@Test
	public void test_initialiseAtm() throws Exception {

		assertEquals(1234, atmCommon.initialiseAtm("1234"));

	}

	@Test
	public void test_customerBalanceInformation() throws Exception {
		String customerInformation = "123 456";

		String[] customerInformationSplit = atmCommon
				.customerBalanceInformation(customerInformation);

		assertEquals("123", customerInformationSplit[0]);
		assertEquals("456", customerInformationSplit[1]);
	}

}
