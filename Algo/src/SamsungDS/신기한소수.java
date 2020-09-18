package SamsungDS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 신기한소수 {
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static String start_string = "2";
	static String end_string = "9";
	static int[] startArray = new int[] {2,3,4,5,7};
	static boolean impossible = false;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		for(int s=0;s<startArray.length;s++) {
			start_string = Integer.toString(startArray[s]);
			end_string = start_string+"9";
			for(int i=1;i<=(N-1);i++) {
				start_string+="0";
			}
			for(int i=2;i<=(N-1);i++) {
				end_string+="9";
			}
			int start = Integer.parseInt(start_string);
			int end = Integer.parseInt(end_string);
			for(int num = start;num<=end;num++) {
				if(num%2==0) continue; //짝수로 나눠지는 거르고
				impossible = false;
				for(int pos = 1; pos<=N;pos++){
					String num_string = Integer.toString(num);
					String checkNum_string = num_string.substring(0,pos);
					int checkNum = Integer.parseInt(checkNum_string);
					if(checkNum!=2 && checkNum%2==0) {
						impossible = true;
						break;
					}
					for(int i=3;i<=Math.sqrt(checkNum);i++) {
						if(checkNum%i==0){
							impossible = true;//하나라도 나눠떨어지는게 있으면 true
							break;
						}
					}
					if(impossible) break;
				}
				if(impossible==false) System.out.println(num);
			}
		}
	}
}
