package test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Test递归 {
    //计算深度
    static int depth = 0;
    public static void main(String[] args) {
        Test递归 test = new Test递归();
        Long n = 50l;
        int n2 = 45;
        long start = System.currentTimeMillis();
        //Long fib = test.fib1(n);
        int fib2 = test.fib2(n2);
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("花费时间"+time+"ms");
        System.out.println("结果："+fib2);
        //System.out.println("深度："+depth);
        //加了容器后 40 花费时间5149ms sum = 102334155 ; n=45 花费时间53800ms  sum = 1134903170
        //
        //没有加容器：40 花费时间980ms   ; n = 45 花费时间9432ms    //结果：1134903170
        //为什么加了容器 反应更慢了
    }

    //f(0) = 0 , f(1)=1 n>1
    //f(n) = f(n-1)+f(n-2);
    //当n很大时，会超时，n=80 n = 40时，sum = 102334155
    //60也输出不了 n = 50 sum = -298632863，换成Long类型后sum = 12586269025
    //没有加容器：花费时间 96834 ms 90多秒 93967ms ；加了容器 加载不出来
    public Long fib1(Long n){
        //depth++;

        //可以用用map存储已经计算过的值,这里不要用Map接口 作泛型，这样会强转返回值类型
        // 【不过好像不用泛型，也要就行类型转换】
        //HashMap hashMap =new HashMap<Long,Long>();
        Long sum = 0l;
        if (n == 1){
            return 1l;
        }
        if (n == 0){
            return 0l;
        }

       /* if (hashMap.containsKey(n)){
            //取值
            return (Long) hashMap.get(n);
        }*/
        sum = fib1(n-1) + fib1(n-2);
        //存值
        //hashMap.put(n,sum);
        return sum;
    }

    /**
     * 力扣 n=40花费时间0ms 结果：102334155
     *      n = 45 花费时间0ms 结果：1134903170
     * @param n
     * @return
     */
    public int fib2(int n) {
        if(n <= 1) {
            return n;
        }
        int first = 0;
        int second = 1;
        //for(int i = 0; i < n - 1 ; i++) {
        for(int i = 2; i <= n  ; i++) {

            int sum = (first + second)  ;//% 1000000007
            //这里 n=2 sum=0+1 ==>n0+n1, n3=n2+n1; nn = nn-1 + nn-2
            //i = 2 = n => sum = 1; i = n =12 => sum=n11+n10
            first = second;
            second = sum;
            System.out.println("i= "+i+" sum = "+sum);
        }
        return second;
    }
}
