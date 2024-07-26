package Operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Utils.Constants;

import DataBasePackage.DatabaseStatements;
import DataBasePackage.DatabaseConnection;


public class UserOperations {
	
	public String userTable = Constants.USER_TABLE_NAME;
	
	

	public UserOperations(Scanner sc,DatabaseStatements databaseStatements) {
		
		Operations(sc,databaseStatements);
	}
	
	public void Operations(Scanner sc,DatabaseStatements databaseStatements)
	{
	
		int choice=0;
		while(choice!=5)
		{
			showUserOptions();
			choice = sc.nextInt();
			
			switch(choice)
			{
			case 1:
				System.out.println("Enter name: ");
				String name = sc.next();
				System.out.println("Enter age:");
				int age = sc.nextInt();
				databaseStatements.UserDetails(name,age);
				break;
				
			case 2:
				System.out.println("Enter userid: ");
				int userid= sc.nextInt();
				 try {
					DatabaseConnection.statement.executeUpdate("delete from "+userTable+" where userid ="+userid+"");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					
					System.out.println(e.getMessage());
				}

				break;
				
			case 3:
				System.out.println("Enter userid: ");
				 userid= sc.nextInt();
				try {
					ResultSet resultset=DatabaseConnection.statement.executeQuery("select * from user where userid="+userid+"");
					
					while( resultset.next())
					{
						name = resultset.getString("name");
						System.out.println("Name: "+name);
						age = resultset.getInt("age");
						System.out.println("User age : "+age);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
				
			case 4:
				try {
					ResultSet resultset=DatabaseConnection.statement.executeQuery("select * from user");
					while( resultset.next())
					{
						userid =  resultset.getInt("userid");
						System.out.println("Userid: "+userid);
						name = resultset.getString("name");
						System.out.println("Name: "+name);
						age = resultset.getInt("age");
						System.out.println("User age : "+age);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				break;
				
			case 5:
				System.out.println("Exit");
				break;
				
			}
		}
	}
	
	

	public void showUserOptions()
	{
		System.out.println("1.Create user");
		System.out.println("2.Delete user");
		System.out.println("3.Show user");
		System.out.println("4.Show all users");
		System.out.println("5.Exit");

	}
}