package Dynamic_Programming;

/**
 * 二维数组 求 右上角节点到左下角节点的最短路径问题
 * @author slf
 *
 */
public class MinDist {

	public static void main(String[] args) {
		MinDist minDist = new MinDist();
		int minDist2 = minDist.minDist(matrix.length-1, matrix[0].length-1);//数组长度减一
		System.out.println("最短路径：" + minDist2);
		System.out.println("--------------");
		int minDistDP = minDist.minDistDP(matrix, n);
		System.out.println("最短路径："+minDistDP);
	}
	
	//二维数组
	private static int[][] matrix =  {{1,3,5,9}, {2,1,3,4},{5,2,6,7},{6,8,4,3}};
	private static int n = 4;
	private int[][] mem = new int[4][4];
	
	public int minDist(int i, int j) { // 调用minDist(n-1, n-1);
	  if (i == 0 && j == 0) return matrix[0][0];
	  if (mem[i][j] > 0) return mem[i][j];
	  int minLeft = Integer.MAX_VALUE;
	  if (j-1 >= 0) {
	    minLeft = minDist(i, j-1);
	  }
	  int minUp = Integer.MAX_VALUE;
	  if (i-1 >= 0) {
	    minUp = minDist(i-1, j);
	  }
	  
	  int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
	  mem[i][j] = currMinDist;
	  return currMinDist;
	}
	
	//方法1 n为数组长度：n*n
	public int minDistDP(int[][] matrix, int n) {
	  int[][] states = new int[n][n];
	  int sum = 0;
	  for (int j = 0; j < n; ++j) { // 初始化states的第一行数据
	    sum += matrix[0][j];
	    states[0][j] = sum;
	  }
	  sum = 0;
	  for (int i = 0; i < n; ++i) { // 初始化states的第一列数据
	    sum += matrix[i][0];
	    states[i][0] = sum;
	  }
	  for (int i = 1; i < n; ++i) {
	    for (int j = 1; j < n; ++j) {
	      states[i][j] = 
	            matrix[i][j] + Math.min(states[i][j-1], states[i-1][j]);
	    }
	  }
	  return states[n-1][n-1];
	}

}
