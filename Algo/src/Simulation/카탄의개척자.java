package Simulation;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 그림을 배열로 표현하는 부분이 까다롭다..
 * 연습장에 엄청 그려보고 구현 방법 생각해냄
 * 6개의 화살표
 * 
 * 육각형 모양의 맵을 2차원 배열로 표시하고
 * 다음 좌표를 정해주는 이동로직을 생각하고 구현하는데 많이 오래 걸렸다.
 */
class Tile implements Comparable<Tile>{
	int id;
	int count;
	public Tile(int i, int c) {
		id = i;
		count = c;
	}
	@Override
	public int compareTo(Tile o) {
		if(this.count==o.count) return Integer.compare(this.id,o.id);
		return Integer.compare(this.count,o.count);
	}

}
public class 카탄의개척자 {
	static int[] dr = {0,-1,-1,0,1,1};
	static int[] dc = {1,1,0,-1,-1,0};//우->우상대각->상->좌->....->하 반시계방향
	static int[][] catanMap;
	static boolean[] adjCheck;
	static int[][] tileNumber;
	static int centerRow,centerCol;
	static PriorityQueue<Tile> pq;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		catanMap = new int[200][200];
		tileNumber = new int[10001][2];
		pq = new PriorityQueue<Tile>();
		centerRow = 100;
		centerCol = 100;
		
		//1~5 pq에 세팅
		makePQ();
		
		//카탄맵 세팅
		makeCatanMap();

		int T = sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++){
			int n = sc.nextInt();
			sb.append(catanMap[tileNumber[n][0]][tileNumber[n][1]]+"\n");
		}
		System.out.println(sb.toString());
	
	}
	private static void makePQ() {
		for(int i=1;i<=5;i++) {
			if(i==1) pq.add(new Tile(i,1));
			else pq.add(new Tile(i,0));
		}
	}
	private static void makeCatanMap() {
		int tile =1;//타일의 개수
		int dir=0;//처음 우 방향 시작
		//중앙 시작
		int nr = centerRow;
		int nc = centerCol;
		catanMap[centerRow][centerCol]=1;
		tileNumber[1][0] = centerRow;
		tileNumber[1][1]= centerCol;//최초 타일 1번의 좌표
		
		while(tile<10000){
			tile++;
			nr = nr+dr[dir];
			nc = nc+dc[dir];//다음 좌표
		
			//다음 칸에 대해 방향값을 실제로 더하진 않고
			//+1 해본뒤 칸을 살핀다.
			//칸을 살폈을 때 
			//0이 아니라면 숫자가 있는 것이므로
			//기존방향대로 직진한다.
			//칸을 살폈는데 0이라면 방향 회전
			while(true){//다음방향 찾아 회전
				if(catanMap[nr+dr[(dir+1)%6]][nc+dc[(dir+1)%6]]!=0) break; 
				dir+=1;
				dir%=6;
			}//-> 나중에 또 활용이 가능할 것같은 회전 로직이므로 잘 외워두자
			
			//새로운 칸이 될 nr,nc의 인접 숫자를 체크
			adjCheck= new boolean[6];
			for(int d=0;d<6;d++){
				int nnr = nr+dr[d]; 
				int nnc = nc+dc[d];
				if(catanMap[nnr][nnc]!=0) adjCheck[catanMap[nnr][nnc]]=true; //0이 아니면 인접한 숫자가 존재하는 것임. true
			}
			
			//조건을 만족하는 후보군 꺼내보기
			ArrayList<Tile> tmp = new ArrayList<Tile>();
			for(int i=0;i<5;i++){//숫자는 총 5개니까 5번만 반복해본다.
				Tile curTile = pq.poll();
				boolean possible = true;
				for(int j=1;j<=5;j++){ //
					if(adjCheck[j]){//인접(true)한 숫자면서 
						if(j==curTile.id){//현재 후보 번호와 같으면 
							possible = false;//불가능
							tmp.add(curTile);//pq에 다시 넣기위해 임시 제외리스트에 넣음
							break;
						}
					}
				}
				if(possible){//가능한 경우
					curTile.count+=1;//1개 누적
					catanMap[nr][nc]=curTile.id; //칸에 적고		
					tileNumber[tile][0] = nr;
					tileNumber[tile][1] = nc;
					pq.add(curTile);//pq에 다시 넣는다.
					for(Tile t : tmp) pq.add(t);//제외리스트에 있는 후보군들 다시 pq에 삽입
					break;
				}
			}
		}
	}
}

