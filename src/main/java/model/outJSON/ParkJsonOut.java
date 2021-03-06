package model.outJSON;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParkJsonOut implements ParkJson{

	private DataSource dataSource;


	public ParkJsonOut() {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/ConnDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
		
	private static final String SELECT_ALL = "select * from park ";		
		

	@Override
	public boolean ParkDbToJsonOut() {
			
			ResultSet rset = null;
			boolean flag = false;
			
			try (
					Connection conn = dataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL)) {
				rset = pstmt.executeQuery();
				JSONObject object = new JSONObject();
				JSONArray array = new JSONArray();
				
				while(rset.next()) {
					if(rset != null) {
						JSONObject subObj = new JSONObject();
						subObj.put("lat", rset.getDouble("lat"));
						subObj.put("lng", rset.getDouble("lng"));
						subObj.put("name", rset.getString("name"));
						subObj.put("address", rset.getString("address"));
						subObj.put("available", rset.getInt("available"));
						subObj.put("total", rset.getInt("total"));					
						subObj.put("updateTime", rset.getTimestamp("updateTime"));
						
						array.put(subObj);
					}
				}
				object.put("parkInfo", array);
				//System.out.println(array);
				
				//save Json
				saveJson(array.toString());
				
				flag = true;

			} catch (SQLException e) {
				e.printStackTrace();
			}finally {	
				if (rset!=null) {
					try {
						rset.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} 
				}
			}
			return flag;
		
		}
	
	
	//save Json
	static void saveJson(String json) {
		try {
	
			FileOutputStream fop = new FileOutputStream("E:\\???????????????\\JAVAEE\\eclipse-workspace\\final-webproj\\src\\main\\webapp\\json\\parkInfo.json");
			OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
			writer.append(json);
			writer.flush();
			writer.close();
		
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
		
	}
	
	

}



