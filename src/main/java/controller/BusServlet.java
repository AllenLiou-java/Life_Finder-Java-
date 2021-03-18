package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.BikeBean;
import model.bean.BusBean;
import model.dao.BusDAO;
import model.dao.BusDaoJdbc;



@WebServlet(
		urlPatterns = {"/pages/bus.controller"}
)
public class BusServlet extends HttpServlet {

	public BusServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	response.setCharacterEncoding("utf-8");
	//接收資料
	String pageno = request.getParameter("pageNos");
	
	//驗證資料
	//轉換資料
	
	//呼叫MODEL
	
	
	
	BusDAO busDao = new BusDaoJdbc();
	int recordCount = busDao.getPage();
	
	
	int pageNo=0;	
	if(pageno != null) {
		pageNo = Integer.parseInt(pageno);	
	}else {
		pageNo = 1;
	}
	
	
	List<BusBean> list = new ArrayList<BusBean>();
	list = busDao.dataInPg(pageNo);
	//System.out.println(list + ":" + recordCount);
	
	//根據MODEL執行結果呼叫VIEW
	
	request.setAttribute("recordCount", recordCount);
	request.setAttribute("lists", list);
	request.setAttribute("pageNos", pageNo);
	request.getRequestDispatcher("/pages/bus.jsp").forward(request, response);
		
		
	}

}
