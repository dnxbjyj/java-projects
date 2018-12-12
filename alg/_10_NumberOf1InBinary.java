/*
计算一个整数的二进制表示中的1的个数
算法一句话核心思想：
 */
public class _10_NumberOf1InBinary{
    public static void main(String argv[]){
	System.out.println(numberOf1(12));
    }

    /*
        计算一个整数的二进制中1的格式
     */
    public static int numberOf1(int n){
	int count = 0;
	while(0 != n){
	    ++count;
	    n = (n - 1) & n;
	}
	return count;
    }
}
