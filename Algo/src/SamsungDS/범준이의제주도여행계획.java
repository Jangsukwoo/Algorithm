package SamsungDS;

/*
 * 14:25~
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 범준이의제주도여행계획 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M;//지점수, 휴가기간
	static int[][] adjMatrix;
	static boolean[] visit;
	static Location[] locations;
	static int maxSatisfaction;
	static String maxSatisfactionPath;
	static class Location{
		char LocationType;
		int useTime;
		int point;
		public Location(char locationType, int useTime, int point) {
			LocationType = locationType;
			this.useTime = useTime;
			this.point = point;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			getMaxSatisfactionPath();
			if(maxSatisfaction!=0) System.out.println("#"+testcase+" "+maxSatisfaction+" "+maxSatisfactionPath);
			else System.out.println(("#"+testcase+" "+maxSatisfaction));
		}
	}
	private static void getMaxSatisfactionPath() {
		/*
		 * 가장 만족도가 높은 여행 경로 구하기
		 * 1.M일 후 다시 공항으로 돌아와야함 (M-1)박 M일
		 * 2.한번 갔던 관광포인트는 다시 가지 않음
		 * 3.하루에 이동 + 놀이 시간 9시간 넘으면 안됌 (540분)
		 * 4.M일 째 되는날도9 시간 사용 가능, 그전에 공항 도착해야함
		 * 5.같은 호텔 사용가능
		 * 6.조건을 만족하는 경로가 없으면 0으로 출력하고 경로는 ㄴㄴ
		 * 7.하루 540분을 써서 반드시 호텔에 투숙해야함
		 * 
		 * 많이 보는게 중요한게 아니고 최대의 만족을 얻는게 목표임
		 */
		dfs(1,1,0,0,"",false);//그냥 다 가보자
	}
	private static void dfs(int currentLocation,int day,int spendTime, int satisfaction, String path, boolean arrival) {
		if(arrival) {
			if(satisfaction>maxSatisfaction) {
				maxSatisfaction = satisfaction;
				maxSatisfactionPath = path;
			}
			return;
		}
		if(day==M) {
			
			for(int next=1;next<=N;next++) {
				if(adjMatrix[currentLocation][next]!=0){
					//연결 된 곳을 가려고 함
					int moveTime = adjMatrix[currentLocation][next];
					if(locations[next].LocationType=='P' && locations[next].point!=0){
						//관광지면 
						int newSpendTime = moveTime+spendTime+locations[next].useTime;
						if(newSpendTime<540){//아직 놀 여유가 있다면
							int point = locations[next].point;
							locations[next].point = 0;
							dfs(next,day,newSpendTime,satisfaction+point,path+Integer.toString(next)+" ",false);
							locations[next].point = point;
						}
					}
					if(locations[next].LocationType=='A' && (moveTime+spendTime)<=540){
						dfs(next,day+1,0,satisfaction,path+Integer.toString(next),true);
					}
				}
			}
			return;
		}
		//연결된 지점
		else {
			for(int next=1;next<=N;next++) {
				if(adjMatrix[currentLocation][next]!=0){
					//연결 된 곳을 가려고 함
					int moveTime = adjMatrix[currentLocation][next];
					if(locations[next].LocationType=='P' && locations[next].point!=0){
						//관광지면 
						int newSpendTime = moveTime+spendTime+locations[next].useTime;
						if(newSpendTime<540){//아직 놀 여유가 있다면
							int point = locations[next].point;
							locations[next].point = 0;
							dfs(next,day,newSpendTime,satisfaction+point,path+Integer.toString(next)+" ",false);
							locations[next].point = point;
						}
					}
					if(locations[next].LocationType=='H' && (moveTime+spendTime)<=540 && spendTime>=240){
						dfs(next,day+1,0,satisfaction,path+Integer.toString(next)+" ",false);
					}
				}
			}
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //3<=N<=35
		M= Integer.parseInt(st.nextToken());//1<=M<=5
		adjMatrix = new int[N+1][N+1];
		locations = new Location[N+1];
		visit = new boolean[N+1];
		maxSatisfaction = 0;
		for(int i=1;i<=(N-1);i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=i+1;j<=N;j++) {
				int moveCost = Integer.parseInt(st.nextToken());
				adjMatrix[i][j] = adjMatrix[j][i] = moveCost;
			}
		}
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			char locationType = st.nextToken().charAt(0);
			if(locationType=='A' || locationType=='H') locations[i] = new Location(locationType, 0, 0);
			else {
				int useTime = Integer.parseInt(st.nextToken());
				int point = Integer.parseInt(st.nextToken());
				locations[i] = new Location(locationType,useTime,point);
			}
		}
	}
}
