package Model;

import java.util.ArrayList;
import java.util.List;
import Model.Floyd;

public class StationMap<T> {
	// 图的邻接矩阵
	protected int[][] subTrainMatrix; 
	// 设置最大权值,若距离等于100，则说明两个点之间没有边
	private static final int MAX_WEIGHT =100; 
	private int[] dist;
	public List<T> vertex;
 
	public int[][] getSubTrainMatrix() {
		return subTrainMatrix;
	}
 
	public void setVertex(List<T> vertices) {
		this.vertex = vertices;
	}
 
	public List<T> getVertex() {
		return vertex;
	}
 
	public int[] getDist() {
		return dist;
	}

	public void setDist(int[] dist) {
		this.dist = dist;
	}
	public int getVertexSize() {
		return this.vertex.size();
	}
 
	public int vertexCount() {
		return subTrainMatrix.length;
	}
 
	public StationMap(int size) {
		this.vertex = new ArrayList<T>();
		this.subTrainMatrix = new int[size][size];
		this.dist = new int[size];
		// 初始化邻接矩阵
		for (int i = 0; i < size; i++) { 
			for (int j = 0; j < size; j++) {
				this.subTrainMatrix[i][j] = (i == j) ? 0 : MAX_WEIGHT;
			}
		}
		
	}
	
	public StationMap(List<T> vertices) {
		this.vertex = vertices;
		int size = getVertexSize();
		this.subTrainMatrix = new int[size][size];
		this.dist = new int[size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.subTrainMatrix[i][j] = (i == j) ? 0 : MAX_WEIGHT;
			}
		}
		
	}
	
	public int getPosInvertex(T s) {
		return vertex.indexOf(s);
	}
 
	public void insertEdge(T start, T stop, int weight) { 
		int n = subTrainMatrix.length;
		int i = getPosInvertex(start);
		int j = getPosInvertex(stop);
		if (i >= 0 && i < n && j >= 0 && j < n
				&& this.subTrainMatrix[i][j] == MAX_WEIGHT && i != j) {
			this.subTrainMatrix[i][j] = weight;
			this.subTrainMatrix[j][i] = weight;
		}
	}
 
	public void addEdge(T start, T dest, int weight) {
		this.insertEdge(start, dest, weight);
	}
 
	public void removeEdge(String start, String stop) { 
		int i = vertex.indexOf(start);
		int j = vertex.indexOf(stop);
		if (i >= 0 && i < vertexCount() && j >= 0 && j < vertexCount()
				&& i != j)
			this.subTrainMatrix[i][j] = MAX_WEIGHT;
	}

	public StringBuilder findByFloyd(T start, T stop,int[][] sub,List<Station> allroute) {
		List<String> change=new ArrayList<>();
		Floyd floyd = new Floyd(sub);
		int startPos = getPosInvertex(start);
		int stopPos = getPosInvertex(stop);
		int[] path = floyd.getPath(startPos, stopPos);
		StringBuilder sb = new StringBuilder();
//		System.out.print(start + " 到 " + stop + " 的路线为: ");
		sb.append(start + " 到 " + stop + " 的路线为: ");
		int k=0;
		for (int i : path) {
			k++;
			sb.append(vertex.get(i) + " --> ");
			if(k%5==0) {
				sb.append(System.lineSeparator());
			}
		}
		sb.delete(sb.lastIndexOf(" --> "), sb.length());
		
		for(int j=1;j<(path.length)-1;j++) {
			int i=path[j];
			
			String s1=findsameStation(vertex.get(path[j-1])+"", vertex.get(i)+"", allroute);
			String s2=findsameStation(vertex.get(i)+"", vertex.get(path[j+1])+"", allroute);
			if(!s1.equals(s2)) {
				change.add(vertex.get(i)+"");
			}
		}
		System.out.println(sb.toString());
		System.out.println("换乘站:");
		sb.append(System.lineSeparator()+"换乘站:"+System.lineSeparator());
		for(String s:change) {
			System.out.println(s);
			sb.append(s+System.lineSeparator());
		}
		System.out.println(start + " -> " + stop + " 经过的站点数为： " + path.length);
		sb.append(start + " -> " + stop + " 经过的站点数为： " + path.length);
		
		
		return sb;

	}
 
	public String findsameStation(String s1,String s2,List<Station> allroute) {
		String route="";
		List<String> routes1=new ArrayList<>();
		List<String> routes2=new ArrayList<>();
		for(Station r:allroute) {
			for(String s:r.getAllStation()) {
				if(s.equals(s1)) 
					routes1.add(r.getStationNo());
				if(s.equals(s2)) 
					routes2.add(r.getStationNo());
			}
		}
		for(int i=0;i<routes1.size();i++) 
			for(int j=0;j<routes2.size();j++) 
				if(routes1.get(i).equals(routes2.get(j))) 
					route=routes1.get(i);
		return route;
	}
}