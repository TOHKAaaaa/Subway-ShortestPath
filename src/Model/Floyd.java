package Model;

public class Floyd {
	// 任意两点之间路径长度
	int[][] length = null;
	// 任意两点之间的路径
	int[][][] path = null;
	
	public Floyd(int[][] G) {
		// 图G的行数
		int row = G.length;
		// 定义任意两点之间经过的点
		int[][] spot = new int[row][row];
		// 记录一条路径
		int[] onePath = new int[row];
		this.length = G;
		path = new int[row][row][];
		for (int i = 0; i < row; i++)
			// 初始化
			for (int j = 0; j < row; j++)
				spot[i][j] = -1;
		for (int i = 0; i < row; i++)
			// 假设任意两点之间的没有路径
			onePath[i] = -1;
		for (int u = 0; u < row; ++u)
			for (int v = 0; v < row; ++v)
				for (int w = 0; w < row; ++w)
					if (length[v][w] > length[v][u] + length[u][w]) {
						// 如果存在更短路径则取更短路径
						length[v][w] = length[v][u] + length[u][w];
						spot[v][w] = u;
					}
		for (int i = 0; i < row; i++) {
			int[] point = new int[1];
			for (int j = 0; j < row; j++) {
				point[0] = 0;
				onePath[point[0]++] = i;
				// 更新路径
				outputPath(spot, i, j, onePath, point);
				path[i][j] = new int[point[0]];
				for (int s = 0; s < point[0]; s++)
					path[i][j][s] = onePath[s];
			}
		}
	}
	private void outputPath(int[][] spot, int i, int j, int[] onePath,
			int[] point) {
		// 输出i 到j 的路径的实际代码,point[]记录一条路径的长度
		if (i == j)
			return;
		if (spot[i][j] == -1)
			// 中间没有经过其他节点，即直接到达
			onePath[point[0]++] = j;
		else {
			outputPath(spot, i, spot[i][j], onePath, point);
			outputPath(spot, spot[i][j], j, onePath, point);
		}
	}
 
	public int[] getPath(int start, int stop) {
		return path[start][stop];
	}
}
