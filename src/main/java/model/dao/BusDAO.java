package model.dao;

import java.util.List;

import model.bean.BusBean;

public interface BusDAO {

	List<BusBean> select();

	int getPage();

	List<BusBean> dataInPg(int pageNo);
	
	void insert(String json);
	
	void deleteOldData();

}