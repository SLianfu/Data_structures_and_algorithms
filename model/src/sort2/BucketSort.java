package sort2;

import java.util.Calendar;

/**
 * @Description:桶排序算法
 * @Author: Hoda
 * @Date: Create in 2019-06-01
 * @Modified By:
 * @Modified Date:
 */
public class BucketSort {
	
    /**
     * 桶排序
     *
     * @param arr 数组
     * @param bucketSize 桶容量
     */
	public static void main(String[] args) {
		int len = 100000000;
		
		int[] arr = new int[len];
		for(int i = 1;i<len;i++) 
		{
			
			int t = (int)(Math.random()*1000000);
			arr[i] = t;
		} 
		//System.out.println("排序前："+Arrays.toString(arr));
		//排序前打印系统时间
			Calendar cal = Calendar.getInstance();//单开模式，只有一个实例
			System.out.println("排序前："+cal.getTime());
		bucketSort(arr, 1000000);
			cal = Calendar.getInstance();
			System.out.println("排序后："+cal.getTime());
		//System.out.println("排序后："+Arrays.toString(arr));
	}
    public static void bucketSort(int[] arr, int bucketSize) {
        if (arr.length < 2) {
            return;
        }

        // 数组最小值
        int minValue = arr[0];
        // 数组最大值
        int maxValue = arr[1];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            } else if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }

        // 桶数量
        int bucketCount = (maxValue - minValue) / bucketSize + 1;//假设数组为0~40，桶的容量为10，要准备5个桶
        int[][] buckets = new int[bucketCount][bucketSize];//将每个桶组成一个二维数组【桶的下标0~4】【桶的容量10】
        int[] indexArr = new int[bucketCount];//桶内元素的下标，数组大小为bucketCount（5个桶）
        //辅助计数数组，(记录某个桶有多少个元素)数组中每个下标对应arr中的一个元素，(默认初始值为0)indexArr用来记录每个元素出现的次数
        //如indexArr[0]=0,
        
        //System.out.println("indexArr的长度应该是 4："+indexArr.length);
        
        // 将数组中值分配到各个桶里
        for (int i = 0; i < arr.length; i++) {
            int bucketIndex = (arr[i] - minValue) / bucketSize;//确定a[i]放到哪个桶（arr[i] - minValue应该是数据的特殊性）
            //System.out.println(i+" 第几个桶bucketIndex：  "+bucketIndex);
            //System.out.println("indexArr[bucketIndex]的值是什么：【这是一个初始值为0的数】"+indexArr[bucketIndex] );
            //是某个桶中以存放的某个数据的个数，【比如3个6，表示3】。可以这样表示，我才知道（为什么可以这样表示呢）
            
            if (indexArr[bucketIndex] == buckets[bucketIndex].length) {//如果bucketIndex（比如第一个桶）
			//buckets[0].length某个桶的数量等于这个桶的容量
                ensureCapacity(buckets, bucketIndex);//扩充数组
            }
            buckets[bucketIndex][indexArr[bucketIndex]++] = arr[i];
            //indexArr[bucketIndex]只是一个初始为0的计数器,
            	//新理解：indexArr[bucketIndex]是indexArr数组的一个具体的值，初始值为0，indexArr[bucketIndex]++后这个值为1，表示第一个桶有一个元素
            
            //如：buckets[1][indexArr[1]]=arr[i]，indexArr[1]=0，然后自加一。表示buckets[1][0]=arr[i]
            //如：buckets[1][indexArr[1]]=arr[j]，indexArr[1]=1，然后自加一。表示buckets[1][1]=arr[j]
        }

        // 对每个桶进行排序，这里使用了快速排序
        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (indexArr[i] == 0) {//如果某个桶为空，如第二个桶为空，indexArr[1] = 0,直接开始下一次循环continue
                continue;
            }
            quickSortC(buckets[i], 0, indexArr[i] - 1);//buckets[i]表示第i个桶的一维数组，0~这个一维数组的长度（元素数量）-1
            for (int j = 0; j < indexArr[i]; j++) {
                arr[k++] = buckets[i][j];
            }
        }
    }

    /**
     * 数组扩容
     *
     * @param buckets
     * @param bucketIndex
     */
    private static void ensureCapacity(int[][] buckets, int bucketIndex) {
        int[] tempArr = buckets[bucketIndex];
        int[] newArr = new int[tempArr.length * 2];
        for (int j = 0; j < tempArr.length; j++) {
            newArr[j] = tempArr[j];
        }
        buckets[bucketIndex] = newArr;
    }

    /**
     * 快速排序递归函数
     *
     * @param arr
     * @param p
     * @param r
     */
    private static void quickSortC(int[] arr, int p, int r) {
        if (p >= r) {
            return;
        }

        int q = partition(arr, p, r);
        quickSortC(arr, p, q - 1);
        quickSortC(arr, q + 1, r);
    }

    /**
     * 分区函数
     *
     * @param arr
     * @param p
     * @param r
     * @return 分区点位置
     */
    private static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, r);
        return i;
    }

    /**
     * 交换
     *
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
