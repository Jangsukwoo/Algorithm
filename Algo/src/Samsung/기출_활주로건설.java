package Samsung;

/*
 * 시작 1045
 * 경사로의 길이 L
 * 경사로의 높이는 무조건 1
 * 
 * 하드코딩함..
 * 끝 1153
 * 걸린시간 1시간 7분
 */

import java.util.Scanner;

public class 기출_활주로건설 {
	static int N,L;
	static int[][] roadMap;
	static int possibleRoad;
	static boolean[][] slipwayRow;
	static boolean[][] slipwayCol;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++) {
			N = sc.nextInt();
			L = sc.nextInt();
			roadMap = new int[N][N];
			slipwayRow= new boolean[N][N];
			slipwayCol= new boolean[N][N];
			possibleRoad=0;
			for(int row=0;row<N;row++)
				for(int col=0;col<N;col++) roadMap[row][col] = sc.nextInt();
			possibleCheck();		
			System.out.println("#"+testcase+" "+possibleRoad);
		}
	}
	private static void possibleCheck(){
		int height=0;
		boolean possible=true;
		for(int row=0;row<N;row++) {
			height = roadMap[row][0];
			possible=true;
			for(int col=1;col<N;col++){
				if(height==roadMap[row][col]) continue;//높이가 같으면 그냥 진행
				else if(roadMap[row][col]-height==1){//오르막길이면
					for(int l=1;l<=L;l++){//경사로 체크
						if((col-l)>=0){//범위 만족
							if(roadMap[row][col-l]==height && slipwayRow[row][col-l]!=true) continue;	
							else {
								possible = false;
								break;
							}
						}else{
							possible = false;
							break;
						}
					}
					if(possible){
						for(int l=1;l<=L;l++) slipwayRow[row][col-l] = true;
						height++;
					}
				}else if(roadMap[row][col]-height==-1){//내리막길이면
					for(int l=0;l<L;l++){//경사로 체크
						if((col+l)<N){//범위 만족
							if(roadMap[row][col+l]==(height-1) && slipwayRow[row][col+l]!=true) continue;	
							else {
								possible = false;
								break;
							}
						}else{
							possible = false;
							break;
						}
					}
					if(possible) {
						for(int l=0;l<L;l++) slipwayRow[row][col+l] = true;
						height--;
					}
				}else {
					possible = false;
					break;
				}
			}
			if(possible) {
				possibleRoad++;
			}
		}
		
		
		for(int col=0;col<N;col++) {
			height = roadMap[0][col];
			possible=true;
			for(int row=1;row<N;row++){
				if(height==roadMap[row][col]) continue;//높이가 같으면 그냥 진행
				else if(roadMap[row][col]-height==1){//오르막길이면
					for(int l=1;l<=L;l++){//경사로 체크
						if((row-l)>=0){//범위 만족
							if(roadMap[row-l][col]==height && slipwayCol[row-l][col]!=true) continue;	
							else {
								possible = false;
								break;
							}
						}else{
							possible = false;
							break;
						}
					}
					if(possible) {
						for(int l=1;l<=L;l++) slipwayCol[row-l][col] = true;
						height++;
					}
				}else if(roadMap[row][col]-height==-1){//내리막길이면
					for(int l=0;l<L;l++){//경사로 체크
						if((row+l)<N){//범위 만족
							if(roadMap[row+l][col]==(height-1) && slipwayCol[row+l][col]!=true) continue;	
							else {
								possible = false;
								break;
							}
						}else{
							possible = false;
							break;
						}
					}
					if(possible) {
						for(int l=0;l<L;l++) slipwayCol[row+l][col] = true;
						height--;
					}
				}else {
					possible = false;
					break;
				}
			}
			if(possible) {
				possibleRoad++;
			}
		}
	}
	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(slipwayRow[row][col]+" ");
			}
			System.out.println(); 
		}
		System.out.println();
	}
}
