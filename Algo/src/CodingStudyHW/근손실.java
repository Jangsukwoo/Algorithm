package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 계속 500이상의 중량을 유지하고 싶다.
 * 운동 기간동안 항상 중량이 500 이상이 되도록 하는 경우의 수를 출력
 * -> 순열
 */
public class 근손실 {
	static int N,K;
	static int[] exerciseKit;
	static int[] pickExerciseKit;
	static int possibleCase;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		setData();
		permutation(0);
		System.out.println(possibleCase);
	}
	private static void permutation(int depth){
		if(depth==N){
			if(checkWeight()) possibleCase++;
			return;
		}
		for(int i=0;i<N;i++){
			if(visit[i]==false){
				visit[i] = true;
				pickExerciseKit[depth] = i;
				permutation(depth+1);
				visit[i] = false;
			}
		}
	}
	
	private static boolean checkWeight() {
		int standard = 500;
		for(int day=0;day<N;day++){
			standard= standard-K+exerciseKit[pickExerciseKit[day]];
			if(standard<500) return false;//하루라도 500미만 치면 false
		}
		//이 반복문 통과시
		//항상 500이상 친거니까 true
		return true;
	}
	private static void setData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		exerciseKit = new int[N];
		visit = new boolean[N];
		pickExerciseKit = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int day=0;day<N;day++) exerciseKit[day] = Integer.parseInt(st.nextToken());
	}
}
