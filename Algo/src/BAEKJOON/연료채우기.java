package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 14:25~
4
4 1
5 1
11 3
15 10
25 10

2
2 3
4 7
14 4
 */
public class 연료채우기 {
	static int N;//주유소 개수
	static boolean[] gasPostion = new boolean[1000001];
	static GasStation[] gasStations;
	static boolean flag;
	static class GasStation{
		int dist;//dist
		int gas;//fuel
		public GasStation(int dist, int gas) {
			this.dist = dist;
			this.gas = gas;
		}
	}
	static int L,P;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static PriorityQueue<GasStation> gasStationlist;
	static int stop;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		move();
		if(flag) System.out.println(stop);
		else System.out.println("-1");
	}
	private static void move() {
		int dist=1;
		int gasStationIdx=0;
		while(true){
			if(dist==L) {
				flag=true;
				return;
			}
			if(gasPostion[dist]) gasStationlist.add(gasStations[gasStationIdx++]);
			P--;
			dist++;
			if(P==0 && !gasStationlist.isEmpty()){
				stop++;
				P += gasStationlist.poll().gas;
			}else if(P==0 && gasStationlist.isEmpty()){
				flag=false;
				return;
			}
		}
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		gasStations = new GasStation[N];
		for(int i=0,dist,gas;i<N;i++){
			st = new StringTokenizer(br.readLine());
			dist = Integer.parseInt(st.nextToken());
			gas = Integer.parseInt(st.nextToken());
			gasStations[i] = new GasStation(dist, gas);
		}
		Arrays.sort(gasStations, new Comparator<GasStation>() {
			@Override
			public int compare(GasStation o1, GasStation o2) {
				return Integer.compare(o1.dist,o2.dist);
			}
		});
		for(int i=0;i<N;i++) gasPostion[gasStations[i].dist] = true;
		gasStationlist = new PriorityQueue<GasStation>(new Comparator<GasStation>() {
			@Override
			public int compare(GasStation o1, GasStation o2) {
				return -Integer.compare(o1.gas,o2.gas);
			}
		});
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		flag = true;
	}
}
