package stack;
/**
 * 基于数组实现的栈
 * @author slf
 *
 */
public class ArrayStack {
	
	private int default_items = 10;
	private int[] items;//定义一个数组
	private int count;//数组中的个数
	private int n; //数组的大小
	
	//初始化数组,默认大小为10
	public ArrayStack() {
		items = new int[default_items];
		count = 0;
		n = default_items;
	}
	public ArrayStack(int capacity) {
		items = new int[capacity];
		count = 0;
		n = capacity;
	}
	
	//入栈
	public boolean push(int item) {
		//判断栈满
		if(count == n) return false;
		items[count] = item;
		count++;
		return true;
	}
	
	//出栈
	public int pop() {
		//判断栈为空,用-1表示没有数据
		if(count == 0) return -1;
		
		int res = items[count-1];
		count--;
		return res;
	}
	
	public void printAll() {
		for(int i = 0; i < count; i++) {
			System.out.print(items[i]+" ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayStack stack = new ArrayStack(6);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		System.out.println("栈内元素：（从栈底到栈底：）");
		stack.printAll();
		stack.pop();
		stack.printAll();

	}

}
