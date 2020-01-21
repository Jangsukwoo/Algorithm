package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


/*
 * NxN 격자맵
 * 처음 모든 땅에 양분은 5만큼 들어있다.
 * 한칸에 여러개의 나무가 심어져있을 수 있다.
 * 
 * 봄
 * 나무가 자신의 나이만큼 양분을 먹고 나이 1증가
 * 하나의 칸에 여러 개의 나무가 있다면 나이가 어린 나무부터 양분을 먹음
 * 만약 땅에 양분이 부족해서 자신의 나이만큼 양분을 먹을 수 없는 나무는 즉시 죽음
 * 
 * 여름 
 * 봄에 죽은 나무가 양분으로 변한다.
 * 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸의 양분으로 추가된다.
 * 
 * 가을
 * 나무가 번식한다. 번식하는 나무는 나이가 5의 배수여야하고
 * 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
 * 
 * 겨울
 * 각 칸에 양분을 추가한다. 양분의 양은 A[r][c]고
 * 입력으로 주어진다.
 * 
 * K년이 지난 후 상도의 땅에 살아있는 나무의 개수는?
 */
public class 나무재테크 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M,K;
	static int[][] nutrientMap;//N by N
	static int[][] supplyNutrient;
	static int[][] groundNumbers;
	static GroundTrees[] groundTrees;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static int treeCount;
	static class GroundTrees{
		int number;
		ArrayList<Tree> trees = new ArrayList<Tree>();
		public GroundTrees(int n){
			number = n;
		}
	}
	static class Tree implements Comparable<Tree> {
		int age;
		boolean life = true;
		public Tree(int a) {
			age = a;
		}
		@Override
		public int compareTo(Tree o) {
			return Integer.compare(this.age,o.age);//나이 순 정렬 
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		simultation();
	}
	private static void simultation() {
		for(int year=1;year<=K;year++){
			//System.out.println("먹기전");
			//System.out.println(nutrientMap[1][1]);
			spring();
			//System.out.println("먹은후");
			//System.out.println(nutrientMap[1][1]);
			summer();
			autumn();
			winter();
		}
		treeCount();
		System.out.println(treeCount);
	}

	private static void spring() {
		for(int row=1;row<=N;row++) {
			for(int col=1;col<=N;col++){
				int treeSize = groundTrees[groundNumbers[row][col]].trees.size();
				if(treeSize==0) continue;
				else {
					Collections.sort(groundTrees[groundNumbers[row][col]].trees);//나이순 정렬
					for(int i=0;i<treeSize;i++) {
						int diff = nutrientMap[row][col]-groundTrees[groundNumbers[row][col]].trees.get(i).age;
						if(diff>=0) {
							nutrientMap[row][col] = diff;
							groundTrees[groundNumbers[row][col]].trees.get(i).age = groundTrees[groundNumbers[row][col]].trees.get(i).age+1;
						}
						else {
							groundTrees[groundNumbers[row][col]].trees.get(i).life= false;//죽음 
						}
					}
				}
			}
		}
	}
	private static void summer() {
		for(int row=1;row<=N;row++) {
			for(int col=1;col<=N;col++){
				int treeSize = groundTrees[groundNumbers[row][col]].trees.size();
				if(treeSize==0) continue;
				else {
					for(int i=treeSize-1;i>=0;i--){//끝에서 지워버리자
						if(groundTrees[groundNumbers[row][col]].trees.get(i).life==false){//죽은 나무면
							nutrientMap[row][col]+=(groundTrees[groundNumbers[row][col]].trees.get(i).age/2);
							groundTrees[groundNumbers[row][col]].trees.remove(i);
						}
					}
				}
			}
		}
	}
	private static void autumn() {
		for(int row=1;row<=N;row++) {
			for(int col=1;col<=N;col++){
				int treeSize = groundTrees[groundNumbers[row][col]].trees.size();
				if(treeSize==0) continue;
				else {
					for(int i=treeSize-1;i>=0;i--){
						if(groundTrees[groundNumbers[row][col]].trees.get(i).age%5==0){//나이가 5의 배수면 
							for(int dir=0;dir<8;dir++) {
								int nr = row+dr[dir];
								int nc = col+dc[dir];
								if(rangeCheck(nr,nc)) {
									groundTrees[groundNumbers[nr][nc]].trees.add(new Tree(1));//애기 추가
								}
							}
						}
					}
				}
			}
		}
	}
	private static void winter() {
		for(int row=1;row<=N;row++)
			for(int col=1;col<=N;col++) nutrientMap[row][col]+=supplyNutrient[row][col];
	}
	
	private static void treeCount() {
		for(int row=1;row<=N;row++) {
			for(int col=1;col<=N;col++){
				int treeSize = groundTrees[groundNumbers[row][col]].trees.size();
				if(treeSize==0) continue;
				else treeCount+=treeSize;
			}
		}
	}
	
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nutrientMap = new int[N+1][N+1];
		supplyNutrient = new int[N+1][N+1];
		groundTrees = new GroundTrees[N*N];
		groundNumbers = new int[N+1][N+1];
		int groundNumber=0;
		for(int row=1;row<=N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=1;col<=N;col++) {
				nutrientMap[row][col] = 5;
				supplyNutrient[row][col] = Integer.parseInt(st.nextToken());
				groundTrees[groundNumber] = new GroundTrees(groundNumber);
				groundNumbers[row][col] = groundNumber++;
			}
		}
		for(int i=0;i<M;i++){
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			groundTrees[groundNumbers[row][col]].trees.add(new Tree(age));
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=1 && nr<=N && nc>=1 && nc<=N) return true;
		return false;
	}
}
