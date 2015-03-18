//Implementation of RMI interface.
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;

public class Bank2Impl extends UnicastRemoteObject implements Bank2
{
	private String acctNum;
	private String surname;
	private String firstNames;
	private double balance;

	public Bank2Impl (String acctNo, String sname, String fnames, double bal)
	throws RemoteException
	{
		acctNum = acctNo;
		surname = sname;
		firstNames = fnames;
		balance = bal;
	}

	public String getAcctNum() 
	{
		return acctNum;
	}

	public String getName()
	{
		return (firstNames + " " + surname);
	}

	public double getBalance() 
	{
		return balance;
	}

	public double withdraw(double amount)
	{
		if (amount <= balance)
			return amount;
		else 
			return 0;
	}

	public void deposit(double amount) 
	{
		if (amount > 0)
			balance += amount;
	}
}