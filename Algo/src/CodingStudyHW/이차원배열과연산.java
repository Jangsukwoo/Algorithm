package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 20:13~
 * 차근차근 ㅎㅎ
 * 예쁘게 짜보자
 * 
 * 크기 3 by 3
 * 1. 행의 개수가 열의 개수보다 같거나 클때
 * R연산 : 모든 행에 대해 정렬
 * 2. 행의 개수가 열의 개수보다 작을 때 
 * C연산 : 모든 열에 대해 정렬
 * 
 * 수의 등장 회수가 커지는 순서로. 그러한 수자 여러가지면 수가 커지는 순으로 정렬.
 * 
 * 이 연산을 통하면
 * 행과 열의 크기가 변하게 되는데
 * 이때 가장 큰 행, 가장 큰 열 기준으로 배열의 크기를 재할당
 * 수를 정렬할 때 0은 무시한다.
 * 또한, 행과 열의 크기가 100을 넘어가는 경우
 * 처음 100개 제외한 나머지 버림
 * A[r][c] 값이 k가 되기 위한 최소 시간은?
 * 단, 값이 100을 넘어가는 경우 -1 출력하고 끝
 * 
 * 21:07 끝
 * 
 * while(time>100) 이라고 해놓고 엉뚱한데서 자꾸 오류를 찾았다..이걸로 한 20분 까먹은듯 ㅡ3 ㅡ....
 * 
 */
public class 이차원배열과연산 {
	static int r,c,k;
	static int Rsize,Csize;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] array;
	static int ans;
	static class Data implements Comparable<Data>{
		int number;
		int frequency;
		public Data(int number, int frequency) {
			this.number = number;
			this.frequency = frequency;
		}
		@Override
		public int compareTo(Data o){
			if(this.frequency==o.frequency) return Integer.compare(this.number,o.number);
			return Integer.compare(this.frequency,o.frequency);
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		calculation();
		System.out.println(ans);
	}
	private static void calculation() {
		int time=0;
		while(time<=100){
			if(findCheck(time)) return;
			else {
				if(Rsize>=Csize) calculation_R();
				else calculation_C();
			}
			time++;
		}
		
	}
	private static void calculation_R(){
		ArrayList<Data>[] dataList= new ArrayList[Rsize];
		int maxSize=0;
		for(int i=0;i<Rsize;i++) dataList[i] = new ArrayList<Data>();
		
		for(int row=0;row<Rsize;row++){
			int[] frequencyArray = new int[101];
			for(int col=0;col<Csize;col++){
				frequencyArray[array[row][col]]++;
			}
			setDatalist(dataList,row,frequencyArray);
			maxSize = Math.max(maxSize,(dataList[row].size()*2));
		}//빈도 수 전부 구함
		array = new int[Rsize][maxSize];
		Csize = maxSize;
		for(int row=0;row<Rsize;row++){
			int col=0;
			for(int i=0;i<dataList[row].size();i++){
				if(col>99) {
					break;
				}
				Data data = dataList[row].get(i);
				array[row][col++] = data.number;
				array[row][col++] = data.frequency;
			}
		}
	}
	private static void calculation_C() {
		ArrayList<Data>[] dataList= new ArrayList[Csize];
		int maxSize=0;
		for(int i=0;i<Csize;i++) dataList[i] = new ArrayList<Data>();
		for(int col=0;col<Csize;col++){
			int[] frequencyArray = new int[101];
			for(int row=0;row<Rsize;row++){
				frequencyArray[array[row][col]]++;
			}
			setDatalist(dataList,col,frequencyArray);
			maxSize = Math.max(maxSize,(dataList[col].size()*2));
		}//빈도 수 전부 구함
		array = new int[maxSize][Csize];
		Rsize = maxSize;
		for(int col=0;col<Csize;col++){
			int row=0;
			for(int i=0;i<dataList[col].size();i++){
				if(row>99) {
					break;
				}
				Data data = dataList[col].get(i);
				array[row++][col] = data.number;
				array[row++][col] = data.frequency;
			}
		}
	}
	
	private static void setDatalist(ArrayList<Data>[] dataList, int idx, int[] frequencyArray) {
		for(int i=1;i<=100;i++) {
			if(frequencyArray[i]>=1){
				dataList[idx].add(new Data(i,frequencyArray[i]));
			}
		}
		Collections.sort(dataList[idx]);
	}


	private static boolean findCheck(int time) {
		if(r<Rsize && c<Csize){//확인 가능하고
			if(array[r][c]==k) {//찾는 값인 경우
				ans=time;
				return true;
			}
		}
		return false; //확인 불가면 더 돌려야하니까 false
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		k = Integer.parseInt(st.nextToken());
		Rsize = 3;
		Csize = 3;
		array= new int[3][3];
		for(int row=0;row<3;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<3;col++) {
				array[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		ans=-1;
	}
}
