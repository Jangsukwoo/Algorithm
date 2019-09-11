package BAEKJOON;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Target implements Comparable<Target>{
	int row;
	int col;
	int D;
	Target(int r,int c,int d){
		row=r;
		col=c;
		D=d;
	}
	@Override
	public int compareTo(Target o) {
		if(this.D==o.D) return Integer.compare(this.col,o.col); //사정거리가 같으면 열기준 정렬
		return Integer.compare(this.D, o.D);  //사정거리 기준 정렬
	}
}
public class 캐슬디펜스{
	static int N,M,D,MaxKill,Kill;
	static int[][] Field;
	static int[][] CopyField;
	static int[] Shooter = new int[3];
	static boolean[][] TargetMap;
	static boolean END;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();//행
		M = sc.nextInt();//열
		D = sc.nextInt();
		Field = new int[N+1][M];
		CopyField = new int[N][M];
		for(int row=0;row<N;row++)
			for(int col=0;col<M;col++) Field[row][col] = sc.nextInt();
		//입력끝
		
		for(int i=0;i<(M-2);i++) {
			for(int j=(i+1);j<(M-1);j++) {
				for(int k=(j+1);k<M;k++){
					Shooter[0]=i;
					Shooter[1]=j;
					Shooter[2]=k;
					Kill=0;
					END=false;				

					for(int row=0;row<N;row++) //맵 카피 
						for(int col=0;col<M;col++) CopyField[row][col]=Field[row][col];

					while(!END) {//현재 조합에 대해 끝날때까지
						SHOOT();
					}				
					MaxKill = Math.max(MaxKill,Kill);
					//if(MaxKill==2) System.out.println(Arrays.toString(Shooter));
				}
			}
		}
		System.out.println(MaxKill);	
	}
	private static void SHOOT() {
		if(targetcheck()) {//타겟이 존재하면
			TargetMap = new boolean[N][M];
			for(int num=0;num<3;num++){//각 궁수에 대해 슈팅
				ArrayList<Target> Targetlist = new ArrayList<>();//현 궁수에 대해 타겟들을 넣을 리스트
				for(int col=0;col<M;col++) {//사격권 조사
					for(int row=(N-1);row>=0;row--) {
						if(CopyField[row][col]==1){//타겟발견
							if((Math.abs(N-row)+Math.abs(Shooter[num]-col))<=D){//사격권 안에있으면
								Targetlist.add(new Target(row,col,Math.abs(N-row)+Math.abs(Shooter[num]-col)));
							}
						}
					}
				}
				Collections.sort(Targetlist);
				if(Targetlist.size()==1) { //타겟이 하나면 어쩔수없이 true
					TargetMap[Targetlist.get(0).row][Targetlist.get(0).col] = true;
				}
				else if(Targetlist.size()>1) { //타겟이 여러개면 가깝고 열이 가장 왼쪽인 것 삭제
					TargetMap[Targetlist.get(0).row][Targetlist.get(0).col] = true;
				}
			}

			for(int row=0;row<N;row++) {//적병 삭제
				for(int col=0;col<M;col++) {
					if(TargetMap[row][col]) {
						CopyField[row][col]=0;
						Kill++;
					}
				}
			}
			TargetMove();//적군 한칸 씩 내려오기
		}else {
			END=true;
			return;
		}
	}
	private static boolean targetcheck() {
		for(int row=0;row<N;row++)
			for(int col=0;col<M;col++) if(CopyField[row][col]==1) return true;
		return false;
	}
	

	private static void TargetMove() {
		for(int col=0;col<M;col++) {
			for(int row=(N-1);row>=0;row--){
				if(CopyField[row][col]==1){//적병인경우
					if(row+1==N) {//다음칸이 궁수 위치면
						CopyField[row][col]=0;//그냥 삭제
					}
					else if((row+1)<N) {
						CopyField[row][col]=0;
						CopyField[row+1][col]=1;
					}
				}
			}
		}
	}
}
