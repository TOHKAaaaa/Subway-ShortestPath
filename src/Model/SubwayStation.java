package Model;

import java.util.List;

public class SubwayStation {
	String name;
	List<StationLine> stationLines;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<StationLine> getStationLines() {
		return stationLines;
	}
	public void setStationLines(List<StationLine> stationLines) {
		this.stationLines = stationLines;
	}
	public StationLine getStationLine(int i) {
		return stationLines.get(i);
	}
}
