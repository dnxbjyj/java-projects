/*
����һ�������Ķ����Ʊ�ʾ�е�1�ĸ���
�㷨һ�仰����˼�룺
 */
public class _10_NumberOf1InBinary{
    public static void main(String argv[]){
	System.out.println(numberOf1(12));
    }

    /*
        ����һ�������Ķ�������1�ĸ�ʽ
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
