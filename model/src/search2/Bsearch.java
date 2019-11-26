package search2;



/**
 * 二分查找的变形问题
 * 变体一：查找第一个值等于给定值的元素
 * 变体二：查找最后一个等于给定值的元素
 * 变体三：查找第一个大于等于给定值的元素
 * 变体四：查找最后一个小于等于给定值的元素
 * @author slf
 *
 */

public class Bsearch {

	public static void main(String[] args) {
		
		Bsearch bs = new Bsearch();
		
		int[] arr = {2,4,5,6,8,8,8,9,11};
		int value = 8;
		
		//int num = bsearch1(arr,arr.length,value);
		int num = bsearch2(arr,arr.length,value);
		System.out.println(num);
		if(num >= 0) {
			System.out.println("arr["+num+"]="+arr[num]);
		} else {
			System.out.println("数组中没有这个值:"+value);
		}
		System.out.println("--------------");
	}

	 //变体一：查找第一个值等于给定值的元素，循环实现
	public static int bsearch1(int[] a, int n, int value) {
		int low = 0;
		int high = n-1;
		
		while(low <= high) {
			int mid = low + (high - low) / 2;//这个要放到循环里面
			if(a[mid] == value) {
				return mid;
			} else if(a[mid] < value) {//在右边
				low = mid + 1;
			} else {
				high = mid -1;
			}
		}
		return -1;
	}
	
	//变体二：查找最后一个等于给定值的元素
	public static int bsearch2(int[] a, int n, int value) {
		return bsearch2Internally(a, 0, n-1, value);
	}
	private static int bsearch2Internally(int[] a, int low, int high, int value) {
		if(low > high) return -1;
		
		int mid = low + (high - low) / 2;
		if( a[mid] < value) {
			return bsearch2Internally(a, mid+1, high, value);
		} else if(a[mid] > value) {
			return bsearch2Internally(a, low, mid-1, value);
		} else {
			//* 思路分析
//			 * 1. 在找到mid 索引值，不要马上返回
//			 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 4. 将Arraylist返回
			//向mid 索引值的右边扫描，将所有满足 值 的元素的下标，
			System.out.println("mid=" + mid);
			int team = mid + 1;
			while(true) {
				if(team <= high || a[team] != value) {
					
					break;//要有循环条件
				}
				team++;
				//当team小于high，且a[team] == value时，team++
				/*if(team <= high && a[team] == value) {
					t = team;
					team++;
					i++;
					continue;
				}
				i++;*/
			}
			return team;
			
		}
	}
}
