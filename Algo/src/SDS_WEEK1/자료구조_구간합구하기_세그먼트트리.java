package SDS_WEEK1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 백준 2042번
 * 세그먼트트리
 * N,M,K (N: 수의 개수, M: 수의 변경이 일어나는 회수, K: 구간의 합을 구하는 회수)
 * 1<=N<=1000000
 * 1<=M<=10000
 * 1<=K<=10000
 * 
 */
public class 자료구조_구간합구하기_세그먼트트리 {
	static int N,M,K;
	static long[] data;
	static long[] segmentTree = new long[2097152];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		inputData();
		setSegmentTree(1,0,(M-1));
		//excuteCommand();
		bw.flush();
		bw.close();
	}
	private static void excuteCommand() throws IOException {
		for(int i=0;i<(M+K);i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int value1 = Integer.parseInt(st.nextToken());
			int value2 = Integer.parseInt(st.nextToken());
			switch (command) {
			case 1://값을 변경 value1번째 수를 value2로 변경
				long diff = value2 -data[value1];
				updateSegmentTree(1,0,(M-1),(value1-1),diff);
				break;
			case 2://구간 합 value1과 value2 구간의 합(left~right Section)
				long sectionValue = getSegmentSectionSum(1,0,11,(value1-1),(value2-1));
				bw.write(sectionValue+"\n");
				break;
			}
		}
	}
	private static long getSegmentSectionSum(int node, int start, int end, int left, int right) {
		if(left>end || right<start) return 0;
		if(left<=start && end<=right) return segmentTree[node];
		int mid=(start+end)/2;
		return getSegmentSectionSum(node*2,start,mid,left,right)+getSegmentSectionSum(node*2+1,mid+1,end,left,right);
	}
	private static void updateSegmentTree(int node, int start, int end,int idx,long diff) {
		if(!(start<=idx && idx<=end)) return;
		segmentTree[node]+=diff;
		if(start!=end) {
			int mid = (start+end)/2;
			updateSegmentTree(node*2, start, mid, idx, diff);
			updateSegmentTree((node*2)+1, mid+1,end, idx, diff);
		}
	}
	private static long setSegmentTree(int node, int start, int end ) {
		if(start==end) return segmentTree[node] = data[start];
		int mid = (start+end)/2;
		return segmentTree[node] = setSegmentTree(node*2,start,mid)+setSegmentTree((node*2)+1,mid+1,end);
	}
	private static void inputData() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		data = new long[N];
		System.out.println(M+" "+N+" "+K);
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			data[i] = Long.parseLong(st.nextToken());
			System.out.println("입력");
		}
		System.out.println(Arrays.toString(data));
	}
}
