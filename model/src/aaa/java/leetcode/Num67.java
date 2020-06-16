package aaa.java.leetcode;

/**
 * Create by slf on 2020/4/26
 */
public class Num67 {

    /**
     * 把字符串转成整型：有点不明白的是，我们Java为什么不可以直接将字符类型转成整数呢
     * 将传入的String 中的数字组 转成整型
     * @param str
     * @return
     */
    public int strToInt1(String str){


        return 0;
    }
    public int strToInt2(String str) {

        str = str.trim();
        if (str.length() == 0)
            return 0;

        int startIdx = 0;
        boolean flag = true;
        char ch = str.charAt(startIdx);

        while (startIdx < str.length() && !((ch >= '0' && ch <= '9') || ch == '+' || ch == '-'))
            startIdx++;

        if (startIdx == str.length())
            return 0;

        ch = str.charAt(startIdx);
        if (!((ch >= '0' && ch <= '9') || ch == '+' || ch == '-'))
            return 0;

        if (ch == '+' || ch == '-') {

            flag = ch == '+' ? true : false;
            startIdx++;
        }

        long cur = 0;

        for (int i = startIdx; i < str.length(); i++) {

            char c = str.charAt(i);
            if (!(c >= '0' && c <= '9'))
                break;

            cur *= 10;
            cur += flag ? (c - '0') : -(c - '0');

            if (cur > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if (cur < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;

        }

        return (int)cur;
    }
}
