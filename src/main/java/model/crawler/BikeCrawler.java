package model.crawler;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class BikeCrawler {
	
	public void executeCrawler() {
		//刪除舊資料
				deleteOldData();

			//bike資料爬蟲
				try {
					URL url = new URL("https://datacenter.taichung.gov.tw/swagger/OpenData/91deb8b8-7547-4a60-8cae-7c95c0df2fda");
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					conn.connect();

					try(BufferedReader reader = 
							new BufferedReader(
									new InputStreamReader(
										conn.getInputStream()))){
						StringBuffer sb = new StringBuffer();
						String line;
						while((line = reader.readLine())!=null) {
							sb.append(line);
						}
						//System.out.println(sb.toString());
						
					//轉換JSON資料，並匯入資料庫
						parseJSON(sb.toString());
						
						//saveJson(sb.toString());
					}		
				} catch (Exception e) {
					System.out.println(e.toString());
				}
	}
	
	
//轉換JSON資料，並匯入資料庫
	static void parseJSON(String json) {
	
	//連線資訊
		Properties prop = new Properties();
		prop.put("user","root");
		prop.put("password", "root");
		prop.put("serverTimezone", "Asia/Taipei");
		
	//進行連線 > 下sql指令
		try (Connection conn = 
				DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/lfproj",prop)){
	
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO bike (ID, Position, EName, CAddress, X_axis, Y_axis, CArea, AvailableCNT, EmpCNT, UpdateTime) VALUES (?,?,?,?,?,?,?,?,?,?)");
	//解析JSON資料，匯入資料庫
			JSONArray root = new JSONArray(json);
	
			for(int i=0;i<root.length();i++) {
				JSONObject dataSet = root.getJSONObject(i);
				String id = dataSet.getString("ID");
				String position = dataSet.getString("Position");
				String name = dataSet.getString("EName");
				String  caddress = dataSet.getString("CAddress");
				Double x = dataSet.getDouble("X");
				Double y = dataSet.getDouble("Y");
				String area = dataSet.getString("CArea");
				int available = dataSet.getInt("AvailableCNT");
				int emp = dataSet.getInt("EmpCNT");
				String updateTime = dataSet.getString("UpdateTime");
				
				pstmt.setString(1, id);
				pstmt.setString(2, position);
				pstmt.setString(3, name);
				pstmt.setString(4, caddress);
				pstmt.setDouble(5, x);
				pstmt.setDouble(6, y);
				pstmt.setString(7, area);
				pstmt.setInt(8, available);
				pstmt.setInt(9, emp);
				pstmt.setString(10, updateTime);
				
				pstmt.executeUpdate();
			}

			System.out.println("BikeData Insert OK :" + new Date());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}	
	
//刪除舊資料
	static void deleteOldData() {
		//連線資訊
			Properties prop = new Properties();
			prop.put("user","root");
			prop.put("password", "root");
			prop.put("serverTimezone", "Asia/Taipei");
		//進行連線 > 下sql指令
			try (Connection conn = 
					DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lfproj",prop)){
				PreparedStatement pstmt = conn.prepareStatement("TRUNCATE TABLE bike");
				pstmt.execute();
			}catch(Exception e){
				System.out.println(e.toString());
			}	
	}
	
	
	
//save Json
	static void saveJson(String json) {
		try {
			ObjectOutputStream oout = 
				new ObjectOutputStream(
					new FileOutputStream("E:\\資策會課程\\JAVAEE\\eclipse-workspace\\final-webproj\\src\\main\\webapp\\json\\bikeInfo.json"));
			
			oout.writeObject(json);
			
			oout.flush();
			oout.close();
			
			System.out.println("OK");
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
		
	}
	
}
