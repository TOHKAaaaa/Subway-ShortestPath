package Model;

public class Floyd {
	// ��������֮��·������
	int[][] length = null;
	// ��������֮���·��
	int[][][] path = null;
	
	public Floyd(int[][] G) {
		// ͼG������
		int row = G.length;
		// ������������֮�侭���ĵ�
		int[][] spot = new int[row][row];
		// ��¼һ��·��
		int[] onePath = new int[row];
		this.length = G;
		path = new int[row][row][];
		for (int i = 0; i < row; i++)
			// ��ʼ��
			for (int j = 0; j < row; j++)
				spot[i][j] = -1;
		for (int i = 0; i < row; i++)
			// ������������֮���û��·��
			onePath[i] = -1;
		for (int u = 0; u < row; ++u)
			for (int v = 0; v < row; ++v)
				for (int w = 0; w < row; ++w)
					if (length[v][w] > length[v][u] + length[u][w]) {
						// ������ڸ���·����ȡ����·��
						length[v][w] = length[v][u] + length[u][w];
						spot[v][w] = u;
					}
		for (int i = 0; i < row; i++) {
			int[] point = new int[1];
			for (int j = 0; j < row; j++) {
				point[0] = 0;
				onePath[point[0]++] = i;
				// ����·��
				outputPath(spot, i, j, onePath, point);
				path[i][j] = new int[point[0]];
				for (int s = 0; s < point[0]; s++)
					path[i][j][s] = onePath[s];
			}
		}
	}
	private void outputPath(int[][] spot, int i, int j, int[] onePath,
			int[] point) {
		// ���i ��j ��·����ʵ�ʴ���,point[]��¼һ��·���ĳ���
		if (i == j)
			return;
		if (spot[i][j] == -1)
			// �м�û�о��������ڵ㣬��ֱ�ӵ���
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
