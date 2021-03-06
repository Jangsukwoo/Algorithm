package Samsung;

/*
 * 시작 547
1            
6 8 3         
0 0 1 0 1 0 0 1
0 1 0 0 0 1 1 1
0 1 1 1 0 0 0 0
1 1 1 1 0 0 0 1
0 1 1 0 1 0 0 1
1 0 1 0 1 1 0 1
 
 끝 903
 
 걸린시간 3시간 16분 걸림..
 처음 조합으로 경우의수 뽑은건 금방 했는데
 두번째 케이스 뽑는거에서 시간 너무많이씀
dfs로 뽑으려니 잘 안나와서
 비트마스킹으로 모든 경우 추출해서 바로 해결
 *
 */
import java.util.Scanner;

public class 모의_보호필름 {
	static int D,W,K; //row,col,합격기준
	static int[][] film;
	static int[][] testingFilm;
	static int[] testData;
	static boolean[] visit;
	static int inertDrugCount;
	static boolean find;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++){
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			film = new int[D][W];
			find = false;
			inertDrugCount = 0;
			for(int row=0;row<D;row++)
				for(int col=0;col<W;col++) film[row][col] = sc.nextInt();
			testingFilm = new int[D][W];
			setTestingFilm();
			while(true){
				if(performanceTest()) break;
				else{//불가능하다면 약품추가해서 찾으러감 
					inertDrugCount++;
					testData = new int[inertDrugCount];
					nCr(0,0);
				}
				if(find) break;
			}

			System.out.println("#"+testcase+" "+inertDrugCount);
			
		}
	}
	private static void nCr(int idx, int cnt) {
		if(cnt==inertDrugCount){ //D 위치 고름
			//부분집합 구하기 
			for(int v=1;v<=inertDrugCount;v++) {//비트의 개수 
				visit = new boolean[inertDrugCount];
				for(int i=0,size=1<<v;i<size;i++){//비트 종류
					for(int k=0;k<v;k++){
						if(((1<<k)&i)==0){ //0인 자리면
							visit[k] = false;
						}else {
							visit[k] = true;
						}
					}
					insertDrug(v);
					if(find) return;
				}
			}
			return;
		}
		for(int i=idx;i<D;i++){
			testData[cnt] = i;
			nCr(i+1,cnt+1);
		}
	}
	private static void insertDrug(int v) {
		setTestingFilm();
		for(int i=0;i<v;i++){
			if(visit[i]==false){
				for(int col=0;col<W;col++) testingFilm[testData[i]][col] = 0;
			}else {
				for(int col=0;col<W;col++) testingFilm[testData[i]][col] = 1;
			}
		}
		if(performanceTest()) find = true;
	}

	private static void setTestingFilm() {
		for(int row=0;row<D;row++)
			for(int col=0;col<W;col++) testingFilm[row][col] = film[row][col];
	}
	private static boolean performanceTest(){
		boolean OK = true;
		
		for(int col=0;col<W;col++){
			int drug = testingFilm[0][col]; 
			int cnt=1;
			for(int row=1;row<D;row++){
				if(cnt==K) break;
				if(drug==testingFilm[row][col]) {
					cnt++;
				}else {
					drug = testingFilm[row][col];
					cnt=1;
				}
			}
			if(cnt!=K) {
				OK=false;
				break;
			}
		}
		if(OK) return true;
		else return false;
	}
}
