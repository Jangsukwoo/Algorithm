package 자료구조연습;

import java.util.Scanner;

/*
 * 링크드리스트 직접 구현해서 풀어보자.
 */
class Node_16198{//노드
	int data;
	Node_16198 next;
	public Node_16198(int e){
		this.data = e;
		this.next = null;
	}
}
class LinkedList_16198{//구현할 연결리스트 (=자바 STL의 LinkedList)
	Node_16198 Head;
	Node_16198 Tail;
	int size=0;
	
	public boolean add_first(int a){//가장 처음 자리에 넣기
		Node_16198 new_node = new Node_16198(a);//새로운 노드
		new_node.next = Head;//새로운 노드의 다음은 Head
		Head = new_node;
		size++;
		if(Head.next==null){//만약 다음을 가리키는 노드가 없다면 꼬리로 지정
			Tail = Head;
		}
		return true;
	}
	public boolean add(int a){
		Node_16198 new_node = new Node_16198(a);//새로운 노드
		if(size==0) {
			add_first(a);
		}
		else {
			Tail.next = new_node;
			Tail = new_node;
			size++;
		}
		return true;
	}

	public Node_16198 node(int idx) {
		Node_16198 n = Head;
		for(int i=0;i<idx;i++) n = n.next;
		return n;
	}
	public boolean add(int idx, int a) {
		if(idx==0) add_first(a);
		else {
			Node_16198 tmp = node(idx-1);
			Node_16198 tmp_next = tmp.next;
			Node_16198 new_node = new Node_16198(a);
			tmp.next = new_node;
			new_node.next = tmp_next;
			size++;
			if(new_node.next==null) Tail = new_node;
		}
		return true;
	}
	public boolean remove_first() {
		Node_16198 tmp =Head;
		Head = Head.next;
		tmp = null;
		size--;
		return true;
	}
	public boolean remove(int idx) {
		if(idx==0) return remove_first();
		else {
			Node_16198 tmp = node(idx-1);
			Node_16198 del = tmp.next;
			tmp.next = tmp.next.next;
			if(del==Tail) Tail = tmp;
			del=null;
			size--;
		}
		return true;
	}
	public int get(int idx) {
		Node_16198 tmp = node(idx);
		return tmp.data;
	}
	public int size() {
		return size;
	}
}

public class 백준_에너지모으기 {
	static int N;
	static int max;
	static LinkedList_16198 myLinkedList = new LinkedList_16198();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for(int i=0;i<N;i++) myLinkedList.add(sc.nextInt());
		dfs(0);
		System.out.println(max);
	}
	private static void dfs(int sum) {
		if(myLinkedList.size()<3) {
			max = Math.max(sum,max);
			return ;
		}
		int value=0;
		int save=0;
		for(int i=1;i<(myLinkedList.size()-1);i++){
			value = myLinkedList.get(i-1)*myLinkedList.get(i+1);
			save = myLinkedList.get(i);
			sum+=value;
			myLinkedList.remove(i);
			dfs(sum);
			myLinkedList.add(i,save);
			sum-=value;
		}
	}
}
