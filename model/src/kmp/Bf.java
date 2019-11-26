package kmp;

/**
 * 暴力匹配
 * @author slf
 *
 */
public class Bf {//ViolenceMatch

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//测试暴力匹配算法
		String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
		String str2 = "尚硅谷你尚硅你";
		int index = violenceMatch(str1, str2);
		System.out.println("index=" + index);

	}

	// 暴力匹配算法实现
	public static int violenceMatch(String str1, String str2) {
		char[] s1 = str1.toCharArray();
		//将此字符串转换为新的字符数组。
		//返回值：一个新分配的字符数组，其长度是此字符串的长度，并且其内容已初始化为包含此字符串表示的字符序列。
		char[] s2 = str2.toCharArray();

		int s1Len = s1.length;//20
		int s2Len = s2.length;//8

		int i = 0; // i索引指向s1
		int j = 0; // j索引指向s2
		while (i < s1Len && j < s2Len) {// 保证匹配时，不越界

			if(s1[i] == s2[j]) {//匹配ok
				i++;
				j++;
			} else { //没有匹配成功
				//如果失配（即str1[i]! = str2[j]），令i = i - (j - 1)，j = 0。
				//主串    ：a b c d a b c e;
				//模式串：a b c e;//不匹配时：i=3,j=3，
				//匹配了3个-》i从0变成3；j=3;i要变成1，1=i-(j-1);【我的天，直接i = i-j+1】
				//i要从0开始继续加一：3 - 2 = 1；【i从开始与j比较的位置加一】
				i = i - (j - 1);
				j = 0;
			}
		}
		
		//判断是否匹配成功
		if(j == s2Len) {
			return i - j;//返回匹配字符串开始的下标
		} else {
			return -1;
		}
	}

}
