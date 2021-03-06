package SDS복습_자료구조;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;



/*
 * 최소힙
 * 배열로 트리를 구현해보자
 * 
 */
public class 자료구조_최대힙 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] tree;//maxHeap
	static int nodeSize;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		tree = new int[(N*4)+1];//배열 트리
		for(int i=0,value;i<N;i++){
			value = Integer.parseInt(br.readLine());
			switch (value) {
			case 0:
				delete();
				break;
			default:
				insert(value);
				break;
			}
		}
		bw.flush();
		bw.close();
	}
	private static void delete() throws IOException{//위에서부터 아래로 
		if(nodeSize==0) bw.write("0"+"\n");
		else {
			bw.write(tree[1]+"\n");//삭제처리
			tree[1] = tree[nodeSize--];
			int parentIdx = 1;
			while(true){
				int leftChildIdx = parentIdx*2;
				int rightChildIdx = parentIdx*2+1;
				if(leftChildIdx>nodeSize) break;
				if(tree[leftChildIdx]>tree[rightChildIdx]){//왼쪽 자식이 더크면
					if(tree[leftChildIdx]>tree[parentIdx]) {
						swap(parentIdx, leftChildIdx);
						parentIdx = leftChildIdx;
					}else break;
				}else{//오른쪽 자식이 더 크면
					if(tree[rightChildIdx]>tree[parentIdx]) {
						swap(parentIdx, rightChildIdx);
						parentIdx = rightChildIdx;
					}else break;
				}
			}
		}	
	}
	private static void insert(int value){ //맨끝에 넣고 치고올라감
		if(nodeSize==0){//현재 노드사이즈가 0이면
			nodeSize++;
			tree[nodeSize] =value;
		}else {
			nodeSize++;
			tree[nodeSize] = value;
			int childIdx = nodeSize;
			int parentIdx = nodeSize/2;
			while(true){
				if(tree[parentIdx]<tree[childIdx]){
					swap(parentIdx,childIdx);
					childIdx = parentIdx;
					parentIdx = childIdx/2;
					if(childIdx==1) break;
				}else break;//아니면 나와버림
			}
		}
	}
	private static void swap(int parentIdx, int childIdx) {
		int tmp = tree[parentIdx];
		tree[parentIdx] = tree[childIdx];
		tree[childIdx] = tmp;
	}
}
