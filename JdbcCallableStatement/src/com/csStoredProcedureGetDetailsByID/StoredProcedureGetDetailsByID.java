package com.csStoredProcedureGetDetailsByID;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import com.JdbcUtil.JdbcUtil;
 
/*
 CREATE DEFINER=`root`@`localhost` PROCEDURE `Get_Details_By_ID`(IN id int, OUT name varchar(50), OUT rate int, OUT qnty int)
BEGIN
  select pname, prate, pqnty
  into name,rate,qnty
  from products
  where pid=id;
END 
 */
public class StoredProcedureGetDetailsByID {
     static String storedprocedure="{CALL Get_Details_By_ID(?,?,?,?)}";
	public static void main(String[] args) {
		Connection con=null;
		CallableStatement cstmt=null;
		Integer id=null;
		try {
			con = JdbcUtil.getJdbcConnection();
			if(con!=null)
			{
				cstmt= con.prepareCall(storedprocedure);
			}
			Scanner sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.print("Enter ID:: ");
				id=sc.nextInt();
			}
			if(cstmt!=null) {
				cstmt.setInt(1, id);
			}
			if(cstmt!=null) {
				cstmt.registerOutParameter(2, Types.VARCHAR);
				cstmt.registerOutParameter(3, Types.INTEGER);
				cstmt.registerOutParameter(4, Types.INTEGER);
			}
			if(cstmt!=null) {
				cstmt.execute();
			}
			if(cstmt!=null) {
				System.out.println("pname\tprate\tpqnty");
				System.out.println(cstmt.getString(2)+"\t"+cstmt.getInt(3)+"\t"+cstmt.getInt(4));
			}
			sc.close();
		} catch (SQLException | IOException e) {
			
			e.printStackTrace();
		}

	}

}
