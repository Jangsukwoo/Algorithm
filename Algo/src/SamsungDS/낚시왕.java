package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 낚시왕 {
	static int R,C,M;
	static BufferedReader br;
	static StringTokenizer st;
	static int[] dr = {0,-1,1,0,0};
	static int[] dc = {0,0,0,1,-1};
	static Shark[] sharks; //상어 정보
	static ArrayList<Shark>[][] sea; 
	static int getAllSharkSize;
	static class Shark implements Comparable<Shark>{
		int row;
		int col;
		int speed;
		int dir;
		int size;
		int sharkId;
		boolean life;
		public Shark(int row, int col, int speed, int dir, int size, boolean life, int sharkId) {
			this.row = row;
			this.col = col;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
			this.life = life;
			this.sharkId = sharkId;
		}
		/*
		 * 처음 알아낸 것.. 이거때문에 시간 많이씀 
		 * 
		 * Integet.compare에 넣는 파라미터 값의 순서도 내림,오름 차순 순서에 영향이 있다...
		 */
		@Override
		public int compareTo(Shark o) {
			return -Integer.compare(this.size,o.size);
			// return -Integer.compare(o.size,this.size); 라고 쓰면 내림차순이 아닌 오름차순이다..
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		simulation();
		System.out.println(getAllSharkSize);
	}
	private static void simulation() {
		for(int shooter=1;shooter<=C;shooter++){
			for(int row=1;row<=R;row++) {
				if(sea[row][shooter].size()!=0){//상어가 있다면
					int findSharkId = sea[row][shooter].get(0).sharkId;
					sharks[findSharkId].life = false;
					sea[sharks[findSharkId].row][sharks[findSharkId].col].clear();
					getAllSharkSize+=sharks[findSharkId].size;
					break;
				} //포획처리, 어차피 그 칸에는 한마리밖에 없었을 테니 clear
			}
			moveAllShark();//상어 이동
			getBigShark(); //가장 큰 상어 생존 시키기 (상어 사이즈별로 정렬 후 첫번째 인덱스 이후 상어 전부 제거)
		}
	}
	//가장 사이즈 큰 상어 살아남기
	private static void getBigShark() { 
		for(int row=1;row<=R;row++) {
			for(int col=1;col<=C;col++){
				if(sea[row][col].size()>1){
					Collections.sort(sea[row][col]);//정렬 해준 뒤
					for(int i=(sea[row][col].size()-1);i>0;i--){
						sharks[sea[row][col].get(i).sharkId].life=false;
						sea[row][col].remove(i);
					}
				}
			}
		}
	}//상어 이동
	private static void moveAllShark() {
		for(int id=1;id<=M;id++){//모든 상어를 조회해본다.
			if(sharks[id].life){//살아있는 상어인가?
				Shark currentShark = sharks[id];
				//이동 후 최종 도착 좌표
				int dir = currentShark.dir;
				int nr = currentShark.row;
				int nc = currentShark.col;
				int time = 0;
				while(time<currentShark.speed){
					nr += dr[dir]; 
					nc += dc[dir];
					if(!wallCheck(nr,nc)){//벽에 튕겨가야하면
						nr-=dr[dir];
						nc-=dc[dir]; //갔던거 뒤로 후진 하고
						
						//역방향으로 바꾼 뒤
						if(dir%2==0) dir-=1;
						else dir+=1; 
						
						//다시 이동
						nr += dr[dir]; 
						nc += dc[dir];
					}
					time++;
				}
				//이동 끝났는데 이동중에 한 칸에 여러 상어가 있는 경우를 고려해서 해당 칸에서 정확히 이동한 상어만 삭제처리하기
				//현재 칸에서 지워주기
				for(int i=0;i<sea[currentShark.row][currentShark.col].size();i++) {
					if(currentShark.sharkId==sea[currentShark.row][currentShark.col].get(i).sharkId){
						sea[currentShark.row][currentShark.col].remove(i);
						break;
					}
				}
				currentShark.row = nr;
				currentShark.col = nc;
				currentShark.dir = dir;
				sea[nr][nc].add(currentShark);
			}
		}
	}
	private static boolean wallCheck(int nr, int nc) {
		if(nr>=1 && nr<=R && nc>=1 && nc<=C) return true;
		return false;
	}
	private static void setData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sea= new ArrayList[R+1][C+1];
		for(int row=1;row<=R;row++)
			for(int col=1;col<=C;col++) sea[row][col] = new ArrayList<Shark>();
		sharks = new Shark[M+1];
		for(int id=1;id<=M;id++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			sharks[id] = new Shark(row, col, speed, dir, size, true,id);
			sea[row][col].add(sharks[id]);
		}
	}
}
