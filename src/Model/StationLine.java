package Model;

import java.util.List;

public class StationLine {
	String station;
	List<String> line;
	List<StationLine> neighbor;
	
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public List<String> getLine() {
		return line;
	}
	public void setLine(List<String> line) {
		this.line = line;
	}
	public List<StationLine> getNeighbor() {
		return neighbor;
	}
	public void setNeighbor(List<StationLine> neighbor) {
		this.neighbor = neighbor;
	}
	
	
}
