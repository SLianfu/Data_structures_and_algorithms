package kmp;

public class KMP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "abcde";
		String b ="abcw";
		KMP k = new KMP();
		k.kmp(a.toCharArray(), a.length(), b.toCharArray(), b.length());
		System.out.println(k.kmp(a.toCharArray(), a.length(), b.toCharArray(), b.length()));

	}
	

	// a, b分别是主串和模式串；n, m分别是主串和模式串的长度。
	public static int kmp(char[] a, int n, char[] b, int m) {
	  int[] next = getNexts(b, m);//得到好前缀公共子串的末尾字符下标【next[0]表示好前缀为1个元素，公共子串为0，next[0] = -1
	  //next[1]表示好前缀长度为2：ab，公共子串为0，用next[1]=-1表示】
	  //next[2]:aba长度j为3:公共子串有a,长度k为1:前缀子串：a,ab;后缀子串：a,ba;其前缀下标为0【用j=0+1表示滑动的位数】
	  //【抽象一点就是主串i不变】
	  //【模式串j=0就是i与模式串开始位置比较，j=1就是i与模式串第二个元素比较】不用纠结移动多少位了
	  int j = 0;
	  for (int i = 0; i < n; ++i) {
	    while (j > 0 && a[i] != b[j]) { // 一直找到a[i]和b[j]，【j>0表示好前缀长度>0】
	      j = next[j - 1] + 1;//如：abaE中;next[j-1]=0;公共子串下标为0，他的下一个元素下标为1
	      //继续拿公共子串的下一个元素：其下标为next[j-1]+1;用j指向next[2]+1,表示模式串的第二个元素继续与主串i进行比较
	    }
	    if (a[i] == b[j]) {
	      ++j;
	    }
	    //System.out.println("-------------");//j=3,i=3时；不相等，直接输出-----------
	    //然后i++，i=4,j=3,刚好a[4] = b[3]
	    if (j == m) { // 找到匹配模式串的了【噢噢，m是等于模式串长度的=4】
	      return i - m + 1;//返回模式串开始下标，i=模式串在主串的结束下标，i-m+1为其在主串的开始下标
	    }
	  }
	  return -1;
	}



	// b表示模式串，m表示模式串的长度
	private static int[] getNexts(char[] b, int m) {
	  int[] next = new int[m];
	  next[0] = -1;	//模式串只取一个字符时，不存在前缀子串后缀子串，公共子串为null，用-1表示没有下标元素
	  int k = -1;  //记录next[i] 的值
	  for (int i = 1; i < m; ++i) {	//遍历模式串
	    while (k != -1 && b[k + 1] != b[i]) {
	      k = next[k];
	    }
	    if (b[k + 1] == b[i]) {
	      ++k;
	    }
	    next[i] = k;
	  }
	  return next;
	}

}
