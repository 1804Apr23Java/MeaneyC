package com.revature.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.dao.CaveDao;
import com.revature.dao.CaveDaoImpl;
import com.revature.domain.Cave;
import com.revature.util.ConnectionUtil;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*try {
			Connection conn = ConnectionUtil.getConnectionFromFile("connection.properties");
			System.out.println(conn.toString());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		CaveDao cd = new CaveDaoImpl();
		
		List<Cave> caves = cd.getCaves();
		
		for (Cave c : caves) {
			System.out.println(c.toString());
		}
		
		Cave c = cd.getCaveById(2);
		System.out.println(c.toString());
		
		cd.AddCave("AWESOMECAVE3", 5);
		cd.UpdateCave(3, "AWESOMECAVE4", 6);
		cd.DeleteCavebyId(3);

	}

}
