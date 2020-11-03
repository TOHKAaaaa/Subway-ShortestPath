package Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import FileRead.ReadTxt;
import Model.Station;
import Model.StationMap;;

public class Main {
	private static StationMap<String> graph;
	public static void main(String[] args) {
		String Path="E:\\winR\\eclipse-workspace\\ShortestPath\\src\\data.txt";
		List<Station> allRoute=new ReadTxt().readText(Path);
		List<String> vert = new ArrayList<String>();
		for(Station station:allRoute) {
			String[] strings=station.getAllStation().toArray(new String[station.getAllStation().size()]);
			for (String t : strings) {
				if (!vert.contains(t)) {
					vert.add(t);
				}
			}
		}

		graph = new StationMap<String>(vert);
		for(Station station:allRoute) {
			String[] s=station.getAllStation().toArray(new String[station.getAllStation().size()]);
			for (int i = 0; i < s.length - 1; i++)
				graph.addEdge(s[i], s[i + 1], 1);
			
		}
		
		Scanner input = new Scanner(System.in);
		System.out.print("请输入起始站点：");
		String start = input.nextLine().trim();
		System.out.print("请输入目标站点：");
		String stop = input.nextLine().trim();
		if (!graph.vertex.contains(start) || !graph.vertex.contains(stop)) {
			System.out.println("地图中不包含该站点！");
			input.close();
			return;
		}
		StringBuilder stringBuilder = graph.findByFloyd(start, stop,graph.getSubTrainMatrix(),allRoute);// 包含自身站点
		File file=new File("E:\\winR\\eclipse-workspace\\ShortestPath\\src\\result.txt");
		input.close();
		try {
			if(file.exists()) {
				file.delete();
			}
			BufferedWriter bw=new BufferedWriter(new FileWriter(file));
			bw.write(stringBuilder.toString());
			//清空缓存
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
