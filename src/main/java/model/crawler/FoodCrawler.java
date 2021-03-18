package model.crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class FoodCrawler {

	public static void main(String[] args) {
		
		//刪除舊資料
			deleteOldData();

		//bike資料爬蟲
			try {
				URL url = new URL("https://gis.taiwan.net.tw/XMLReleaseALL_public/restaurant_C_f.json");
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
					
					//字串切割，取Object內容
					int start = sb.indexOf("[");
					int last = sb.length()-9;
					
					String res = sb.substring(start,last);
					String result = res.replace(" ", "");
				
					
					//System.out.println(sb.toString());
					
				//轉換JSON資料，並匯入資料庫
					parseJSON(result.toString());
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
			"INSERT INTO food (Name, Region, Description, Tel, Opentime, Px, Py) VALUES (?,?,?,?,?,?,?)");
		//解析JSON資料，匯入資料庫
				JSONArray root = new JSONArray(json);
				
				for(int i=0;i<root.length();i++) {
					JSONObject dataSet = root.getJSONObject(i);
					
					
					if(dataSet.getString("Add").startsWith("臺中")) {
					
						String name = dataSet.getString("Name");
						String address = dataSet.getString("Region");
						String desc = dataSet.getString("Description");
						String tel = dataSet.getString("Tel");
						String opentime = dataSet.getString("Opentime");
						Double px = dataSet.getDouble("Px");
						Double py = dataSet.getDouble("Py");
						
						
						pstmt.setString(1, name);
						pstmt.setString(7, address);
						pstmt.setString(2, desc);
						pstmt.setString(3, tel);
						pstmt.setString(4, opentime);
						pstmt.setDouble(5, px);
						pstmt.setDouble(6, py);
						
						
						pstmt.executeUpdate();
					}
				}

				System.out.println("OK");
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
					PreparedStatement pstmt = conn.prepareStatement("TRUNCATE TABLE food");
					pstmt.execute();
				}catch(Exception e){
					System.out.println(e.toString());
				}	
	}

}
