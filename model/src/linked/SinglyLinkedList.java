package linked;

import linked.SinglyLinkedList.Node;
/**
 * 单链表：的插入、删除、查询操作（）修改呢？
 * 链表中存储的是int类型
 * 
 * @author slf
 *
 */

public class SinglyLinkedList {
	
	//定义头节点（头指针）【value=null，next=null】
	private Node head = null;//可以理解成他就是一个指针吗，初始值为null
	
	//按值查找链表
	public Node findByValue(int value) {
		Node p = head;
		while(p != null && p.data != value) {
			p=p.next;
		}
		return p;//要是找不到也是返回null
	}
	
	//按下标查找链表
	public Node findByIndex(int index) {
		Node p = head;
		int pos = 0;//刚才在想这个是干嘛的，
		//不过想一下，查找链表需要一个值来遍历链表下标: pos != index
		while (p != null && pos != index) {
			p = p.next;
			++pos;
		}
		return p;//返回节点p
	}
	
	//无头节点
	//表头插入
	//这种操作将于输入的顺序相反，逆序	【按值插入】
	public void insertToHead(int value) {	//?
		Node newNode  = new Node(value, null);
		insertToHead(newNode);
	}
	
	public void insertToHead(Node newNode) {//【按节点插入】
		if(head == null) {//判断是否为空链表
			head = newNode;//head=null  变成 head = B：B.next -> null(没有初始化，默认值为null,好像上面有初始化了，额)
		} else {
			newNode.next = head;//先这样理解吧，头节点存储着一个数【head是一个初始值为null的指针】
			//head（c）->a->b==value:1,2,3;新来的节点就直接指向head，变成，n,1,2,3，新的链表head->c->a->b;
			head = newNode;
			// head = B；==》  head = A -> B ->null
		}
	}
	
	//顺序插入
	//链表尾部插入
	public void insertTail(int value) {
		
		Node newNode = new Node(value, null);//考虑空链表，就把他作为头节点
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
	
	public void insertAfter(Node p,int value) {
		Node newNode = new Node(value, null);
		insertAfter(p, newNode);
	}
	public void insertAfter(Node p, Node newNode) {//插入到 p节点 后面【指定插入到那个节点之后】
		//这里怎么不用找p这个节点的？？
		
		if(p == null) return;//返回空？有点不懂【应该是没有找到p这个节点】
		
		//这里遍历吧
			Node q = head;
			//遍历链表
			while (q != null && q.next != p) {
				q = q.next;
			}
			//要么q==null,要么q->p;
			if(q == null) return ;
		newNode.next = p.next;
		p.next = newNode;
	}
	
	public void insertBefore(Node p, int value) {
		Node newNode = new Node(value, null);
		insertBefore(p, newNode);
	}
	public void insertBefore(Node p, Node newNode) {//头插法，把newNode插在p前面
		if(p == null) return ;
		
		if(head == p) {
			insertToHead(newNode);
			return;
		}
		
		Node q = head;
		//遍历链表
		while (q != null && q.next != p) {
			q = q.next;
		}
		//要么q==null,要么q->p;
		if(q == null) return ;
		
		newNode.next = p;
		q.next = newNode;
	}
	
	public void deleteByNode(Node p) {
		if(p == null || head == null) return ;
		if(p == head) {
			head = head.next;//删除头节点
			return ;
		}
		
		Node q = head;
		while(q != null && q.next != p) { //找到p 的前一个节点：Q
			q = q.next;
		}
		
		if(q == null) {//链表中没有这个节点
			return;
		}
		
		//找到p这个节点：由q-》p
		q.next = q.next.next;
		
	}
	//按值删除节点
	public void deleteByValue(int value) {
		if(head == null) return ;
		
		Node p = head;
		Node q = null;//【指针索引，记录 p节点.data = value  的前一个节点】
		while(p.next != null && p.data != value) {
			q = p;//此时q为那些值不等于value的节点
			p = p.next;//p.data=value；跳出
		}
		
		if(p == null) return ;//链表没有这个值，
		if(q == null) {	//要删除的节点 是头节点
			head = head.next;//？不过这样删除节点是干嘛的【q=null,表明head.data=value,while循环没有执行】
		} else {
			q.next = q.next.next;//删除节点：删除p,
		}
		
		// 可重复删除指定value的代码
        /*
           if (head != null && head.data == value) {
           head = head.next;
           }

           Node pNode = head;
           while (pNode != null) {
	           if (pNode.next.data == value) { // pNode的下个节点等于 要删除的 value 
	           pNode.next = pNode.next.next;
	           continue;
	           }
	           pNode = pNode.next;//遍历完整个链表
           }
         */
		
	}
	
	//打印整个链表
	public void printAll() {
		Node p = head;
		//System.out.println("p.Data是指第一个链表值"+p.getData());//链表的长度吗，不是
		while(p != null) {
			System.out.print(p.data + " ");
			p = p.next;
		}
		System.out.println();
	}
	//根据某个节点，打印以这个节点开始的链表
	public void printAll(Node q) {
		Node p = q;
		//System.out.println("p.Data是指第一个链表值"+p.getData());//链表的长度吗，
		while(p != null) {
			System.out.print(p.data + " ");
			p = p.next;
		}
		System.out.println();
		String s = "ddd";
		s.length();
	}
	
	//判断true or false//相等
    public boolean TFResult(Node left, Node right){
        Node l = left;
        Node r = right;

        System.out.println("left_:"+l.data);
        System.out.println("right_:"+r.data);
        while(l != null && r != null){
           if (l.data == r.data){//两边的值相等，跳出循环时：1、有不相等的；2、l和r同时等于null
               l = l.next;
               r = r.next;//两者同步进行
               continue;
           }else{
               break;
           }
        }
        System.out.println("什么结果:"+"left != right可能相等");//这里有点奇怪，应该输出，可能相等
        if (l==null && r==null){
           System.out.println("什么结果:"+"left == right");
           return true;
        }else{
           return false;
        }
    }
    
    //判断是否为回文	//没有链表传入？【链表直接调用这个方法：Link.palindrome,返回的是boolean，用作判断条件】
    public boolean palindrome() {//要是字符串为偶数怎么处理
    	if(head == null) {//是否为空链表
    		return false;
    	} else {
    		System.out.println("开始执行找到中间节点");
    		Node p = head;
    		Node q = head;
	    		System.out.println("p:这里应该是head的地址：（可能跟head一样）"+p);
	    		System.out.println("p的第一个值"+p.data);
	    		System.out.println("p的第一个值"+p.getData());
    		if(p.next == null) {
    			System.out.println("只有一个元素");
    			return true;
    		}
    		while( q.next != null && q.next.next != null) {
    			//【1234321】p等于4时跳出；【123321】p等于第一个3时跳出
    			p = p.next;		//慢节点;
    			q = q.next.next;//快节点;//偶数节点？【这个说法错的】
    		}
    		
    		System.out.println("中间结点："+p.data);
    		System.out.println("开始执行奇数节点的回文判断");
    		Node leftLink = null;
    		Node rightLink = null;
    		if(q.next == null) {//偶数节点的回文判断
    			//p 一定为整个链表的中间节点，且节点数目为奇数
    			rightLink = p.next;//【321】
    			leftLink = inverseLinkList2(p).next;//加个next把4去掉：321【根据p=中间节点，翻转0~p链表】
				//【先反转头节点到p节点，p.data=4,反转后为4321】
    			System.out.println("左边第一个节点"+leftLink.data);
                System.out.println("右边第一个节点"+rightLink.data);
    		} else {////奇数节点的回文判断
    			//p q 均为中点
    			rightLink  = p.next;//把第一个3去掉【321】
    			leftLink = inverseLinkList2(p);//【321】
    		}
    		return TFResult(leftLink, rightLink);
    	}
    	
    }
	
    //带头节点的链表翻转
    public Node inverseLinkList_head(Node p) {
//    	//Head 为新建的一个头节点
//    	Node Head = new Node(9999,null);//带头结点的：头结点存储长度信息，头结点的next指向第一个实际节点；
//    	//p 为原来整个链表的头节点，现在Head指向整个链表
//    	Head.next = p;
//    	/**
//    	 * 带头节点的链表翻转等价于
//    	 * 从第二个元素开始重新头插法建立链表
//    	 */
//    	Node Cur = p.next;//第二个元素
//    	p.next = null;	//头指针指向null：H【9999】-》【1】-》null;然后用这个头插法插入（把Head当作固定指针）【234】
//    	Node next = null;
//    	
//    	while(Cur != null) {//遍历链表
//    		next = Cur.next;//存储第3个元素,先记录下这个节点
//    		Cur.next = Head.next;
//    		Head.next = Cur;
//    		System.out.println("first " + Head.data);
//    		//这里有点不懂，Head.data不是9999吗【头节点的data是指链表的长度？】
//    		Cur = next;//Cur后移一位
//    	}
    	
   
      //　Head　为新建的一个头结点
      // p　为原来整个链表的头结点,现在Head指向　整个链表
    	System.out.println("P的地址：这里应该也是head的地址:"+p);//是的
    	Node Head = new Node(9999,null);
    	System.out.println("Head的地址："+Head);//@4e25154f
      	Head.next = p;
     
      	Node Cur = p.next;//哦哦，就是这里了，cur最后会变成null;所以p->cur=p->null;P:第一个元素3，null；
      	p.next = null;
      	Node next = null;

      	while(Cur != null){
          next = Cur.next;
          Cur.next = Head.next;
          Head.next = Cur;
          System.out.println("first " + Head.data);

          Cur = next;
      }
      	//此时Head等于【9999，1，2，3，4，3】
      	System.out.println("Head=Head.next;前：Head的地址："+Head);//@4e25154f
      	Head=Head.next;//(这样就返回12343了)【会改变Head地址，9999和1的地址不一样】
      	System.out.println("返回前：Head的地址："+Head);//@70dea4e
      return Head;
     
    	
//    	Node pre,cur,next;
//    	System.out.println("p:"+p.data);
//    	cur = p.next;//【1234】中的1,cur.data=2【p.data是1】头节点是有值得
//    	//System.out.println("cur = p.next;//【1234】中的1:"+cur.data);
//    	pre = null;
//    	while(cur != null) {
//    		next = cur.next;//反转后不能再用cur->next，所以先记录下这个节点
//    		
//    		cur.next = pre;//cur = 【1】-》null
//    		pre = cur;//把cur当前翻转情况存起来，pre:【1】-》null;【2】-》【1】-》
//    		cur = next;
//    	}
//    	//p=pre;
//    	p.next = pre;//把翻转链表交给p//【感觉这里也不对，p=(1234时)（p原先的指针就会丢失？）】
////    	是错的
//    	System.out.println("inverseLinkList_head(Node):这个时候的链表：");
//    	this.printAll();
//    	return p;
    	
    }
    
    
    //无头节点链表翻转
    public Node inverseLinkList(Node p) {
    	
    	Node pre = null;
    	Node r = head;
    	System.out.println("z---------"+r.data);//r的值
    	Node next = null;
    	while(r != p) {//r的值，不等于p的值，p.data=4,r=1
    		next = r.next;
    		
    		r.next = pre;
    		pre = r;
    		r = next;
    	}
    	r.next = pre;
    	
    	
    	return r;
    }
    
  //单链表翻转：无头节点 【翻转头节点 到 p节点】
  	public Node inverseLinkList2(Node p) {
  		Node h = head;
  		//记录翻转好的结点 pre:第一次翻转头节点pre = h -> null
  		Node pre = null;
  		//记录 要翻转 的下一个节点 next
  		Node next;
  		while( h != p) {
  			//下一个节点
  			next = h.next;
  			h.next = pre;//先独立头节点
  			//记录已经翻转的节点
  			pre = h;
  			
  			//继续翻转下一个节点
  			h = next;	
  			
  		}
  		//当头节点 == p节点时 pre已经记录了 头节点到 p节点的上一个节点,用 p 去连接 已经翻转的链表pre
  		p.next = pre;
  		
  		return p;
  	}
	//先定义结构体【这里有一个静态类】
	public static class Node {
		private int data;
		private Node next;//改成public吧
		
		public Node(int data, Node next) {//带头结点的：头结点存储第一个节点的值(长度信息,这是以前的理解)，头结点的next指向第二个实际节点；
			this.data = data;
			this.next = next;
		}
		
		//给外面的接口：获取数值
		public int getData() {
			return data;
		}
	}
	
	public static Node createNode(int value) {
		return new Node(value, null);
	}
	
	   public static void main(String args[]) {
	    	SinglyLinkedList link = new SinglyLinkedList();
	    	System.out.println("hello");
	    	
	    	int data[] = {1,2,3,4,3,2,1};
	    	System.out.println("link.head:"+link.head);//null
	    	//int data[] = {1};
	    	for(int i = 0; i <data.length; i++) {
	    		//link.insertTail(data[i]);
	    		link.insertToHead(data[i]);
	    	}
	    	System.out.println("插入值后：link.head:"+link.head);
	    	//com.atguigu.linked.SinglyLinkedList$Node@7852e922是一个地址
	    	System.out.println("插入值后：link.head.data:"+link.head.data);
	    	
	    	System.out.println("原始打印：");
	    	 link.printAll(link.head);
	    	 Node tempHead =  link.head;
//	    	 System.out.println("tempHead的地址："+tempHead);
//	         Node p = link.inverseLinkList_head(link.head);//这里返回Head：@4e25154f
//	         //不过p的地址是@70dea4e
//	         System.out.println("插入值后：link.head:"+link.head);
//		    System.out.println("插入值后：link.head.data:"+link.head.data);
//		    link.printAll(link.head);//只打印一个三
//	    	 //Node p = link.inverseLinkList(link.head);
//	    	 //为什么翻转之后head为一个元素，
//	         System.out.println("带头节点p的翻转的打印：");
//	         System.out.println("p=:"+p);
//	         System.out.println("p=:"+p.data+"  "+p.getData());
//	         //这里是不同于head的地址；com.atguigu.linked.SinglyLinkedList$Node@4e25154f
//	         link.printAll(p);
	    	 
//	         while(p != null){
//	             System.out.println("aa"+p.data);
//	             p = p.next;
//	         }
	    	 //不去纠结了，反正翻转就会改变原来的链表，
	    	 
//	         link.printAll();// p = p.next;这里让p=最后一个元素
	    	 System.out.println("----------------------");
	    	 link.inverseLinkList2(tempHead);
	    	link.printAll();
	    	if(link.palindrome()) {
	    		System.out.println("回文");
	    	} else {
	    		System.out.println("不是回文");
	    	}
	    	
	    }

	
}
