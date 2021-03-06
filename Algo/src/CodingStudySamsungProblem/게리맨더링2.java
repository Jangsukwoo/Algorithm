package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
				pickStandard(d1,d2);
			}
		}
	}
	private static void pickStandard(int d1, int d2){//정해진 경계선 길이에 대해서 가능한 모든 시작점에 대해 시뮬레이션
		for(int x=0;x<N;x++) {//x가 행
			for(int y=0;y<N;y++){//y가 열
				//선정된 점에서  
				if(possible(x,y,d1,d2)){//경계선 나누기가 가능하면
					divideCity = new int[N][N];
					setArea(x, y, d1, d2);//선거구를 색칠함
					divide(x,y,d1,d2);//행, 열 , 거리
					setLastArea();//남은 영역에 5로 색칠하고
					updateAnswer();
				} else continue;//불가능하면 다음 점 선정
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
	private static void setArea(int x, int y, int d1, int d2){
		//각 선거구에 해당하는 area 넘버링
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++){
				if(row>=0 && row<(x+d1) && col>=0 && col<=y) divideCity[row][col]=1;
				else if(row>=0 && row<=(x+d2) && col>=(y+1) && col<N) divideCity[row][col]=2;
				else if(row>=(x+d1) && row<N && col>=0 && col<(y-d1+d2)) divideCity[row][col]=3;
				else if(row>=(x+d2+1) && row<N && col>=(y-d1+d2) && col<N) divideCity[row][col]=4;
			}
		}
	}
	private static void divide(int x, int y, int d1, int d2) {
		for(int i=0;i<=d1;i++) {
			divideCity[x+i][y-i]=5;
			divideCity[x+d2+i][y+d2-i]=5;
		}
		for(int i=0;i<=d2;i++) {
			divideCity[x+i][y+i]=5;
			divideCity[x+d1+i][y-d1+i]=5;
		}
	}
	private static boolean possible(int x, int y, int d1, int d2) {
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
