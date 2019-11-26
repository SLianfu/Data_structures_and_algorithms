package tree;

public class Tree01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	private Node tree;

	  public Node find(int data) {
	    Node p = tree;
	    while (p != null) {
	      if (data < p.data) p = p.left;
	      else if (data > p.data) p = p.right;
	      else return p;
	    }
	    return null;
	  }
	  

	public void insert(int data) {
	  if (tree == null) {
	    tree = new Node(data);
	    return;
	  }
	
	  Node p = tree;
	  while (p != null) {
	    if (data > p.data) {
	      if (p.right == null) {
	        p.right = new Node(data);
	        return;
	      }
	      p = p.right;
	    } else { // data < p.data
	      if (p.left == null) {
	        p.left = new Node(data);
	        return;
	      }
	      p = p.left;
	    }
	  }
	}

	  public static class Node {
	    private int data;
	    private Node left;
	    private Node right;

	    public Node(int data) {
	      this.data = data;
	    }
	  }




	  public void delete(int data) {
	  Node p = tree; // p指向要删除的节点，初始化指向根节点
	  Node pp = null; // pp记录的是p的父节点
	  while (p != null && p.data != data) {//p指向要删除的节点
	    pp = p;
	    if (data > p.data) p = p.right;
	    else p = p.left;
	  }
	  if (p == null) return; // 没有找到

	  // 要删除的节点有两个子节点
	  if (p.left != null && p.right != null) { // 查找右子树中最小节点
	    Node minP = p.right;
	    Node minPP = p; // minPP表示minP的父节点
	    while (minP.left != null) {//【遍历左子树不为空的情况】
	      minPP = minP;//minPP指向minP
	      minP = minP.left;//minP->minP.left;直到找到最小左节点，用minP指向它
	    }
	    p.data = minP.data; // 将minP的数据替换到p中
	    p = minP; // 下面就变成了删除minP了【现在要删除的是minP这个节点】，
	    //这里不懂！直接minpp.left = null不就好了？
	    //好像这样又不可以，要是没有找到左节点呢？
	    pp = minPP;
	    //这两行代码是怎么删除minP这个节点的？这里没有说用这两行代码删除minP,
	    //而是用p指向minP和pp指向minPP,之前p记录的是要删除的节点p.data=18,现在p.data=25或者19，
	    //把指向要删除的节点。然后利用情况一和情况二：
	    /**
	     * 一：删除的节点没有子节点，我们只需要直接将父节点中，指向要删除节点的指针置为 null
	     * 二：要删除的节点只有一个子节点（只有左子节点或者右子节点，这里只有右节点），我们只需要更新父节点中，
	     * 指向要删除节点的指针，让它指向要删除节点的子节点就可以了：pp.right = child
	     */
	  }

	  // 删除节点是叶子节点或者仅有一个子节点
	  Node child; // p的子节点
	  if (p.left != null) child = p.left;
	  else if (p.right != null) child = p.right;
	  else child = null;

	  if (pp == null) tree = child; // 删除的是根节点
	  else if (pp.left == p) pp.left = child;
	  else pp.right = child;
	}
	  
	  public Node findMin() {
	    if (tree == null) return null;
	    Node p = tree;
	    while (p.left != null) {
	      p = p.left;
	    }
	    return p;
	  }

	  public Node findMax() {
	    if (tree == null) return null;
	    Node p = tree;
	    while (p.right != null) {
	      p = p.right;
	    }
	    return p;
	  }
}


	  
	