package FileTxt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Model.StationLine;
import Model.SubwayStation;

public class FileRead {
	public static List<SubwayStation> lineset = new ArrayList<SubwayStation>();
	public FileRead(String subwayname) throws FileNotFoundException,IOException {
		// TODO Auto-generated constructor stub
		File file = new File(subwayname);
		InputStreamReader input = new InputStreamReader(new FileInputStream(file));
		BufferedReader bufferedReader = new BufferedReader(input);
		//��·����,����ȷ����·����
		String str = "";
		str = bufferedReader.readLine();
		int linenum = Integer.parseInt(str);
		//��������
		List<String> subwayline = new ArrayList<String>();
		
		for(int i=0;i<linenum;i++) {
			//��subway.txt�е����ݰ��ж��룬������ȥ���еĿո���������
			str = bufferedReader.readLine();
			//��¼������·��
			String[] subwaystation = str.split(" ");
			//subwaystation��¼��·����stationlines��¼ÿ���ߵ���·�Լ������ߣ�ʹ��list�洢��
			SubwayStation subwayStation = new SubwayStation();
			List<StationLine> stationLines = new ArrayList<StationLine>();
			
			subwayStation.setName(subwaystation[0]);
			for(int j=0;j<subwaystation.length-1;j++) {
				StationLine stationLine = new StationLine();
				//��¼ÿ��վ��
				List<String> linename = new ArrayList<String>();
				List<StationLine> neighborLines = new ArrayList<StationLine>();
				
				stationLine.setStation(subwaystation[j+1]);
				int isExit = 0;
				int isCircle = 0;
				int lineindex;
				int flagnum=0,stationnum=0;
				for(lineindex = 0;lineindex<lineset.size();i++) {
					for(int k = 0;k<lineset.get(lineindex).getStationLines().size();k++) {
						flagnum = lineindex;
						stationnum = k;
						stationLine = lineset.get(lineindex).getStationLines().get(k);
						linename = stationLine.getLine();
						neighborLines = stationLine.getNeighbor();
						linename.add(subwayStation.getName());
						isExit = 1;
						break;
					}
				}
				if(isExit==0)
					linename.add(subwayStation.getName());
				int p;
				for(p=1;p<j+1;p++) {
					if(subwaystation[p].equals(stationLine.getStation())) {
						stationLine = subwayStation.getStationLine(p-1);
						linename = stationLine.getLine();
						neighborLines = stationLine.getNeighbor();
					}
				}
				if(j!=0) 
					neighborLines.add(subwayStation.getStationLines().get(j-1));
				stationLine.setLine(linename);
				stationLine.setNeighbor(neighborLines);
				if(j!=0) {
					List<StationLine> stationLines2 = new ArrayList<StationLine>();
					stationLines2 = subwayStation.getStationLines().get(j-1).getNeighbor();
					stationLines2.add(stationLine);
					subwayStation.getStationLines().get(j-1).setNeighbor(stationLines2);
				}
				stationLines.add(stationLine);
				if(isExit==1) {
					List<StationLine> newStationLines = new ArrayList<StationLine>();
					newStationLines = lineset.get(flagnum).getStationLines();
					newStationLines.set(stationnum,stationLine);
					lineset.get(flagnum).setStationLines(newStationLines);
				}
				if(isCircle==1) 
					stationLines.set(p-1, stationLine);
				subwayStation.setStationLines(stationLines);
			}
			lineset.add(subwayStation);
		}
	}
}
