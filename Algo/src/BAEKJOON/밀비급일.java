package BAEKJOON;

import java.util.Scanner;

public class 밀비급일 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true){
			String readLine = sc.nextLine();
			if("END".equals(readLine)) break;
			for(int i = readLine.length()-1;i>=0;i--){
				System.out.print(readLine.charAt(i));
			}
			System.out.println();
		}
	}
}
