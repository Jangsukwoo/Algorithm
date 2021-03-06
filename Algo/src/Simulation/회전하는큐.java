package Simulation;

import java.util.ArrayList;
import java.util.Scanner;
/*
 * 처음에 dequeue로 생각했다가
 * 찾으려고하는 위치의 값에 대한 조회를 하려면
 * 어레이리스트가 편하기 때문에 어레이리스트로 구현함
 */
public class 회전하는큐 {
	static int N,M;
	static int[] data;
	static ArrayList<Integer> dq;
	static int left,right;
	static int cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		dq = new ArrayList<Integer>();
		data = new int[M];
		for(int i=0;i<M;i++) data[i] = sc.nextInt();
		for(int num=1;num<=N;num++) dq.add(num);
		for(int target=0;target<M;target++){
			sideCheck(target);
			shift(target);
		}
		System.out.println(cnt);
	}
	private static void shift(int target) {
		int size = dq.size();
		if(left>right){//오른쪽이 더 작으면
			while(true){
				int value = dq.get(0);
				if(value == data[target]){
					dq.remove(0);
					break;
				}
				else { //오른쪽으로 시프트
					int last = dq.get(size-1);
					dq.remove(size-1);
					dq.add(0,last);
					cnt++;
				}
			}
		}else {
			while(true){
				int value = dq.get(0);
				if(value == data[target]){
					dq.remove(0);
					break;
				}
				else { //왼쪽으로 시프트
					dq.remove(0);
					dq.add(value);
					cnt++;
				}
			}
		}
	}
	private static void sideCheck(int target){
		int size = dq.size();
		left=0;
		right=0;
		for(int i=0;i<size;i++){
			left++;
			if(dq.get(i)==data[target]) break;
		}
		for(int i=(size-1);i>=0;i--) {
			right++;
			if(dq.get(i)==data[target]) break;
		}
	}
}
