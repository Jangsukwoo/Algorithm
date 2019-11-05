package Simulation;
/*
어레이리스트로 넣고 빼고 하려다가 시간이 터질것같아서 
1부터 100까지 찍어보니
2의 승수만큼 반복하는 규칙발견
규칙대로 넣어주니 해결
 */
import java.util.Scanner;

public class 카드2 {
	static int N;
	static int[] answerTable = new int[500001];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		makeAnswerTable();
		System.out.println(answerTable[sc.nextInt()]);
	}
	private static void makeAnswerTable() {
		int cnt=2;
		int pow=1;
		answerTable[1] = 1;
		while(true){
			for(int i=2;i<=Math.pow(2,pow);i+=2){
				if(cnt>500000) return;
				answerTable[cnt] = i;
				cnt++;
			}
			pow++;
		}
	}
}
