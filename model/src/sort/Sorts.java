package sort;

import java.util.HashMap;
import java.util.Map;

/**
 * 递归
 * 当我们面对的是一个问题要分解为多个子问题的情况，递归代码就没那么好理解了。
 * 像我刚刚讲的第二个例子，人脑几乎没办法把整个“递”和“归”的过程一步一步都想清楚。
 * 
 * 如果一个问题 A 可以分解为若干子问题 B、C、D，你可以假设子问题 B、C、D 已经解决，在此基础上思考如何解决问题 A。
 * 而且，你只需要思考问题 A 与子问题 B、C、D 两层之间的关系即可，不需要一层一层往下思考子问题与子子问题，
 * 子子问题与子子子问题之间的关系。屏蔽掉递归细节，这样子理解起来就简单多了。
 * @author slf
 *
 */

public class Sorts {
	
	private static final Exception RuntimeException = null;
	//避免堆栈溢出
	//全局变量，表示递归深度
	int depth = 0;
	
	//map
	Map hasSolvedList = new HashMap<>();
	
	public static void main(String[] args) {
		
		Sorts test = new Sorts();
		
		int num2 = test.f2(12);
		System.out.println("num2="+num2);
		
		int num = test.f(12);
		
		System.out.println("num"+num);
	}
	/**
	 * 递归算法
	 * f(3)=3;
	 * f(4) = 5
	 * f(5) = 8
	 * f(6) = 13
	 * @param n
	 * @return
	 * @throws Exception 
	 */
	int f(int n)   {
		depth++;
		System.out.print("n="+ n+" ");
		System.out.println("depth="+depth);
		if(depth > 1000) return -1;
		if (n == 1) return 1;
		if (n == 2) return 2;
		 if (hasSolvedList.containsKey(n)) {    
			 return (int) hasSolvedList.get(n);  
		}
		int ret = f(n-1) + f(n-2);
		hasSolvedList.put(n, ret);
		  
		  return ret;
		}
//将递归代码转化成非递归
int f2(int n) {
  if (n == 1) return 1;
  if (n == 2) return 2;
  
  int ret = 0;//总和
  int pre = 2;//f2
  int prepre = 1;//f1
  for (int i = 3; i <= n; ++i) {
    ret = pre + prepre;
    prepre = pre;
    pre = ret;
  }
  return ret;
}


}
