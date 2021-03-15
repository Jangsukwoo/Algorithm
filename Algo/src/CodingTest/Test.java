package CodingTest;

import java.util.Stack;
public class Test {
	static Stack<Integer> inbox;
	static Stack<Integer> outbox;
	public static void main(String[] args) {
		init();
		System.out.println(dequeue());
		inqueue(1);
		System.out.println(dequeue());
		inqueue(2);
		inqueue(3);
		System.out.println(dequeue());
		inqueue(4);
		System.out.println(dequeue());
	}
	private static void init() {
		inbox = new Stack<Integer>();
		outbox = new Stack<Integer>();
	}
	private static int dequeue() {
		int popNum = -1;
		while(!inbox.isEmpty()) outbox.push(inbox.pop());
		if(outbox.isEmpty()) popNum = -1;
		else popNum = outbox.pop();
		return popNum;
		
	}
	private static void inqueue(int num) {
		while(!outbox.isEmpty()) inbox.push(outbox.pop());
		inbox.push(num);
	}
}
