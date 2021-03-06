package BAEKJOON;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//8방향
//모래성이 쌓여있지 않은 개수
//자기 모래성의 튼튼함보다 많거나 같으면 무너짐


//시간초과로 애먹은 문제.
//처음에 모래성을 큐에 담았는데
//모래바닥을 기준으로 큐에 담는게 훨씬 빠르다는 것을 깨닫고 모래바닥을 넣었음


class sandCastleHW{
	int height,width;
	
	public sandCastleHW(int h,int w) {
		height = h;
		width = w;
	}
}
public class 모래성 {
	static char[][] sandCastle;
	static int H,W;
	static int[] dH = {-1,-1,0,1,1,1,0,-1};
	static int[] dW = {0,1,1,1,0,-1,-1,-1};//12시부터 시계방향
	static Queue<sandCastleHW> q = new LinkedList<sandCastleHW>();
	static int count;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		H = sc.nextInt();
		W = sc.nextInt();
		sandCastle = new char[H][W];
		String tmp;
		sc.nextLine();
		for(int r=0;r<H;r++) {
			tmp = sc.nextLine();
			sandCastle[r] = tmp.toCharArray();
		}
		for(int r=0;r<H;r++) {
			for(int c=0;c<W;c++){
				if(sandCastle[r][c]=='.'){
					q.add(new sandCastleHW(r, c));//모래바닥은 전부 큐에 넣기
				}
			}
		}
		//int t = Character.getNumericValue(ch)
		
		simulation();
		
		System.out.println((count-1));
		//view();
		
	}
	private static void simulation() {
		boolean[][] CheckMap = new boolean[H][W];
		while(!q.isEmpty()){
			//view();
			int size = q.size();
			for(int i=0; i<size;i++) {//꺼낸다.
				
				sandCastleHW curSandHW = q.poll();
				int nH=0;
				int nW=0;
				int sum=0;

				for(int dir=0;dir<8;dir++){//8방향 보기
					nH = curSandHW.height+dH[dir];
					nW = curSandHW.width+dW[dir];
					if(rangeCheck(nH,nW)){//영역 만족하고
						if(sandCastle[nH][nW]!='.' && sandCastle[nH][nW]>'0'){ //.이 아니면 
							sandCastle[nH][nW]= (char) (sandCastle[nH][nW]-1); 
							if(sandCastle[nH][nW]=='0'){
								q.add(new sandCastleHW(nH, nW));
							}
						}
					}
				}//8방향 검사 끝
			}//모든 모래성 조사 끝
			count++;
		}
	}
	

	private static boolean rangeCheck(int nH, int nW) {
		if(nH>=0 && nH<H && nW>=0 && nW<W) return true;
		return false;
	}
	private static void view() {
		for(int r=0;r<H;r++) {
			for(int c=0;c<W;c++) {
				System.out.print(sandCastle[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

