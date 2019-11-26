/**
 * 用数组实现的队列
 */
package queue;

public class ArrayQueue {
	//数组：items 数组大小：n
	private int[] items;
	private int n = 0;
	//
	private int head = 0;
	private int tail = 0;
	
	public ArrayQueue(int capacity) {
		items = new int[capacity];
		n = capacity;
	}
	
//	//入队
//	public boolean enqueue(int item) {
//		//如果队列已满
//		if(tail == n) return false;
		//if(head == 0) return false;
		//for
//		items[tail] = item;
//		tail++;
//		return true;
//	}
	//入队；队满时进行数组搬移
	   // 入队操作，将 item 放入队尾
	  public boolean enqueue(int item) {
	    // tail == n 表示队列末尾没有空间了
	    if (tail == n) {
	      // tail ==n && head==0，表示整个队列都占满了
	      if (head == 0) return false;
	      // 数据搬移
	      System.out.println("数组搬移。。");
	      for (int i = head; i < tail; ++i) {
	        items[i-head] = items[i];
	      }
	      // 搬移完之后重新更新 head 和 tail
	      tail -= head;
	      head = 0;
	    }
	    
	    items[tail] = item;
	    ++tail;
	    return true;
	  }

	//出队
	public int dequeue() {
		//队空
		if(head == tail) return -0;
		int res = items[head];
		//head = head++;这个是什么鬼，0 = 0
		head++;
		return res;
	}
	public void printAll() {
		if(head == tail) return ;
		for(int i = head; i < tail; i++) {
			System.out.print(items[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayQueue aq = new ArrayQueue(8);
		int[] arr = {1,2,3,4,5,6,7};
		for(int i = 0; i < arr.length; i++) {
			aq.enqueue(arr[i]);
		}
		
		System.out.println("输出原始队列：");
		aq.printAll();
		aq.enqueue(11);
		
		int dequeue1 = aq.dequeue();
		aq.dequeue();
		System.out.println("出队队列："+dequeue1+" 此时head="+aq.head);
		aq.enqueue(22);//要进行数组搬移了
		aq.enqueue(32);
		
		aq.printAll();
		
	}
	

}
