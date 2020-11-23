package 자료구조연습;

import java.util.Stack;

public class Stack두개로Queue {
	static class QueueByDoubleStack{
		Stack<Integer> inbox = new Stack<Integer>();
		Stack<Integer> outbox = new Stack<Integer>();
		public QueueByDoubleStack() {
		}
		public void add(int num) {
			inbox.add(num);
		}
		public int poll() {
			 
			if(!inbox.isEmpty()) {
				while(!inbox.isEmpty()) outbox.add(inbox.pop());
			}
			int popdata = outbox.pop();
			if(!outbox.isEmpty()) {
				while(!outbox.isEmpty()) inbox.add(outbox.pop());
			}
			return popdata;
		}
		
	}
	public static void main(String[] args) {
		QueueByDoubleStack queue = new QueueByDoubleStack();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		System.out.println(queue.poll());
		queue.add(5);
		System.out.println(queue.poll());
	}
}
