package kmp;

public class BM {
	
	public static void main(String[] args) {
		String str1 = "abcabcqwewer";
		String str2 = "wer";
		//int index = violenceMatch(str1, str2);
		//System.out.println("index=" + index);
		BM test = new BM();
		int index = test.bm(str1.toCharArray(), str1.length(), str2.toCharArray(), str2.length());
		System.out.println("index = "+index);
		System.out.println("===============");
		//test.generateBC(str1.toCharArray(), str1.length(), new int[SIZE]);
		

	}
	

		private static final int SIZE = 256; // 全局变量或成员变量
		//构建坏字符散列表
		private void generateBC(char[] b, int m, int[] bc) {
		  for (int i = 0; i < SIZE; ++i) {
		    bc[i] = -1; // 初始化bc
		  }
		  for (int i = 0; i < m; ++i) {
		    int ascii = (int)b[i]; // 计算b[i]的ASCII值
		    bc[ascii] = i;
		  }
		}

		//a,b表示主串和模式串；n，m表示主串和模式串的长度。
		public int bm(char[] a, int n, char[] b, int m) {
			int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
			generateBC(b, m, bc); // 构建坏字符哈希表
			int[] suffix = new int[m];
			boolean[] prefix = new boolean[m];
			generateGS(b, m, suffix, prefix);
			int i = 0; // j表示主串与模式串匹配的第一个字符
			while (i <= n - m) {
			 int j;
			 for (j = m - 1; j >= 0; --j) { // 模式串从后往前匹配
			   if (a[i+j] != b[j]) break; // 坏字符对应模式串中的下标是j
			 }
			 if (j < 0) {
			   return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
			 }
			 int x = j - bc[(int)a[i+j]];
			 int y = 0;
			 if (j < m-1) { // 如果有好后缀的话
			   y = moveByGS(j, m, suffix, prefix);
			 }
			 i = i + Math.max(x, y);
			}
			return -1;
		}

		//j表示坏字符对应的模式串中的字符下标; m表示模式串长度
		//计算好后缀规则要移动的位数
		private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
			int k = m - 1 - j; // 好后缀长度
			if (suffix[k] != -1) return j - suffix[k] +1;//直接返回要移动的位数
			//初始化时，suffix[] = -1;
			//这里：模式串前缀子串中，suffix[3] 的值是子串【0，m-2】与整个模式串的公共后缀子串的开始下标
			//如 模式串： cabcwb  有子串为 ccwb suffix[3]记录该子串开始下标1
			
			//没有好后缀子串；查找好后缀的后缀子串b;b的范围【r,m-1】
			//j为坏字符下标，j+1为好后缀开始下标，所以j+2为好后缀的后缀子串开始下标的最大值
			for (int r = j+2; r <= m-1; ++r) {
			 if (prefix[m-r] == true) {//好后缀 abcwb,坏字符j 后缀子串cwb; prefix[3] = true:
				//模式串前缀有：cwb，直接移动r位：cwb abcwb;从0开始移动到下标c的位置：5位
				//长度为 m-r【6-4】【好后缀子串长度为2】 的后缀子串，有可匹配的前缀子串，这样我们可以把模式串后移 r 位。
			   return r;
			 }
			 /**
			  * 为什么要用这个做判断呢？（prefix[m-r] == true）模式串假设为cabc Aabc,主串有一个WAabc,w为坏字符
			  * 因为如：好后缀：Aabc	模式串子串中没有Aabc;不过有abc如果abc 与abc 对齐，c与A还是不匹配
			  * 所以要用前缀子串去判断【这里要用到好后缀后缀子串：c,c与前缀子串c相等，即prefix[1] = true】j=3,r=m-1=7,移动7位
			  */
			 
			//否则r++; 如果r = m-1 ; 移动到好后缀最后位置还是没有相匹配的前缀子串，
			}
			return m;
		}


		//b表示模式串，m表示长度，suffix，prefix数组事先申请好了
		//计算suffix[k],prefix[k]：记录模式串的后缀子串(wb)是否能匹配模式串的前缀子串 下标开始位置
		//前缀子串{c,ca,cab,cabc,cabcw}
		private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
			for (int i = 0; i < m; ++i) { // 初始化
			 suffix[i] = -1;
			 prefix[i] = false;
			}
			//我们拿下标从 0 到 i 的子串（i 可以是 0 到 m-2）与整个模式串，求公共后缀子串。从小到大
			for (int i = 0; i < m - 1; ++i) { // b[0, i]
			 int j = i;//【i最大可以等于6】
			 int k = 0; // 公共后缀子串长度
			 while (j >= 0 && b[j] == b[m-1-k]) { //b[0, i] 与b[0, m-1]求公共后缀子串
			   --j;
			   ++k;
			   suffix[k] = j+1; //j+1表示公共后缀子串在b[0, i]中的起始下标
			 }
			 //aaaa aaaa
			 //极端情况下：suffix[1] = 0,suffix[2] = 1,suffix[3] = 2 ...suffix[7] = 6【要进行优化】
			 if (j == -1) prefix[k] = true; //如果公共后缀子串也是模式串的前缀子串
			}
		}

}


