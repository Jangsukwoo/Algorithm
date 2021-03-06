package SamsungDS;

import java.util.Scanner;

public class 테스트어른상어 {
	static int map[][];//상어의 이동경로
	static boolean visit[][];
	static int smell[][];
	static int dir[][][];
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	static class Shark{
		int r,c,d;
		public Shark(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	static int n,m,k;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		k=sc.nextInt();
		int sharknum=m;
		Shark s[] = new Shark[m+1];
		map = new int[n][n];
		visit = new boolean[n][n];
		smell = new int[n][n];
		dir = new int[m+1][4][4];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j]!=0) {
					smell[i][j]=k;
					visit[i][j] = true;
					s[map[i][j]] = new Shark(i,j,0);
				}
			}
		}
		for (int i = 1; i <= m; i++) {
			s[i].d=sc.nextInt()-1;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 0; j < 4; j++) {
				for (int l = 0; l < 4; l++) {
					dir[i][j][l] = sc.nextInt()-1;
				}
			}
		}
		int cnt=0;
		while(true) {
			int temp_map[][] = new int[n][n];
			int temp_smell[][] = new int[n][n];
			boolean temp_visit[][] = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					temp_map[i][j] = map[i][j];
					temp_smell[i][j] = smell[i][j];
				}
			}
			for (int i = 1; i <= m; i++) {
				//샤크가 살았는지 확인
				if(s[i].d<0) continue;
				//방향파악
				int d = s[i].d;
				//우선순위에 따른 다음 좌표 구하기
				boolean flag=false;
				for (int j = 0; j < 4; j++) {
					int nr = s[i].r + dr[dir[i][d][j]];
					int nc = s[i].c + dc[dir[i][d][j]];
					if(nr<0 || nr>=n || nc<0 || nc>=n) continue;
					if(map[nr][nc]==0) { 
						//가능한 위치 중에서 다른 상어가 있는 경우 continue
						if(temp_map[nr][nc]!=0) {
							//만약 이동한 곳이 상어가 있는 곳이면 가면 안된다
							s[i].d=-1;//상어죽이기
							flag=true;//움직였음
							visit[s[i].r][s[i].c] = false;//현재좌표 미방문처리
							sharknum--;//상어의 수 감소
							break;
						}
						temp_smell[nr][nc] = k;
						temp_map[nr][nc] = i;
						//현 위치를 바꿔준다
						visit[s[i].r][s[i].c] = false;
						visit[nr][nc] = true;
						//상어위치변경
						s[i] = new Shark(nr,nc,dir[i][d][j]);
						flag=true;
						break;
					}
				}
				//4방향에 빈곳이 없다면?
				if(!flag) {
					for (int j = 0; j < 4; j++) {
						int nr = s[i].r + dr[dir[i][d][j]];
						int nc = s[i].c + dc[dir[i][d][j]];
						if(nr<0 || nr>=n || nc<0 || nc>=n) continue;
						//4방향중 내 냄새가 있는곳만 갈수있음
						if(map[nr][nc]==i) {
							//냄새 풍기기
							temp_smell[nr][nc] = k;
							//맵에 상어경로저장
							temp_map[nr][nc] = i;
							//상어의 현재 위치 파악
							visit[s[i].r][s[i].c] = false;
							visit[nr][nc] = true;
							//상어위치변경
							s[i] = new Shark(nr,nc,dir[i][d][j]);
							break;
						}
					}
				}
			}//상어를 다 탐색함
			
			//이동끝난후 temp를 오리지널에 복사하기
			for (int j = 0; j < n; j++) {
				for (int j2 = 0; j2 < n; j2++) {
					map[j][j2] = temp_map[j][j2];
					smell[j][j2] = temp_smell[j][j2];
				}
			}
			//복사후 현재위치 빼고 냄새들 1씩 줄이기
			for (int j = 0; j < n; j++) {
				for (int j2 = 0; j2 < n; j2++) {
					if(visit[j][j2]==true)continue;
					if(smell[j][j2]>0) { 
						smell[j][j2]--;
						//냄새지우면서 0되면 map도 갱신
						if(smell[j][j2]==0)
							map[j][j2]=0;
					}
				}
			}
			System.out.println("시간 : "+cnt);
			for(int row=0;row<n;row++) {
				for(int col=0;col<n;col++) {
					System.out.print(map[row][col]+" ");
				}
				System.out.println();
			}
			System.out.println();
			//시간 증가
			cnt++;
			if(cnt>1000) { 
				cnt=-1;
				break;
			}
			if(sharknum==1) break;
		}//while문
		System.out.println(cnt);
	}
}