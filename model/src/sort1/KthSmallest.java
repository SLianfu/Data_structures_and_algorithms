package sort1;

import java.util.Arrays;

/**
 * 快速排序，得到第几大（）的值
 */
public class KthSmallest {
	
	public static void main(String[] args) {
		int[] arr = new int[] {8,4,5,7,1,3,6,2};
		System.out.println("排序前："+Arrays.toString(arr));
		
		int i = kthSmallest(arr, 1);
		
		System.out.println("排序后："+Arrays.toString(arr));
		System.out.println("第kthSmallest(arr, 4)小的值："+i);
	}

    public static int kthSmallest(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return -1;
        }

        int partition = partition(arr, 0, arr.length - 1);
        while (partition + 1 != k) {
            if (partition + 1 < k) {
                partition = partition(arr, partition + 1, arr.length - 1);
            } else {
                partition = partition(arr, 0, partition - 1);
            }
        }

        return arr[partition];
    }

    private static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];

        int i = p;
        for (int j = p; j < r; j++) {
            // 这里要是 <= ，不然会出现死循环，比如查找数组 [1,1,2] 的第二小的元素
            if (arr[j] <= pivot) {//arr[j] <= pivot这里求从小到大
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, r);

        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
