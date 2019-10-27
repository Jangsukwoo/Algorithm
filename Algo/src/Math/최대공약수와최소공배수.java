package Math;
/*
 * Euclid's Algorithm
 * 유클리드 호제법
 * 
 * 유클리드 호제법은
 * 
 * 두 정수의 최대공약수를 구하는 공식,알고리즘이다.
 * A,B 두 정수가 있을 때 
 * 두 수가 서로 상대방의 수를 나누고, 나머지값을 이용하면
 * 결국 최대 공약수가 나온다는 이론이다.
 * 
 * 최소공배수는 다음과 같은 관계까 있다.
 * A,B 두 정수에 대해서 최대공약수 값이 G라고 할 때 
 * 
 * A = Gx
 * B = Gy
 * 
 * 이때 A*B = G*G*x*y 이고
 * x와 y는 서로소인 관계이다. 
 * 서로소(Relatively prime)의 정의 : 1이외에는 공약수를 갖지 않는 둘 이상의 양의 정수
 * 
 * 즉 G는 A와B의 최대공약수이고 x와 y는 서로소여야한다. 
 * 그렇지않으면 
 * x와 y 사이 간에 1 이외의 공약수가 존재한다는 것이고
 * A*B = GGxy에서
 * 
 * x와 y의 1보다 큰 공약수와 G값이 곱해진 새로운 최대 공약수가 나와버려서
 * G는 최대공약수가 아니게된다.
 * 
 * 따라서 이론에 의해 x와 y는 서로소이고
 * 집합의 관점으로 접근했을 때 
 * 더이상 공약수가 존재하지않는 x,y 그리고 최대공약수 G를 곱한 것이
 * 최대공배수가 된다는 이론이고 Gxy가 합집합이 된다. 
 * 즉 양변을 G로 나누면
 * A*B/G = Gxy (최소공배수) 가 된다.
 * 따라서 정리하면
 * 최대공배수 lcm은
 * A*B/gcd인 것이다.
 * 참고 : http://staff.www.ltu.se/~larserik/applmath/chap10en/part3.html
 */
import java.util.Scanner;

public class 최대공약수와최소공배수 {
	static int gcd;//최대공약수 (The Greatest Common Denominator = GCD)
	static int lcm;//최소공배수 (The Least Common Multiple = LCM)
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt(); 
		int B = sc.nextInt();
		
		/*
		 * A와 B의 최대공약수
		 * 유클리드 호제법으로 구하기
		 * A와 B의 최대공약수를 (A,B)라고 표현한다.
		 * A를 B로 나눈 나머지 R에 대해서 유클리드 호제법에 의하면 
		 * (A,B) = (B,R)가 성립한다.
		 * 즉,
		 * A가 1071이고 B가 1029라면
		 * (1071,1029)=(1029,42)=(42,21)=(21,0)=21 이다.
		 * 이를 코드로 구현하면 다음과 같다.
		 */
		 gcd = getGCD(A,B);//gcd는 최대공약수	 
		 /*
		  * 최소공배수 X 최대공약수 = A * B 이다.
		  * 이를 이용하면
		  * 최소공배수 = A*B/gcd가 된다.
		  * 위에서 구한 gcd값을 이용하면 다음과 같다.
		  */
		 lcm = A*B/gcd; //lcm는 최소공배수
		 System.out.println(gcd);
		 System.out.println(lcm);
		 
	}
	private static int getGCD(int A, int B){
		while(B!=0) {
			//(A,B) = (B,R)
			int R = A%B;
			A = B;
			B = R; 
		}
		return A;
	}
}
