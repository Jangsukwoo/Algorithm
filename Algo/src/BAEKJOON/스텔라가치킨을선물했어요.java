package BAEKJOON;

import java.util.Arrays;
import java.util.Scanner;

class Participant implements Comparable<Participant>{
	int solve;
	int penalty;
	public Participant(int s, int p){
		this.solve = s;
		this.penalty = p;
	}
	@Override
	public int compareTo(Participant o){
		if(this.solve == o.solve) return Integer.compare(this.penalty,o.penalty);
		else return -Integer.compare(this.solve,o.solve);
	}
	
}
public class 스텔라가치킨을선물했어요 {
	static int N;
	static int studentCount;
	static int solve;
	static Participant[] participants;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		participants = new Participant[N];
		for(int i=0;i<N;i++){
			int s = sc.nextInt();
			int p = sc.nextInt();
			participants[i] = new Participant(s, p);
		}
		Arrays.sort(participants);
		solve = participants[4].solve;//5등의 solve 수 저장
		for(int i=5;i<participants.length;i++){
			if(participants[i].solve==solve) studentCount++;
			else break;
		}
		System.out.println(studentCount);
		
	}
}
