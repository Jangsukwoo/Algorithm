package SDS_WEEK2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 도시의 중심는 N개의 동서방향 도로, N개의 남북방향 도로
 * 2차원 맵으로 주어지고 각 격자간의 거리는 1
 * 이 격자맵 위에는 두 대의 경찰차(경찰차 1, 경찰차2)이 있다.
 * 경찰차 1의 초기 위치는 항상 1,1
 * 경찰차 2의 초기위치는 항상 N,N
 * 
 * 처리할 사건이 있을 때
 * 그 사건이 발생된 위치를 두대의 경찰차중 한대에 알려주고
 * 연락받은 경찰차는 가장 빠른길로 이동해 사건을 처리함.
 * 
 * 경찰 본부에서는 사건이 발생한 순서대로 두 대의 경찰차에 맡김
 * 이 사건들을 나누어 두 대의 경찰차에 맡기되 두 대의 경찰차들이 이동하는 거리의 합을 최소화 하도록하기
 * 
 * dp에 대한 표현을 어떻게 할까?
 * 
 * ->dp[첫번쨰 경찰차 위치][두번째 경찰차 위치]
 */
public class 동적계획법_5일차_경찰차 {
	static final int INF = 987654321;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] dp;
	static int[] pickPolice;
	static Accident[] accidents;
	static int N;
	static int W;
	static class Accident{
		int row;
		int col;
		public Accident(int r,int c) {
			row = r;
			col = c;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		setDP();
		setPoliceNumber();
	}
	private static void setPoliceNumber() {
		path(0,1,0);
	}
	private static void path(int firstCar, int secondCar, int depth) {
		if(firstCar==W+1 || secondCar==W+1) {
			for(int i=0;i<W;i++) {
				System.out.println(pickPolice[i]);
			}
			return;
		}
		int nextAccidentNumber = Math.max(firstCar,secondCar)+1;//다음 사건 번호 
		int firstCarDist = dp[nextAccidentNumber][secondCar] + dist(accidents[firstCar],accidents[nextAccidentNumber]);
		int secondCarDist = dp[firstCar][nextAccidentNumber] + dist(accidents[nextAccidentNumber],accidents[secondCar]);
		if(firstCarDist<secondCarDist){//첫번째 경찰차가 더 짧게 간다.
			pickPolice[depth] = 1;
			path(nextAccidentNumber,secondCar,depth+1);
		}
		else {
			pickPolice[depth] = 2;
			path(firstCar,nextAccidentNumber,depth+1);
		}
		
	}
	private static void setDP() {
		int ans = memoization(0,1);
		System.out.println(ans);
	}
	private static int memoization(int firstCar, int secondCar) {
		if(firstCar==W+1 || secondCar ==W+1){//두 경찰차중 하나라도 마지막 사건에 도착 했다면
			return 0;
		}
		int ret = dp[firstCar][secondCar];
		if(ret!=0) return ret;//dp값이 이미 존재하는 경우에는 return dp값. 즉 이미 계산한 값이면
		int nextAccidentNumber = Math.max(firstCar,secondCar)+1;//다음 사건 번호 
		int firstCarDist = memoization(nextAccidentNumber,secondCar) + dist(accidents[firstCar],accidents[nextAccidentNumber]);
		int secondCarDist = memoization(firstCar,nextAccidentNumber) + dist(accidents[nextAccidentNumber],accidents[secondCar]);
		ret = Math.min(firstCarDist,secondCarDist);//더 작은 사건값 
		return dp[firstCar][secondCar]=ret;
	}
	private static int dist(Accident ac1, Accident ac2) {
		return Math.abs(ac1.row-ac2.row)+Math.abs(ac1.col-ac2.col);
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		W = Integer.parseInt(br.readLine());
		dp = new int[W+2][W+2];		
		pickPolice = new int[W];
		accidents = new Accident[W+2];//사건개수+경찰차 위치
		accidents[0] = new Accident(1,1);
		accidents[1] = new Accident(N,N);
		for(int i=2,row,col;i<(W+2);i++) {
			st = new StringTokenizer(br.readLine());
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			accidents[i] = new Accident(row,col);
		}
		
	}
}