package Samsung;
import java.util.ArrayList;
import java.util.Collections;
/*
 * 봄
 * 나무가 자신의 나이만큼 양분 먹고 나이 1증가
 * 나무가 여러그루면 나이가 어린 나무부터 먹음
 * 양분이 부족하여 자신의 나이만큼 먹을 수 없다면 죽음
 * 
 * 여름
 * 죽은 나무가 양분으로 변함
 * 죽은 나무의 나이를 2로 나눈 값이 그 칸의 양분으로 더해짐
 * 
 * 가을
 * 나이가 5의 배수인 나무들이 번식 8방향으로 번식
 * 인접한 8개의 칸에 나이가 1인 나무가 새로 생긴다.
 * 
 * 겨울
 * 땅에 양분을 추가한다.
 */
import java.util.Scanner;
class GroundTrees{
	int groundNumber;
	ArrayList<Tree> treelist;
	public GroundTrees(int gn) {
		groundNumber = gn;
		treelist = new ArrayList<Tree>();
	}
}
class Tree implements Comparable<Tree>{
	int age;
	boolean life;
	public Tree(int a) {
		age=a;
		life = true;
	}
	@Override
	public int compareTo(Tree o) {
		return Integer.compare(this.age,o.age);
	}
}
public class 기출_나무재테크 {
	static int N,M,K;
	static int[][] nutrient;
	static int[][] ground;
	static int[][] groundNumber;
	static GroundTrees[] groundtrees;
	static int groundSize;
	static int result;
	static int[] dr = {-1,-1,-1,0,1,1,1,0};
	static int[] dc = {-1,0,1,1,1,0,-1,-1};//11시방향부터 시계방향
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //MapSize
		M = sc.nextInt(); //처음 나무 수
		K = sc.nextInt(); //K년
		nutrient = new int[N+2][N+2];
		ground = new int[N+2][N+2];
		groundNumber = new int[N+2][N+2];
		groundSize = (int) Math.pow(N,2);
		groundtrees = new GroundTrees[groundSize];
		int number=0;
		for(int row=1;row<=N;row++) {
			for(int col=1;col<=N;col++) {
				ground[row][col] = 5;
				groundNumber[row][col] = number; //땅 넘버링
				groundtrees[number] = new GroundTrees(number); //땅마다 나무리스트
				nutrient[row][col] = sc.nextInt(); //양분 정보
				number++; 
			}
		}
		for (int m=0;m<M;m++) { //나무 입력
			int row = sc.nextInt();
			int col = sc.nextInt();
			int age = sc.nextInt();
			groundtrees[groundNumber[row][col]].treelist.add(new Tree(age));
		}

		for(int year=1;year<=K;year++){
			spring();//봄
			summer();//여름
			fall();//가을
			winter();//겨울
		}
		countingTree();
		System.out.println(result);
	}

	private static void spring() {//양분먹고 나이증가, 어린나무부터, 양분없으면 life = false;
		for(int row=1;row<=N;row++){
			for(int col=1;col<=N;col++){
				int size = groundtrees[groundNumber[row][col]].treelist.size();
				if(size==0) continue;
				else if(size>0){
					Collections.sort(groundtrees[groundNumber[row][col]].treelist);//어린순 정렬
					for(int tree=0;tree<size;tree++) {
						int treeAge = groundtrees[groundNumber[row][col]].treelist.get(tree).age;
						int nutri = ground[row][col];
						if((nutri-treeAge)>=0){//양분이 충분
							ground[row][col] = nutri-treeAge;
							groundtrees[groundNumber[row][col]].treelist.get(tree).age = treeAge+1;//나이증가
						}
						else{//양분이 없음
							groundtrees[groundNumber[row][col]].treelist.get(tree).life = false;//죽음
						}
					}	
				}

			}
		}
	}
	private static void summer(){//죽은 나무가 양분으로 변함. 죽은 나무 나이 나누기 2가 양분으로 더해짐
		for(int row=1;row<=N;row++){
			for(int col=1;col<=N;col++){
				int size = groundtrees[groundNumber[row][col]].treelist.size();
				if(size==0) continue;
				else if(size>0){
					for(int tree=(size-1);tree>=0;tree--){//리스트 특성상 끝에서 지우자.
						if(groundtrees[groundNumber[row][col]].treelist.get(tree).life==false){		
							ground[row][col]+=(groundtrees[groundNumber[row][col]].treelist.get(tree).age/2);							
							groundtrees[groundNumber[row][col]].treelist.remove(tree);//삭제							
						}
					}	
				}
			}
		}
	}
	private static void fall() {//나무의 나이가 5의 배수면 8방향 번식.나이 1인 나무 추가
		for(int row=1;row<=N;row++){
			for(int col=1;col<=N;col++){
				int size = groundtrees[groundNumber[row][col]].treelist.size();
				if(size==0) continue;
				else if(size>0){
					for(int tree=0;tree<size;tree++) {
						if(groundtrees[groundNumber[row][col]].treelist.get(tree).age%5==0){//나이가 5의배수
							for(int dir=0;dir<8;dir++){//8방향 검사
								int nr = row+dr[dir];
								int nc = col+dc[dir];
								if(rangeCheck(nr,nc)){//영역 만족
									groundtrees[groundNumber[nr][nc]].treelist.add(new Tree(1));//애기 추가
								}
							}
						}
					}	
				}
			}
		}
	}

	private static void winter() {//땅에 양분 추가
		for(int row=1;row<=N;row++){
			for(int col=1;col<=N;col++){
				ground[row][col]+=nutrient[row][col];
			}
		}
	}
	private static void countingTree() {
		for(int row=1;row<=N;row++){
			for(int col=1;col<=N;col++){
				result += groundtrees[groundNumber[row][col]].treelist.size();
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=1 && nr<=N && nc>=1 && nc<=N) return true;
		return false;
	}
}
