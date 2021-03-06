package codeTest;

import java.util.ArrayList;
import java.util.Scanner;

public class 감시_주영이{
	static int N, M;
	static int[][] Room = new int[10][10];
	static int dx[] = { 0,1,0,-1 };
	static boolean[][] testMap = new boolean[10][10];
	static int dy[] = { 1,0,-1,0 };//동남서북
	static int MMin = Integer.MAX_VALUE;
	static int NNN = 0;
	static ArrayList<Info> CCTV = new ArrayList<Info>();
	static class Info{
		int row;
		int col;
		int num;
		public Info(int r, int c, int n) {
			row = r;
			col = c;
			num = n;
		}
	}
	public static void main(String[] args) 
	{		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				Room[i][j] = sc.nextInt();
			if (Room[i][j] < 5 && Room[i][j]>0) {
				CCTV.add(new Info(i,j,Room[i][j]));
				NNN++;
			}
			else if (Room[i][j] == 5)
			{
				testMap[i][j] = true;
				testMap = one(i, j, 0, testMap);
				testMap = one(i, j, 1, testMap);
				testMap = one(i, j, 2, testMap);
				testMap = one(i, j, 3, testMap);
			}
			else if (Room[i][j] == 6)
				testMap[i][j] = true;
			}
		}

		//재귀로 cctv돌리기
		Run(0,testMap);

		System.out.println(MMin);
	}


	public static boolean[][] one(int x, int y,int a, boolean[][] check)
	{
		int nx = x+dx[a]; int ny = y+dy[a];
		while (nx >= 0 && ny >= 0 && nx < N && ny < M && Room[nx][ny] < 5)
		{
			check[nx][ny] = true;
			nx += dx[a];
			ny += dy[a];
		}
		return check;
	}
	public static boolean[][] clearone(int x, int y, int a,  boolean[][] check)
	{
		int nx = x + dx[a]; int ny = y + dy[a];
		while (nx >= 0 && ny >= 0 && nx < N && ny < M && Room[nx][ny] < 5)
		{
			check[nx][ny] = false;
			nx += dx[a];
			ny += dy[a];
		}
		return check;
	}
	public static void Run(int idx, boolean[][] check)
	{
		boolean[][] copyMap = new boolean[10][10];
		for(int row=0;row<10;row++) {
			for(int col=0;col<10;col++) {
				copyMap[row][col] = check[row][col];
			}
		}
		if (idx == NNN)
		{
			for(int row=0;row<N;row++) {
				for(int col=0;col<M;col++) {
					if(Room[row][col]==5) {
						check[row][col] = true;
						check = one(row, col, 0, check);
						check = one(row, col, 1, check);
						check = one(row, col, 2, check);
						check = one(row, col, 3, check);
					}
				}
			}
			
			int[][] map = new int[10][10];
			for(int row=0;row<10;row++) {
				for(int col=0;col<10;col++) {
					if(check[row][col]==false) map[row][col]=1;
				}
			}
//			for(int row=0;row<N;row++) {
//				for(int col=0;col<M;col++) {
//					System.out.print(map[row][col]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			//사각지대세기
			int count = 0;
			for (int i = 0; i < N; i++)
			{
				for (int j = 0; j < M; j++)
				{
					if (check[i][j] == false) count++;
				}
			}
			MMin = Math.min(MMin, count);

			return;
		}

		Info cctvN = CCTV.get(idx);

		if (cctvN.num == 1)
		{
			for (int i = 0; i < 4; i++) {
				copyMap[cctvN.row][cctvN.col] = true;
				copyMap = one(cctvN.row, cctvN.col, i,copyMap);
				Run(idx + 1,copyMap);
				copyMap[cctvN.row][cctvN.col] = false;
				copyMap = clearone(cctvN.row,cctvN.col,i,copyMap);
			}
		}
		else if (cctvN.num == 2)
		{
			for (int i = 0; i < 2; i++)
			{
				copyMap[cctvN.row][cctvN.col] = true;
				copyMap = one(cctvN.row, cctvN.col, i,copyMap);
				copyMap = one(cctvN.row, cctvN.col, i+2,copyMap);
				Run(idx + 1,copyMap);
				copyMap[cctvN.row][cctvN.col] = false;
				copyMap = clearone(cctvN.row,cctvN.col,i,copyMap);
				copyMap = clearone(cctvN.row,cctvN.col,i+2,copyMap);
			}
		}
		else if (cctvN.num == 3)
		{
			for (int i = 0; i < 4; i++)
			{
				copyMap[cctvN.row][cctvN.col] = true;
				copyMap = one(cctvN.row, cctvN.col, i,copyMap);
				copyMap = one(cctvN.row, cctvN.col, (i+1)%4,copyMap);
				Run(idx + 1,copyMap);
				copyMap[cctvN.row][cctvN.col] = false;
				copyMap = clearone(cctvN.row, cctvN.col, i,copyMap);
				copyMap = clearone(cctvN.row, cctvN.col, (i+1)%4,copyMap);
			}
		}
		else if (cctvN.num == 4)
		{
			for (int i = 0; i < 4; i++)
			{
				copyMap[cctvN.row][cctvN.col] = true;
				copyMap = one(cctvN.row, cctvN.col, i,copyMap);
				copyMap = one(cctvN.row, cctvN.col, (i + 1) % 4,copyMap);
				copyMap = one(cctvN.row, cctvN.col, (i + 2) % 4,copyMap);
				Run(idx + 1,copyMap);
				copyMap[cctvN.row][cctvN.col] = false;
				copyMap = clearone(cctvN.row, cctvN.col, i,copyMap);
				copyMap = clearone(cctvN.row, cctvN.col, (i + 1) % 4,copyMap);
				copyMap = one(cctvN.row, cctvN.col, (i + 2) % 4,copyMap);
			}
		}
	}
}
