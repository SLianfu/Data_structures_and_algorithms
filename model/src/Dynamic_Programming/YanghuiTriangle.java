package Dynamic_Programming;

/**
 * 杨辉三角：现在对它进行一些改造。每个位置的数字可以随意填写，
 * 经过某个数字只能到达下面一层相邻的两个数字
 * 从最高层移动到最底层的最短路径长度
 * @author slf
 *
 */
public class YanghuiTriangle {

	public static void main(String[] args) {
		YanghuiTriangle trangle = new YanghuiTriangle();
		int yanghuiTriangle = trangle.yanghuiTriangle(matrix);
		System.out.println("杨辉三角最短路径："+yanghuiTriangle);
		int yanghuiTriangle2 = trangle.yanghuiTriangle2(matrix);
		System.out.println("杨辉三角最短路径2："+yanghuiTriangle2);
	}
	//杨辉三角，用二维数组表示
	private static int[][] matrix = {{5},{7,8},{2,3,4},{4,9,6,1},{2,7,9,4,5}};

	public int yanghuiTriangle(int[][] matrix) {
	    int[][] state = new int[matrix.length][matrix.length];
	    state[0][0] = matrix[0][0];
	    for (int i = 1; i < matrix.length; i++) {
	        for (int j = 0; j < matrix[i].length; j++) {
	            if (j == 0) state[i][j] = state[i - 1][j] + matrix[i][j];
	            else if (j == matrix[i].length - 1) state[i][j] = state[i - 1][j - 1] + matrix[i][j];
	            else {
	                int top1 = state[i - 1][j - 1];
	                int top2 = state[i - 1][j];
	                state[i][j] = Math.min(top1, top2) + matrix[i][j];
	            }
	        }
	    }
	    int minDis = Integer.MAX_VALUE;
	    for (int i = 0; i < matrix[matrix.length - 1].length; i++) {
	        int distance = state[matrix.length - 1][i];
	        if (distance < minDis) minDis = distance;
	    }
	    return minDis;
	}
	
	//倒推，即从底部往顶部递推计算会更好些，且空间消耗可以进一步优化，代码也更简洁些
	public int yanghuiTriangle2(int[][] matrix) {
	    int length = matrix.length; //一维数组的长度：5
	    // 用于存储每一层的状态
	    int[] min = new int[length + 1];
	    for (int i = length - 1; i >= 0; i--) {
	        int[] rawNums = matrix[i];
	        int rowLength = rawNums.length;
	        for (int j = 0; j < rowLength; j++) {
	            min[j] = Math.min(min[j], min[j + 1]) + rawNums[j];
	        }
	    }
	    return min[0];
	}

}
