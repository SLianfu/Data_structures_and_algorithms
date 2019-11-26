package Dynamic_Programming;

/**
 * 0 1 背包问题：回溯法
 * @author slf
 *
 */
public class Back0_1 {

	public static void main(String[] args) {
		Back0_1 b0_1 = new Back0_1();
		b0_1.f(n, w);
	}

	private int maxW = Integer.MIN_VALUE; // 结果放到maxW中
	private int[] weight = {2,2,4,6,3};  // 物品重量
	private int[] value = {3,4,8,9,6}; // 物品的价值
	private static int n = 5; // 物品个数
	private static int w = 9; // 背包承受的最大重量
	private boolean[][] mem = new boolean[5][10]; // 备忘录，默认值false
	
	public void f(int i, int cw) { // 调用f(0, 0)
	  if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了
	    if (cw > maxW) maxW = cw;
	    return;
	  }
	  if (mem[i][cw]) return; // 重复状态
	  mem[i][cw] = true; // 记录(i, cw)这个状态【直接开始返回，不用继续往后递归f( i , cw)】
	  f(i+1, cw); // 选择不装第i个物品
	  if (cw + weight[i] <= w) {
	    f(i+1,cw + weight[i]); // 选择装第i个物品
	  }
	}
	

	//0 1 背包问题：升级版
	/**
	 * 对于 (i, cw) 相同的不同状态，那我们只需要保留 cv 值最大的那个，继续递归处理，其他状态不予考虑
	 * 如果用回溯算法，这个问题就没法再用“备忘录”解决了,所以，动态规划就上场了
	 */
	private int maxV = Integer.MIN_VALUE; // 结果放到maxV中
	
	public void f(int i, int cw, int cv) { // 调用f(0, 0, 0)
	  if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了
	    if (cv > maxV) maxV = cv;
	    return;
	  }
	  f(i+1, cw, cv); // 选择不装第i个物品：【记：f(1,0,0)】
	  if (cw + weight[i] <= w) {
	    f(i+1,cw+weight[i], cv+value[i]); // 选择装第i个物品【记：f(1,2,3)】
	  }
	}

}
