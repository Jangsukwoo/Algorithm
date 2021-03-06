package Simulation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 7:36~
 * 큐로 빼면서 대전붙이고
 * 조건문걸어서 간단히 풀었다.
 * 8:03
 * 
 * 
 */
public class 토너먼트 {
	static int N;
	static int jimin,hansu;
	static int round;
	static int A,B;
	static boolean find;
	static Queue<Integer> q = new LinkedList<Integer>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		round = 1;
		jimin = sc.nextInt();
		hansu = sc.nextInt();
		find = false;
		for(int num=1;num<=N;num++) q.add(num);
		while(!q.isEmpty()){
			int participantSize = q.size();
			if(participantSize%2==0){//짝수 대전이면
				for(int i=0;i<(participantSize/2);i++){
					A = q.poll();
					B = q.poll();
					if(check()) {
						find = true;
						break;
					}
					else win();
				}
			}else{//홀수 대전이면
				for(int i=0;i<((participantSize-1)/2);i++){
					A = q.poll();
					B = q.poll();
					if(check()) {
						find = true;
						break;
					}
					else win();
				}
				q.add(q.poll());
			}
			if(find) break;
			round++;
		}
		if(find) System.out.println(round);
		else System.out.println("-1");
	}
	private static void win() {
		if(A== jimin || A==hansu) q.add(A);
		else if(B==jimin || B==hansu) q.add(B);
		else q.add(A);
		
	}
	private static boolean check() {
		if((A==jimin && B==hansu) || (B==jimin && A==hansu)) return true;
		return false;
	}
}
