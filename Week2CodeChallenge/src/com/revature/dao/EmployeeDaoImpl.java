package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	private String filename = "connection.properties";

	@Override
	public void printAvgSalary() {
		// TODO Auto-generated method stub
		try {
			Connection conn = ConnectionUtil.getConnectionFromFile(filename);
			String sql = "select department_name, avg(salary) Avg from department inner join employee on department_id";
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String department_name = rs.getString("DEPARTMENT_NAME");
				float avgSalary = rs.getFloat("AVG");
				System.out.println(department_name+' '+avgSalary);
			}
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void spRaiseProcedure() {
		// TODO Auto-generated method stub
		Connection conn;
		try {
			conn = ConnectionUtil.getConnectionFromFile(filename);
			String sql = "declare\r\n" + 
					"    avg_salary number(8,2);\r\n" + 
					"    valid_department boolean := false;\r\n" + 
					"begin\r\n" + 
					"    sp_give_raise(1,avg_salary,valid_department);\r\n" + 
					"end;";
			CallableStatement cstmt = null;
			cstmt = conn.prepareCall(sql);
			cstmt.executeQuery();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
