package manifesto.atm.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import manifesto.atm.AtmCommon;
import manifesto.atm.AtmModel;

/**
 * @author MBrooks 2015
 *
 */
public class UkBankImpl extends AtmCommon implements AtmModel {

	private String filepath;

	// TODO: consider minus numbers?
	// TODO: Use proper logging solution rather that sysout (log4j)

	public void processTransactions() throws FileNotFoundException {

		List<String> list = readInputFile();

		int atmBalance = initialiseAtm(list.get(0));

		if (list.size() > 2) {

			List<String> customer = new ArrayList<String>();

			// first customer exists
			for (int i = 2; i < list.size(); i++) {

				if (!list.get(i).isEmpty()) {
					customer.add(list.get(i));
				} else {
					// blank line, customer done

					processCustomer(customer, atmBalance);
					customer.clear();
				}

			}

			// process last customer, no blank line
			processCustomer(customer, atmBalance);
			
			//TODO: process blank lines at the end of the input file

		} else {
			// no customers - handle this explicitly?

		}
	}

	private List<String> readInputFile() throws FileNotFoundException {

		Scanner s = new Scanner(new File(filepath));
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNextLine()) {
			list.add(s.nextLine());
		}
		s.close();

		return list;

	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
