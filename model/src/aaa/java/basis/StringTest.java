package aaa.java.basis;

/**
 * Create by slf on 2020/4/26
 */
/**
 * 测试字符串
 *  字符串中的空格在字符串长度内
 *   String trim后的长度为 去掉空格后的长度
 *   " +123 45"的String.trim()后 输出+123 45。所以trim() 是用来删除前置和后置空格的
 *
 *
 *   “空格” 的字符类型 的整数大小为32
 */

public class StringTest {
    public static void main(String[] args) {
        String str = "+123 45  "; //长度为5，即空格也算字符
        System.out.println(str.length());
        System.out.println(str);
        System.out.println(str.trim().length()); //长度为3
        System.out.println(str.trim());

        char  char1 = 'a';
        int i = char1 - 0;
        System.out.println(i);

        String testmidnull = "123 45";
        char[] chars = testmidnull.toCharArray();

        System.out.println(chars[3]+1);
        char char2 = ' ';
        System.out.println(char2);//输出了空格
        System.out.println(char2+0);//输出32
    }
}
