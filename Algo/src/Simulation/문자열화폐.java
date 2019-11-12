package Simulation;

import java.util.Scanner;

/*
 * 뒤부터 채운다.
 * 수식의 규칙성 찾아본다.
 * 처음에 나머지로 했는데
 * 나머지가 아니라 그냥 단순히 빼면 되는거였음.
 * 
 */
public class 문자열화폐 {
	static int N;
	static int X;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		X = sc.nextInt();
		if(X>(N*26)) System.out.println("!");
		else if(X<N) System.out.println("!");
		else {
			int alphabet = 26;
			for(int i=N;i>=1;i--){
				while(true){
					if(alphabet==1) {
						X = X-alphabet;
						sb.append((char)('A'+(alphabet-1)));
						break;
					}
					int portion = (X/alphabet);//몫
					int reamin = (X-alphabet);//나머지
					if(portion>0 && (reamin>=(i-1))){
						X = X-alphabet;
						sb.append((char)('A'+(alphabet-1)));
						break;
					}else alphabet--;
				}
			}	
		}
		System.out.println(sb.reverse().toString());
	}
}
