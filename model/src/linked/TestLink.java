/**
 * 测试带头节点的链表头插法   不过好像有问题
 */
package linked;

/**
 *  链表
   1.带头结点的：head里面存放链表长度（或其他信息），head->next指向第一个实际节点；
   2.不带头结点的：head即第一个实际节点
 * @author slf
 *
 */
public class TestLink {
	private Node head = null;
	public static void main(String args[]) {
		TestLink link = new TestLink();
		int[] data = {1,2,3,4,5};
		for(int i = 0; i <data.length; i++) {
    		link.insertTail(data[i]);
    		//link.insertToHead(data[i]);
    	}
		System.out.println("打印原始链表：");
		link.printAll(link.head);
		
		//
		System.out.println("翻转链表");
		Node Head = link.inverseList(link.head);//1 5 4 3 2 
		link.printAll(Head);
		
	}
	
	public Node inverseList(Node head) {//head=1
		
		Node H = new Node(999,null);
		H.next = head;
		Node cur = head.next;//当前结点为链表的第二节点
		head.next = null;
		Node pre,next;
		//
		
		pre = null;
		while(cur != null) {
			next = cur.next;//记录当前结点的下一个节点
			cur.next = H.next;
			H.next = cur;//pre记录cur链表
			cur = next;//cur后移一位
		}
		System.out.println("此时，head地址：="+head+"  head = "+head.data+"  head.next = "+head.next);
		//head.next = pre;
		System.out.println("H的地址："+H);
		H = H.next;
		System.out.println("H的地址："+H);
		return H;
	}
	
	public void printAll(Node q) {
		Node p = q;
		//System.out.println("p.Data是指第一个链表值"+p.getData());//链表的长度吗，
		while(p != null) {
			System.out.print(p.data + " ");
			p = p.next;
		}
		System.out.println();
	}
	
	//尾插法
	private void insertTail(int i) {
		Node newNode = new Node(i, null);//考虑空链表，就把他作为头节点
		//空链表，可以插入新的节点作为head，也可以不操作
		if(head == null) {
			head = newNode;
		} else {
			Node q = head;//q节点始终指向最后一个节点
			//遍历
			while(q.next != null) {
				q = q.next;
				
			}
			newNode.next = q.next;//交换指针时，一定要先考虑节点后面的指向，即q.next
			q.next = newNode;	
		}		
	}

	
	//链表结构
	public static class Node{
		private int data;
		private Node next;
		public Node(int data, Node next) {
			
			this.data = data;
			this.next = next;
		}
		public int getData() {
			return data;
		}
		
	}

}
