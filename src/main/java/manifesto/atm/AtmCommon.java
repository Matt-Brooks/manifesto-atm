package manifesto.atm;

import java.util.List;

public class AtmCommon {

	/**
	 * Takes a customer (represented by List<String>) and the current balance of
	 * the atm and processes them
	 * 
	 * @param customer
	 * @param atmBalance
	 */
	protected void processCustomer(List<String> customer, int atmBalance) {

		Boolean accountAccessGranted = customerPinCorrect(customer.get(0));

		if (customer.size() > 2) {

			// customer has made a request

			String[] customerBalanceInformation = customerBalanceInformation(customer
					.get(1));
			int currentBalance = Integer
					.parseInt(customerBalanceInformation[0]);
			int currentOverdraft = Integer
					.parseInt(customerBalanceInformation[1]);

			for (int i = 2; i < customer.size(); i++) {

				String request = customer.get(i);

				if (!accountAccessGranted) {

					// incorrect pin, request not granted
					System.out.println(AtmConstants.ACCOUNT_ERROR);

				} else {

					// TODO: change to switch - helps for longer list

					if (request.equals(AtmConstants.BALANCE_ENQ)) {

						System.out.println(currentBalance);

					} else {

						// TODO: Refactor this?
						String[] withdrawalRequest = request.split(" ");

						if (withdrawalRequest[0]
								.equals(AtmConstants.CASH_WITHDRAWAL)) {

							int requestedWithdrawalAmmount = Integer
									.parseInt(withdrawalRequest[1]);

							// Check atm balance
							if (doesAtmHaveFundsAvailable(atmBalance,
									requestedWithdrawalAmmount)) {

								// customer has sufficient budget in account
								if (requestedWithdrawalAmmount <= currentBalance) {

									// Reduce customer balence by withdrawal
									// amount
									currentBalance = currentBalance
											- requestedWithdrawalAmmount;

									// Reduce atm balance by withdrawal amount
									atmBalance = atmBalance
											- requestedWithdrawalAmmount;

									System.out.println(currentBalance);

								}
								// customer has sufficient budget with overdraft
								else if (requestedWithdrawalAmmount <= currentBalance
										+ currentOverdraft) {

									// Reduce atm balance by withdrawal amount
									atmBalance = atmBalance
											- requestedWithdrawalAmmount;

									// Reduce withdrawl amount from customer
									// balance first, then overdraft amount
									requestedWithdrawalAmmount = requestedWithdrawalAmmount
											- currentBalance;
									currentOverdraft = currentOverdraft
											- requestedWithdrawalAmmount;

									System.out.println(currentBalance);

								}
								// not enough budget - exception
								else {

									System.out
											.println(AtmConstants.FUNDS_ERROR);
								}
							} else {
								// not enough atm funds available
							}

						}

					}

				}

			}

		} else {
			// customer has not made a request
		}

	}

	/**
	 * Checks if a withdrawal is possible at the atm
	 * 
	 * @param atmBalance
	 * @param withdrawalAmount
	 * @return false if withdrawal amount is greater than atm balance
	 */
	protected boolean doesAtmHaveFundsAvailable(int atmBalance,
			int withdrawalAmount) {

		if (withdrawalAmount > atmBalance) {
			System.out.println(AtmConstants.ATM_ERROR);
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Checks if the customer has entered the correct pin
	 * 
	 * @param customerAccountInfo
	 * @return true if entered pin matches correct pin
	 */
	protected boolean customerPinCorrect(String customerAccountInfo) {

		String[] customerInformation = customerAccountInfo.split(" ");

		@SuppressWarnings("unused")
		String accountNumber = customerInformation[0]; // nothing done with this
														// for now...

		String correctPin = customerInformation[1];
		String enteredPin = customerInformation[2];

		return correctPin.equals(enteredPin);

	}

	/**
	 * Splits the customer balance information into balance and overdraft amount
	 * 
	 * @param customerBalance
	 * @return an array of String where [0] is balance and [1] is overdraft
	 */
	protected String[] customerBalanceInformation(String customerBalance) {

		return customerBalance.split(" ");
	}

	/**
	 * Reads the String atmAmount and returns it as an int
	 * 
	 * @param atmAmount
	 * @return the atm starting balance as an int
	 */
	protected int initialiseAtm(String atmAmount) {

		return Integer.parseInt(atmAmount);

	}

}
