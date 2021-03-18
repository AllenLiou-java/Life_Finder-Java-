package model.dao;

import java.util.List;

import model.bean.BikeBean;


public interface BikeDAO {

	List<BikeBean> select();

	//計算總頁數
	int getPage();

	//查詢指定頁的資料
	List<BikeBean> dataInPg(int pageNo);

}