package model.bean;

public class ParkBean {

	private Double lat;
	private Double lng;
	private String name;
	private String address;
	private Integer available;
	private Integer total;

	
	
	@Override
	public String toString() {
		return "ParkBean [lat=" + lat + ", lng=" + lng + ", name=" + name + ", address=" + address + ", available="
				+ available + "]";
	}

	
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
