package stack;
/**
 * 基于链表实现的栈。
 * 好处：不用初始化大小
 * @author slf
 *
 */
public class StackBaseOnLindedLise {

	private Node top = null;//定义栈顶
	public static void main(String[] args) {
		StackBaseOnLindedLise linked = new StackBaseOnLindedLise();
		linked.push(2);
		linked.push(3);
		linked.push(4);
		linked.push(5);
		linked.printAll();
		linked.pop();
		linked.printAll();
	}
	
	//入栈
	public void push(int value) {
		//创建一个节点元素
		Node newNode = new Node(value, null);
		//判断是否为空
		if(top == null) {
			top = newNode;
		} else {
			newNode.next = top;
			top = newNode;//栈顶指针指向这个新的节点；【0《-1（top）】【0《-1<-2(top)】
		}
	}
	
	//出栈
	public int pop() {
		if(top == null) return -1;//表示为空栈
		int value = top.data;//
		top = top.next;
		return value;
	}
	
	public void printAll() {
		Node p = top;
		while(p != null) {
			System.out.print(p.data+" ");
			p = p.next;
		}
		System.out.println();
	}
	
	private static class Node{
		private int data;
		private Node next;
		public Node(int data,Node next) {
			this.data = data;
			this.next = next;
		}
		public int getData() {
			return data;
		}
	}

}
