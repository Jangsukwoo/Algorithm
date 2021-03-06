package CodingStudySamsungProblem;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/*
 * 3x3 배열 A
 * 연산의 형태는 두개
 * R : 모든 행에 대해 정렬 (행의개수>=열의개수)
 * C : 모든 열에 대해 정렬 (행의개수 <열의 개수)
 * 
 * 매번 연산마다 완전히 새로운 배열이 되는데
 * 그 새로운 배열의 값을 정하는 규칙은 다음과 같다.
 * 숫자를 먼저 쓰고 출현빈도수를 쓰는데 
 * 기존 데이터가 3,1,1인경우
 * 3이 1번 출현, 1이 2번 출현했다.
 * 숫자를 먼저 쓸건데 3과 1 중에 1이 출현빈도수가 더 높으므로
 * 3의 1을 먼저 쓰고
 * 출현빈도수인 1의 2를 써서 결국
 * 3 1 1 2 라는 새로운 배열이 탄생.
 * 즉, 3이 1개 출현, 1이 2개 출현  = 3 1 1 2 
 * 
 * 백준 문제상에서 
 * Arr[row][col] 값이 100이 넘는 조건으로 해석하고 계속 쓸데없는 디버깅을 했다...
 * 조건 문장이 모호함.. 부들부들
 * 시간이 100초가 넘어가면 -1을 출력하도록 되어있어야 맞는듯하다.
 */

public class 이차원배열과연산 {
	static int r,c,k;
	static int rowSize,colSize;
	static int[][] matrix;
	static int time;
	static boolean find;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		r = sc.nextInt();
		c = sc.nextInt();
		k = sc.nextInt();
		rowSize=colSize=3;
		matrix = new int[rowSize][colSize];//초기값
		for(int row=0;row<3;row++){
			for(int col=0;col<3;col++) {
				matrix[row][col] = sc.nextInt();
			}
		}
		while(true){
			if(time>100) break;
			if(r<=rowSize && c<=colSize){//찾으려고하는 값이 영역 내에 있으면 검사
				if(matrix[r-1][c-1]==k){
					find = true;
					break;
				}
				if(matrix[r-1][c-1]>100) break;
			}
			if(rowSize>=colSize){//행에 대헤 정렬 연산 수행
				sortRow();
			}else{//열에 대해 정렬 연산 수행 
				sortCol();
			}
			time++;
		}
		if(find) System.out.println(time);
		else System.out.println("-1");
	}

	private static void sortRow() {
		int maxColSize=0;
		int[][] tempMap = new int[100][100];
		for(int row=0;row<rowSize;row++){//각 행에대해서
			int[] cnt = new int[150];
			ArrayList<int[]> number = new ArrayList<int[]>();
			for(int col=0;col<colSize;col++){//빈도수 카운트
				if(matrix[row][col]!=0){
					cnt[matrix[row][col]]++;
				}
			}
			for(int i=1;i<150;i++){ //빈도값이 존재하면 리스트에 넣고
				if(cnt[i]>0){
					number.add(new int[] {i,cnt[i]});
				}	
			}
			Collections.sort(number, new Comparator<int[]>(){//빈도수에대해 정렬한다.
				@Override
				public int compare(int[] o1, int[] o2){
					if(o1[1]==o2[1]) Integer.compare(o1[0],o2[0]);//빈도수가 같으면 숫자가 더 큰것으로
					return Integer.compare(o1[1],o2[1]);
				}
			});
			if(maxColSize<number.size()) maxColSize= number.size();//새로 배열에 표현될 값에 대한 최대 사이즈 업데이트
			int col=0;
			for(int i=0;i<number.size();i++){ //임시 맵에 쓰고
				int[] value = number.get(i);
				tempMap[row][col] = value[0];
				tempMap[row][col+1] = value[1];
				col+=2;
				if(col==100) break;//100개까지
			}
		}
		colSize = (maxColSize*2); 
		matrix = new int[rowSize][colSize]; //새롭게 배열 만든다.
		for(int row=0;row<rowSize;row++){//복사
			for(int col=0;col<colSize;col++){
				matrix[row][col] = tempMap[row][col];
			}
		}
	}
	private static void sortCol() { //sortRow와 똑같이
		int maxRowSize=0;
		int[][] tempMap = new int[100][100];
		for(int col=0;col<colSize;col++){//각 열에대해서
			int[] cnt = new int[101];
			ArrayList<int[]> number = new ArrayList<int[]>();
			for(int row=0;row<rowSize;row++){//빈도수 카운트
				if(matrix[row][col]!=0){
					cnt[matrix[row][col]]++;
				}
			}
			for(int i=1;i<101;i++){
				if(cnt[i]>0){
					number.add(new int[] {i,cnt[i]});
				}	
			}
			Collections.sort(number, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2){
					if(o1[1]==o2[1]) Integer.compare(o1[0],o2[0]);
					return Integer.compare(o1[1],o2[1]);
				}
			});
			if(maxRowSize<number.size()) maxRowSize= number.size();
			int row=0;
			for(int i=0;i<number.size();i++){
				int[] value = number.get(i);
				tempMap[row][col] = value[0];
				tempMap[row+1][col] = value[1];
				row+=2;
				if(row==100) break;//100개까지
			}
		}
		rowSize = (maxRowSize*2);
		matrix = new int[rowSize][colSize];
		for(int row=0;row<rowSize;row++) {
			for(int col=0;col<colSize;col++){
				matrix[row][col] = tempMap[row][col];
			}
		}
	}
}
