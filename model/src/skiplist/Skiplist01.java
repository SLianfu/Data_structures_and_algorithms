package skiplist;

/**
 * 没理解明白
 * * 跳表的一种实现方法。
	 * 跳表中存储的是正整数，并且存储的是不重复的。
	 *
	 * Author：ZHENG

 * @author slf
 *
 */
public class Skiplist01 {

	//main测试
	public static void main(String[] args) {
		Skiplist01 skip = new Skiplist01();
		skip.insert(2);
		skip.insert(3);
		skip.insert(4);
		skip.insert(5);
		skip.insert(6);
		skip.printAll();
		
		skip.insert(6);
		skip.printAll();
		Node find1 = skip.find(6);
		System.out.println(find1);
		
		skip.delete(6);
		System.out.println("删除一个元素之后：");
		skip.printAll();
		//System.out.println(levelCount);
		
		
		
	}

	
	  private static final float SKIPLIST_P = 0.5f;//每两个结点提取一个结点到上一级
	  private static final int MAX_LEVEL = 16;

	  private int levelCount = 1;//表示原始链表？：节点的高度

	  private Node head = new Node();  // 带头链表

	  public Node find(int value) {
	    Node p = head;
	    for (int i = levelCount - 1; i >= 0; --i) {//先假设levelCount=2：一级索引，value=7
	      while (p.forwards[i] != null && p.forwards[i].data < value) {//在这一层【levelCount】
		  //如果p【p为头节点】的（应该是next）节点不等于null，p的下个节点的值小于value，
	        p = p.forwards[i];//p节点后移一位，直到找到p=第二节点2,p.forwrds[1]=4<7
			//然后跳出while循环，进入下一层索引或原始链表，重复之前的操作
	      }
	    }
		//直到原始链表中，p->5,p.forwords[[0]=5<7;
		//如果p的下节点不等于null，并且下节点的值==value：7；返回p的下个节点
	    if (p.forwards[0] != null && p.forwards[0].data == value) {
	      return p.forwards[0];
	    } else {
	      return null;
	    }
	  }

	  public void insert(int value) {
	    int level = randomLevel();//随机分布其在第几层建立索引【】
	    Node newNode = new Node();
	    newNode.data = value;
	    newNode.maxLevel = level;//插入节点的索引级数
	    Node update[] = new Node[level];//记录该节点在第几层？：【节点数组】Node[0~level-1]:
	    //update[0]表示【记录】第0层元素所在下标位置，update[1]表示第一层元素下标位置
	    for (int i = 0; i < level; ++i) {
	      update[i] = head;//每一层的第一个元素为头节点
	    }

	    // record every level largest value which smaller than insert value in update[]
		//记录每个级别的最大值，该最大值小于update []中的插入值
	    Node p = head;
	    for (int i = level - 1; i >= 0; --i) {//从最后一层开始遍历【最上层】
	      while (p.forwards[i] != null && p.forwards[i].data < value) {//：最上层：p.forwards[n-1]=1,data=1<6
	        p = p.forwards[i];//p后移一位p->1.然后开始下一层判断
	      }
	      update[i] = p;// use update save node in search path
		  //在搜索路径中使用更新保存节点：update[0] = p ->5
	    }

	    // in search path node next node become new node forwords(next)
		//在搜索路径节点中，下一个节点成为新节点的单词（下一个）:插入操作【不过要怎么理解插入节点的索引呢？】
	    for (int i = 0; i < level; ++i) {
	      newNode.forwards[i] = update[i].forwards[i];//p.forwards->7,赋值给新节点的forwards
	      update[i].forwards[i] = newNode;
	    }

	    // update node hight更新节点的高
	    if (levelCount < level) levelCount = level;
	  }

	  public void delete(int value) {
	    Node[] update = new Node[levelCount];
	    Node p = head;
	    for (int i = levelCount - 1; i >= 0; --i) {
	      while (p.forwards[i] != null && p.forwards[i].data < value) {
	        p = p.forwards[i];
	      }
	      update[i] = p;
	    }

	    if (p.forwards[0] != null && p.forwards[0].data == value) {
	      for (int i = levelCount - 1; i >= 0; --i) {
	        if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
	          update[i].forwards[i] = update[i].forwards[i].forwards[i];
	        }
	      }
	    }

	    while (levelCount>1&&head.forwards[levelCount]==null){
	      levelCount--;
	    }

	  }

	  // 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
	  // 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
	  // 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
	  //        50%的概率返回 1
	  //        25%的概率返回 2
	  //      12.5%的概率返回 3 ...
	  private int randomLevel() {//返回第几层索引，1表示一级索引节点
	    int level = 1;

	    while (Math.random() < SKIPLIST_P && level < MAX_LEVEL)//SKIPLIST_P=0.5，随机生成的数有
	    	//百分之五十：ran>0.5,返回1，一级索引；
	    	//50%的50%：执行了一次level+1；返回2，二级索引；12.5%返回3，以此类推得出索引的层数分布。
	      level += 1;
	    return level;
	  }

	  public void printAll() {
	    Node p = head;
	    while (p.forwards[0] != null) {
	      System.out.print(p.forwards[0] + " ");
	      p = p.forwards[0];
	    }
	    System.out.println();
	  }

	  public class Node {
	    private int data = -1;
	    private Node forwards[] = new Node[MAX_LEVEL];//【节点数组】forwards[0]表示第0层，最多有第15层；有前进的意思，指下一个链表元素，
	    //MAX_LEVEL值最高层最多有16层,表示链表的索引层
	    private int maxLevel = 0;//maxLevel=0表示原始节点

	    @Override
	    public String toString() {
	      StringBuilder builder = new StringBuilder();
	      builder.append("{ data: ");
	      builder.append(data);
	      builder.append("; levels: ");
	      builder.append(maxLevel);
	      builder.append(" }");

	      return builder.toString();
	    }
	  }

	}
