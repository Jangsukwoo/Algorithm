package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * N개의 줄에 타일이 놓여있고
 * 각 조각은 정사각형이며 
 * 1~6숫자가 쓰여있음
 * 홀수 줄에는 N개의 타일
 * 짝수 줄에는 N-1개의 타일
 * 
 * 출력
 * 첫째줄에 가장 짧은 경로의 길이(타일의개수)
 * 둘쨰줄에 경로 상의 타일의 번호를 공백으로 구분해서 출력
 * 짧은 경로가 여러가지면 아무거나 출력 
 * 
 * 각 타일은 넘버링해야함
 * 
 * 각 타일별로 직전 타일의 번호를 가지고 있다가
 * 마지막 타일부터 타고 올라가면서 경로 저장후 출력 .
 * 
 * 런타임 에러가 났었는데
 * 만약 마지막 도착지점까지 갈 수 없는경우
 * 도달한 최대 타일번호까지의 경로와 사이즈를 출력애햐야하는 조건이 있었다.
 * 
 */
public class 과외맨 {
	static int N;
	static int[][] tileMap;
	static int[][] tileNumberMap;
	static boolean[] tileVisit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//우하좌
	static Tile[] tiles;
	static int lastTileNumber;
	static Queue<Integer> q;
	static int end;
	static class Tile{
		int leftRow;
		int leftCol;
		int rightRow;
		int rightCol;
		int tileNumber;
		int beforTileNumber;
		public Tile(int leftRow, int leftCol, int rightRow, int rightCol, int tileNumber) {
			this.leftRow = leftRow;
			this.leftCol = leftCol;
			this.rightRow = rightRow;
			this.rightCol = rightCol;
			this.tileNumber = tileNumber;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		bfs();
		output();
	}

	private static void output() {
		ArrayList<Integer> answerPath = new ArrayList<Integer>();
		int arrivePathNumber = getArriveTileNumber();
		int pathNumber=arrivePathNumber;
		while(pathNumber!=0){
			answerPath.add(pathNumber);
			pathNumber = tiles[pathNumber].beforTileNumber;
		}
		System.out.println(answerPath.size());
		for(int i=answerPath.size()-1;i>=0;i--) System.out.print(answerPath.get(i)+" ");
	}


	private static int getArriveTileNumber() {
		int last=0;
		if(tiles[lastTileNumber].beforTileNumber!=0) {//마지막까지 도달한 경우에는 그 경로를 출력
			return lastTileNumber;
		}else {//마지막 타일로 못가는 경우는 도달한 최대 타일의 번호까지의 답을 출력한다.
			for(int i = lastTileNumber;i>=1;i--){
				if(tiles[i].beforTileNumber!=0) {
					last= i;
					break;
				}
			}
		}
		return last;
	}

	private static void bfs() {
		insertQueue(1);
		tiles[1].beforTileNumber=0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int currentTileNumber = q.poll();
				adjCheck(tiles[currentTileNumber].leftRow,tiles[currentTileNumber].leftCol);
				adjCheck(tiles[currentTileNumber].rightRow,tiles[currentTileNumber].rightCol);
				//타일의 왼쪽,오른쪽 좌표 다 넣어서 검사해봄
			}
		}
	}
	private static void adjCheck(int cr, int cc) {//좌표 던져주면 인접한 숫자 조사해서 큐에 넣어준다.
		for(int dir=0;dir<4;dir++){
			int nr = cr+dr[dir];
			int nc = cc+dc[dir];
			if(rangeCheck(nr,nc)) {//영역 만족하면
				if(tileMap[nr][nc]==tileMap[cr][cc] && tileVisit[tileNumberMap[nr][nc]]==false){//검사 안해본 타일만
					insertQueue(tileNumberMap[nr][nc]);
					tiles[tileNumberMap[nr][nc]].beforTileNumber=tileNumberMap[cr][cc];//직전 타일번호 넣어주기
				}
			}
		}
	}
	private static void insertQueue(int tileNum) {
		q.add(tileNum);
		tileVisit[tileNum] = true;
	}

	private static boolean rangeCheck(int adjRow, int adjCol) {
		if(adjRow>=1 && adjRow<=N && adjCol>=1 && adjCol<=(2*N)) return true;
		return false;
	}

	private static void setData() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tileMap = new int[N+1][N*2+1];//1부터 시작할거임
		tileNumberMap = new int[N+1][N*2+1];
		end = N*2;
		int tileNumber=1;
		int tileCnt = (N*N)-(N/2);
		lastTileNumber = tileCnt;
		tiles = new Tile[tileCnt+1];
		tileVisit= new boolean[tileCnt+1];
		q = new LinkedList<Integer>();
		int lineIdx=1;
		int tileIdx=1;
		for(int i=0,A,B;i<tileCnt;i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			tileNumberMap[lineIdx][tileIdx] = tileNumber;
			tileMap[lineIdx][tileIdx++]=A;
			tileNumberMap[lineIdx][tileIdx] = tileNumber;
			tiles[tileNumber] = new Tile(lineIdx, tileIdx-1, lineIdx, tileIdx, tileNumber);
			tileMap[lineIdx][tileIdx++]=B;
			if(lineIdx%2==1 && tileIdx==(end+1)){//홀수 라인이면서 tileIdx==N이면 다음라인으로
				tileIdx=2;//짝수라인은 2부터 시작
				lineIdx++;//짝수라인으로 
			}else if(lineIdx%2==0 && tileIdx==end){
				//짝수라인이면서 tileIdx==N-1이면 홀수라인으로
				tileIdx=1;//홀수라인은 1부터 시작
				lineIdx++;//홀수라인으로
			}
			tileNumber++;
		}
	}
}
