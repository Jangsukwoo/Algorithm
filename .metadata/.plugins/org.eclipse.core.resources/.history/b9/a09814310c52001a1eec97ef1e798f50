package CodingStudy;

import java.util.Scanner;
/*
 * dfs로 풀면 안되겠다..
 * c++로 코드 바꿔서 냈는데도 틀림 
 */
public class 출근경로 {
	static int w,h;
	static int path;
	static int[] dr = {0,1};
	static int[] dc = {1,0};//우,하
	public static void main(String[] args) {
		setData();
		getPath();
		System.out.println(path);
	}

	private static void getPath() {
		for(int dir=0;dir<2;dir++) {
			dfs(0,0,dir,true);//0,0에서 출발하고 교차 안꺾음			
		}
	}

	private static void dfs(int row, int col, int dir, boolean turn){
		if(!downCheck(row) || !rightCheck(col)) {
			return;//영역 넘어가면 안봄
		}
		if(row==(h-1) && col==(w-1)){
			path%=100000;
			path++;
			return;
		}
		if(turn==false){//안꺾어봤으면 꺾거나 직진한다.
			dfs(row+dr[(dir+1)%2],col+dc[(dir+1)%2],(dir+1)%2,true);//꺾는거
			dfs(row+dr[dir],col+dc[dir],dir,false);//직진하는거
		}else if(turn==true){//꺾인채로 왔으면 직진하고 꺾은거 초기화
			dfs(row+dr[dir],col+dc[dir],dir,false);
		}
	}

	private static boolean rightCheck(int nc){
		if(nc<w) return true;
		return false;
	}

	private static boolean downCheck(int nr) {
		if(nr<h) return true;
		return false;
	}

	private static void setData() {
		Scanner sc = new Scanner(System.in);
		w = sc.nextInt();
		h = sc.nextInt();
	}
}
