package model.crawler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.dao.BusDAO;
import model.dao.BusDaoJdbc;

public class BusCrawler extends Thread {

	public static void main(String[] args) {

		//刪除舊資料
		deleteOldData();
		
		System.out.println("delete OK");

		try {
			
//			//寫法1. 三道資料串接
//			FileInputStream fis = new FileInputStream("src\\main\\webapp\\json\\臺中市市區公車路線圖_1.json");
//			InputStreamReader ir = new InputStreamReader(fis);
//			BufferedReader reader = new BufferedReader(ir);
			
			//寫法2. 兩道資料串接
			FileReader fr = new FileReader("src\\main\\webapp\\json\\臺中市市區公車路線圖_1.json");
			BufferedReader reader = new BufferedReader(fr);

			StringBuffer sb = new StringBuffer();
			String line;
			
			while((line = reader.readLine())!= null) {
				sb.append(line);
			}
			
			reader.close();
			
			System.out.println(sb.toString());
			//
			parseJSON(sb.toString());
			
					
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
					"INSERT INTO bus (OperatorName, RouteName, Headsign, RouteMapImageUrl, 	Outbound, Inbound) VALUES (?,?,?,?,?,?)");
		//解析JSON資料，匯入資料庫
			JSONArray root = new JSONArray(json);
		
			for(int i=0;i<root.length();i++) {
				JSONObject dataSet = root.getJSONObject(i);
				String operatorName = dataSet.getString("公車業者");
				String routeName = dataSet.getString("路線");
				String headsign = dataSet.getString("路線說明");				
				String routeMapImageUrl = dataSet.getString("路線圖");
				//爬蟲撈取路線圖URL
				String phurl = deepcraw(routeMapImageUrl);
				String outbound = dataSet.getString("去程");
				String inbound = dataSet.getString("回程");
				
				pstmt.setString(1, operatorName);
				pstmt.setString(2, routeName);
				pstmt.setString(3, headsign);
				pstmt.setString(4, phurl);
				pstmt.setString(5, outbound);
				pstmt.setString(6, inbound);
				
				pstmt.executeUpdate();
			}
		
			System.out.println("BusData Insert OK :" + new Date());
		} catch (Exception e) {
				System.out.println(e.toString());
		}
		System.out.println("bus更新完成");
	}	
	
	
	
	//爬蟲作業 : 抓取路徑圖URL
	public static String deepcraw(String url) {
		
		String resulturl=null;
		try {
			String res=null;
			Document doc = Jsoup.connect(url).get();
			Elements elems = doc.select("img[src~=(?i)\\.(png|jpe?g)]");
			
				for(Element elem : elems) {
					String strelem = elem.toString();
					int pos = strelem.indexOf("alt");
					if(pos==5) {
						
						int jpgpos1 = strelem.indexOf(".jpg");
						int jpgpos2 =  strelem.indexOf(".JPG");
						int pngpos = strelem.indexOf(".png"); 
						int jpegpos = strelem.indexOf(".jpeg");
						String startVar = "/df";
						
						String endVar1 = ".jpg";
						String endVar2 = ".JPG";
						String endVar3 = ".png";
						String endVar4 = ".jpeg";
						int end = 0;
						
						int start = strelem.indexOf(startVar);
						if(jpgpos1 > 0) {
							end = strelem.indexOf(endVar1)+4;
						}else if(jpgpos2 > 0) {
							end = strelem.indexOf(endVar2)+4;
	
						}else if(pngpos > 0){
							end = strelem.indexOf(endVar3)+4;	
						}else if(jpegpos > 0) {
							end = strelem.indexOf(endVar4)+4;
						}
						res = strelem.substring(start, end);
						resulturl = "https://www.traffic.taichung.gov.tw" + res;
						return resulturl;
						
					}else {
						resulturl = null;		
					}
				}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return resulturl ;
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
			PreparedStatement pstmt = conn.prepareStatement("TRUNCATE TABLE bus");
			pstmt.execute();
		}catch(Exception e){
			System.out.println(e.toString());
		}	
	}
}
