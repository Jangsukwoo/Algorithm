package CodingStudyHW;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


/*
 * 세그먼트트리 복습
 * 1. 세그먼트트리만들기 : setSegmentTree
 * 2. 세그먼트트리 구간 합 구하기 : getSumFromSegmentTree
 * 3. 특정 값 변경시 세그먼트트리의 해당되는 값에 흡수시키고 나머지 노드들에 영향 주기 : updateSegmentTree
 * 
 * 문제에서 처음에 주어지는 모든 숫자들은 0 
 * 
 * 이니까 세그먼트트리 따로 안만들어줘도 될듯하다.
 * 
 * 명령 번호 0 이면 Sum (getSumFromSegmentTree)
 * 명령 번호 1 이면 Modify(updateSegmentTree)
 * 
 * Sum(i,j)는 a[i] ~a[j]까지의 구간 합
 * Modify(i,k)는 a[i] = k
 * 
 * 업데이트할때 해당 인덱스값 실제로 바꿔주는 처리를 항상 까먹는다.
 * 
 * 세그먼트트리 만들때 
 * 세그먼트트리 배열 사이즈 결정하는 기준, 결정하는 방법 확실하게 알아두기
 * 
 */
public class 수들의합 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N,M;//데이터 수 , 명령 수
	static int nodeSize;
	static long[] numbers;
	static long[] segementTree;
	public static void main(String[] args) throws IOException {
		setData();
		excute();
		bw.flush();
		bw.close();
		
	}
	private static void excute() throws IOException {
		for(int excute=0,functionNumber;excute<M;excute++){
			st = new StringTokenizer(br.readLine());
			functionNumber = Integer.parseInt(st.nextToken());
			switch (functionNumber) {
			case 0://FuntionNumber 0 이면 Sum (left~right)
				int left = Integer.parseInt(st.nextToken())-1;
				int right = Integer.parseInt(st.nextToken())-1;//left,right도 인덱스다
				if(left>right) {//문제에서 i>j인 경우가 있으니 swap시켜준다.
					int tmp = left;
					left = right;
					right = tmp;
				}
				long result = sum(1,0,(N-1),left,right);//노드번호 : 1, data 시작인덱스, data 끝인덱스, left,right 구간
				bw.write(result+"\n");
				break;
			case 1://FuntionNumber 1 이면 Modify
				int idx = Integer.parseInt(st.nextToken())-1;
				long target = Long.parseLong(st.nextToken());
				long diff = (target - numbers[idx]);//여기 순서 뒤바꿨었다.
				numbers[idx] = target;//이렇게 해당 인덱스 값 직접 변경하는 라인을 항상 까먹음 ㅠㅠ
				modify(1,0,(N-1),idx,diff);
				break;
			}
		}
	}

	private static long sum(int node, int start, int end, int left, int right){
		//조건 두개
		if(right<start || left>end) return 0;//아예 말이 안되는경우
		if(left<=start && end<=right) return segementTree[node];//left와 right가 start,end 인덱스를 포함하고 있는, 감싸고있는 경우(구간)
		int mid = (start+end)/2;
		return sum((node*2),start,mid,left,right)+sum((node*2)+1,mid+1,end,left,right);
	}
	private static void modify(int node, int start, int end, int idx, long diff){
		if(!(start<= idx && idx<=end)) return ;//영역 만족하지 않으면 보지않고 끝내버림 
		segementTree[node] +=diff;
		if(start!=end){//끝까지 안봤으면
			int mid = (start+end)/2;
			modify(node*2, start, mid, idx, diff);
			modify((node*2)+1, mid+1, end, idx, diff);
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(nodeSize=1;nodeSize<N;nodeSize*=2);
		nodeSize*=2;
		//그냥 *4+1하는거랑 이렇게 하는거 메모리차이 크게는 없었다.
		//세그먼트트리 배열 사이즈 결정하는 기준+결정하는 방법들 확실하게 알아두기
		numbers = new long[N];
		segementTree = new long[nodeSize];//세그먼트 트리 배열 
	}
}
