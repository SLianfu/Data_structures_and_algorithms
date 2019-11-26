package sort2;

import java.util.Arrays;

/**
 * 计数排序
 * @author slf
 *
 */
public class CountingSort {
	
	public static void main(String[] args) {
		int[] arr = {8,4,5,7,1,3,6,2,5,5};
		System.out.println("排序前："+Arrays.toString(arr));
		countingSort(arr, arr.length);
		System.out.println("排序后："+Arrays.toString(arr));
	}

	//计数排序，a是数组，n是数组大小，假设没有负数
	public static void countingSort(int[] a, int n) {
		if(n <= 1) return;
		
		//查找数组中数据的范围
		int max = a[0];
		for(int i =1; i < n; i++) {
			if(max < a[i]) {
				max = a[i];
			}
		}
		
		//申请一个计数数组c，下标大小为[0，max],(0~5)需要6个数来存储具体值
		int[] c = new int[max+1];
		
		//第一步，统计技术
		//计算（0~max中）每个元素的个数，放入c中
		for(int i = 0; i < n; i++) {
			c[a[i]]++;//如:当a[i] = 3时，c[3] = 1（c[3]计数为1 当另外一个数等于3时，c[3]再加一）
		}
		
		//第二步：还原数组【通过扫描计数数组得以得出，累加求和的值（为7）即为有序数组的序号（排第7位）（具体数组下标为7-1）】
		//如何快速知道计数数组c中在有序数组中对应的存储位置呢？【先累加求和，扫描原始数组，利用这个某个元素的累加和得到他在有序数组中的排序】
		//扫描计数数组counting[0, max]，通过每个元素的计数，还原arr[N]。【这里比较好理解不过怎么实现呢？】
		for(int i = 1; i < max+1; i++) {
			c[i] = c[i-1] + c[i];//c[0]依旧=c[0]的计数
		}
		
		//临时数组r，存储排序之后的结果
		int[] r = new int[n];
		
		//关键步骤,扫描原始数组，得到原始数组某个值的计数器值
		for(int i = n-1; i >= 0; i--) {
			//得到有序数组下标 
			int index = c[a[i]] - 1;//得到a[i]要放入临时数组的下标
			//扫描A数组中最后一个元素，个元素，a[n-1] = 3的话，c[3]原先表示a数组中3的个数，累加求和后=7，表示临时数组排序时的具体位置，具体下标要减一
		    
			r[index] = a[i];//将a[n-1]赋值给临时数组R
			c[a[i]]--;//计数器减一
		}
		
		//将结果拷贝到a数组
		for(int i = 0; i < n; i++) {
			a[i] = r[i];
		}
	}
}
