package CodingStudy;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * 20191206 스터디
 * 규칙을 가진 수열 배열을 하나 만들고
 * 답이랑 비교하기
 */
public class 모의고사 {
	static int[] series1 = {1,2,3,4,5}; //A규칙
	static int[] series2 = {2,1,2,3,2,4,2,5}; //B규칙
	static int[] series3 = {3,3,1,1,2,2,4,4,5,5}; //C규칙 
	static int[] A;
	static int[] B;
	static int[] C;
	static int Amax,Bmax,Cmax;
	static int Max;
	static ArrayList<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] {1,2,3,4,5})));
	}
	public static int[] solution(int[] answers) {
		int[] answer = {};
		int size = answers.length;
		makeSeries(size);
		for(int i=0;i<size;i++) { //정답검사
			if(answers[i]==A[i]) Amax++;
			if(answers[i]==B[i]) Bmax++;
			if(answers[i]==C[i]) Cmax++;
		}
		//Max값 찾기
		if(Max<=Amax) Max = Amax;
		if(Max<=Bmax) Max = Bmax;
		if(Max<=Cmax) Max = Cmax;
		//Max값이랑 같은 숫자 찾기
		if(Max==Amax) list.add(1);
		if(Max==Bmax) list.add(2);
		if(Max==Cmax) list.add(3);
		answer = new int[list.size()];
		for(int i=0;i<answer.length;i++) {
			answer[i] = list.get(i);
		}
		return answer;
	}
	private static void makeSeries(int size){
		A = new int[size];
		B = new int[size];
		C = new int[size];
		for(int i=0;i<size;i++){
			A[i] = series1[i%series1.length];
			B[i] = series2[i%series2.length];
			C[i] = series3[i%series3.length];
		}
	}

}
