package CodingTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class 라게인213 {
	static class Order{
		String menu;
		int orderTime;
		public Order(String menu, int orderTime) {
			this.menu = menu;
			this.orderTime = orderTime;
		}
	}
	static HashMap<String, Integer> cookTime = new HashMap<String, Integer>();
	static int[] ovens;
	static Queue<Order> orderQueue = new LinkedList<Order>();
	public static void main(String[] args) {
		System.out.println(solution(2, new String[] {"SPAGHETTI 3","FRIEDRICE 2", "PIZZA 8"},
				new String[] {"PIZZA 1", "FRIEDRICE 2", "SPAGHETTI 4", "SPAGHETTI 6", "PIZZA 7", "SPAGHETTI 8"}));
//		System.out.println(solution(2, new String[] {"A 3","B 2"}, new String[] {"A 1","A 2","B 3","B 4"}));
//
//		System.out.println(solution(2, new String[] {"COOKIE 10000"}, new String[] {"COOKIE 300000"}));

	}
	public static int solution(int n, String[] recipes, String[] orders) {
		int answer = 0;
		/*
		 * 마지막 주문이 완성되는 시간.
		 */

		init(n,recipes,orders);


		answer = getAnswerByCooking();

		return answer;
	}
	private static int getAnswerByCooking() {
		int answer = 0;

		int time = 1;
	
		while(!orderQueue.isEmpty()) { //모든 오더가 끝날 때 까지
		


			for(int i=0;i<ovens.length;i++){//비어있는 화구찾기
				if(ovens[i]==0) {
					if(orderQueue.peek().orderTime<=time){//가능
						Order order = orderQueue.poll(); //주문 받고

						int getTime = cookTime.get(order.menu);
						ovens[i] = getTime;
						System.out.println("현재시간"+time);
						System.out.println("주문받음");
						System.out.println("시간 time"+time+" 오븐상태");
						System.out.println(Arrays.toString(ovens));
						if(orderQueue.isEmpty()){//만약 마지막 주문이라면
							System.out.println("마지막주문"+getTime);
							System.out.println("현재시간"+time);
							answer = getTime+time;
							return answer;
						}
					}
				}
			} 
			time++;
			for(int i=0;i<ovens.length;i++) {
				if(ovens[i]>0) ovens[i]--; //조리
			}
		}
		return answer;
	}
	private static void init(int n, String[] recipes, String[] orders) {
		ovens = new int[n];

		for(int i=0;i<recipes.length;i++) {
			String[] tokens = recipes[i].split(" ");
			cookTime.put(tokens[0],Integer.parseInt(tokens[1]));
		}

		for(int i=0;i<orders.length;i++) {
			String[] tokens = orders[i].split(" ");
			orderQueue.add(new Order(tokens[0], Integer.parseInt(tokens[1])));
		}
	}
}
