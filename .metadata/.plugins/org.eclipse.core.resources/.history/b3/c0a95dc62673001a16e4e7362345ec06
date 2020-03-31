package CodingTest;

import java.util.HashMap;
import java.util.Scanner;


public class BugsCardCollection {
	static int N;
	static String data;
	static int Mapsize;
	static boolean buyPossible;
	static boolean nonBuyPossible;
	static int Max;
	static int remainCount;
	static int Maxcount=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashMap<String,Integer> Map = new HashMap<String, Integer>();
		N = sc.nextInt();
		for(int i=0;i<N;i++) {
			data = sc.next();
			if(!Map.containsKey(data)){
				Map.put(data,1);
			}else if(Map.containsKey(data)){
				Map.put(data, (Map.get(data)+1));
			}
		}
		for(Integer data : Map.values()) if(Max<=data) Max = data;
		for(Integer data : Map.values()) if((Max-1)==data) remainCount++;
		for(Integer data : Map.values()) if(Max==data) Maxcount++;
		
		Mapsize = Map.size();
		if(remainCount==1) buyPossible = true;
		else if(Maxcount==Mapsize) nonBuyPossible =true;

		if(buyPossible) {
			System.out.println("Y");
			System.out.println(N+1);
			System.out.println(Mapsize);
		}else if(nonBuyPossible) {
			System.out.println("Y");
			System.out.println(N);
			System.out.println(Mapsize);
		}
		else {
			System.out.println("N");
			System.out.println(N);
			System.out.println(Mapsize);
		}
		
		
	}
}
