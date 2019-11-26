package sort1;

import java.util.Arrays;

/**
 * 归并排序
 * 基于比较的排序算法
 * @author slf
 *
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] arr = {8,4,5,7,1,3,6,2};
		
		//
		System.out.println("排序前："+Arrays.toString(arr));
		mergeSort(arr,8);
		System.out.println("排序后："+Arrays.toString(arr));
		
	}
	
	
	public static void mergeSort(int[] a, int n) {
		mergeSortInternally(a,0,n-1);
	}
	
	//递归调用函数
	private static void mergeSortInternally(int[] a, int p, int r) {
		//递归终止条件
		if(p >= r) return;
		//(0,1) q=0调用mergeSortInternally（a,0,0）mergeSortInternally（a,1,1）终止递归，得到空。
		//(2,3) q=2调用mergeSortInternally（a,2,2）
		
		//取p到r之间的中间位置q,防止（p+r）的和超过int类型最大值【用p + (r-p)/2;】
		int q = p + (r-p)/2;
		//分治递归【将0~7分成（0~3和4~7），0~3再分成（0，1）（2，3）此时再分一次即可终止递归：（0）（1），此时p=0,q=0,r=1                         】
		
		mergeSortInternally(a, p, q);//分解成一个元素的数组时，返回为空，开始合并【0，1，2】
		mergeSortInternally(a, q+1, r);//分解成mergeSortInternally(a,2,2);==a[2];执行这一步后开始“归”
		
		//将A[p~q]和A[q+1~r]合并为A[p~r]
		//merge(a,p,q,r);//第一次合并时，q=1【即0，1合并】
		
		//哨兵法
		mergeBySentry(a, p, q, r);
		
	}
	//合并数组
	private static void merge(int[] a, int p, int q, int r) {
		//1、建立索引和临时数组
		int i = p;//左边索引
		int j = q+1;//右边索引
		int k = 0;//临时数组的下标
		int[] tmp = new int[r-p+1];// 申请一个大小跟a[p...r]一样的临时数组
		
		//2、先把左右两边（有序）的数据按照规则填充到tmp数组
		//直到左右两边的有序序列，又一边处理完为止
		//while( i <= p && j <= r) {【这里错了,i在赋值的就等于p了】
		while( i <= q && j <= r) {
			if(a[i] <= a[j]) {//在临时数组中填充a[i]的值
				tmp[k++] = a[i++];  //这里搞混了a[i++] = tmp[k++];
			} else {
				tmp[k++] = a[j++]; //否则填充a[j]的值
			}
		}
		
		//3、把有剩余的数据的一边依次全部填充到tmp
		//判断哪个子数组中有剩余，假设左边有
		int start = i;
		int end = q;
		if(j <= r) {
			start = j;
			end = r;
		}
		while(start <= end) {
			tmp[k++] = a[start++];
		}
		
		//4、把tmp数组元素拷贝到a数组中
		for(i = 0; i <= r-p; i++) {
			a[p+i] = tmp[i];
		}
		
	}
	/**
	   * 合并(哨兵)
	   *
	   * @param arr
	   * @param p
	   * @param q
	   * @param r
	   */
	  private static void mergeBySentry(int[] arr, int p, int q, int r) {//【0~5~10】
	    int[] leftArr = new int[q - p + 2];//数组大小为7【0~6】
	    int[] rightArr = new int[r - q + 1];//数组大小位6个【6~11】

	    for (int i = 0; i <= q - p; i++) {//【0~5】
	      leftArr[i] = arr[p + i];
	    }
	    // 第一个数组添加哨兵（最大值）
	    leftArr[q - p + 1] = Integer.MAX_VALUE;//【6】

	    for (int i = 0; i < r - q; i++) {//【6~10】下标【0~4】
	      rightArr[i] = arr[q + 1 + i];
	    }
	    // 第二个数组添加哨兵（最大值）
	    rightArr[r-q] = Integer.MAX_VALUE;//【5】这个是int中的最大值吗

	    int i = 0;
	    int j = 0;
	    int k = p;
	    while (k <= r) {
	      // 当左边数组到达哨兵值时，i不再增加，直到右边数组读取完剩余值，同理右边数组也一样
	      if (leftArr[i] <= rightArr[j]) {
	        arr[k++] = leftArr[i++];
	      } else {
	        arr[k++] = rightArr[j++];
	      }
	    }
	  }

}
