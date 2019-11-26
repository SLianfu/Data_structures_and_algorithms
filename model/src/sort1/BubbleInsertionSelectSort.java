package sort1;

import java.util.Arrays;

/**
 * 冒泡排序、插入排序、选择排序
 * 基于比较的排序
 * @author slf
 *
 */

public class BubbleInsertionSelectSort {
	
	//冒泡,a是数组，n表示数组大小【从小到大排序】
	public static void bubbleSort(int[] a, int n) {
		if(n <= 1) return;
		
		for(int i = 0; i < n; i++) {
			//提前退出标志
			boolean flag = false;//等于false时，退出外循环
			for(int j = 0; j < n-i-1; j++) {//j < n-i-1;j=4时可完成与j=5的比较
				if(a[j] > a[j+1]) {//交换
					int temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
					flag = true;//标志此次冒泡有数据交换
				}
			}
			if(!flag) break;//没有数据交换，提前退出(内循坏没有数据交换，即排序完成提前退出)
			
		}
	}
	
	/**
     * 冒泡排序改进:在每一轮排序后记录最后一次元素交换的位置,作为下次比较的边界,
     * 对于边界外的元素在下次循环中无需比较.//这样的就节省了j < n-i-1的计算次数
     */
	public static void bubuleSort2(int[] a, int n) {
		if( n <= 1) return ;
		//最后一次交换的位置
		int lastExchange = 0;
		//无序数据边界，每次只需要比较到这里即可退出
		int sortBorder = n-1;//记录
		for(int i =0; i < n; i++) {
			//提前退出标志位
			boolean flag = false;
			for(int j=0; j < sortBorder; j++) {
				if(a[j] > a[j+1]) {
					int tmp = a[j];
					a[j] = a[j+1];
					a[j+1] = tmp;
					flag = true;
					//更新最后一次交换的位置
					lastExchange = j;//j=4;完成a[4]与a[5]的比较
				}
			}
			sortBorder = lastExchange;
			if(!flag) break;//没有数据交换，提前退出
			
		}
		
	}
	
	//插入排序
	public static void insertionSort(int[]a, int n) {
		if(n <= 1) return ;
		
		for(int i = 1; i < n; i++) {//外循环为无序数组
			int value = a[i];//要插入的值
			int j=i-1;//【因为后面插入数据要用到j,所以j要定义到这里】
			for(; j >= 0; j--) {//0-j为有序数组，（判断到最后时）j=-1时跳出内循环
				if(a[j] > value) {
					a[j+1] = a[j];//数组后移
				} else {
					break;
				}
			}
			a[j +1] = value;//插入数据
		}
	}
	
	//选择排序
	public static void selectionSort(int[] a, int n) {
		if(n <= 1) return ;
		
		for(int i = 0; i < n-1; i++) {//开始假设数据全是无序的，为什么是n-1？:因为外循环为有序数组，当i=n-1-1时，i=4时，i[4]、i[5]为无序，完成查找后[0~4]为有序，a[5]为最大值，都有序了                                          
			//查找最小值【的下标】
			int minIndex = i;
			for(int j = i+1; j < n; j++) {
				if(a[j] < a[minIndex]) {
					minIndex = j;
				}
			}
			//交换
			int tmp = a[i];
			a[i] = a[minIndex];
			a[minIndex] = tmp;
		}
	}
	
	
	

	public static void main(String[] args) {
		int[] array = new int[] {3,4,2,1,5,6,7,8};
		//bubuleSort2(array, array.length);
		//selectionSort(array, array.length);
		//insertionSort(array, array.length);
		System.out.println(Arrays.toString(array));
	}

}
