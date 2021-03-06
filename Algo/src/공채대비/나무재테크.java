package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * N,M,K
 */
public class 나무재테크 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M,K;
	static ArrayList<Tree>[][] ground;
	static int[][] nutrient;
	static int[][] addNutrient;
	static int answer;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static class Tree implements Comparable<Tree>{
		int row;
		int col;
		int age;
		boolean life;
		public Tree(int row, int col, int age, boolean life) {
			this.row = row;
			this.col = col;
			this.age = age;
			this.life = life;
		}
		@Override
		public int compareTo(Tree o) {
			return Integer.compare(this.age,o.age); //어린순
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		simulation();
		getAnswer();
		System.out.println(answer);
	}
	private static void getAnswer() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				answer+=ground[row][col].size();
			}
		}
	}
	private static void simulation() {
		for(int year=1;year<=K;year++){
			spring();
			summer();
			fall();
			winter();
		}
		
	}
	private static void spring() {
		/*
		 * 나무가 자신의 나이만큼 양분을 먹고 나이가 1 증가함.
		 * 하나의 칸에 여러 나무가 있으면 어린나무부터 양분을 먹고
		 * 양분이 부족해서 자신의 나이만큼 양분을 먹을 수 없으면 죽음
		 */
		
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(ground[row][col].size()>0){//나무가 존재
					Collections.sort(ground[row][col]);
					for(Tree tree : ground[row][col]) {
						if(nutrient[row][col]>=tree.age){
							nutrient[row][col]-=tree.age;
							tree.age+=1;//나이증가
						}else tree.life = false;
					}
				}
			}
		}
	}
	private static void summer() {
		/*
		 * 죽은 나무가 양분으로 변함
		 * 죽은 나무의 나이를 2로 나눈 값이 칸에 양분으로 추가
		 */
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(ground[row][col].size()>0){
					for(int i=ground[row][col].size()-1;i>=0;i--) {
						if(ground[row][col].get(i).life==false) {
							int nutri = ground[row][col].get(i).age/2;
							nutrient[row][col]+=nutri;
							ground[row][col].remove(i);
						}else break;
					}
				}
			}
		}
	}
	private static void fall() {
		/*
		 * 나무가 번식
		 * 나이가 5의 배수인 나무는 번식
		 */
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(ground[row][col].size()>0){
					for(Tree tree : ground[row][col]) {
						if(tree.age%5==0) {
							for(int dir=0;dir<8;dir++) {
								int nr = tree.row+dr[dir];
								int nc = tree.col+dc[dir];
								if(rangeCheck(nr,nc)) {
									ground[nr][nc].add(new Tree(nr,nc,1,true));
								}
							}
						}
					}
				}
			}
		}
	}
	private static void winter() {
		/*
		 * 양분 추가
		 */
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				nutrient[row][col]+=addNutrient[row][col];
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc){
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}

	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ground = new ArrayList[N][N];
		nutrient = new int[N][N];
		addNutrient = new int[N][N];
		for(int row=0;row<N;row++){
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				nutrient[row][col] = 5;
				ground[row][col] = new ArrayList<Tree>();
				addNutrient[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken())-1;
			int col = Integer.parseInt(st.nextToken())-1;
			int age = Integer.parseInt(st.nextToken());
			ground[row][col].add(new Tree(row, col, age, true));
		}
	}
}
