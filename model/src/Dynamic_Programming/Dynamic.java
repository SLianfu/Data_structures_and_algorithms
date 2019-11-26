package Dynamic_Programming;

/**
 * 动态规划：0 1 背包问题
 * @author slf
 *
 */
public class Dynamic {
	private int maxW = Integer.MIN_VALUE; // 结果放到maxW中
	private static int[] weight = {2,2,4,6,3};  // 物品重量
	//private static int[] weight = {8,10	,20};  // 物品重量
	private static int n = 5; // 物品个数
	private static int w = 9; // 背包承受的最大重量
	 //计数：计算次数
	static int sum = 0;
	
	public static void main(String[] args) {
		Dynamic dynamic = new Dynamic();
		int index = dynamic.knapsack2(weight, n, w);
		System.out.println(index);//输出的是啥？？：直接输出 最大重量 9
	}

	public static int knapsack2(int[] items, int n, int w) {
	  boolean[] states = new boolean[w+1]; // 默认值false
	  states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
	  if (items[0] <= w) {
	    states[items[0]] = true;
	  }
	  /**
	   * 分解过程：
		 i = 1时；states[0],states[items[0]]=states[2] = true;
		j = 7(允许第二个物品放入)，j--,当j=0和j = 2时：states[j] = true
		执行：states[0+2或者2+2] = true;    此时为true的重量【装入背包的重量情况为0，2，4】
		i = 2；j = 9 - 4 = 5；然后 j-- ;states[0,2,4] = true;
		    states[j + items[i]] = states[4，6，8]= true【这是放入第三个物品】合并没放入第三物品的背包情况：states[0,2,4]==>states[0,2,4,6,8]
		i = 3；j = 9 -6 =3; j--；states[0,2] = true; 加上items[3] = 6
		    states[6,8] = true; 合并未加入：states[0,2,4,6,8]
		i = 4; j = 9 -3 = 6;j--；states[0,2,4,6] = true; + 3
		    states[3,5,7,9] = true；合并：states[ 0,2,3,4,5,6,7,8,9]【完成，跳出for】
		
		输出背包内最大的重量：假设 int i = w; i >= 0; i --
		    如果 states[i] 【背包重要为9的情况为true】 直接输出 最大重量 9
	   */
	  for (int i = 1; i < n; ++i) { // 动态规划
		  //j 需要从大到小来处理：比如 j = 0, item[i] = 5, w=10，： for (int j = 0; w-items[i]>= j ; ++j)【第一个物品为2【这里要也是5】】
		  //如果正向循环，j=0 时会设置 state[5] = true, 
		  //而当遍历至 j=5时，由于 state[5]=true，会设置 state[10] = true，【但是实际上将 5 这个重量使用了两次，所以导致了重量的重复使用，不太理解这里】
	    for (int j = w-items[i]; j >= 0; --j) {//把第i个物品放入背包
		//for (int j = 0; w-items[i]>= j ; ++j) {//【知道了：当i=0得出i=2是true，i=2得出i=4是true，此时，第一次for循环中i = 4是不等于true的（i=6也会变成true）】
	      if (states[j]==true) {
	    	  states[j+items[i]] = true;
	      		sum++;
	      		System.out.println("i = "+i+"  ;sum = " + sum); //sum = 11，顺序排序j，sum = 15,i = 1和4都有重复计算
	      }
	      //我把sum放到if外面了：25没有发生改变啊
	    }
	  }
	  for (int i = w; i >= 0; --i) { // 输出结果
	    if (states[i] == true) return i;
	  }
	  return 0;
	}


	//weight:物品重量，n:物品个数，w:背包可承载重量
	public int knapsack(int[] weight, int n, int w) {
	  boolean[][] states = new boolean[n][w+1]; // 默认值false
	  //boolean[n][w+1]背包承受的重量范围：【0，w】，需要数组长度为W+1
	  states[0][0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
	  if (weight[0] <= w) {
	    states[0][weight[0]] = true;
	  }
	  for (int i = 1; i < n; ++i) { // 动态规划状态转移
	    for (int j = 0; j <= w; ++j) {// 不把第i个物品放入背包
	      if (states[i-1][j] == true) states[i][j] = states[i-1][j];
	    }
	    for (int j = 0; j <= w-weight[i]; ++j) {//把第i个物品放入背包
	    	//j <= w-weight[i]：要满足 原先重量 j +  第 i + 1 物品重量weight[i] <= 背包重量W
	      if (states[i-1][j]==true) states[i][j+weight[i]] = true;
	    }
	  }
	  for (int i = w; i >= 0; --i) { // 输出结果
	    if (states[n-1][i] == true) return i;
	  }
	  return 0;
	}
	
	
}
