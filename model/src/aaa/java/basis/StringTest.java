package aaa.java.basis;

/**
 * Create by slf on 2020/4/26
 */
//测试字符串
public class StringTest {
    public static void main(String[] args) {
        String str = "  ddd"; //长度为5，即空格也算字符
        System.out.println(str.length());
        System.out.println(str);
        System.out.println(str.trim().length()); //长度为3
    }
}
