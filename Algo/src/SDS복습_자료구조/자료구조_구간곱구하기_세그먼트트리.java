package SDS복습_자료구조;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


/*
 * 세그먼트트리 코드 안보고 쌩으로 만들어보기
 * 
 */
public class 자료구조_구간곱구하기_세그먼트트리 {
	static int N,M,K;
	static long[] number;
	static long[] segmentTree;
	static long mod = 1000000007;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		setData();
		getAnswer();
		bw.flush();
		bw.close();
	}
	private static void getAnswer() throws IOException {
		for(int i=0;i<(M+K);i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			switch (a) {
			case 1://b를 c로 바꾼다.		
				long val =  c;
				number[b-1] = c; //<- number값도 업데이트 해줘야한다.
				updateSegmentTree(1,0,N-1,val,b-1);
				break;
			case 2://b부터 c사이의 합을 출력 
				long multipleResult = getMultiPleFromSegmentTree(1,0,N-1,b-1,c-1);
				bw.write(multipleResult+"\n");
				break;
			}
		}
	}
	private static long getMultiPleFromSegmentTree(int node, int start, int end, int left, int right) { // 범위 중요
		if(end<left || right<start) return 1;//아예 범위가 말이 안되는경우 
		if(left<=start && end<=right) return segmentTree[node];//영역이 걸쳐있는 경우. 즉,4~9의 구간의 합이면, 5~6의 합은 당연히 속해있다.
		int mid = (start+end)/2;
		return ((getMultiPleFromSegmentTree(node*2, start, mid, left, right)%mod)*(getMultiPleFromSegmentTree((node*2+1), mid+1, end, left, right)%mod))%mod;
	}
	private static long updateSegmentTree(int node, int start, int end, long val, int idx) {
		if(!(start<=idx && idx<=end)) return segmentTree[node];//왜 세그트리의 노드값을 반환해야하는지??
		if(start==end) 	return segmentTree[node]=val;
		int mid = (start+end)/2;	
		return segmentTree[node] = (((updateSegmentTree(node*2, start, mid, val, idx)%mod) * ((updateSegmentTree((node*2+1), mid+1, end, val, idx)%mod))%mod));

	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		number = new long[N];
		segmentTree = new long[N*4+1];
		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			number[i] = Integer.parseInt(st.nextToken());
		}
		setSegmentTree(1,0,N-1);
	}
	private static long setSegmentTree(int node, int start, int end){
		if(start==end) return segmentTree[node] = number[start]; //스타트가 아니라 node임 
		int mid = (start+end)/2;
		return segmentTree[node] = (((setSegmentTree(node*2, start, mid))%mod) * ((setSegmentTree((node*2)+1,mid+1, end))%mod))%mod;
	}
}
