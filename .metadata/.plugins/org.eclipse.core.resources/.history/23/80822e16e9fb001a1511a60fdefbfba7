package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 17:05 시작
 */
public class 점프게임 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[][] line;
	static StringTokenizer st;
	static boolean[][] visit;
	static int N,K;
	static Queue<int[]> q;
	static boolean success;
	public static void main(String[] args) throws IOException {
		setData();
		if(bfs()) System.out.println("1");
		else System.out.println("0");
	}
	private static boolean bfs() {
		q = new LinkedList<int[]>();
		insertQueue(new int[] {1,0});
		int time = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] current = q.poll();
				int currentLine = current[0];
				int currentPosition = current[1];
				int nextLine = 0;
				int nextPosition = 0;
				//뒤로
				if(currentPosition<time) continue;
				nextPosition = currentPosition-1;
				nextLine = currentLine;
				
				if(rangeCheck(nextPosition)
						&& nextPosition>=time
						&& visit[nextLine][nextPosition] == false
						&& line[nextLine][nextPosition]=='1') insertQueue(new int[] {nextLine , nextPosition});
				//앞으로
				
				
				nextPosition = currentPosition+1;
				nextLine = currentLine;
				
				if(nextPosition>(N-1)) return true;
				if(rangeCheck(nextPosition)
						&& nextPosition>=time
						&& visit[nextLine][nextPosition] == false
						&& line[nextLine][nextPosition]=='1') insertQueue(new int[] {nextLine , nextPosition});
				//반대편 라인 +K
				if(currentLine%2==0) nextLine = 1;
				else nextLine= 2;
				nextPosition = currentPosition+K;
				if(nextPosition>(N-1)) return true;
				if(rangeCheck(nextPosition)
						&& nextPosition>=time
						&& visit[nextLine][nextPosition] == false
						&& line[nextLine][nextPosition]=='1') insertQueue(new int[] {nextLine , nextPosition});
			}

			time++;
		}
		return false;
	}
	private static boolean rangeCheck(int nextPosition) {
		if(nextPosition>=0 && nextPosition<N) return true;
		return false;
	}
	private static void insertQueue(int[] data) {
		q.add(data);
		visit[data[0]][data[1]] = true;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		line = new char[3][N];
		visit = new boolean[3][N];
		line[1] = br.readLine().toCharArray();
		line[2] = br.readLine().toCharArray();
	}
}
