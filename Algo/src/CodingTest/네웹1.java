package CodingTest;

import java.util.Collections;
import java.util.PriorityQueue;

public class 네웹1 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] {32000, 18000}, new int[] {1}));
		
	}
    public static int solution(int[] prices, int[] discounts) {
        int answer = 0;
        PriorityQueue<Integer> descPricePQ = new PriorityQueue<Integer>(Collections.reverseOrder());
        PriorityQueue<Integer> descDiscountPQ = new PriorityQueue<Integer>(Collections.reverseOrder());
        
        for(Integer price : prices) descPricePQ.add(price);
        for(Integer discount : discounts) descDiscountPQ.add(discount);
        
        
        int idx = 0;
        while(!descDiscountPQ.isEmpty()) {
        	if(!descPricePQ.isEmpty()) {
        		double maxPrice = descPricePQ.poll();
        		double maxDiscount = descDiscountPQ.poll();
        		double discountPirce = maxPrice * (maxDiscount/100);
        		double applyDiscountPrice = maxPrice - discountPirce;
        		prices[idx++]  = (int) applyDiscountPrice;
        	}else break;
        }
        
        for(Integer price : prices) answer+=price;
        
        return answer;
    }
}
