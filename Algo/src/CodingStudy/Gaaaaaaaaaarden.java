package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 19:39 시작
 * 
 * 2차원 맵이 있고 배양액을 뿌림
 * 배양액을 뿌릴 수 있는 땅은 정해져있다.
 * 
 * 흰땅은 배양액을 뿌릴 수 없는 땅
 * 황토색은 배양액을 뿌릴 수 있는 땅
 * 하늘색칸은 호수
 * 
 * 빨강배양액과 초록 배양액이 동시에 도달한 땅에는 꽃이 피어남
 * 
 * 꽃이 핀 땅은 더이상 배양액이 접근할수 없다
 * 
 * 피울 수 있는 꽃의 최대 개수
 * 
 * 0은 호수 , 1은 배양액을 뿌릴 수 없는 땅, 2는 배양액을 뿌릴 수 있는 땅
 * 
 * 배양액은 남김없이 사용해야함.
 * 
 * 21:30 끝
 * 
 * 1시간 50분 걸림..
 */
public class Gaaaaaaaaaarden {
	static int N,M,G,R;
	static int[][] garden;
	static ArrayList<CultureMediumPosition> cultureMediumlist;
	static ArrayList<Character> colorList;
	static CultureMedium[][] cultureMediumMap; 
	static Set<String> colorSet;
	static boolean[] visit;
	static int[] pickCultureMediumList;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int maxFlower;
	static Queue<CultureMedium> q;
	static char[][] viewMap;
	
	static class CultureMediumPosition{
		int row;
		int col;
		public CultureMediumPosition(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	static class CultureMedium{
		int row;
		int col;
		int time;
		char color;
		public CultureMedium(int row, int col, int time, char color) {
			this.row = row;
			this.col = col;
			this.time = time;
			this.color = color;
		}

	}
	public static void main(String[] args) throws IOException {
		setData();
		pickColor(0,"");
		pickCultureMedium(0,0); //pick using dfs
		System.out.println(maxFlower);
	}
	private static void pickColor(int depth,String color) {
		if(depth==(R+G)){
			colorSet.add(color);
			return;
		}
		for(int i=0;i<(R+G);i++){
			if(visit[i]==false){
				visit[i] = true;
				pickColor(depth+1, color+colorList.get(i));
				visit[i] = false;
			}
		}
	}
	private static void pickCultureMedium(int idx, int depth) {
		if(depth==(R+G)){//땅의 위치는 다 골랐음
			insertCultureMedium();
			return ;
		}
		for(int i=idx;i<cultureMediumlist.size();i++){
			pickCultureMediumList[depth] = i;
			pickCultureMedium(i+1,depth+1);
		}
	}
	private static void insertCultureMedium(){//배양액 주입
		for (String colorOrder : colorSet){//현재 선택된 땅에 대해서 배양액을 색깔별로 다 넣어본다.
			cultureMediumMap = new CultureMedium[N][M];
			q.clear();
			viewMap = new char[N][M];
			for(int i=0;i<colorOrder.length();i++){
				char pickColor = colorOrder.charAt(i);
				int pickGroundIdx = pickCultureMediumList[i];
				int pickRow = cultureMediumlist.get(pickGroundIdx).row;
				int pickCol = cultureMediumlist.get(pickGroundIdx).col;
				viewMap[pickRow][pickCol] = pickColor;
				cultureMediumMap[pickRow][pickCol] = new CultureMedium(pickRow,pickCol,0, pickColor);
				q.add(new CultureMedium(pickRow,pickCol,0, pickColor));
			}//배양액 배치 끝
			spread();//배양액 퍼뜨리기 
		}
	}
	private static void spread(){//bfs
		int time=0;
		int flower=0;
		while(!q.isEmpty()){
			int size = q.size();
			time++;
			for(int i=0;i<size;i++){
				CultureMedium currentCultureMedium = q.poll();
				int cr = currentCultureMedium.row;
				int cc = currentCultureMedium.col;
				char currentColor = currentCultureMedium.color;
				if(cultureMediumMap[cr][cc].color=='F') continue;//꽃으로 바꼈으면 그냥 진행
				for(int dir=0;dir<4;dir++){
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc) && garden[nr][nc]!=0) {//갈수 있는 영역이고 호수가 아닌 경우 
						if(cultureMediumMap[nr][nc]==null){//최초 도착이면 만들어준다.
							cultureMediumMap[nr][nc]= new CultureMedium(nr,nc,time,currentColor);
							viewMap[nr][nc] = currentColor;
							q.add(new CultureMedium(nr,nc,time,currentColor));
						}else{//null이 아니면 뭔가 있다는 건데
							if(cultureMediumMap[nr][nc].color!='F' //꽃이 아니고 
							&& cultureMediumMap[nr][nc].color!=currentColor //현재 색깔과 다른 색이며
							&& cultureMediumMap[nr][nc].time==time){//지금 가려는 시간과 똑같으면
								cultureMediumMap[nr][nc].color='F';//꽃으로 바꾼다
								flower++;
								viewMap[nr][nc] = 'F';
							}
						}
					}
				}
			}
		}
		maxFlower = Math.max(flower,maxFlower);
	}

	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<M) return true;
		return false;
	}
	private static void setData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//행
		M = Integer.parseInt(st.nextToken());//열
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		pickCultureMediumList = new int[G+R];
		garden = new int[N][M];
		cultureMediumlist = new ArrayList<CultureMediumPosition>();
		colorList = new ArrayList<Character>();
		colorSet = new HashSet<String>();
		visit = new boolean[R+G];
		q = new LinkedList<CultureMedium>();
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<M;col++) {
				garden[row][col] = Integer.parseInt(st.nextToken());
				if(garden[row][col]==2) cultureMediumlist.add(new CultureMediumPosition(row, col));
			}
		}
		for(int i=0;i<G;i++) colorList.add('G');
		for(int i=0;i<R;i++) colorList.add('R');
	}
}
