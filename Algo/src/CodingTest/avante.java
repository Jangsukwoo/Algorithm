package CodingTest;
/*
 * hyndai
 */
import java.util.PriorityQueue;

class RentCar implements Comparable<RentCar>{
	String plate;
	int odo;
	
	public RentCar(String p, int o) {
		plate = p;
		odo = o;
	}

	@Override
	public int compareTo(RentCar o) {
		if(this.odo==o.odo) return this.plate.compareTo(o.plate);
		else return Integer.compare(this.odo,o.odo);
	}
}
public class avante {
	public static void main(String[] args) {
		
	}
    public String solution(int n, String[] plates, int[] odo, int k, int[] drives){
    	PriorityQueue<RentCar> pq = new PriorityQueue<RentCar>();
    	for(int i=0;i<n;i++) pq.add(new RentCar(plates[i],odo[i]));	
    	for(int i=0;i<k;i++) {
    		RentCar rentcar = pq.poll();
    		rentcar.odo+= drives[i];
    		pq.add(rentcar);
    	}
    	String answer = pq.peek().plate;
    	System.out.println(answer);
        return answer;
    }
}
