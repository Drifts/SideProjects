//RMI interface.
import java.rmi.*;
import java.io.*;

public interface Bank2 extends Remote
{
	public String getAcctNum();

	public String getName();

	public double getBalance();

	public double withdraw(double amount);

	public void deposit(double amount);
}

