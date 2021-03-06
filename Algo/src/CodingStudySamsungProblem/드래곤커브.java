package CodingStudySamsungProblem;

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
 * 
 * ->codingStudyHW 패키지에서 품
 */
public class 드래곤커브 {
	
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] dragonCurveMap = new int[100][100];
	static int[] dr = {0,-1,0,1};
	static int[] dc = {1,0,-1,-1};//우,상,좌,하
	public static void main(String[] args) throws IOException {
		setData();
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		for(int i=0,x,y,d,g;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			//방향의 역순?
			
		}
	}
}
