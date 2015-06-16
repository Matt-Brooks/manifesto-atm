package manifesto.atm;

import java.io.FileNotFoundException;

public interface AtmModel {

	public abstract void processTransactions() throws FileNotFoundException;

	public abstract void setFilepath(String filepath);

}