package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 13:15~
 */
public class 게리맨더링2 {
	static int N;
	static int[][] people;
	static int[][] area;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] peopleCnt;
	static int answer=Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		divide();
		System.out.println(answer);
	}
	private static void divide() {
		for(int d1=1;d1<=(N-1);d1++) {
			for(int d2=1;d2<=(N-1);d2++) {
				for(int x=0;x<N;x++) {
					for(int y=0;y<N;y++){//점 하나 찍고
						if(rangeCheck(x,y,d1,d2)){ //조건에 맞으면
							area = new int[N][N];
							setArea(x,y,d1,d2);//영역 및 경계선 그리기
							updateAnswer();
						}
					}
				}
			}
		}
	}
	private static void updateAnswer() {
		peopleCnt = new int[6];
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				peopleCnt[area[row][col]]+=people[row][col];
			}
		}
		int min=Integer.MAX_VALUE;
		int max=0;
		for(int i=1;i<=5;i++) {
			min = Math.min(peopleCnt[i],min);
			max = Math.max(peopleCnt[i],max);
		}
		answer = Math.min(answer,max-min);
	}
	private static void setArea(int x, int y, int d1, int d2) {
		for(int row=0;row<(x+d1);row++) {
			for(int col=0;col<=y;col++) {
				area[row][col] = 1;
			}
		}
		for(int row=0;row<=(x+d2);row++) {
			for(int col=y+1;col<N;col++) {
				area[row][col] = 2;
			}
		}
		for(int row=x+d1;row<N;row++) {
			for(int col=0;col<(y-d1+d2);col++) {
				area[row][col] = 3;
			}
		}
		for(int row=x+d2+1;row<N;row++) {
			for(int col=y-d1+d2;col<N;col++) {
				area[row][col] = 4;
			}
		}
		for(int d=0;d<=d1;d++) area[x+d][y-d] = 5;
		for(int d=0;d<=d2;d++) area[x+d][y+d] = 5;
		for(int d=0;d<=d2;d++) area[x+d1+d][y-d1+d] = 5;
		for(int d=0;d<=d1;d++) area[x+d2+d][y+d2-d] = 5;
		for(int row=0;row<N;row++){
			//한 행에 대해서 
			int fiveCnt = 0;
			int startCol=0;
			int endCol=0;
			for(int col=0;col<N;col++) {
				if(area[row][col]==5 && fiveCnt==0) {
					startCol = col;
					fiveCnt++;
				}else if(area[row][col]==5 && fiveCnt==1) {
					endCol=col;
					fiveCnt++;
				}
			}
			if(fiveCnt==2) {
				for(int col=startCol;col<endCol;col++) {
					area[row][col] = 5;
				}
			}
		}
		//view();
	}
	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(area[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
	private static boolean rangeCheck(int x, int y, int d1, int d2){
		if(x>=0 && x<(x+d1+d2) && (x+d1+d2)<N && 0<=(y-d1) && (y-d1)<y && y<(y+d2) && (y+d2)<N) return true;
		return false;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		people = new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				people[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
