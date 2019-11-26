package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找：递归实现与非递归实现
 * @author slf
 *
 */
public class Bsearch {

	public static void main(String[] args) {
		int[] arr = {2,4,5,6,8,8,9,11};
		int value = 0;
		Bsearch bs = new Bsearch();
		int num = bsearch2(arr,arr.length,value);
		System.out.println(num);
		if(num >= 0) {
			System.out.println("arr[num]="+arr[num]);
		} else {
			System.out.println("数组中没有这个值:"+value);
		}
		System.out.println("--------------");
		List<Integer> num2 = bs.binarySearch2(arr, 0,arr.length-1,value);
		
		System.out.println("输出list："+num2.toString());
		System.out.println("list的长度："+num2.size());
		System.out.println("num2:"+num2);
		if(num2.size() > 0) {
			//输出这个数组的值
			for(int i =0; i < num2.size(); i++) {
				System.out.print("num2中的值："+"arr["+num2.get(i)+"]="+arr[num2.get(i)]+" ");
			}
		}
		
	}
	
	//a是数组，n是数组长度，value是要查找的值,返回的是数组下标【非递归】
	public static int bsearch(int[] a, int n, int value) {
		//准备低位和高位的索引
		int low = 0;
		int high = n-1;
		
		while(low <= high) {//反过来也可以：while(low > high){return -1;},这样不可以吧
		//判断：当low = high;区间为0：【0，0】【1，1】，只有一个值【这是最后一次判断】
			//取中间值，数组大小单数时，
			int mid = (low + high)/2;//0~6单数7，mid=3正中间，0~7偶数8，mid=3中间较小的值
			System.out.println("mid="+mid);
			if(a[mid] == value) {
				return mid;
			} else if(a[mid] < value){//值在右半边
				low = mid+1;
			} else {//值在左半边
				high = mid -1;
			}
		}
		
		return -1;
	}

	//二分查找递归实现
	public static int bsearch2(int[] a, int n, int value) {
		return bsearchInternally(a, 0, n-1, value);
	}
	//递归实现的调用方法
	private static int bsearchInternally(int[] a, int low, int high, int value) {
		if( low > high) return -1;
		
		int mid = low + (high - low) / 2;
		System.out.println("mid="+mid);
		if(a[mid] == value) {
			return mid;
		} else if(a[mid] < value) {
			return bsearchInternally(a,mid+1,high,value);
		} else {
			return bsearchInternally(a, low, mid-1, value);
		}
	}

	//完成一个课后思考题:
		/*
		 * 课后思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
		 * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
		 * 
		 * 思路分析
		 * 1. 在找到mid 索引值，不要马上返回
		 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
		 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
		 * 4. 将Arraylist返回
		 */
	public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

		System.out.println("hello~");
		// 当 left > right 时，说明递归整个数组，但是没有找到
		if (left > right) {
			return new ArrayList<Integer>();
		}
		int mid = (left + right) / 2;
		int midVal = arr[mid];

		if (findVal > midVal) { // 向 右递归
			return binarySearch2(arr, mid + 1, right, findVal);
		} else if (findVal < midVal) { // 向左递归
			return binarySearch2(arr, left, mid - 1, findVal);
		} else {
//			 * 思路分析
//			 * 1. 在找到mid 索引值，不要马上返回
//			 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 4. 将Arraylist返回
			
			List<Integer> resIndexlist = new ArrayList<Integer>();
			//向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
			int temp = mid - 1;
			while(true) {
				if (temp < 0 || arr[temp] != findVal) {//退出【满足team>=0且findVal==arr[mid]时往下执行】
					break;
				}
				//否则，就temp 放入到 resIndexlist
				resIndexlist.add(temp);
				temp -= 1; //temp左移
			}
			resIndexlist.add(mid);  //
			
			//向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
			temp = mid + 1;
			while(true) {
				if (temp > arr.length - 1 || arr[temp] != findVal) {//退出
					break;
				}
				//否则，就temp 放入到 resIndexlist
				resIndexlist.add(temp);
				temp += 1; //temp右移
			}
			
			return resIndexlist;
		}

	}
}
