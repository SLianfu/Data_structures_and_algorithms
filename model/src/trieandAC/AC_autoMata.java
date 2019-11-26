package trieandAC;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;



public class AC_autoMata {
	private AcNode root = new AcNode('/'); // 存储无意义字符

//	public static void main(String[] args) {
//        char[] patterns = {'t', 'a','o', 's'};//敏感词集合1
//        String text = "sartoarsoarsat";//输入字符串，这里只能查出一个敏感词开始下标
//        System.out.println(match(patterns));
//
//        String[] patterns2 = {"Fxtec Pro1", "谷歌Pixel"};//敏感词几个2
//
//        String text2 = "一家总部位于伦敦的公司Fxtex在MWC上就推出了一款名为Fxtec Pro1的手机，该机最大的亮点就是采用了侧滑式全键盘设计。DxOMark年度总榜发布 华为P20 Pro/谷歌Pixel 3争冠";
//        System.out.println(match(text2, patterns2));
//    }
	
	/*private void insert (String pattern) {
        AcNode node = root;	//node指针 P
        int len = pattern.length();
        for (int i = 0; i < len; i++) {
            String c = pattern.charAt(i) + "";//charAt返回该元素下标的char value ='a'+"";
            if(Objects.isNull(node.children.get(c))) {//从根节点那里查找c,如果Map中没有该字符,就创建一个节点 存储该字符
                node.children.put(c, new ACNode(c));
            }
            node = node.children.get(c);//指向下一个子元素
        }

        node.isEndingChar = true;//当某个节点的子节点为空时，标记true
        node.length = pattern.length();//[pattern：abcd]的长度为4
    }*/
	public void insert(char[] text) {
		AcNode p = root;
	    for (int i = 0; i < text.length; ++i) {
	      int index = text[i] - 'a';
	      if (p.children[index] == null) {
	    	  AcNode newNode = new AcNode(text[i]);
	        p.children[index] = newNode;
	      }
	      p = p.children[index];
	    }
	    p.isEndingChar = true;
	  }

public void match(char[] text) { // text是主串
  int n = text.length;
  AcNode p = root;
  for (int i = 0; i < n; ++i) {
    int idx = text[i] - 'a';
    while (p.children[idx] == null && p != root) {
      p = p.fail; // 失败指针发挥作用的地方
    }
    p = p.children[idx];
    if (p == null) p = root; // 如果没有匹配的，从root开始重新匹配
    AcNode tmp = p;
    while (tmp != root) { // 打印出可以匹配的模式串
      if (tmp.isEndingChar == true) {
        int pos = i-tmp.length+1;
        System.out.println("匹配起始下标" + pos + "; 长度" + tmp.length);
      }
      tmp = tmp.fail;
    }
  }
}
	
public void buildFailurePointer() {
  Queue<AcNode> queue = new LinkedList<>();
  root.fail = null;
  queue.add(root);
  while (!queue.isEmpty()) {
    AcNode p = queue.remove();
    for (int i = 0; i < 26; ++i) {
      AcNode pc = p.children[i];
      if (pc == null) continue;
      if (p == root) {
        pc.fail = root;
      } else {
        AcNode q = p.fail;
        while (q != null) {
          AcNode qc = q.children[pc.data - 'a'];
          if (qc != null) {
            pc.fail = qc;
            break;
          }
          q = q.fail;
        }
        if (q == null) {
          pc.fail = root;
        }
      }
      queue.add(pc);
    }
  }
}

public class AcNode {
  public char data; 
  public AcNode[] children = new AcNode[26]; // 字符集只包含a~z这26个字符
  public boolean isEndingChar = false; // 结尾字符为true
  public int length = -1; // 当isEndingChar=true时，记录模式串长度
  public AcNode fail; // 失败指针
  public AcNode(char data) {
    this.data = data;
  }
}
}
