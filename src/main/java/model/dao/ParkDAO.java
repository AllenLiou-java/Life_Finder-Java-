package model.dao;

import java.util.List;

import model.bean.ParkBean;

public interface ParkDAO {

	List<ParkBean> select();

	//計算總頁數
	int getPage();

	//查詢指定頁的資料
	List<ParkBean> dataInPg(int pageNo);
	
	boolean parkOutJson();

}