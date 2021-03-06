package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 15:06
 * 게리맨더링 그냥 풀어보기
 * 인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차이의 최소값
 * 부등식 그대로 짜본다. 경계선을 그어준다.
 * 
 * 차근차근 예쁘게 
 * 
 * 선거구는 적어도 하나의 구를 포함하고 있어야한다.
 * 
 * ->5번으로 경계선을 그은 다음에 1,2,3,4 지역구를 나누려고 했는데
 * 문제에 써있는 부등식대로 지역구를 나누려니 5를 자꾸 덮어 썼다.
 * 
 * 해결 -> 
 * 1,2,3,4 로 지역구를 나눈 후 
 * 5로 경계선을 그어준 뒤
 * 5내부에있는 숫자를 전부 5로 바꾸고
 * 값을 구했다.
 * 
 * 에전에 푼 풀이를 보니 BFS로 경계선 그어서 풀었는데
 * 그 풀이보다 지금 풀이가 훨씬더 시간,메모리적으로 월등한 풀이다.
 */
public class 게리맨더링2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] city;
	static int[][] divideCity;
	static int[] people = new int[6];
	static int N;
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		divdeAndGetMinValue();
		System.out.println(answer);
	}
	private static void divdeAndGetMinValue() {
		for(int d1=1;d1<=(N-1);d1++) {
			for(int d2=1;d2<=(N-1);d2++){//경계선 길이 설정
				for(int x=0;x<N;x++) {//x가 행
					for(int y=0;y<N;y++){//y가 열
						//선정된 점에서  
						if(possible(x,y,d1,d2)){//경계선 나누기가 가능하면
							divideCity = new int[N][N];
							setArea(x, y, d1, d2);//선거구를 색칠함
							divide(x,y,d1,d2);//행, 열 , 거리
							setLastArea();//남은 영역에 5로 색칠하고
							updateAnswer();
						}
					}
				}
			}
		}
	}
	private static void updateAnswer(){
		people = new int[6];
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				people[divideCity[row][col]]+=city[row][col];
			}
		}
		int max = 0;
		int min = Integer.MAX_VALUE;
		for(int i=1;i<=5;i++) {
			max = Math.max(max,people[i]);
			min = Math.min(min,people[i]);
		}
		answer = Math.min(answer,(max-min));
	}
	private static void setLastArea(){
		for(int row=0;row<N;row++) {
			int startCol=-1;
			int endCol=-1;
			for(int col=0;col<N;col++){
				if(divideCity[row][col]==5) {
					if(startCol==-1) {
						startCol=col;
					}else {
						endCol=col;
					}
				}
			}
			if(startCol!=-1 && endCol!=-1){//5가 두개 발견되는 행에 대해서는 5로 채워줘야 한다.
				for(int col=startCol;col<=endCol;col++) {
					divideCity[row][col] = 5;
				}	
			}
			
		}
	}
	private static void setArea(int x, int y, int d1, int d2) {
		for(int row=0;row<(x+d1);row++) {
			for(int col=0;col<=y;col++) {
				divideCity[row][col] = 1;
			}
		}
		for(int row=0;row<=(x+d2);row++) {
			for(int col=y+1;col<N;col++) {
				divideCity[row][col] = 2;
			}
		}

		for(int row=(x+d1);row<N;row++) {
			for(int col=0;col<(y-d1+d2);col++) {
				divideCity[row][col] = 3;
			}
		}

		for(int row=(x+d2+1);row<N;row++) {
			for(int col=(y-d1+d2);col<N;col++) {
				divideCity[row][col] = 4;
			}
		}
	}
	private static void divide(int x, int y, int d1, int d2) {
		for(int i=0;i<=d1;i++) divideCity[x+i][y-i]=5;
		for(int i=0;i<=d2;i++) divideCity[x+i][y+i]=5;
		for(int i=0;i<=d2;i++) divideCity[x+d1+i][y-d1+i]=5;
		for(int i=0;i<=d1;i++) divideCity[x+d2+i][y+d2-i]=5;
	}
	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(divideCity[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
	private static boolean possible(int x, int y, int d1, int d2) {
		//x가 행 y가 열 
		if((x+d1)<N && (y-d1)>=0 && (x+d2)<N && (y+d2)<N&& (x+d2+d1)<N) return true;
		return false;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		city = new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				city[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		answer=Integer.MAX_VALUE;
	}
}
