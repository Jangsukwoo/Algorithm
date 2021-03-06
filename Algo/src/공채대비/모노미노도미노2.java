package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 모노미노도미노2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0}; //우,하,좌,상
	static int[][] blocks;
	static int score;
	static int tile;
	static boolean[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		simulation();
		getTileCount();
		System.out.println(score);
		System.out.println(tile);
	}
	private static void getTileCount() {
		for(int row=0;row<10;row++) {
			for(int col=0;col<10;col++) {
				if(map[row][col]) tile++;
			}
		}
	}
	private static void simulation(){
		for(int turn=0;turn<N;turn++) {
			//먼저 블록 떨구기
			switch (blocks[turn][0]) {
			case 1:
				right1(blocks[turn][1],blocks[turn][2]);
				down1(blocks[turn][1],blocks[turn][2]);
				break;
			case 2:
				right2(blocks[turn][1],blocks[turn][2]);
				down2(blocks[turn][1],blocks[turn][2]);
				break;
			case 3: 
				right3(blocks[turn][1],blocks[turn][2]);
				down3(blocks[turn][1],blocks[turn][2]);
				break;
			}//블록 떨어뜨리기 끝
			//getScoreAndGravity
			getScoreAndGravity();
			//lineRemove
			lineRemove();
			System.out.println("턴"+(turn+1));
			view();
		}
	}	


	private static void view() {
		for(int row=0;row<10;row++) {
			for(int col=0;col<10;col++) {
				System.out.print(map[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void right1(int cr, int cc) {
		//1,우
		int nr = cr;
		int nc = cc;
		while(true) {
			if(!rangeCheck(nr,nc) || map[nr][nc]) break;
			nr+=dr[0];
			nc+=dc[0];
		}
		map[nr-dr[0]][nc-dc[0]] = true;
	}

	private static void down1(int cr, int cc) {
		//1,하
		int nr = cr;
		int nc = cc;
		while(true) {
			if(!rangeCheck(nr,nc) || map[nr][nc]) break;
			nr+=dr[1];
			nc+=dc[1];
		}
		map[nr-dr[1]][nc-dc[1]] = true;
	}
	private static void right2(int cr, int cc) {
		//2,우
		int nr = cr;
		int nc = cc+1;
		while(true) {
			if(!rangeCheck(nr,nc) || map[nr][nc]) break;
			nr+=dr[0];
			nc+=dc[0];
		}
		map[nr-dr[0]][nc-dc[0]] = true;
		map[nr-dr[0]][nc-(dc[0]*2)] = true;
	}
	private static void down2(int cr, int cc) {
		//2,하
		int nr = cr;
		int nc = cc;
		int nr2 = cr;
		int nc2 = cc+1;
		while(true) {
			if(!rangeCheck(nr,nc) || map[nr][nc] ||!rangeCheck(nr2, nc2) || map[nr2][nc2]) break;
			nr+=dr[1];
			nc+=dc[1];
			nr2+=dr[1];
			nc2+=dc[1];
		}
		map[nr-dr[1]][nc-dc[1]] = true;
		map[nr2-dr[1]][nc2-dc[1]] = true;
	}
	private static void right3(int cr, int cc) {
		//3,우
		int nr = cr;
		int nc = cc;
		int nr2 = cr+1;
		int nc2 = cc;
		while(true) {
			if(!rangeCheck(nr,nc) || map[nr][nc] ||!rangeCheck(nr2, nc2) || map[nr2][nc2]) break;
			nr+=dr[0];
			nc+=dc[0];
			nr2+=dr[0];
			nc2+=dc[0];
		}
		map[nr-dr[0]][nc-dc[0]] = true;
		map[nr2-dr[0]][nc2-dc[0]] = true;
	}
	private static void down3(int cr, int cc) {
		//3,하
		int nr = cr+1;
		int nc = cc;
		while(true) {
			if(!rangeCheck(nr,nc) || map[nr][nc]) break;
			nr+=dr[1];
			nc+=dc[1];
		}
		map[nr-dr[1]][nc-dc[1]] = true;
		map[nr-(dr[1]*2)][nc-dc[1]] = true;
	}


	private static void getScoreAndGravity() {
		//우 
		int colIdx=0;
		boolean flag = false;
		int removeCol=0;
		for(int col=4;col<=9;col++){
			int cnt=0;
			for(int row=0;row<=3;row++){
				if(map[row][col]) cnt++;
			}
			if(cnt==4){//4줄 완성
				removeCol++;
				score++;
				if(flag==false) {
					colIdx = col;
					flag = true;
				}
				for(int r=0;r<=3;r++) map[r][col] = false;//삭제
			}
		}

		if(removeCol==1){ //중력처리
			for(int row=0;row<=3;row++) {
				for(int c=colIdx;c>=4;c--){
					if(map[row][c-1]) {//c-1이 true라면
						map[row][c-1] = false;
						map[row][c] = true;
					}
				}
			}
		}else if(removeCol==2) {//두번 중력처리
			for(int row=0;row<=3;row++) {
				for(int c=colIdx+1;c>=4;c--){
					if(map[row][c-1]) {//c-1이 true라면
						map[row][c-1] = false;
						map[row][c] = true;
					}
				}
			}
			for(int row=0;row<=3;row++) {
				for(int c=colIdx;c>=4;c--){
					if(map[row][c-1]) {//c-1이 true라면
						map[row][c-1] = false;
						map[row][c] = true;
					}
				}
			}
		}




		int rowIdx=0;
		flag = false;
		int removeRow=0;
		for(int row=4;row<=9;row++){
			int cnt=0;
			for(int col=0;col<=3;col++){
				if(map[row][col]) cnt++;
			}
			if(cnt==4){//4줄 완성
				removeRow++;
				score++;
				if(flag==false) {
					rowIdx = row;
					flag = true;
				}
				for(int c=0;c<=3;c++) map[row][c] = false;//삭제
			}
		}

		if(removeRow==1){ //중력처리
			for(int col=0;col<=3;col++) {
				for(int r=rowIdx;r>=4;r--){
					if(map[r-1][col]) {//r-1이 true라면
						map[r-1][col] = false;
						map[r][col] = true;
					}
				}
			}
		}else if(removeRow==2) {//두번 중력처리
			for(int col=0;col<=3;col++) {
				for(int r=rowIdx+1;r>=4;r--){
					if(map[r-1][col]) {//r-1이 true라면
						map[r-1][col] = false;
						map[r][col] = true;
					}
				}
			}	
			for(int col=0;col<=3;col++) {
				for(int r=rowIdx;r>=4;r--){
					if(map[r-1][col]) {//r-1이 true라면
						map[r-1][col] = false;
						map[r][col] = true;
					}
				}
			}
		}
	}
	private static void lineRemove(){
		int cnt=0;
		for(int col=4;col<=5;col++) {
			for(int row=0;row<4;row++) {
				if(map[row][col]) {
					cnt++;
					break;
				}
			}
			if(cnt==1){
				for(int r=0;r<4;r++) map[r][9] = false;//한줄 삭제
				//당기기
				for(int r=0;r<4;r++) {
					for(int c=9;c>=4;c--) {
						if(map[r][c-1]) {
							map[r][c-1] = false;
							map[r][c] = true;
						}
					}
				}
			}
			if(cnt==2){
				for(int r=0;r<4;r++) map[r][9] = false;//한줄 삭제
				//당기기
				for(int r=0;r<4;r++) {
					for(int c=9;c>=4;c--) {
						if(map[r][c-1]) {
							map[r][c-1] = false;
							map[r][c] = true;
						}
					}
				}
			}
		}
		
		cnt=0;
		for(int row=4;row<=5;row++) {
			for(int col=0;col<4;col++) {
				if(map[row][col]) {
					cnt++;
					break;
				}
			}
			if(cnt==1){
				for(int c=0;c<4;c++) map[9][c] = false;//한줄 삭제
				//당기기
				for(int c=0;c<4;c++) {
					for(int r=9;r>=4;r--) {
						if(map[r-1][c]) {
							map[r-1][c] = false;
							map[r][c] = true;
						}
					}
				}
			}
			if(cnt==2){
				for(int c=0;c<4;c++) map[9][c] = false;//한줄 삭제
				//당기기
				for(int c=0;c<4;c++) {
					for(int r=9;r>=4;r--) {
						if(map[r-1][c]) {
							map[r-1][c] = false;
							map[r][c] = true;
						}
					}
				}
			}
		}
	}
	



	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<10 && nc>=0 && nc<10) return true;
		return false;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		blocks = new int[N][3];
		map = new boolean[10][10];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			blocks[i][0] = Integer.parseInt(st.nextToken());
			blocks[i][1] = Integer.parseInt(st.nextToken());
			blocks[i][2] = Integer.parseInt(st.nextToken());
		}
	}
}
