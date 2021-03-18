package model.crawler;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mysql.cj.ParseInfo;


public class ParkCrawler {
	
	public void executeCrawler() {
		//刪除舊資料
				deleteOldData();

				String url = "https://tcparking.taichung.gov.tw/ParkWeb/PublicService/ParkingRemainder";
				Document doc;
				try {
					doc = Jsoup.connect(url).timeout(3000).get();
					Elements elem = doc.getElementsByAttributeValue("type", "text/javascript");
					String str = elem.toString();
					
					int startIndex = str.indexOf("MarkInfoDataList");
					int endIndex = str.indexOf("}];");
					String res = str.substring(startIndex+19, endIndex+3);

					//System.out.println(res);	
				//轉換JSON資料，並匯入資料庫
					parseJSON(res);	
					
					saveJson(res.toString());
					
				} catch (IOException e) {
					e.printStackTrace();
				}	
	}
	
	
//轉換JSON資料，並匯入資料庫
	static void parseJSON(String json) {
		Properties prop = new Properties();
		prop.put("user","root");
		prop.put("password", "root");
		prop.put("serverTimezone", "Asia/Taipei");
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lfproj",prop)){
			PreparedStatement ptmt = conn.prepareStatement
					("INSERT INTO park (lat, lng, name, address, available, total) VALUES (?,?,?,?,?,?)");
			JSONArray root = new JSONArray(json);
			for (int i=0; i<root.length(); i++) {
				JSONObject row = root.getJSONObject(i);
				Double lat = row.getDouble("lat");
				Double lng = row.getDouble("lng");
				String status = row.getString("desc");
				ptmt.setDouble(1, lat);
				ptmt.setDouble(2, lng);

				
				//解析status內容(網頁上的desc內容)，將其拆成 1.名稱 2.地址 3.可用車位 4.總車位
				String spst[] = status.split("<br/>");
				
				for(int j=0;j<spst.length;j++) {
					int firstIdx=0;
					switch(j) {
						case 0:
							firstIdx = spst[j].indexOf(":");
							String name = spst[j].substring(firstIdx+1);
							ptmt.setString(3, name);
							//System.out.println(name);
							break;
						case 2:
							firstIdx = spst[j].indexOf(":");
							String address = spst[j].substring(firstIdx+1);
							ptmt.setString(4, address);
							//System.out.println(address);
							break;
						case 3:
							String spaceInfo[] = spst[j].split(",");
							for(int k=0;k<spaceInfo.length;k++) {
								switch (k) {
									case 0:
										firstIdx = spaceInfo[k].indexOf(":");
										String strAvailable = spaceInfo[k].substring(firstIdx+1);
										int available = Integer.parseInt(strAvailable);
										ptmt.setInt(5, available);
										//System.out.println("尚有車位" + available);
										break;
									case 1:
										firstIdx = spaceInfo[k].indexOf(":");
										String strTotal = spaceInfo[k].substring(firstIdx+1);
										int total = Integer.parseInt(strTotal);
										ptmt.setInt(6, total);
										//System.out.println("總車位" + total);
										break;
								}
							}			
							break;
					}
				}
				
				ptmt.executeUpdate();

			}
			System.out.println("ParkData Insert OK :" + new Date());
		}catch(Exception e) {
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
				PreparedStatement pstmt = conn.prepareStatement("TRUNCATE TABLE park");
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
					new FileOutputStream("E:\\資策會課程\\JAVAEE\\eclipse-workspace\\final-webproj\\src\\main\\webapp\\json\\parkInfo.json"));
			
			oout.writeObject(json);
			
			oout.flush();
			oout.close();
			
			System.out.println("OK");
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
		
	}

}
