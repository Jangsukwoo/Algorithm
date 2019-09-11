package BAEKJOON;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
class RowCol2933{
	int row;
	int col;
	public RowCol2933(int r, int c){
		row = r;
		col = c;
	}
}
public class 미네랄{
	static char[][] Cave;
	static int R,C,N;
	static int[] shoot;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상 우 하 좌 
	static boolean[][] cluster;
	static boolean[][] cluster2;
	static Queue<RowCol2933> q;
	static boolean Air;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		Cave = new char[R][C];
		for(int row=0;row<R;row++) Cave[row] = sc.next().toCharArray();
		N = sc.nextInt();
		shoot = new int[N];
		for(int i=0;i<N;i++) shoot[i] = sc.nextInt()-1;
		play();
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				System.out.print(Cave[row][col]);
			}
			System.out.println();
		}
	}
	private static void play() {
		for(int i=0;i<N;i++) { //N번만큼 발사
			switch (i%2) {
			case 0: //Left
				leftShoot(shoot[i]); //왼쪽 슛
				break;
			case 1://Right
				rightShoot(shoot[i]); //오른쪽 슛
				break;
			}
		}
	}
	private static void leftShoot(int h) {
		int height = R-h-1;
		for(int col=0;col<C;col++) {
			if(Cave[height][col]=='x') {//미네랄만남
				Cave[height][col]='.';//파괴!
				gravity(); //중력작동
				break;
			}
		}
	}
	private static void rightShoot(int h) {
		int height = R-h-1;
		for(int col=(C-1);col>=0;col--) {
			if(Cave[height][col]=='x') {//미네랄 만남
				Cave[height][col]='.';//파괴!
				cluster = new boolean[R][C];
				gravity(); //중력작동
				break;
			}
		}
	}
	private static void gravity() { //부시고 중력

		connectcheck(); //바닥 체크. 바닥으로 연결되어있는 애들 전부 true로만듬
		if(check()){//바닥체크 후 공중에 떠있는 미네랄이 있으면 
			down();//내리기
			connectcheck();
		}
	}
	private static void down() {//공중에 떠있는 애들 하나라도 밑에 엑스가 있으면 멈춰야함
		ArrayList<RowCol2933> list = new ArrayList<>();
		ArrayList<RowCol2933> targetlist = new ArrayList<>();
		
		for(int col=0;col<C;col++)
			for(int row=(R-1);row>=0;row--) {
				if(Cave[row][col]=='x' && !cluster[row][col]){
					list.add(new RowCol2933(row,col));
				}
				if(Cave[row][col]=='x' && !cluster[row][col]&&row<(R-1) && Cave[row+1][col]=='.') {
					targetlist.add(new RowCol2933(row,col));
				}
			}//클러스터들 리스트에
		Air = true;
		while(true) {
			int size = list.size();
			int size2 = targetlist.size();
			for(int i=0; i<size; i++) {//클러스터들 한칸씩 내리기 
				if((list.get(i).row+1)<=(R-1)) {
					Cave[list.get(i).row][list.get(i).col]='.' ;
					Cave[list.get(i).row+1][list.get(i).col]='x' ;
					list.set(i, new RowCol2933(list.get(i).row+1,list.get(i).col));
				}		
			}
			for(int i=0; i<size2; i++) {//타겟에 해당하는 좌표도 똑같이  
				if((targetlist.get(i).row+1)<=(R-1)) {
					targetlist.set(i, new RowCol2933(targetlist.get(i).row+1,targetlist.get(i).col));
				}		
			}
			for(int i=0;i<size2;i++) {
				if((targetlist.get(i).row+1)<=(R-1)) {
					if(Cave[targetlist.get(i).row+1][targetlist.get(i).col]=='x'
					|| (targetlist.get(i).row+1) ==R){
						Air = false;
						break;
					}
				}		
				if((targetlist.get(i).row+1)==R) {
					Air = false;
					break;
				}
				
			}
			if(!Air) break;
		}
	}
	private static boolean check() {
		for(int row=0;row<R;row++)
			for(int col=0;col<C;col++) {
				if(Cave[row][col]=='x') {//미네랄이고
					if(!cluster[row][col]) return true;// 공중에 떠있으면 
				}
			}
		return false;
	}
	private static void connectcheck() {//바닥이랑 연결된 미네랄 전부 true
		cluster = new boolean[R][C];
		for(int col=0;col<C;col++) {
			if(Cave[R-1][col]=='x' && !cluster[R-1][col]) {
				cluster[R-1][col] = true;
				dfs(R-1,col);
			}
		}
	}

	private static void dfs(int cr,int cc) {
		for(int dir=0;dir<4;dir++) {
			int nr = cr+dr[dir];
			int nc = cc+dc[dir];
			if(range(nr, nc)) {
				if(Cave[nr][nc]=='x' && !cluster[nr][nc]) {
					cluster[nr][nc]=true;
					dfs(nr,nc);
				}
			}
		}
	}
	private static boolean range(int nr, int nc){
		if((nr>=0&&nr<R) && (nc>=0 && nc<C)) return true;
		return false;
	}
}
