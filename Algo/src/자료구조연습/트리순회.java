package 자료구조연습;
import java.util.Scanner;
class Node1991{
	char data;
	Node1991 left,right;
}
class tree{
	public void preorder(Node1991 root) { //전위순회
		if(root != null) {
			System.out.print(root.data); //루트
			preorder(root.left); //왼
			preorder(root.right); //오
		}
	}
	public void inorder(Node1991 root) { //중위순회
		if(root != null) {
			inorder(root.left); //왼
			System.out.print(root.data); //루트
			inorder(root.right); //오
		}
	}
	public void postorder(Node1991 root) { //후위순회
		if(root != null) {
			postorder(root.left); //왼
			postorder(root.right); //오
			System.out.print(root.data); //루트
		}
	}
}
public class 트리순회{
	public static void main(String[] args) {
		tree t = new tree();
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		Node1991[] n = new Node1991[V];
		sc.nextLine();
		for(int i=0; i<V;i++) n[i] = new Node1991();	
		for(int i=0; i<V;i++) {
			String[] input = sc.nextLine().trim().split(" ");
			if(input.length==3) {
				n[input[0].charAt(0)%65].data = input[0].charAt(0);
				if(input[1].charAt(0)!='.')n[input[0].charAt(0)%65].left = n[input[1].charAt(0)%65];
				if(input[2].charAt(0)!='.')n[input[0].charAt(0)%65].right = n[input[2].charAt(0)%65]; 
			}
		}
		t.preorder(n[0]);
		System.out.println();
		t.inorder(n[0]);
		System.out.println();
		t.postorder(n[0]);
		
	}
}