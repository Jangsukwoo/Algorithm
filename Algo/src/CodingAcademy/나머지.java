package CodingAcademy;

import java.util.Scanner;

public class 나머지 {
	static boolean[] visit = new boolean[42];
	static int answer=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i=1;i<=10;i++) {
			int a = sc.nextInt();
			visit[a%42] = true;
		}
		for(int i=0;i<42;i++){
			if(visit[i]) answer++;
		}
		System.out.println(answer);
	}
}
