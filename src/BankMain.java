import java.util.Scanner;

import DataBasePackage.DatabaseConnection;
import DataBasePackage.DatabaseStatements;
import Operations.*;
public class BankMain {

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		
		DatabaseConnection databaseConnection = new DatabaseConnection();
		DatabaseStatements databaseStatements = new DatabaseStatements();
		databaseStatements.createTable();
		//int balance=0;
		int choice=0;
		while(choice!=4) {
		showoptions();
		choice = sc.nextInt();
		
		switch(choice)
		{
		case 1:
			UserOperations userOperations = new UserOperations(sc,databaseStatements);
			break;
			
		case 2:
			AccountOperations accountOperations =new AccountOperations(sc,databaseStatements);
			break;
			
		case 3:
			TransactionOperations transactionOperations = new TransactionOperations(sc,databaseStatements);
			break;
			
		case 4:
			System.out.println("Exit");
			break;
		}
	}
		
		
		
		
		
		
	
	}
	public static void showoptions()
	{
	System.out.println("1.User Operations");	
	System.out.println("2.Account Operations");	
	System.out.println("3.Transactions Operations");	
	System.out.println("4.Exit");	
	System.out.println("Enter choice: ");	
	}
}
