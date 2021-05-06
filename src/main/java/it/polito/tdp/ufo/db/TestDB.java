package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDB {

	public static void main(String[] args) {
		String jdbcURL="jdbc:mysql://localhost/ufo_sightings?user=root&password=silviapoli";
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			
			String sql="SELECT DISTINCT shape "+
					"FROM sighting "+
					"WHERE shape<>'' "+
					"ORDER BY shape ASC";
			PreparedStatement st=conn.prepareStatement(sql);
			
			
			ResultSet res= st.executeQuery(sql);
			List <String>forme_ufo= new ArrayList<String>();
			
			while(res.next()) {
				String forma= res.getString("shape");
				forme_ufo.add(forma);
			}
			
			st.close();
			System.out.println(forme_ufo);
			
			String sql2= "SELECT COUNT(*)AS conteggio FROM sighting WHERE shape=? ";
			PreparedStatement st2=conn.prepareStatement(sql2);
			String shapescelta= "circle";
			st2.setString(1, shapescelta);
			ResultSet res2=st2.executeQuery();
			res2.first();
			int count= res2.getInt("conteggio");
			
			st2.close();
			System.out.println("ci sono "+count+" avvistamenti di ufo con forma "+shapescelta);
			
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
