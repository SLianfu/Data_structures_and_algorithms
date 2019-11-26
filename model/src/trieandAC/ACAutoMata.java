package trieandAC;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 * AC自动机
 * @author wayne
 */
public class ACAutoMata {
    private ACNode root;

    public ACAutoMata() {
        this.root = new ACNode("/");
    }

    private void insert (String pattern) {
        ACNode node = this.root;	//node指针 P
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
    }

    //构建失败指针
    //fail指针。它表示文本串在当前节点失配后，我们应该到哪个节点去继续匹配
    private void buildFailurePointer() {
        ACNode root = this.root;
        LinkedList<ACNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            ACNode p = queue.pop();//删除并返回此列表的第一个元素

            for(ACNode pc: p.children.values()){//pc为p节点的子节点：可能是：a，b ,c,
                if (Objects.isNull(pc)) {//如果提供的引用pc为null，则返回true，否则返回false。
                    continue;
                }

                if(p == root) {
                    pc.fail = root;//pc的fail失败指针 指向root
                } else {
                    ACNode q = p.fail;//a的fail为root，为非null
                    while (Objects.nonNull(q)) {//如果提供的引用为非null，则返回true，否则返回false。
                    	//通过子节点的key值 查找 当前节点的失败指针 的下一个元素 是否能找到对应的节点，找不到为null
                        ACNode qc = q.children.get(pc.data);//pc为p【a】（当前节点）子节点【b】，q为失败指针【root】，qc为失败指针的子节点【null】
                        if(Objects.nonNull(qc)) {//qc不为null
                            pc.fail = qc;//子节点的失败指针指向qc
                            break;
                        }
                        //没有找到子节点的失败指针qc,继续向上遍历q,直到 q 是 root 为止，让节点 pc 的失败指针指向 root
                        q = q.fail;
                    }
                    if(Objects.isNull(q)) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);	//将p的子节点加入队列
            }
        }
    }

    private Boolean match (String text) {
        ACNode root = this.root;
        ACNode p = root;

        int n = text.length();
        for(int i = 0; i < n; i++) {
            String c = text.charAt(i) + "";
            while(Objects.isNull(p.children.get(c)) && p != root){//p指向root，p的子节点（根据c字符查找子节点）为空的话，并且pbu'等于root
                p = p.fail;
            }

            p = p.children.get(c);//p= p的子节点
            if(Objects.isNull(p)) {//p等于null，则p是root节点
                p = root;
            }

            ACNode tmp = p;	//把p赋值给tmp
            while ( tmp != root) {
                if (tmp.isEndingChar == true) {//当tmp找到敏感词，终止查询
                    System.out.println("Start from " + (i - p.length + 1));//i从0开始，i=6时找到敏感词（长度3），敏感词下标6-3+1
                    return true;
                }
                tmp = tmp.fail;
            }
        }

        return false;
    }

    public static boolean match(String text, String[] patterns) {//patterns：多个模式串的集合
        ACAutoMata automata = new ACAutoMata();
        for (String pattern: patterns) {
            automata.insert(pattern);
        }

        automata.buildFailurePointer();
        return automata.match(text);
    }

    public class ACNode {
        private String data;
        private Map<String, ACNode> children;	//字符集
        private Boolean isEndingChar;	//标记位，当isEndingChar=true时，记录模式串长度
        private Integer length;	//	当isEndingChar=true时，记录模式串长度
        private ACNode fail;	//失败指针

        public ACNode(String data) {
            this.data = data;
            this.children = new HashMap<>();
            this.isEndingChar = false;
            this.length = 0;
            this.fail = null;
        }
    }

    public static void main(String[] args) {
        String[] patterns = {"at", "art", "oars", "soar"};//敏感词集合1
        String text = "sartoarsoarsat";//输入字符串，这里只能查出一个敏感词开始下标
        System.out.println(match(text, patterns));

        String[] patterns2 = {"Fxtec Pro1", "谷歌Pixel"};//敏感词几个2

        String text2 = "一家总部位于伦敦的公司Fxtex在MWC上就推出了一款名为Fxtec Pro1的手机，该机最大的亮点就是采用了侧滑式全键盘设计。DxOMark年度总榜发布 华为P20 Pro/谷歌Pixel 3争冠";
        System.out.println(match(text2, patterns2));
    }
}
