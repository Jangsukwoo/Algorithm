package 자료구조연습;

import java.util.Arrays;

/*
 * 세그먼트트리. 기본 뼈대는 이진트리
 * 가장 밑에 리프노드들은 자식노드
 * 두 자식의 부모 노드는
 * 그 두 자식의 합
 * 세그먼트트리를 구현할 때 배열을 쓴다.
 * 
 * 배열로 트리를 표현 
 * 배열의 사이즈를 잡아줄 떈
 * N=12라면
 * 2^k 중에 N값인 12를 넘는 k값을 찾아주는게 좋지만 번거로울 수 있기 때문에
 * 여기서 k값은 4고 
 * 2^4 => 16
 * 16x2 = 32 <- 세그먼트 트리의 크기
 * 그냥 넉넉히 N*4로 잡아줘도 된다.
 * 
 * 배열을 잡았다면 
 * 1번인덱스가 최초의 루트노드
 * 왼쪽 노드는 (x2)
 * 오른쪽 노드는 (x2+1)
 * 
 * 즉 최초의 루트 노드의 왼쪽 자식은 2, 오른쪽 자식은 3
 * 
 * 트리의 높이는 맨위의 루트로부터 리프까지의 거리.
 * 
 * 
 */
public class 세그먼트트리 {
	public static void main(String[] args) {
		int[] segmentTree = new int[48];
		int[] data = {3,5,6,7,2,9,4,5,2,8,1,5};
		setSegmentTree(segmentTree,data,1,0,11);//배열로 세그먼트 트리 만들기
		updateSegmentTree(segmentTree,1,0,11,4,8);//세그먼트 트리의 값 갱신하기. 1번 노드부터 시작할거고 4번 인덱스를 10으로 만들건데 원래 값이 2니까 8만큼 더해줘서 10을 만든다.
		//8만큼 더해줄 때 모든 트리의 값도 업데이트해야함
		int sectionSum = getSectionSum(segmentTree,1,0,11,8,11);//특정 구간의 합을 구한다.
		printSegmentTree(segmentTree);
		System.out.println("구간합"+sectionSum);
	}

	private static int getSectionSum(int[] tree, int node, int start, int end, int left,int right) {
		/*
		 * 합을 구하는 과정은 크게 4가지
		 * 구하고자 하는 Section의 영역 left~right
		 * 1. left,right와 start,end가 겹치지 않는 경우 ->if(left>end || right<start)
		 * 2. left,right가 start,end를 포함하는 경우 -> if(left<=start && end<=right)
		 * 3. start,end가 left,right를 포함하는 경우
		 * -> return sum(tree,node*2,start,mid,left,right)+sum(tree,node*2+1,mid+1,end,left,right)
		 * 4.left,right가 start,end가 겹쳐져있는경우 
		 * ex) left<=start<=right<=end 같은 
		 * -> return sum(tree,node*2,start,mid,left,right)+sum(tree,node+2+1,mid+1,end,left,right)
		 * 
		 */
		if(left>end || right<start) return 0;
		if(left<=start && end<=right) return tree[node];
		int mid = (start+end)/2;
		return getSectionSum(tree,node*2,start,mid,left,right)+getSectionSum(tree,(node*2)+1,mid+1,end,left,right);
	}

	private static void updateSegmentTree(int[] segmentTree,int node,int start,int end,int idx,int diff) {
		if(!(start<=idx && idx<=end)){//구간에 속하지 않는 노드의 번호면
			return; //보지않고 끝냄
		}
		segmentTree[node]+=diff;
		if(start!=end) {
			int mid = (start+end)/2;
			updateSegmentTree(segmentTree, node*2, start, mid, idx, diff);
			updateSegmentTree(segmentTree, (node*2)+1, mid+1,end, idx, diff);
		}
	}

	private static int setSegmentTree(int[] segmentTree, int[] data,int node,int start, int end){
		//node : 노드 번호
		if(start==end) return segmentTree[node] = data[start];
		int mid = (start+end)/2;
		return segmentTree[node] = setSegmentTree(segmentTree,data,node*2,start,mid) + setSegmentTree(segmentTree,data,(node*2)+1,mid+1,end);
	}
	private static void printSegmentTree(int[] segmentTree) {
		for(int idx=0;idx<48;idx++) {
			if(segmentTree[idx]!=0) {
				System.out.println("노드의번호:"+idx+" 구간합값"+segmentTree[idx]);
			}
		}
	}
	
}
