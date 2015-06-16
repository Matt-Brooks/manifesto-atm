package manifesto.atm;

public class Customer {

	private String accountInformation;
	private String accountBalance;
	private String[] requests;
	
	public String getAccountInformation() {
		return accountInformation;
	}
	public void setAccountInformation(String accountInformation) {
		this.accountInformation = accountInformation;
	}
	public String getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String[] getRequests() {
		return requests;
	}
	public void setRequests(String[] requests) {
		this.requests = requests;
	}
	
}
