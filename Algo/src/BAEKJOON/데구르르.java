package BAEKJOON;

import java.util.Scanner;

public class 데구르르 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true){
			int N = sc.nextInt();
			if(N==0) break;
			int blank=0;
			sc.nextLine();
			for(int i=0;i<N;i++) {
				String readLine = sc.nextLine()+" ";
				char[] line = readLine.toCharArray();
				for(int j=0;j<line.length;j++){
					if(line[j]==' ' && j>=blank){
						blank = j;
						break;
					}
				}
			}
			System.out.println((blank+1));
		}
	}
}
