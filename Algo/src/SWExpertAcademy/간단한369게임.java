package SWExpertAcademy;

import java.util.Scanner;

/*
 * N 최대 1000
 */
public class 간단한369게임 {
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N =sc.nextInt();
		for(int i=1;i<=N;i++){
			String data = Integer.toString(i);
			int cnt=0;
			for(int j=0;j<data.length();j++) {
				if(data.charAt(j)=='3'||data.charAt(j)=='6'||data.charAt(j)=='9') cnt++;
			}
			switch (cnt) {
			case 0:
				sb.append(i+" ");
				break;
			case 1:		
				sb.append("-"+" ");
				break;
			case 2:				
				sb.append("--"+" ");
				break;
			case 3:				
				sb.append("---"+" ");
				break;
			}
		}
		System.out.println(sb.toString());
	}
}
