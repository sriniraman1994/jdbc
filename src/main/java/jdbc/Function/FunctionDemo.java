package jdbc.Function;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class FunctionDemo {
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		CallableStatement myStmt = null;

		try {
			// Get a connection to database
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/hibernate", "test", "test");


			// Prepare the function call
			myStmt = myConn
					.prepareCall("{?=call test_function(?, ?)}");
			int value1 = 100;
			int value2 = 100;

			// Set the parameters
			myStmt.registerOutParameter(1, Types.INTEGER);
			myStmt.setInt(2, value1);
			myStmt.setInt(3, value2);

			// Call stored procedure
			System.out.println("Calling function test_function(100,100)");
			myStmt.execute();
			System.out.println("Finished calling function");			

			// Get the value of the OUT parameter
			int theCount = myStmt.getInt(1);

			System.out.println("\nThe output of the function = " + theCount);

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(myConn, myStmt);
		}
	}

	private static void close(Connection myConn, Statement myStmt) throws SQLException {
		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}	
}
