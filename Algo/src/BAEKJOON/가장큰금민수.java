package BAEKJOON;

import java.util.ArrayList;
import java.util.Scanner;

public class 가장큰금민수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String data = " ";
		char[] num;
		boolean numCheck;
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i=4;i<=N;i++){ //다 구한다.
			data = String.valueOf(i); //스트링으로 바꾸고
			numCheck = false; //만족체크 변수
			num = data.toCharArray(); //캐릭터로 바꿈
			for(int j=0;j<num.length;j++) {//조사
				if(num[j]!='4' && num[j]!='7'){//4도 아니고 7도 아니면 그만
					numCheck =true;
					break;
				}
			}
			if(numCheck==false) result.add(i); //금민수인경우에 리스트에넣기
		}
		System.out.println(result.get(result.size()-1));
	}
}
