package linked;

public class Longest_Palindrome {


    public static int longestPalindrome(String s) {
        int[] count = new int[128];
        int max = 0;
        //计算重复的个数
        for(char c: s.toCharArray()){
            if (++count[c] == 2){//这里妙啊
                max ++;
                count[c] = 0;
            }
        }
        max *= 2;
        return max < s.length() ? ++max : max;
    }
    public static void main(String[] args) {
        String a = "asdwe";
        int i = longestPalindrome(a);
        System.out.println(i);
    }
}
class Solution {
    public int longestPalindrome(String s) {
        int[] count = new int[128];
        int max = 0;
        //计算重复的个数
        for(char c: s.toCharArray()){
            if (++count[c] == 2){//这里妙啊
                max ++;
                count[c] = 0;
            }
        }
        max *= 2;
        return max < s.length() ? ++max : max;
    }
}