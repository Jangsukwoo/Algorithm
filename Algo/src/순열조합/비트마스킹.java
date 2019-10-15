package 순열조합;
import java.util.Arrays;

public class 비트마스킹 {
	static boolean[] visit;
	public static void main(String[] args) {
		 //visit = new boolean[3];
		 for(int i = 1; i <= 3; i++) {// i는 비트를 몇개 쓸것인가
			 visit = new boolean[i];
			 for(int j = 0, size = 1 << i; j < size; j++) { //j는 2^i - 1 까지 본다.
				 //1로 다 채워진 비트를 만드려는 반복문임
				 //j<size 니까 1로 채워진 애만 보겠지 
				 //숫자 j는 0~7이고 이를 각각 비트로 표현하면 000부터 111이니까 
				 //숫자 자체가 모든 경우의수를 표현한다. 
				 for(int k = 0; k < i; k++) {//자리를 검사 
					 if(((1 << k) & j) == 0) {
						 visit[k] = false;
					 } else {
						visit[k] = true;
					 }
				 }
				 System.out.println(Arrays.toString(visit));
			 }
		 }
	}
}
