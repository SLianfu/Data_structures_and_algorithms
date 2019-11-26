package array;

public class ShuZuYueJie {

	public static <Sting> void main(String[] args) {
		// TODO Auto-generated method stub
		/*int j = 3;
		int i = 0;
		//int arr[j] = {0};不可以这样声明；
		int[] arr = new int[3];*/
		//arr[3]=10;
		//System.out.println("数组不越界吗？肯定越界啦");
		GenericArray<String> array = new GenericArray<>(8);
		//array= {"a","b"};数组常量只能在初始化程序中使用
		int count = array.getCount();
		array.insert(0, "ab");
		array.insert(0, "abc");
		array.insert(2, "size");
		//扩充
		array.insert(3, "add");
		
		//String remove = array.remove(2);
		//System.out.println("移除的数值："+remove);
		
		array.removeElement("ab");
		array.removeElement("www");
		array.remove(2);
		
		//array.toString();
		
		array.printAll();
		System.out.println("数组的长度="+array.getCapacity());
		System.out.println("数组的实际大小="+array.getCount());
	}

}
