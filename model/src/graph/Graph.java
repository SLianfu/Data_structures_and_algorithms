package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 有点不懂！！！
 * @author slf
 *
 */
public class Graph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph graph = new Graph(8);
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(1, 2);
		graph.addEdge(1, 4);
		graph.addEdge(3, 4);
		graph.addEdge(2, 5);
		graph.addEdge(4, 5);
		graph.addEdge(4, 6);
		graph.addEdge(5, 7);
		graph.addEdge(6, 7);
		
		graph.bfs(0, 6);
		System.out.println();
		graph.print(prev , 0, 6);//new int[8]
		System.out.println("-----------");
		//graph.print(new int[8] , 0, 6);//输出：0 6，这里是用来搞笑的，哈哈哈
		//输出：0 1 4 6 0 1 4 6 ，什么鬼【噢噢，这是在bfs以及输出路径了	】 这里在输出就输出两次了
	}

 // 无向图
  private int v; // 顶点的个数		8
  private LinkedList<Integer> adj[]; // 邻接表
  private static int[] prev;

  public Graph(int v) {
    this.v = v;
    adj = new LinkedList[v];	//adj[8]
    for (int i=0; i<v; ++i) {
      adj[i] = new LinkedList<>();
    }
  }

  public void addEdge(int s, int t) { // 无向图一条边存两次
    adj[s].add(t);
    adj[t].add(s);
  }
  
  public void bfs(int s, int t) {
	  if (s == t) return;
	  boolean[] visited = new boolean[v];	//标记已经被访问的定点，用true标记
	  visited[s]=true;
	  Queue<Integer> queue = new LinkedList<>();//队列：存储已经被访问但其连接定点未被访问
	  queue.add(s);
	   prev = new int[v];//记录搜索路径//把他放到全局变量
	  //prev[w] 存储的是，顶点 w 是从哪个前驱顶点遍历过来的。比如，我们通过顶点 2 的邻接表访问到顶点 3，那 prev[3] 就等于 2。
	  
	  for (int i = 0; i < v; ++i) {//开始都标记为-1
	    prev[i] = -1;
	  }
	  while (queue.size() != 0) {
		//poll检索并删除此队列的头，或如果此队列为空，则返回{@code null}。
	    int w = queue.poll(); //w为0【0出队，w=0：1，3】，1【连接顶点2，4】，3【4】，
	    //，这里直接是4出队：4【5，6】，2【5】
	   for (int i = 0; i < adj[w].size(); ++i) {	//刚开始0连接1，3	adj[0] = 2吧
		   
	      int q = adj[w].get(i);	//q=1，3，q=4,2【这里应该可以2，4】，q=4直接跳过。
	      if (!visited[q]) {	//visited【1】不等于true
	        prev[q] = w;		//prev[1] = 0,记录0，记录的是定点q(1)是从0那里遍历过来的
	        //prev[2]是从顶点1那里来的
	        if (q == t) {	//找到终点，打印路径，结束
	          print(prev, s, t);
	          return;
	        }
	        visited[q] = true;//否则，将定点1标记为true，并且入队，继续遍历定点0的连接
	        queue.add(q);
	      }
	    }
	  }
	}
	
	private void print(int[] prev, int s, int t) { // 递归打印s->t的路径
	  if (prev[t] != -1 && t != s) {
	    print(prev, s, prev[t]);
	  }
	  System.out.print(t + " ");
	}
	

boolean found = false; // 全局变量或者类成员变量

public void dfs(int s, int t) {
  found = false;
  boolean[] visited = new boolean[v];
  int[] prev = new int[v];
  for (int i = 0; i < v; ++i) {
    prev[i] = -1;
  }
  recurDfs(s, t, visited, prev);
  print(prev, s, t);
}

private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
  if (found == true) return;
  visited[w] = true;
  if (w == t) {
    found = true;
    return;
  }
  for (int i = 0; i < adj[w].size(); ++i) {
    int q = adj[w].get(i);
    if (!visited[q]) {
      prev[q] = w;
      recurDfs(q, t, visited, prev);
    }
  }
}

}


