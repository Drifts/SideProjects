//Server.
import java.rmi.*;
import java.io.*;

public class Bank2Server
{
	private static final String HOST = "localhost";

	public static void main(String[] args) throws Exception
	{
		Bank2Impl[] account =
			{new Bank2Impl("111111","Tony","Blair",112.58),
			new Bank2Impl("222222","Bridget","Jones",507.85),
			new Bank2Impl("234567","Peter","Pan",2345.00),
			new Bank2Impl("666666","Geroge","Bush",666.00)};

		for (int i=0; i<account.length; i++)
		{
			String acctNum = account[i].getAcctNum();
			Naming.rebind("//" + HOST + "/account" + acctNum, account[i]);
			//Could omit host name, since 'localhost' would be
			//assumed by default.
		}
		System.out.println("Binding complete...\n");
	}
}


