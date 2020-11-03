package Model;

import java.util.ArrayList;
import java.util.List;

public class Station {
	public String stationNo;
	public List<String> allStation=new ArrayList<String>();
	
	public String getStationNo() {
		return stationNo;
	}
	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}
	public List<String> getAllStation() {
		return allStation;
	}
	public void setAllStation(List<String> allStation) {
		this.allStation = allStation;
	}
	
}
