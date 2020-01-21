package SDS_WEEK1;

import java.util.Scanner;

public class 스택_3일차{
	public static int[] stack = new int[10000];
	public static int top = -1;
	public static void main(String[] args) {
		//입력
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String operation;
		int value=0;
		for(int i=0; i<N; i++) {
			operation = sc.next();
			switch (operation) {
			case "push":
				value = sc.nextInt();
				push(value);
				break;
			case "pop":
				pop();
				break;		
			case "size":
				size();
				break;		
			case "empty":
				empty();
				break;		
			case "top":
				top();
				break;		
			}
		}
	}
	private static void top() {
		if(top!=-1) {
			System.out.println(stack[top]);	
		}
		else {
			System.out.println("-1");
		}
		
	}
	private static void empty() {
		if(top==-1) {
			System.out.println("1");
		}
		else {
			System.out.println("0");
		}	
	}
	private static void size() {
		System.out.println(top+1);
	}
	private static void pop() {
		if(top==-1) {
			System.out.println("-1");
		}
		else {
			System.out.println(stack[top]);
			top--;
		}
	}
	private static void push(int value) {
		if(top<9999) {
			top++;
			stack[top] = value;
		}
	}
}
