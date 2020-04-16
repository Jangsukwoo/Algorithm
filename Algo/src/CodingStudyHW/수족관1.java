package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * 세로,가로 범위 4만 by 4만
 * 제일 높은 위치부터 물을 빼자 
8
0 0 
0 5
1 5
1 3
2 3
2 4
3 4
3 0
1
0 5 1 5

8
0 0 
0 3 
1 3 
1 4 
2 4
2 3 
3 3 
3 0 
1
1 4 2 4

16
0 0
0 8
1 8
1 7
3 7
3 11
5 11
5 9
7 9
7 0
9 0
9 8
11 8
11 7
15 7
15 0
1
5 9 7 9

14
0 0
0 5
1 5
1 3
2 3
2 4
3 4
3 2
5 2
5 4
6 4
6 3
8 3
8 0
1
0 5 1 5

16
0 0
0 8
1 8
1 7
3 7
3 11
5 11
5 9
7 9
7 10
9 10
9 8
11 8
11 7
15 7
15 0
2
11 7 15 7
1 7 3 7

14
0 0
0 5
1 5
1 3
2 3
2 4
3 4
3 2
5 2
5 4
6 4
6 3
8 3
8 0
1
0 5 1 5


4
0 0 
0 8 
10 8 
10 0
1
0 8 10 8

전처리랑
자료구조를 어떻게 할지랑
누수되는 물의양을 판단하는게 너무 어려웠다..
 */
public class 수족관1 {
	static int N,holeCnt;//꼭지점 개수, 구멍 개수
	static int totalWater;
	static int remainWater;
	static Hole[] holes;
	static boolean[] wall;
	static int[] waterHeight = new int[40001];
	static int[] leakHeight = new int[40001];
	static int maxWallSize;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static class Hole{
		int leftX;
		int rightX;
		int height;
		public Hole(int leftX, int rightX, int height) {
			this.leftX = leftX;
			this.rightX = rightX;
			this.height = height;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		heightSorting();
		leakWater();
		System.out.println(remainWater);
	}
	private static void leakWater() {
		for(int i=0;i<holeCnt;i++) { //구멍 개수만큼
			
			int currentHeight = holes[i].height;
			int currentLeftX = holes[i].leftX;
			for(int j=currentLeftX;j>=0;j--) {
				if(currentHeight>waterHeight[j]){//점점 높아지면 높아지는 높이를 저장한다. 낮아지는 경우에 대해서는 갱신하지 않음.
					currentHeight = waterHeight[j];
				}
				if(leakHeight[j]<currentHeight) {//누수량이 기존에 아는것보다 더작으면 넣어준다.
					leakHeight[j]=currentHeight;
				}
			}
			currentHeight = holes[i].height; //다시 갱신해주고 오른쪽 확인
			for(int j=currentLeftX;j<maxWallSize;j++) {
				if(currentHeight>waterHeight[j]) {
					currentHeight = waterHeight[j];
				}
				if(leakHeight[j]<currentHeight){
					leakHeight[j]=currentHeight;
				}
			}
		}
		
		for(int j=0;j<maxWallSize;j++) {
			remainWater+=(waterHeight[j]-leakHeight[j]);
		}
	}
	private static void heightSorting() {
		Arrays.sort(holes , new Comparator<Hole>() {
			@Override
			public int compare(Hole o1, Hole o2) {
				return Integer.compare(o1.height,o2.height);
			}
		});
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		int maxWall=0;
		int currentX = 0;
		int maxCurrentY = 0;
		int beforeX=0;
		int beforeY=0;
		for(int i=1,x,y;i<=(N/2);i++){ //전체 물량 구하기
			st = new StringTokenizer(br.readLine());
			int firstX = Integer.parseInt(st.nextToken());
			int firstY = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int secondX = Integer.parseInt(st.nextToken());
			int secondY = Integer.parseInt(st.nextToken());
			currentX = firstX;
			maxCurrentY = Math.min(firstY,firstY);
			if(i==0) {
				beforeY = secondY;
				beforeX = secondX;
				waterHeight[i-1] = maxCurrentY;
			}
			if(i>0){
				int width = currentX-beforeX;
				totalWater+=(beforeY*width);
				for(int j=beforeX+1;j<=currentX;j++){
					waterHeight[j-1] = beforeY;
				}
				beforeX = firstX;
				beforeY = secondY;
			}
			maxWallSize = Math.max(secondX, maxWallSize);	
		}
		holeCnt = Integer.parseInt(br.readLine());
		holes = new Hole[holeCnt];
		wall = new boolean[maxWall+1];
		for(int i=0,leftX,leftY,rightX,rightY;i<holeCnt;i++){
			st = new StringTokenizer(br.readLine());
			leftX = Integer.parseInt(st.nextToken());
			leftY = Integer.parseInt(st.nextToken());
			rightX = Integer.parseInt(st.nextToken());
			rightY = Integer.parseInt(st.nextToken());
			holes[i] = new Hole(leftX,rightX,rightY);
		}
	}
}
