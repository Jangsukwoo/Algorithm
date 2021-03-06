package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 보물 {
	static Integer[] seriesA;
	static Integer[] seriesB;
	static int N;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		logic();
		System.out.println(min);
	}
	private static void logic(){
		Arrays.sort(seriesA);
		Arrays.sort(seriesB, Collections.reverseOrder());
		for(int i=0;i<N;i++) {
			min+=(seriesA[i]*seriesB[i]);
		}
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		seriesA = new Integer[N];
		seriesB = new Integer[N];
		st = new StringTokenizer(br.readLine());
		for(int j=0;j<N;j++) seriesA[j] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int j=0;j<N;j++) seriesB[j] = Integer.parseInt(st.nextToken());
		
	}
}
