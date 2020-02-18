package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 스타트링크 {
	static int F,S,G,U,D;
	static boolean[] floor;
	static int button;
	static boolean arrive;
	public static void main(String[] args) throws IOException {
		setData();
		if(S==G) {
			System.out.println("0");
			return;
		}
		else findUsingBFS();			
		if(arrive) System.out.println(button);
		else System.out.println("use the stairs");
	}
	private static void findUsingBFS() {
		Queue<Integer> q = new LinkedList<Integer>();
		insertQueue(q,S);
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int currentFloorNumber = q.poll();
				
				//목적지면 return;
				if(currentFloorNumber==G) {
					arrive=true;
					return;
				}
				
				//아니면 버튼을 눌러본다.
				int nextFloor = currentFloorNumber+U;
				if(rangeCheck(nextFloor) && floor[nextFloor]==false) insertQueue(q,nextFloor);
				nextFloor = currentFloorNumber-D;
				if(rangeCheck(nextFloor) && floor[nextFloor]==false) insertQueue(q,nextFloor);
			}
			button++;
		}
	
	}
	private static boolean rangeCheck(int nextFloor) {
		if(nextFloor>=1 && nextFloor<=F) return true;
		return false;
	}
	private static void insertQueue(Queue<Integer> q, int floorNumber) {
		q.add(floorNumber);
		floor[floorNumber] = true;
	}
	private static void setData() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());//건물높이
		S = Integer.parseInt(st.nextToken());//현재위치
		G = Integer.parseInt(st.nextToken());//목적지
		U = Integer.parseInt(st.nextToken());//위층버튼
		D = Integer.parseInt(st.nextToken());//아래층버튼
		floor = new boolean[F+1];//건물높이
	}
}
