package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;



/*
 * +,-,*,/
 * 
1
5
2 1 0 1
3 5 3 7 9

1
6
4 1 0 0 
1 2 3 4 5 6

1
5
1 1 1 1
9 9 9 9 9 
 */
public class 숫자만들기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[] operatorCnt;
	static int[] numbers;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static HashSet<String> set;
	public static void main(String[] args) throws NumberFormatException, IOException{
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			setData();
			nPr(0,"");
			System.out.println("#"+testcase+" "+(max-min));
		}
	}
	private static void nPr(int depth, String oper) {
		if(depth==(N-1)){
			if(set.contains(oper)) return;
			else {
				set.add(oper);
				calculator(oper);	
			}
			return;
		}
		for(int i=0;i<4;i++) {
			if(operatorCnt[i]>0) {
				operatorCnt[i]--;
				switch (i) {
				case 0:
					nPr(depth+1,oper+"+");
					break;
				case 1:
					nPr(depth+1,oper+"-");
					break;
				case 2:
					nPr(depth+1,oper+"*");
					break;
				case 3:
					nPr(depth+1,oper+"/");
					break;
				}
				operatorCnt[i]++;
			}
		}
	}
	private static void calculator(String oper){
		int result =0;
		int leftValue = numbers[0];
		int rightValue = numbers[1];
		for(int i=0;i<(N-1);i++){
			if(i>0) leftValue = result;
			rightValue = numbers[i+1];
			switch (oper.charAt(i)){
			case '+':
				result = leftValue+rightValue;
				break;
			case '-':
				result = leftValue-rightValue;
				break;
			case '*':
				result = leftValue*rightValue;
				break;
			case '/':
				result = leftValue/rightValue;
				break;
			}
		}
		max = Math.max(result,max);
		min = Math.min(result,min);
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		operatorCnt = new int[4];
		set= new HashSet<String>();
		numbers = new int[N];
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<4;i++) operatorCnt[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0;i<4;i++) {
			for(int j=0;j<operatorCnt[i];j++) {

			}
		}
	}
}
