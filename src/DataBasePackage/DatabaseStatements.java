package DataBasePackage;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import com.mysql.cj.jdbc.result.ResultSetFactory;
import com.mysql.cj.protocol.Resultset;

public class DatabaseStatements extends DatabaseConnection  {
	

	public void createTable() 
	{
		createUserTable(super.statement);
		createAccountTable(super.statement);
		createTransactionTable(super.statement);

	}
	
	
public void createUserTable(Statement statement) 
{
	try {
		statement.executeUpdate("create table User(userid int, name varchar(255),age int)");
		
	} catch (SQLException se) {
		//se.printStackTrace();
		System.out.println(se.getMessage());
		
	}catch(Exception e)
	{
		//System.out.println(e.getMessage());
		//System.out.println("User table already exists");
	}
	//System.out.println("User Table created");
}

public void createAccountTable(Statement statement)
{
	try {
		statement.executeUpdate("create table Account(userid int, accountid int, balance float, acctype varchar(255))");
		
	} catch (SQLException se) {
		//se.printStackTrace();
		System.out.println(se.getMessage());
	
	}catch(Exception e)
	{
		//System.out.println(e.getMessage());
	    //System.out.println("Account table already exists");
	}
	//System.out.println("Account Table created");
}

public void createTransactionTable(Statement statement)
{
	try {
		statement.executeUpdate("create table Transaction(userid int, accountid int, acctype varchar(255), amount float, Balance float,Type varchar(255))");
	} catch (SQLException se) {
		//se.printStackTrace();
		System.out.println(se.getMessage());
	}catch(Exception e)
	{
		//System.out.println(e.getMessage());
		//System.out.println("User table already exists");
	}
	//System.out.println("Balance Table created");
}
int userid=1;
//public static ResultSet resultset;//Resultset can be used inside a function
public void UserDetails(String name, int age)
{

	try{

		ResultSet resultset =statement.executeQuery("select userid from User order by userid desc limit 1");

		
			while(resultset.next())
			{
			userid = resultset.getInt("userid")+1;

			 }
	
			
		statement.executeUpdate("insert into User values("+userid+",'"+name+"',"+age+")");
	}catch(SQLException se)
	{
		System.out.println("Values entered");
	}


}

 
public void AccountDetails(String account,int userid)
{
	float balance=0;
	int accountid=1;
	try {
		ResultSet resultset =statement.executeQuery("select accountid from Account");
		 while(resultset.next())
			{
			 accountid = resultset.getInt("accountid")+1;

			 }

		statement.executeUpdate("insert into Account values("+userid+",'"+accountid+"',"+balance+",'"+account+"')");
	} catch (SQLException e) {
		//e.printStackTrace();
		System.out.println("Values entered");

	}

}
public float getBalance(int accountid)
{
	float balance=0;
	try {
		ResultSet resultset=DatabaseConnection.statement.executeQuery("select balance from Account where accountid ="+accountid);
		while(resultset.next())
		{
			balance = resultset.getFloat("balance");
			return balance;
		}
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		
	}
	return -1;

}

public void getAccountDetails(int accountid,float amount,float balance,String type)
{
	

	try {
		ResultSet resultset=DatabaseConnection.statement.executeQuery("select userid,acctype,balance from Account where accountid ="+accountid);
		while(resultset.next())
		{
			int userid = resultset.getInt("userid");
			String acctype = resultset.getString("acctype");
			//float balance = resultset.getFloat("balance");
			statement.executeUpdate("insert into Transaction values("+userid+",'"+accountid+"','"+acctype+"',"+amount+","+balance+",'"+type+"')");
			//System.out.println("values entered");
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
	}

}
	
}
