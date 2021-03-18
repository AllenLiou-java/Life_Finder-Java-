package model.bean;

public class BusBean {

	private String operatorName;
	private String routeName;
	private String headsign;
	private String routeMapImageUrl;
	private String outbound;
	private String inbound;
	
	@Override
	public String toString() {
		return "BusBean [operatorName=" + operatorName + ", routeName=" + routeName + ", headsign=" + headsign
				+ ", routeMapImageUrl=" + routeMapImageUrl + ", outbound=" + outbound + ", inbound=" + inbound + "]";
	}
	
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getHeadsign() {
		return headsign;
	}
	public void setHeadsign(String headsign) {
		this.headsign = headsign;
	}
	public String getRouteMapImageUrl() {
		return routeMapImageUrl;
	}
	public void setRouteMapImageUrl(String routeMapImageUrl) {
		this.routeMapImageUrl = routeMapImageUrl;
	}
	public String getOutbound() {
		return outbound;
	}
	public void setOutbound(String outbound) {
		this.outbound = outbound;
	}
	public String getInbound() {
		return inbound;
	}
	public void setInbound(String inbound) {
		this.inbound = inbound;
	}

	
}
