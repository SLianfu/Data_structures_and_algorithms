package backtrack;

/**
 * 回溯算法：
 * 1，八皇后问题【不要想着一步一步推了，根据深度优先遍历，当遍历到第几行所有单位列都不满足时，例如当第(row=5)六行都不满足，】
 * @author slf
 *
 */
public class Backtrack_algorithm {


	int[] result = new int[8];//全局或成员变量,下标表示行,值表示queen存储在哪一列
	int sum = 0;//92种
	public int maxW = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		Backtrack_algorithm back = new Backtrack_algorithm();
		back.cal8queens(0);//back.cal8queens(8);直接输入8的话会输出第一列8个Q
	}
	public void cal8queens(int row) { // 调用方式：cal8queens(0);
	  if (row == 8) { // 8个棋子都放置好了，打印结果
	    printQueens(result);
	    System.out.println("sum="+ ++sum);
	    return; // 8行棋子都放好了，已经没法再往下递归了，所以就return
	  }
	  /*
	   * 回溯法 就是在一个 for循环中调用递归（循环中的参数为B），递归时（递归参数为A）
	   *  把参数加一(改变参数)，当回溯到某个值都不满足继续递归时（递归方法（A参数）），
	   * 开始返回（这里没有return ，遍历完后返回），接着继续遍历 （递归方法（A参数-1的下一位置））
	   */
	  for (int column = 0; column < 8; ++column) { // 每一行都有8中放法:【定点为（0，0）输出图案在第四象限】
	    if (isOk(row, column)) { // 有些放法不满足要求【isOk（0，0）-》ok-》第一个坐标（0，0）】
	    //当isOk(5,0~7)都不满足时；回溯：怎么回溯呢？又是怎么判断他回溯到哪里的？
	    //cal8queens(0)，(column = 0)调用cal8queens(1)(column = 2)，继续调用cal8queens(2)(column = 4)、
	    //cal8queens(3)(column = 1)、cal8queens(4)(column = 3)、cal8queens(5)没有column满足，开始返回
	    	//返回到cal8queens(4)(column要等于7)，继续调用cal8queens(5)看看有没有满足的column，以此类推
	    	
	      result[row] = column; // 第row行的棋子放到了column列
	      cal8queens(row+1); // 考察下一行
	    }
	  }
	}
	
	/**
	 * 
	 * @param row：第几行：1表示第0行的下一行【这是根据输出来算的，先输出第0行，在在下一行输出第一行】
	 * @param column：第几列；0表示地0列，1表示第一列
	 * @return true则在row行，column列填充一个Q
	 * 先是（0，0）
	 * 然后判断（1，0）：leftup:-1,rightup:1; i = row-1 = 0 ; result[0] == column=0; 0 == 
	 */
	private boolean isOk(int row, int column) {//判断row行column列放置是否合适
	  int leftup = column - 1, rightup = column + 1;
	  for (int i = row-1; i >= 0; --i) { // 逐行往上考察每一行
	    if (result[i] == column) return false; // 第i行的column列有棋子吗？
	    if (leftup >= 0) { // 考察左上对角线：第i行leftup列有棋子吗？
	      if (result[i] == leftup) return false;
	    }
	    if (rightup < 8) { // 考察右上对角线：第i行rightup列有棋子吗？
	      if (result[i] == rightup) return false;
	    }
	    --leftup; ++rightup;//当row-1行>= 0 时，进行row-1-1判断，每往上一层相应的左上节点的纵坐标要-1，右上角纵坐标+1
	  }
	  return true;
	}
	
	private void printQueens(int[] result) { // 打印出一个二维矩阵
	  for (int row = 0; row < 8; ++row) {
	    for (int column = 0; column < 8; ++column) {
	      if (result[row] == column) System.out.print("Q ");
	      else System.out.print("* ");
	    }
	    System.out.println();
	  }
	  System.out.println();
	}
}
