package SDS복습_자료구조;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 이진 검색 트리를 만들고
 * 전위 순회한 결과를 출력해보자
 */
public class 자료구조_이진검색트리 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BinaryTree binaryTree = new BinaryTree();
	static class Node{
		 int value;
		 Node leftChild,rightChild;
		 public Node(int v) {
			 value = v;
		 }
	}
	static class BinaryTree{
		Node root;
		public void insert(int value) {
			Node current = root;
			while(true) {
				if(current.value<value) {
					if(current.rightChild==null) {
						current.rightChild = new Node(value);
						break;
					}else current = current.rightChild;
					
				}else {
					if(current.leftChild==null) {
						current.leftChild = new Node(value);
						break;
					}else current = current.leftChild;
				}
			}
		}
		public void preorder(Node current){//VLR
			if(current!=null) {
				System.out.println(current.value);
				preorder(current.leftChild);
				preorder(current.rightChild);
			}
		}
		public void postorder(Node current){//LRV
			if(current!=null) {
				postorder(current.leftChild);
				postorder(current.rightChild);
				System.out.println(current.value);
			}
		}
	}
	public static void main(String[] args) throws IOException {
		setBinaryTree();
		binaryTree.postorder(binaryTree.root);
	}
	private static void setBinaryTree() throws IOException {
		while(true) {
			String read = null;
			read = br.readLine();
			if(read==null || read.equals("")) break;
			else {
				if(binaryTree.root==null) {
					binaryTree.root = new Node(Integer.parseInt(read));
				}else {
					binaryTree.insert(Integer.parseInt(read));
				}
			}
		}
	}
}
