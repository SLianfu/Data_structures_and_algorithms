package sort1;

import java.util.Arrays;
import java.util.Calendar;

/**
 * 快速排序
 * 基于比较的排序算法
 * @author slf
 *
 */
public class Quick_sort {

	public static void main(String[] args) {
		int[] arr = {8,4,5,7,1,3,6,2,5,5};
		
		/*构建无序数列
		让程序随机产生一个1-10000的数,每次循环随机产生一个数
		Math.random()会产生一个0~1的数*/
		/*int len = 100000000;
		
		int[] arr = new int[len];
		for(int i = 1;i<len;i++) 
		{
			
			int t = (int)(Math.random()*100000000);
			arr[i] = t;
		}  */
		System.out.println("排序前："+Arrays.toString(arr));
		//排序前打印系统时间
		Calendar cal = Calendar.getInstance();//单开模式，只有一个实例
		System.out.println("排序前："+cal.getTime());
		quickSort(arr,arr.length);
		cal = Calendar.getInstance();
		System.out.println("排序后："+cal.getTime());
		System.out.println("==============");
		System.out.println("排序后："+Arrays.toString(arr));
	}
	
	//快速排序，a是数组，n表示数组大小
	public static void quickSort(int[] a, int n) {
		quickSortInternally(a, 0, n-1);
	}
	//快速排序递归函数，p,r为下标
	private static void quickSortInternally(int[] a, int p, int r) {
		if(p >= r) return;
		
		int q = partition(a, p, r);//获取分区点下标，然后递归调用
		quickSortInternally(a, p, q-1);//递归排序左半部分
		//quickSortInternally(a, p, q);要减一
		//Stack OverflowError堆栈溢出错误
		quickSortInternally(a, q+1, r);
		
	}
	//分区点(下标)函数
	private static int partition(int[] a, int p, int r) {
		//1、先取要进行比较的值，这里去最右边
		int pivot = a[r];//枢纽的意思
		
		//2、从左边开始比较
		int i = p;//用i（第一个数做下标索引） 记录比pivot小的数，没记录一个，i++,遍历数组结束后
		
		//3、用j索引遍历数组
		for(int j = p; j < r; j++) {//当j索引指向r(最后一个元素是，跳出循环)
			if(a[j] < pivot) {
				if(i == j) {//i索引==j索引，i和j都后移
					++i;
				} else {//i索引不等于j索引，交换a[i]与a[j],i++;
					/*int tmp = a[i];
					a[i++] = a[j];//然后i+1
					a[j] = tmp;*/
					swap(a, i, j);
					i++;
				}
			}
		}
		//把最后一个元素a[r]或者pivot放到最中间的位置【i索引指向的位置】
		swap(a,i,r);
		//System.out.println("i="+i);
		return i;
	}
	
	private static void swap(int[] a, int i, int j) {
		if( i == j) {
			return;
		}
		
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	

}
