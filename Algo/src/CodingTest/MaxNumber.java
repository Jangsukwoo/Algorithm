package CodingTest;
/*
8 8
-00000013
 */
import java.util.Scanner;

public class MaxNumber {
	static int N,K;
	static String number;
	static int max;
	public static void main(String[] args){
		setData();
		find();
	}
	private static void find() {
		for(int i=0;i<=(N-K);i++){
			String stringNumber = "";
			for(int j=0;j<K;j++){
				stringNumber+=number.charAt(i+j);
			}
			max = Math.max(max,Integer.parseInt(stringNumber));
		}
		System.out.println(max);
	}
	private static void setData(){
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		sc.nextLine();
		number = sc.nextLine();
	}
}
