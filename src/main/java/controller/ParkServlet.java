package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.ParkBean;
import model.dao.ParkDAO;
import model.dao.ParkDaoJdbc;

@WebServlet(
		urlPatterns = {"/pages/park.controller"}
)
public class ParkServlet extends HttpServlet {

	public ParkServlet() {
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
	
	
	
	ParkDAO parkDao = new ParkDaoJdbc();
	int recordCount = parkDao.getPage();
	
	
	int pageNo=0;	
	if(pageno != null) {
		pageNo = Integer.parseInt(pageno);	
	}else {
		pageNo = 1;
	}
	
	
	List<ParkBean> list = new ArrayList<ParkBean>();
	list = parkDao.dataInPg(pageNo);
	//System.out.println(list + ":" + recordCount);
	
	//根據MODEL執行結果呼叫VIEW
	
	request.setAttribute("recordCount", recordCount);
	request.setAttribute("lists", list);
	request.setAttribute("pageNos", pageNo);
	request.getRequestDispatcher("/pages/parkAddMap.jsp").forward(request, response);
		
		
	}

}
