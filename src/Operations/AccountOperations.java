package Operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import DataBasePackage.DatabaseConnection;
import DataBasePackage.DatabaseStatements;
import Utils.Constants;


public class AccountOperations {
	public String accountTable = Constants.ACCOUNT_TABLE_NAME;


	public AccountOperations(Scanner sc,DatabaseStatements databaseStatements)
	{
		Operations(sc,databaseStatements);
	}
	public void Operations(Scanner sc,DatabaseStatements databaseStatements)
	{
		int choice =0;
		while(choice!=4)
		{
			showOptions();
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Enter userid: ");
				int userid = sc.nextInt();
				System.out.println("Enter account type: ");
				String accountType = sc.next();
				databaseStatements.AccountDetails(accountType,userid);
				break;
				
			case 2:
				System.out.println("Enter accountid: ");
				int accountid= sc.nextInt();
				 try {
					DatabaseConnection.statement.executeUpdate("delete from "+accountTable+" where accountid ="+accountid+"");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					
					System.out.println(e.getMessage());
				}
				break;
				
			case 3:
				System.out.println("Enter accountid: ");
				 accountid= sc.nextInt();
				try {
					ResultSet resultset=DatabaseConnection.statement.executeQuery("select * from "+accountTable+" where accountid ="+accountid+"");
					
					while( resultset.next())
					{
						userid = resultset.getInt("userid");
						System.out.println("Userid: "+userid);
						float balance = resultset.getFloat("balance");
						System.out.println("Balance: "+balance);
						String type = resultset.getString("acctype");
						System.out.println("Account type: "+type);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
				
			case 4:
				System.out.println("Exit");
				break;
			}
		}
	}
	
	public void showOptions()
	{
		System.out.println("1.Create account");
		System.out.println("2.Delete account");
		System.out.println("3.Show account");
		System.out.println("4.Exit");
		System.out.println("Enter choice: ");

	}
}
