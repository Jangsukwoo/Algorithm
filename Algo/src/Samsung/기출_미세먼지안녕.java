package Samsung;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class MunG{
	int row;
	int col;
	int amount;
	MunG(int r, int c, int a){
		row = r;
		col = c;
		amount =a;
	}
}
class RC{
	int row;
	int col;
	RC(int r, int c){
		row = r;
		col = c;
	}
}
public class 기출_미세먼지안녕{
	static int[][] Map;
	static int R,C,T,Ans,Time;
	static int clearR1,clearR2,clearcount;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static Queue<MunG> q = new LinkedList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		Map = new int[R][C];
		for(int row=0;row<R;row++)
			for(int col=0;col<C;col++) {
				Map[row][col] = sc.nextInt();
				if(Map[row][col]>0) {
					q.add(new MunG(row,col,Map[row][col]));
				}
				if(Map[row][col]==-1 && clearcount==0) {
					clearR1 = row;
					clearcount++;
				}
				else if(Map[row][col]==-1 && clearcount==1) {
					clearR2=row;
				}
			}
		bfs();//먼지시작
		for(int row=0;row<R;row++)
			for(int col=0;col<C;col++) {
				if(Map[row][col]>0) Ans+=Map[row][col];
			}
		System.out.println(Ans);
	}
	private static void bfs() {
		while(!q.isEmpty()){
			if(Time==T) break;
			Time++;
			int size = q.size();
			int[][] amountMap = new int[R][C];
			ArrayList<RC> targetlist = new ArrayList<>();
			for(int i=0;i<size;i++){//각 미세먼지에 대해
				MunG curMunG = q.poll();
				int count=0;
				for(int dir=0;dir<4;dir++) {
					int nr = curMunG.row+dr[dir];
					int nc = curMunG.col+dc[dir];
					if(range(nr,nc) && Map[nr][nc] !=-1) {
						count++;
						amountMap[nr][nc] += curMunG.amount/5;
					}
				}//4방향끝
				Map[curMunG.row][curMunG.col] = curMunG.amount- (count*(curMunG.amount/5));

			}//방출 끝

			for(int row=0;row<R;row++)//유입 처리
				for(int col=0;col<C;col++) {
					if(Map[row][col]!=-1) {
						Map[row][col] += amountMap[row][col];
					}
				}
			cleaner();//공기청정기가동
			for(int row=0;row<R;row++)
				for(int col=0;col<C;col++) if(Map[row][col]>0) targetlist.add(new RC(row,col));
			for(int i=0;i<targetlist.size();i++) {
				q.add(new MunG(targetlist.get(i).row,targetlist.get(i).col,Map[targetlist.get(i).row][targetlist.get(i).col]));
			}
		}
	}
	private static void cleaner() {
		int save=0;
		int tmp=0;
		for(int col=1;col<C;col++) {
			if(col==1) {
				save = Map[clearR1][col];
				Map[clearR1][col]=0;
			}
			else {
				tmp = Map[clearR1][col];
				Map[clearR1][col]=save;
				save = tmp;
			}
		}
		for(int row=(clearR1-1);row>=0;row--) {
			tmp = Map[row][C-1];
			Map[row][C-1] = save;
			save = tmp;
		}
		for(int col=(C-2);col>=0;col--) {
			tmp = Map[0][col];
			Map[0][col] = save;
			save = tmp;
		}
		for(int row=1;row<clearR1;row++){
			tmp = Map[row][0];
			Map[row][0] = save;
			save = tmp;
		}
		save =0;
		tmp =0;
		for(int col=1;col<C;col++) {
			if(col==1) {
				save = Map[clearR2][col];
				Map[clearR2][col]=0;
			}
			else {
				tmp = Map[clearR2][col];
				Map[clearR2][col]=save;
				save = tmp;
			}
		}
		for(int row=(clearR2+1);row<R;row++) {
			tmp = Map[row][C-1];
			Map[row][C-1] = save;
			save = tmp;
		}

		for(int col=(C-2);col>=0;col--) {
			tmp = Map[R-1][col];
			Map[R-1][col] = save;
			save = tmp;
		}

		for(int row=(R-2);row>clearR2;row--){
			tmp = Map[row][0];
			Map[row][0] = save;
			save = tmp;
		}
		save =0;
		tmp =0;
	}
	private static boolean range(int nr, int nc) {
		if((nr>=0&&nr<R) && (nc>=0&& nc<C)) return true;
		return false;
	}

}

