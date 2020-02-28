package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 두뤠권컬브~~
 * 드래곤 커브의 속성
 * 시작점, 시작 방향, 세대
 * 0세대 드래곤 커브는 길이가 1인 선분
 * 0,0에서 시작하고 시작 방향은 오른쪽
 * 
 * 1세대 드래곤 커브는 0세대 드래곤 커브의 끝점 기준 시계방향 90도 회전 후
 * 0세대 드래곤 커브의 끝점에 붙인 것 
 * 
 * 이와 같이
 * K세대 드래곤 커브는
 * K-1세대 드래곤 커브를 끝 점 기준으로 시계방향 회전 후 끝에 붙인것 
 * 크기가 100by100인 격자 위에
 * 드래곤 커브가 N개 있는데
 * 이때 크기가 1x1인 정사각형의 네 꼭지점이 모든 드래곤 커브의
 * 일부인 것의 개수
 * 
 * 드래곤 커브의 정보를 받아 맵에 표시 후 갯수 카운팅
 *
 * 드래곤 커브 방향 정보를 미리 다 만들어 둔 다음
 * 각각의 시작위치에서 그려주는 방법을 쓰면 되겠다
 */
public class 드래곤커브 {
	
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] dragonCurveMap = new int[101][101];
	static int[] dragonCurveDirection;
	static int[] dr = {0,-1,0,1};
	static int[] dc = {1,0,-1,0};//우,상,좌,하 반시계방향 
	static int square;
	public static void main(String[] args) throws IOException {
		setData();
		countSquare();
		System.out.println(square);
	}
	private static void countSquare() {
		for(int row=0;row<100;row++) {
			for(int col=0;col<100;col++) {
				if(squareCheck(row,col)) square++;
			}
		}
	}
	private static boolean squareCheck(int row, int col) {
		if(dragonCurveMap[row][col]==1 
			&& dragonCurveMap[row][col+1]==1
			&& dragonCurveMap[row+1][col]==1
			&& dragonCurveMap[row+1][col+1]==1) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		dragonCurveDirection = new int[1024];
		for(int i=1,x,y,d,g;i<=N;i++) {//드래곤 커브의 개수, 방향 규칙에 따라서 입력을 받는 순간 맵에 다 그려버림
			st = new StringTokenizer(br.readLine());
			int curveSize=0;
			//드래곤 커브 시작점 x,y
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			dragonCurveMap[y][x] = 1;
			//드래곤 커브 시작방향
			d = Integer.parseInt(st.nextToken());
			//드래곤 커브 세대 수
			g = Integer.parseInt(st.nextToken());
			dragonCurveDirection[curveSize++] = d;//처음 시작 방향 넣어주고 
			for(int j=0;j<g;j++){//세대수만큼
				for(int k=curveSize-1;k>=0;k--) {
					dragonCurveDirection[curveSize++] = (dragonCurveDirection[k]+1)%4;
				}
			}
			
			//그냥 바로 그리면 될거같다.
			for(int j=0;j<curveSize;j++) {
				y=y+dr[dragonCurveDirection[j]];
				x=x+dc[dragonCurveDirection[j]];
				dragonCurveMap[y][x] = 1;
			}
		}

	}
}
