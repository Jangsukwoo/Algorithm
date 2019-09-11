package BAEKJOON;
import java.util.Scanner;

public class 보이저1호 {

	static int N,M,PR,PC;
	static int maxSecond=1;
	static int maxDir;
	static boolean infinite;
	static char[][] Map;
	static char[] direction= {'U','R','D','L'}; 
	static boolean[][][] checkMap;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		Map = new char[N][M];

		sc.nextLine();
		for(int i=0; i<N;i++) Map[i] = sc.nextLine().toCharArray();
		PR = sc.nextInt()-1;
		PC = sc.nextInt()-1;

		shoot();
		if(infinite) {
			System.out.println(direction[maxDir]);
			System.out.println("Voyager");
		}
		else{
			System.out.println(direction[maxDir]);
			System.out.println(maxSecond);
		}

	}
	private static void shoot() {
		for(int dir=0; dir<4;dir++){

			checkMap = new boolean[N][M][4];
			checkMap[PR][PC][dir]=true;
			int second=1;
			int cR = PR;
			int cC = PC;
			int cDir = dir;
			while(true){
				cR = cR + dr[cDir];
				cC = cC+ dc[cDir];
				if(rangeCheck(cR,cC)){
					if(checkMap[cR][cC][cDir]){
						infinite = true; 
						break;
					}
					else { 
						if(Map[cR][cC]=='C') break; 
						else if(Map[cR][cC]=='\\'){
							checkMap[cR][cC][cDir]=true;
							second++;
							switch(cDir){
							case 0:
								cDir = 3;
								break;
							case 1:
								cDir = 2;
								break;
							case 2:
								cDir = 1;
								break;
							case 3:
								cDir = 0;
								break;
							}
						}
						else if(Map[cR][cC]=='/'){
							checkMap[cR][cC][cDir]=true;
							second++;
							switch(cDir){
							case 0:
								cDir = 1;
								break;
							case 1:
								cDir = 0;
								break;
							case 2:
								cDir = 3;
								break;
							case 3:
								cDir = 2;
								break;
							}
						}
						else if(Map[cR][cC]=='.') {
							checkMap[cR][cC][cDir]=true;
							second++;
						}
					}
				}
				else break;

			}
			if(infinite) {
				maxDir = dir;
				break;
			}
			else {
				if(second>maxSecond) {
					maxDir=dir;
					maxSecond = Math.max(maxSecond,second);
				}
			}
			
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if((nr>=0&&nr<N) && (nc>=0 && nc<M)) return true;
		return false;
	}
}
