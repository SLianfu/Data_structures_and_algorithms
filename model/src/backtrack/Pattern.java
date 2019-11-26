package backtrack;

/**
 * 回溯算法：正则表达式
 * @author slf
 *
 */


	
	

public class Pattern {
	  private boolean matched = false;  //标志位，标志已经找到 与正则表达式相匹配的字符串
	  private static char[] pattern = {'a','b','c','*','?','a'}; // 正则表达式
	  private static int plen = pattern.length; // 正则表达式长度
	   
	
	  public static void main(String[] args) {
		  Pattern patt = new Pattern(pattern, plen);
		  String text = "abcdeeedda";
		  int tlen = text.length();
		  boolean match = patt.match(text.toCharArray(), tlen);
		  System.out.println(match);
		}
	 
	  
	  public Pattern(char[] pattern, int plen) {
	    this.pattern = pattern;
	    this.plen = plen;
	  }
	
	  public boolean match(char[] text, int tlen) { // 文本串及长度
	    matched = false;
	    rmatch(0, 0, text, tlen);
	    return matched;
	  }
	
	  private void rmatch(int ti, int pj, char[] text, int tlen) { //ti(标记字符串长度), pj（标记正则表达式长度） 标记长度
	    if (matched) return; // 如果已经匹配了，就不要继续递归了
	    if (pj == plen) { // 正则表达式到结尾了
	      if (ti == tlen) matched = true; // 文本串也到结尾了
	      return;
	    }
	    if (pattern[pj] == '*') { // *匹配任意个字符
	      for (int k = 0; k <= tlen-ti; ++k) { //tlen-ti：正则表达式长度-字符串长度:10 - 6 = 4
	        rmatch(ti+k, pj+1, text, tlen);
	      }
	    } else if (pattern[pj] == '?') { // ?匹配0个或者1个字符
	      rmatch(ti, pj+1, text, tlen);
	      rmatch(ti+1, pj+1, text, tlen);
	    } else if (ti < tlen && pattern[pj] == text[ti]) { // 纯字符匹配才行
	      rmatch(ti+1, pj+1, text, tlen);
	    }
	  }
}


