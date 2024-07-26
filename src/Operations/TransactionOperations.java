package Operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import DataBasePackage.DatabaseConnection;

import DataBasePackage.DatabaseStatements;

public class TransactionOperations {
	
	public TransactionOperations(Scanner sc,DatabaseStatements databaseStatements)
	{
		Operations(sc,databaseStatements);
	}
	public void Operations(Scanner sc,DatabaseStatements databaseStatements)
	{
		int choice=0;
		float amount =0;
		float balance=0;
		while(choice!=4)
		{
			showOptions();
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				 String type = "debit";

				System.out.println("Enter accountid: ");
				int accountid = sc.nextInt();
				if(accountidValidate(accountid)) {
					
					System.out.println("Enter amount to debit: ");
					 amount = sc.nextFloat();
					 balance= DebitAccount(amount,databaseStatements,accountid);
					 databaseStatements.getAccountDetails(accountid,amount,balance,type);
					try {
						//DatabaseConnection.statement.executeUpdate("update transaction set Balance = "+balance+", amount ="+amount+",Type = 'debit'");
						DatabaseConnection.statement.executeUpdate("update account set Balance = "+balance+" where accountid = "+accountid);

					} catch (SQLException e) {
						
						System.out.println(e.getMessage());
					}
				}
				 else
				 {
					 System.out.println("Enter valid userid");
				 }
				


				break;
				
			case 2:
				System.out.println("Enter accountid: ");
				accountid = sc.nextInt();
				System.out.println(accountid);
				 if(accountidValidate(accountid)) {
					 System.out.println("Enter amount to credit: ");
					 amount = sc.nextFloat();
					 balance= CreditAccount(amount,databaseStatements,accountid);
					  type = "credit";
					 databaseStatements.getAccountDetails(accountid,amount,balance,type);

					try {

						//DatabaseConnection.statement.executeUpdate("update transaction set Balance = "+balance+", amount ="+amount+",Type = 'credit'");
						DatabaseConnection.statement.executeUpdate("update account set Balance = "+balance+" where accountid = "+accountid);

					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println(e.getMessage());
					}
				 }
				 else
				 {
					 System.out.println("Enter valid userid");
				 }
				
				break;
				
			case 3:
				String type2 = "Transfer Credit";
				String type1="Transfer Debit";
				System.out.println("Enter from account id ");
				int accountid1=sc.nextInt();
				System.out.println("Enter to account id ");
				int accountid2=sc.nextInt();
				if(accountidValidate(accountid1) &&accountidValidate(accountid2) ) {
					
					System.out.println("Enter amount to transfer: ");
					 amount = sc.nextFloat();
					float balance1= DebitAccount(amount,databaseStatements,accountid1);
					float balance2= CreditAccount(amount,databaseStatements,accountid2);
					 databaseStatements.getAccountDetails(accountid1,amount,balance1,type1);
					 databaseStatements.getAccountDetails(accountid2,amount,balance2,type2);



					try {
						//DatabaseConnection.statement.executeUpdate("update transaction set Balance = "+balance1+", amount ="+amount+",Type = 'Transfer(Debit)' where accountid = "+accountid1);
						//DatabaseConnection.statement.executeUpdate("update transaction set Balance = "+balance2+", amount ="+amount+",Type = 'Transfer(Credit)' where accountid = "+accountid2);
						DatabaseConnection.statement.executeUpdate("update account set Balance = "+balance1+" where accountid = "+accountid1);
						DatabaseConnection.statement.executeUpdate("update account set Balance = "+balance2+" where accountid = "+accountid2);

					} catch (SQLException e) {
						
						System.out.println(e.getMessage());
					}
				}
				 else
				 {
					 System.out.println("Enter valid userid");
				 }
				
				
				
				break;
				
			case 4:
				System.out.println("Exit");
				break;
			}
			
		}
	}
	public float DebitAccount(float amount,DatabaseStatements databaseStatements,int accountid)
	{
		float balance =databaseStatements.getBalance(accountid);	
	if(amount>0 && amount<=balance)
	{
		balance=balance-amount;
		return balance;
	}
	else
	{
		System.out.println("Amount greater than balance or less than 0");
	}
	return balance;
	}
	public float CreditAccount(float amount,DatabaseStatements databaseStatements,int accountid)
	{
		float balance =databaseStatements.getBalance(accountid);	

		if(amount>0)
		{
			balance=balance+amount;
			return balance;
		}
		else
		{
			System.out.println("Amount less than 0");
		}
		return balance;
		
	}
	
	public boolean accountidValidate(int accountid)//this should be in databasestatements as it is connected to database
	{
		int accountid1=0;
		try {
			ResultSet resultset=DatabaseConnection.statement.executeQuery("select accountid from Account where accountid ="+accountid);
			
			while(resultset.next())
			{
				//System.out.println("true");
				return true;
			}
		
			
		} catch (SQLException e) {
		
			System.out.println(e.getMessage());
			//System.out.println("false");
		}
		return false;

	}
	
	public void showOptions()
	{
		System.out.println("1.Debit");
		System.out.println("2.Credit");
		System.out.println("3.Transfer");
		System.out.println("4.Exit");
		System.out.println("Enter your choice: ");
	}
	
}


