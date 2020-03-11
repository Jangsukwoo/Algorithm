package BAEKJOON;

import java.util.ArrayList;
import java.util.Scanner;

public class RoundRobin{
	static int N,T;//N = players, T = turn
	static ArrayList<Integer> playerList;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		playerList = new ArrayList<Integer>();
		while(true) {
			N = sc.nextInt();
			if(N==0) break;//끝
			T = sc.nextInt();
			playerList.clear();
			for(int i=0;i<N;i++) playerList.add(0);
			roundRobinStart();
			
		}
		
	}
	private static void roundRobinStart() {
		int idx=0;//처음시작은 0 
		while(true){
			boolean end = true;
			int size = playerList.size();
			for(int i=0;i<(T-1);i++){//턴만큼 돌건데
				playerList.set(idx,playerList.get(idx)+1);
				idx++;
				idx = idx%size;

			}//턴 끝
			playerList.remove(idx);
			for(int i=0;i<(size-2);i++){
				if(playerList.get(i)!=playerList.get(i+1)){
					end = false;
					break;
				}
			}	
			if(end) {
				System.out.println(playerList.size()+" "+playerList.get(0));
				break;
			}
		}
	}
}
