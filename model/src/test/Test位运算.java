package test;

public class Test位运算 {
    public static void main(String[] args) {
        //1100 1000 0000
        int h = 3200;
        int keyhash = h >>> 16;
        System.out.println("3200>>>16:除以2的16次方="+keyhash); //=0
        keyhash = h ^ (h >>> 16); //任何数 异或上0 值不变
        System.out.println(keyhash);


        int n = 134217728; //2的27次方
        //int n = 19;
        //cap |= cap>>>1; //先除以2 = 9 ，然后cap = 19 | 9 =27
        n |= n >>> 1; //9|19 = 27
        System.out.println("cap="+n);
        n |= n >>> 2; //27除以4=6 27|6 = 31
        System.out.println("cap="+n);
        n |= n >>> 4; //31除以2的四次方16 = 1  31|1 = 31
        System.out.println("cap="+n);
        n |= n >>> 8;  //31除以256 = 0  31|0 = 31，（任何数 或上 0 值不变）
        System.out.println("cap="+n);
        n |= n >>> 16; //除以65,536 = 0
        System.out.println("cap="+(n+1)); //返回32 //2048 2的11次方
    }
}
