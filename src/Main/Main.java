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
		System.out.print("��������ʼվ�㣺");
		String start = input.nextLine().trim();
		System.out.print("������Ŀ��վ�㣺");
		String stop = input.nextLine().trim();
		if (!graph.vertex.contains(start) || !graph.vertex.contains(stop)) {
			System.out.println("��ͼ�в�������վ�㣡");
			input.close();
			return;
		}
		StringBuilder stringBuilder = graph.findByFloyd(start, stop,graph.getSubTrainMatrix(),allRoute);// ��������վ��
		File file=new File("E:\\winR\\eclipse-workspace\\ShortestPath\\src\\result.txt");
		input.close();
		try {
			if(file.exists()) {
				file.delete();
			}
			BufferedWriter bw=new BufferedWriter(new FileWriter(file));
			bw.write(stringBuilder.toString());
			//��ջ���
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
