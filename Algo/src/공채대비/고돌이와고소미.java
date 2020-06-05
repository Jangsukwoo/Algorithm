package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 각자 위치에서 집으로 돌아가는 최단시간
 * 1은 웅덩이 
 * 0은 빈칸
 */
public class 고돌이와고소미 {
	static int N;
	static int godolR,godolC,godolHouseR,godolHouseC;
	static int goSoonR,goSoonC,goSoonHouseR,goSoonHouseC;
	static int[][] town;
	static boolean[][][][] visit;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static Queue<int[]> q = new LinkedList<int[]>();
	static int time;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		System.out.println("고돌이 고순이 집좌표");
		System.out.println(godolHouseR+" "+godolHouseC);
		System.out.println(goSoonHouseR+" "+goSoonHouseC);
		bfs();
		System.out.println(time);
	}
	private static void bfs() {
		while(!q.isEmpty()) {
			int size = q.size();
			System.out.println("큐사이즈"+size);
			for(int i=0;i<size;i++) {
				int[] currentInfo = q.poll();
				int cgr = currentInfo[0];
				int cgc = currentInfo[1];
				int cgsr = currentInfo[2];
				int cgsc = currentInfo[3];
				System.out.println("현재 좌표");
				System.out.println("고돌:"+cgr+" "+cgc);
				System.out.println("고순:"+cgsr+" "+cgsc);
				System.out.println();
				if(cgr==godolHouseR && cgc==godolHouseC && cgsr==goSoonHouseR && cgsc==goSoonHouseC) {
					System.out.println("이동완료");
					return;
				}
				for(int d=0;d<8;d++) {
					for(int dir=0;dir<8;dir++){//8방향을 볼는데
						int ngr=0;
						int ngc=0;
						int ngsr = 0;
						int ngsc = 0;
						if(cgr!=godolHouseR && cgc!=godolHouseC) {
							ngr = cgr+dr[d];
							ngc = cgc+dc[d];
						}else {
							ngr = cgr;
							ngc = cgc;
						}
						if(cgsr!=goSoonHouseR && cgsc!=goSoonHouseC) {
							ngsr = cgsr+dr[dir];
							ngsc = cgsc+dc[dir];
						}else {
							ngsr = cgsr;
							ngsc = cgsc;
						}
						//int dist = Math.abs(ngr-ngsr)+Math.abs(ngc-ngsc);
						if(!rangeCheck(ngr, ngc)) {
							ngr = cgr;
							ngc = cgc;
						}
						if(!rangeCheck(ngsr, ngsc)) {
							ngsr = cgsr;
							ngsc = cgsc;
						}
						if(insideCheck(ngr,ngc,ngsr,ngsc)) {
							if(visit[ngr][ngc][ngsr][ngsc]==false 
									&& town[ngr][ngc]!=1
									&& town[ngsr][ngsc]!=1){
								q.add(new int[] {ngr,ngc,ngsr,ngsc});
								visit[ngr][ngc][ngsr][ngsc] = true;
							}
						}
						//						if(dist>=2
						//								&& visit[ngr][ngc][ngsr][ngsc]==false 
						//								&& town[ngr][ngc]!=1
						//								&& town[ngsr][ngsc]!=1){
						//							q.add(new int[] {ngr,ngc,ngsr,ngsc});
						//							visit[ngr][ngc][ngsr][ngsc] = true;
						//						}
					}
				}
			}
			time++;
		}
	}
	private static boolean insideCheck(int ngr, int ngc, int ngsr, int ngsc) {
		if(ngr==ngsr && ngc== ngsc) return false;
		for(int dir=0;dir<8;dir++) {
			int nr = ngr+dr[dir];
			int nc = ngc+dc[dir];
			if(rangeCheck(nr, nc)){//범위 안
				if(nr==ngsr && nc== ngsc) return false;
			}
		}
		return true;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		town = new int[N][N];
		visit = new boolean[N][N][N][N];
		st = new StringTokenizer(br.readLine());
		godolR = Integer.parseInt(st.nextToken())-1;
		godolC = Integer.parseInt(st.nextToken())-1;
		godolHouseR = Integer.parseInt(st.nextToken())-1;
		godolHouseC = Integer.parseInt(st.nextToken())-1;
		st = new StringTokenizer(br.readLine());
		goSoonR = Integer.parseInt(st.nextToken())-1;
		goSoonC = Integer.parseInt(st.nextToken())-1;
		goSoonHouseR = Integer.parseInt(st.nextToken())-1;
		goSoonHouseC = Integer.parseInt(st.nextToken())-1;
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				town[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		q.add(new int[] {godolR,godolC,goSoonR,goSoonC});
		visit[godolR][godolC][goSoonR][goSoonC] = true;
	}
}
