package SDS복습;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


/*
 * 세그먼트트리 코드 안보고 쌩으로 만들어보기
 * 
 * setSegmentTree, updateSegmentTree, getSumFromSegmentTree 메소드 세개 구현해보기
 */
public class 자료구조_구간합구하기_세그먼트트리 {
	static int N,M,K;
	static long[] number;
	static long[] segmentTree;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		setData();
		getAnswer();
	}
	private static void getAnswer() throws IOException {
		for(int i=0;i<(M+K);i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			switch (a) {
			case 1://b를 c로 바꾼다.		
				long diff =  c - number[b-1];
				updateSegmentTree(1,0,N-1,diff,b-1);
				break;
			case 2://b부터 c사이의 합을 출력 
				long sum = getSumFromSegmentTree(1,0,N-1,b-1,c-1);
				break;
			}
		}
	}
	private static long getSumFromSegmentTree(int node, int start, int end, int left, int right) {
		if(end<=left && right<=start) return 0;//아예 범위가 말이 안되는경우 
		return 0;
	}
	private static void updateSegmentTree(int node, int start, int end, long diff, int idx) {
		if(!(start<=idx && idx<=end)) return;//영역안에 속해있지 않으면 끝내버린다.
		segmentTree[node]+=diff;
		int mid = (start+end)/2;
		updateSegmentTree(node*2, start, mid, diff, idx);
		updateSegmentTree((node*2+1), start, mid, diff, idx);
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		number = new long[N];
		segmentTree = new long[N*4];
		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			number[i] = Integer.parseInt(st.nextToken());
		}
		setSegmentTree(1,0,N-1);
	}
	private static long setSegmentTree(int node, int start, int end){
		if(start==end) return segmentTree[node] = number[start]; //스타트가 아니라 node임 
		int mid = (start+end)/2;
		return segmentTree[node] = setSegmentTree(node*2, start, mid) + setSegmentTree((node*2)+1,mid+1, end);
	}
}
