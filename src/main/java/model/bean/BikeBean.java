package model.bean;

import org.json.JSONObject;

public class BikeBean {
	
	private String id;
	private String position;
	private String ename;
	private String caddress;
	private Double x;
	private Double y;
	private String carea;
	private Integer availableCNT;
	private Integer empCNT;
	private String updateTime;
	
	
	@Override
	public String toString() {
		return "BikeBean [id=" + id + ", position=" + position + ", ename=" + ename + ", caddress=" + caddress + ", x="
				+ x + ", y=" + y + ", carea=" + carea + ", availableCNT=" + availableCNT + ", empCNT=" + empCNT
				+ ", updateTime=" + updateTime + "]";
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getCaddress() {
		return caddress;
	}
	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public String getCarea() {
		return carea;
	}
	public void setCarea(String carea) {
		this.carea = carea;
	}
	public Integer getAvailableCNT() {
		return availableCNT;
	}
	public void setAvailableCNT(Integer availableCNT) {
		this.availableCNT = availableCNT;
	}
	public Integer getEmpCNT() {
		return empCNT;
	}
	public void setEmpCNT(Integer empCNT) {
		this.empCNT = empCNT;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	
	
	
	

}
