package CodingTest;

import java.util.Stack;

public class 이투2 {
	static class Ledger{
		int day;
		int rate;
		int money;
		public Ledger(int day, int rate, int money) {
			this.day = day;
			this.rate = rate;
			this.money = money;
		}
	}
	public static void main(String[] args) {
		System.out.println(solution(new String[] {"01/01 4 50000","01/11 6 3555","02/01 0 -23555","02/25 5 5000","03/25 0 -15000","06/09 8 43951","12/30 9 99999"}));
	}
	public static int solution(String[] ledgers) {
		int answer = 0;
		int[] months = {0,31,59,90,120,151,181,212,243,273,304,334};
		Stack<Ledger> stack = new Stack<>();
		for (int i = 0; i < ledgers.length; i++) {
			String[] input = ledgers[i].split(" ");
			int month = months[Integer.parseInt(input[0].substring(0, 2))-1];
			int day = Integer.parseInt(input[0].substring(3, 5));
			int rate = Integer.parseInt(input[1]);
			int money = Integer.parseInt(input[2]);

			if(money >=0) { //양수 or 0이면
				stack.add(new Ledger(month+day,rate,money));
			}else {
				while(money !=0) {
					Ledger ledger = stack.pop();
					if(ledger.money >= Math.abs(money)) {
						answer += Math.abs(money) * ledger.rate/100 * ((month+day)-ledger.day) / 365;
						if(ledger.money !=Math.abs(money)) {
							stack.add(new Ledger(ledger.day, ledger.rate, ledger.money + money));
						}
						money = 0;
					}else {
						money = money + ledger.money;
						answer += ledger.money * ledger.rate/100 * ((month+day)-ledger.day) / 365;
					}
				}
			}
		}
		while(!stack.isEmpty()) {
			Ledger ledger = stack.pop();
			answer += ledger.money * ledger.rate/100 * (365 -ledger.day) / 365;
		}

		return answer;
	}

}
