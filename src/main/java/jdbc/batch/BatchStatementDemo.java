package jdbc.batch;

import java.sql.*;
import java.io.*;

class BatchStatementDemo {
	public static void main(String args[]) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernate", "test", "test");

			ps = con.prepareStatement("insert into bulk_emp values(?,?,?)");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while (true) {

				System.out.println("enter id");
				String s1 = br.readLine();
				int id = Integer.parseInt(s1);

				System.out.println("enter name");
				String name = br.readLine();

				System.out.println("enter salary");
				String s3 = br.readLine();
				int salary = Integer.parseInt(s3);

				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setInt(3, salary);

				ps.addBatch();
				System.out.println("Want to add more records y/n");
				String ans = br.readLine();
				if (ans.equals("n")) {
					break;
				}

			}
			ps.executeBatch();

			System.out.println("records successfully saved");

		} catch (Exception e) {
			System.out.println(e);
		}finally{
			if(con !=null) {
				con.close();
			}
		}

	}
}