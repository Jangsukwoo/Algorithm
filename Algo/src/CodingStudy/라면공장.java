package CodingStudy;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * 하루에 밀가루를 1톤씩 사용함
 * stock : 현재 남아있는 밀가루 수량
 * datas : 밀가루 공급 일정
 * supplies : 해당 시점에 공급 가능한 밀가루 수량
 * 원래 공장으로부터 공급받을 수 있는 시점 k
 * 밀가루가 떨어지지 않고 공장을 운영하기 위해 
 * 최소한 몇 번 해외 공장으로부터 밀가루를 공급 받아야하나?
 * k일까지 버텨야함
 */
public class 라면공장 {
	public static void main(String[] args) {
		System.out.println(solution(4,new int[] {4,10,15},new int[] {20,5,10},30));
		
	}
	static public int solution(int stock, int[] dates, int[] supplies, int k) {
		int answer = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return -Integer.compare(o1,o2); //내림차순
			}
		});
		int idx=0;
		int N = dates.length;
		for(int day=1;day<k;day++){//k일까지 버틴다.
			if(idx<N && dates[idx]==day){//공급 받을 수 있는 날이 되었으면 일단 pq에 넣음
				pq.add(supplies[idx]);
				idx++;
			}
			stock--;
			if(stock==0){//소진됐을 때는 공급받을 수 있는 날에 공급 받은 밀가루 량중에 가장 큰 밀가루를 받은 걸로 치고 stock을 갱신
				stock+=pq.poll();
				answer++;
			}
		}
		return answer;
	}
}
