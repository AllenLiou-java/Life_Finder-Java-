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

public class BikeJsonOut implements BikeJson{
	private DataSource dataSource;


	public BikeJsonOut() {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/ConnDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
		
	private static final String SELECT_ALL = "select * from bike ";		
		
	@Override
	public boolean BikeDbToJsonOut() {
			
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
						subObj.put("id", rset.getString("ID"));
						subObj.put("position", rset.getString("Position"));
						subObj.put("ename", rset.getString("EName"));
						subObj.put("address", rset.getNString("CAddress"));
						subObj.put("lng", rset.getDouble("X_axis"));
						subObj.put("lat", rset.getDouble("Y_axis"));
						subObj.put("carea", rset.getString("CArea"));
						subObj.put("available", rset.getInt("AvailableCNT"));
						subObj.put("emp", rset.getInt("EmpCNT"));
						subObj.put("updateTime", rset.getString("UpdateTime"));
																	
						array.put(subObj);
					}
				}
				object.put("bikeInfo", array);
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
	
			FileOutputStream fop = new FileOutputStream("E:\\資策會課程\\JAVAEE\\eclipse-workspace\\final-webproj\\src\\main\\webapp\\json\\bikeInfo.json");
			OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
			writer.append(json);
			writer.flush();
			writer.close();
		
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
		
	}
}
