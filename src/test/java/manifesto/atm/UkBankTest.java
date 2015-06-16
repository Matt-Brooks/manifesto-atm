package manifesto.atm;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import manifesto.atm.impl.UkBankImpl;
import manifesto.atm.utils.TestUtils;

import org.junit.Before;
import org.junit.Test;

public class UkBankTest

{

	private final String SAMPLE_DATA_FILE = "sampleTestData.txt";
	private final String FILEPATH = "../manifesto-atm/src/test/resources/";

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	private PrintStream sysDefault;

	@Before
	public void setUpStreams() {
		sysDefault = System.out;
		System.setOut(new PrintStream(outContent));
	}

	@Test
	public void testProcessTransactions_SampleTestData() throws Exception {

		AtmModel ukBank = new UkBankImpl();
		ukBank.setFilepath(FILEPATH + SAMPLE_DATA_FILE);

		ukBank.processTransactions();

		String output = outContent.toString();

		List<String> regexRemovedStrings = TestUtils.regexRemover(output
				.split("\n"));

		assertEquals("500", regexRemovedStrings.get(0));
		assertEquals("400", regexRemovedStrings.get(1));
		assertEquals("90", regexRemovedStrings.get(2));
		assertEquals("FUNDS_ERR", regexRemovedStrings.get(3));
		assertEquals("0", regexRemovedStrings.get(4));

	}

	@Test
	public void testProcessTransactions_incorrectPin() throws Exception {
		AtmModel ukBank = new UkBankImpl();
		ukBank.setFilepath(FILEPATH + "testData_incorrectPin.txt");

		ukBank.processTransactions();

		String output = outContent.toString();

		List<String> regexRemovedStrings = TestUtils.regexRemover(output
				.split("\n"));

		assertEquals("ACCOUNT_ERR", regexRemovedStrings.get(0));
		assertEquals("ACCOUNT_ERR", regexRemovedStrings.get(1));
	}

	@Test
	public void testProcessTransactions_atmNoFunds() throws Exception {
		AtmModel ukBank = new UkBankImpl();
		ukBank.setFilepath(FILEPATH + "testData_atmNoFunds.txt");

		ukBank.processTransactions();

		String output = outContent.toString();

		List<String> regexRemovedStrings = TestUtils.regexRemover(output
				.split("\n"));

		assertEquals("500", regexRemovedStrings.get(0));
		assertEquals("ATM_ERR", regexRemovedStrings.get(1));
	}

	@Test
	public void testProcessTransactions_overdrawn() throws Exception {
		AtmModel ukBank = new UkBankImpl();
		ukBank.setFilepath(FILEPATH + "testData_overdrawn.txt");

		ukBank.processTransactions();

		String output = outContent.toString();

		List<String> regexRemovedStrings = TestUtils.regexRemover(output
				.split("\n"));

		assertEquals("0", regexRemovedStrings.get(0));
		assertEquals("0", regexRemovedStrings.get(1));
		assertEquals("0", regexRemovedStrings.get(2));
	}

	@Test
	public void testProcessTransactions_multipleWithdrawals() throws Exception {
		AtmModel ukBank = new UkBankImpl();
		ukBank.setFilepath(FILEPATH + "testData_multipleWithdrawals.txt");

		ukBank.processTransactions();

		String output = outContent.toString();

		List<String> regexRemovedStrings = TestUtils.regexRemover(output
				.split("\n"));

		assertEquals("400", regexRemovedStrings.get(0));
		assertEquals("350", regexRemovedStrings.get(1));
		assertEquals("325", regexRemovedStrings.get(2));
		assertEquals("200", regexRemovedStrings.get(3));
		assertEquals("200", regexRemovedStrings.get(4));
	}
	
	@Test
	public void testProcessTransactions_noCustomers() throws Exception {
		AtmModel ukBank = new UkBankImpl();
		ukBank.setFilepath(FILEPATH + "testData_noCustomers.txt");

		ukBank.processTransactions();

		String output = outContent.toString();
		
		assertTrue(output.isEmpty());
		
	}
	
	@Test
	public void testProcessTransactions_noRequests() throws Exception {
		AtmModel ukBank = new UkBankImpl();
		ukBank.setFilepath(FILEPATH + "testData_noRequests.txt");

		ukBank.processTransactions();

		String output = outContent.toString();
		
		assertTrue(output.isEmpty());
		
	}

	@Test
	public void testProcessTransactions_customFile() throws Exception {

		// Restore system.out for testing
		System.setOut(sysDefault);

		String file = System.getProperty("fileName");

		if (file == null) {

			file = "sampleTestData";

		}

		AtmModel ukBank = new UkBankImpl();
		ukBank.setFilepath(FILEPATH + file + ".txt");

		ukBank.processTransactions();
		assertTrue(true);

	}

}
