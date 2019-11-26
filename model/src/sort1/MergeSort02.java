package sort1;

import java.util.Arrays;

public class MergeSort02 {

	/**
	 * Created by wangzheng on 2018/10/16.
	 */
	public static void main(String[] args) {
		int[] arr = {8,4,5,7,1,3,6,2};
		
		//
		System.out.println("排序前："+Arrays.toString(arr));
		mergeSort(arr,8);
		System.out.println("排序后："+Arrays.toString(arr));
		
	}
	  // 归并排序算法, a是数组，n表示数组大小
	  public static void mergeSort(int[] a, int n) {
	    mergeSortInternally(a, 0, n-1);
	  }

	  // 递归调用函数
	  private static void mergeSortInternally(int[] a, int p, int r) {
	    // 递归终止条件
	    if (p >= r) return;//用if(p < r){}应该更好理解
		

	    // 取p到r之间的中间位置q,防止（p+r）的和超过int类型最大值
	    int q = p + (r - p)/2;
	    // 分治递归
	    mergeSortInternally(a, p, q);//分解成一个元素的数组时，返回为空，开始合并【0，1，2】
	    mergeSortInternally(a, q+1, r);//分解成mergeSortInternally(a,2,2);==a[2];执行这一步后开始“归”

	    // 将A[p...q]和A[q+1...r]合并为A[p...r]
	    merge(a, p, q, r);
	  }

	  private static void merge(int[] a, int p, int q, int r) {
	    int i = p;
	    int j = q+1;
	    int k = 0; // 初始化变量i, j, k
	    int[] tmp = new int[r-p+1]; // 申请一个大小跟a[p...r]一样的临时数组【当r=1时，int[2],length=2】
	    while (i<=q && j<=r) {
	      if (a[i] <= a[j]) {
	        tmp[k++] = a[i++]; // i++等于i:=i+1
	      } else {
	        tmp[k++] = a[j++];
	      }
	    }

	    // 判断哪个子数组中有剩余的数据
	    int start = i;
	    int end = q;
	    if (j <= r) {
	      start = j;
	      end = r;
	    }

	    // 将剩余的数据拷贝到临时数组tmp
	    while (start <= end) {
	      tmp[k++] = a[start++];
	    }

	    // 将tmp中的数组拷贝回a[p...r]
	    for (i = 0; i <= r-p; ++i) {
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
	  private static void mergeBySentry(int[] arr, int p, int q, int r) {
	    int[] leftArr = new int[q - p + 2];
	    int[] rightArr = new int[r - q + 1];

	    for (int i = 0; i <= q - p; i++) {
	      leftArr[i] = arr[p + i];
	    }
	    // 第一个数组添加哨兵（最大值）
	    leftArr[q - p + 1] = Integer.MAX_VALUE;

	    for (int i = 0; i < r - q; i++) {
	      rightArr[i] = arr[q + 1 + i];
	    }
	    // 第二个数组添加哨兵（最大值）
	    rightArr[r-q] = Integer.MAX_VALUE;

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
