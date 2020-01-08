package SDS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 세그먼트트리
 * N: 수의 개수 
 * M :수의 변경이 일어나는 회수 ->update
 * K :구간의 합을 구하는 회수
 * 
 * 1<=N<=1000000
 * 1<=M<=10000
 * 1<=K<=10000
 * 
 * 2^K>1000000인 K를 찾으면 좋지만
 * 
 * 그냥 N*4로 잡는다
 * 
 * 데이터를 추가하려면 세그먼트 트리를 다시 만들어야한다
 * 
 * 
 */
public class 구간합구하기_3일차 {
	static int N,M,K;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static long[] data;
	static long[] segmentTree;
	public static void main(String[] args) throws IOException {
		
		input();
	
	}
	private static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		data = new long[N+1];
		segmentTree = new long[(N*4)+1];
		for(int i=1;i<=N;i++) data[i] = Long.parseLong(br.readLine());
		
		setSegmentTree(1,1,N); //0~N-1은 인덱스 

		for(int excute=0;excute<(M+K);excute++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());//commandType
		
			switch (a) {
			case 1://b번째 수를 c로 바꾼다. -> update
				int target = Integer.parseInt(st.nextToken());//idx번째 수
				long value = Long.parseLong(st.nextToken());
				long diff = value - data[target];//차이값
				data[target] = value;
				updateSegmentTree(1,1,N,target,diff);
				break;
			case 2://b번째 수부터 c번째 수까지 합을 구한다. -> idx가 b~c인 구간의 합을 구하자.
				int left = Integer.parseInt(st.nextToken());//idx번째 수
				int right = Integer.parseInt(st.nextToken());
				long sum = getSectionSumSegmentTree(1,1,N,left,right);//node,start,end,left,right
				bw.write(sum+"\n");
				break;
			}
		}
		
		bw.flush();
		bw.close();
	}
	private static long getSectionSumSegmentTree(int node, int start, int end, long left, long right) {
		if(end<left || right<start) return 0;//아예 영역 밖일 떄
		if(start>=left && end<=right) return segmentTree[node]; //구간보다 클때
		//여기까지 왔으면 무조건 겹치는 구간
		
		int mid = (start+end)/2;
		return getSectionSumSegmentTree(node*2,start,mid,left,right)+getSectionSumSegmentTree((node*2)+1,mid+1,end,left,right);
	}
	private static void updateSegmentTree(int node, int start, int end, int idx, long diff) {//바꾸려는  idx가 구간에 속한 모든 노드의 값 갱신 
		if(!(start<=idx && idx<=end)) {
			//구간에 속하지 않으면 return
			return;
		}//통과되면
		segmentTree[node]+=diff;//값 갱신
		if(start!=end) {
			int mid = (start+end)/2;
			updateSegmentTree(node*2,start,mid,idx,diff);
			updateSegmentTree((node*2)+1,mid+1,end,idx,diff);
		}	
	}
	private static long setSegmentTree(int node, int start, int end){ //반환값 long..?
		if(start==end) return segmentTree[node] = data[start];
		int mid = (start+end)/2;//중간으로 찢는다.
		return segmentTree[node]=setSegmentTree(node*2,start,mid)+setSegmentTree((node*2)+1,mid+1,end);
	}
}
