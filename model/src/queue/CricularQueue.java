/**
 * 循环队列；基于数组实现的【要考虑的主要是队满、队空的情况】
 * 所存储的队列大小为 capacity-1
 */
package queue;

public class CricularQueue {
	//数组：itmes 数组大小：n
	private String[] items;
	private int n = 0;
	// head表示队头下标，tail 表示队尾下标
	private int head = 0;
	private int tail = 0;
	//构造函数，申请一个大小为capacity的数组
	public CricularQueue(int capacity) {
		items = new String[capacity];
		n = capacity;
	}
	
	//入队
	public boolean enqueue(String item) {
		//队列满了
		if((tail+1) % n == head) {
			System.out.println("队列满了。。。");
			return false; 
		}
		
		items[tail] = item;
		tail = (tail+1) % n;//队尾加一 再取与n的余数
		return true;
	}
	
	//出队
	public String dequeue() {
		//队为空
		if(head == tail) return null;//if(head == 0) return null;这里错的离谱
		
		String res = items[head];
		head = (head+1) % n;//队头加一 再取 与n的余数
		return res;
	}
	
	public void printAll() {
		if(0 == n) return ;
		for(int i = head; i%n != tail; i=(i+1)%n) {//i != tail 这里想的太简单了，这个i++也是不可以的
			System.out.print(items[i] +" ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CricularQueue cq = new CricularQueue(8);
		String[] arr = {"a","b","c","d","e","f","g"};
		System.out.println(arr.length);
		for(int i = 0; i < arr.length; i++) {
			cq.enqueue(arr[i]);
		}
		
		System.out.println("输出原始队列：");
		cq.printAll();
		
		String dequeue1 = cq.dequeue();
		cq.dequeue();
		cq.dequeue();
		System.out.println("出队元素："+dequeue1);
		cq.enqueue("aa");
		cq.enqueue("bb");
		cq.enqueue("cc");
		cq.printAll();
		
	}

}
