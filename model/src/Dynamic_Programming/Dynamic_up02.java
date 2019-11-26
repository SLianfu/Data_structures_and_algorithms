package Dynamic_Programming;

/**
 * 动态规划：0 1 背包问题升级版 :求背包重量不超过w(9),价值最高的情况，输出该最高值
 * 以及 双11 凑单问题
 * @author slf
 * 还是把整个求解过程分为 n 个阶段，每个阶段会决策第一个物品是否放入背包中
 *   决策完之后背包中的总重量和总价值 会有多种情况 即多种不同的状态
 *   我们用一个二维数组 states[n][w+1]，来记录每层可以达到的不同状态
 *   	存储的是：当前状态对应的最大总价值
 *   我们把每一层中 (i, cw) 重复的状态（节点）合并，只记录 cv 值最大的那个状态
 *   	然后基于这些状态来推导下一层的状态
 *
 */
public class Dynamic_up02 {

	private int maxW = Integer.MIN_VALUE; // 结果放到maxW中
	private static int[] weight = {2,2,4,6,3};  // 物品重量
	private static int[] value = {3,4,8,9,6}; // 物品的价值
	private static int n = 5; // 物品个数
	private static int w = 9; // 背包承受的最大重量
	
	public static void main(String[] args) {
		Dynamic_up02 dy = new Dynamic_up02();
		int knapsack3 = Dynamic_up02.knapsack3(weight, value, n, w);
		System.out.println("背包中价格最高值为：knapsack3="+knapsack3);
		
		Dynamic_up02.double11advance(items, sum, ww);
	}

	//weight【】 重量数组， value【】 价值数组，n 物品的数量， W 背包的容量
	public static int knapsack3(int[] weight, int[] value, int n, int w) {
	  int[][] states = new int[n][w+1]; //记录每层可以达到的状态，记录值是该阶段的最大价值
	  for (int i = 0; i < n; ++i) { // 初始化states
	    for (int j = 0; j < w+1; ++j) {
	      states[i][j] = -1;
	    }
	  }
	  states[0][0] = 0;	//用00来记录第一个物品 不放入背包，
	  if (weight[0] <= w) {
	    states[0][weight[0]] = value[0];	//用02记录第一个物品放入背包
	  }
	  for (int i = 1; i < n; ++i) { //动态规划，状态转移
	    for (int j = 0; j <= w; ++j) { // 不选择第i个物品
	      if (states[i-1][j] >= 0) states[i][j] = states[i-1][j];//不等于-1即为true
	    }
	    
	    for (int j = 0; j <= w-weight[i]; ++j) { // 选择第i个物品
	      if (states[i-1][j] >= 0) {
	        int v = states[i-1][j] + value[i];	//背包中包括第i物品的总价值+ 第i+1物品的价值
	        if (v > states[i][j+weight[i]]) { //states[i][j+weight[i]]【这个也是总价值】 （价值） < v（总价值）?这个是什么鬼？
	        	//如果总价值 大于 【只需要记录价值最大的】
	          states[i][j+weight[i]] = v;	//【这里指进行到其他状态，当别的总价值< V时，用V代替】
	        }
	      }
	    }
	  }
	  // 找出最大值
	  int maxvalue = -1;
	  for (int j = 0; j <= w; ++j) {
	    if (states[n-1][j] > maxvalue) maxvalue = states[n-1][j];
	  }
	  return maxvalue;
	}
	

	private static int[] items = {30,60,90,15,18,22,166,133,120,10,10}; //商品价格
	private static int sum = 6; //商品个数【要初始化】
	private static int ww = 200; //表示满减条件
	
	// items商品价格，n商品个数, w表示满减条件，比如200
	public static void double11advance(int[] items, int sum, int ww) {
	  boolean[][] states = new boolean[sum][3*ww+1];//超过3倍就没有薅羊毛的价值了
	  states[0][0] = true;  // 第一行的数据要特殊处理
	  if (items[0] <= 3*ww) {
	    states[0][items[0]] = true;
	  }
	  for (int i = 1; i < sum; ++i) { // 动态规划
	    for (int j = 0; j <= 3*ww; ++j) {// 不购买第i个商品
	      if (states[i-1][j] == true) states[i][j] = states[i-1][j];
	    }
	    for (int j = 0; j <= 3*ww-items[i]; ++j) {//购买第i个商品
	      if (states[i-1][j]==true) states[i][j+items[i]] = true;
	    }
	  }

	  /**
	   * 状态 (i, j) 只有可能从 (i-1, j) 或者 (i-1, j-value[i]) 两个状态推导过来
	   * 如果 states[i-1][j] 可达，就说明我们没有选择购买第 i 个商品，
	   * 如果 states[i-1][j-value[i]] 可达，那就说明我们选择了购买第 i 个商品。
	   * 如果两个都可达，就随意选择一个【就是买了这个商品 和 没买这个商品 都可以凑足200块】
	   */
	  int j;
	  for (j = ww; j < 3*ww+1; ++j) { 
	    if (states[sum-1][j] == true) break; // 输出结果大于等于w的最小值
	  }
	  if (j == 3*ww+1) return; // 没有可行解
	  //打印出选择购买哪些商品的【倒序】
	  for (int i = sum-1; i >= 1; --i) { // i表示二维数组中的行，j表示列
	    if(j-items[i] >= 0 && states[i-1][j-items[i]] == true) {
	      System.out.print(items[i] + " "); // 购买这个商品
	      j = j - items[i];
	    } // else 没有购买这个商品，j不变。
	  }
	  //最后  	  如果 j 等于 0；输出 第一个 物品的价格
	  if (j != 0) System.out.print(items[0]);
	}
	
}
