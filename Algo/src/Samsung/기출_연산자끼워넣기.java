package Samsung;

import java.util.HashSet;
import java.util.Scanner;

/*
 * 시작 450
 * int 최대 21억
 * 
 * max값이 음수일 수 도 있다는 것을 간과함... 반레 잡느라 시간 많이씀..ㅠ
 * 끝 607
 * 
 * 걸린시간 1시간17분
 * 
 */
public class 기출_연산자끼워넣기 {
	static int N;//수의 개수
	static int[] series;//수열
	static char[] operationData;
	static char[] operationSeries;
	static boolean[] visit;
	static StringBuilder makeOperationSeries = new StringBuilder();
	static HashSet<String> operationCase = new HashSet<String>();
	static int[] operatorCount = new int[4];// 0:+, 1:-, 2:x, 3:/
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		series = new int[N];
		operationSeries = new char[N-1];
		visit = new boolean[N-1];
		for(int i=0;i<N;i++) series[i] = sc.nextInt();
		for(int i=0;i<4;i++) {
			operatorCount[i] = sc.nextInt();
			if(operatorCount[i]>0){//연산자가 존재하면
				for(int j=0;j<operatorCount[i];j++) makeOperationSeries.append(i);
			}
		}
		
		operationData = makeOperationSeries.toString().toCharArray();
		permutation(0);
		
		System.out.println(max);
		System.out.println(min);
		
	}
	private static void permutation(int cnt) {
		if(cnt==(N-1)){
			String operatorSeries = String.valueOf(operationSeries);
			if(!operationCase.contains(operatorSeries)){//존재하지 않을 때만
				operationCase.add(operatorSeries);
				calculation();
			}
			return;
		}
		for(int i=0;i<(N-1);i++){
			if(visit[i]==false) {
				operationSeries[cnt] = operationData[i];
				visit[i] = true;
				permutation(cnt+1);
				visit[i] = false;
			}
			 
		}
	}
	private static void calculation(){
		int value=0;
		for(int o=0;o<(N-1);o++){
			switch (operationSeries[o]){
			case '0': // +
				if(o==0) value = series[o]+series[o+1];
				else value = value + series[o+1];
				break;
			case '1': // -		
				if(o==0) value = series[o]-series[o+1];
				else value = value - series[o+1];
				break;
			case '2': // *	
				if(o==0) value = series[o]*series[o+1];
				else value = value*series[o+1];
				break;
			case '3': // /	
				if(o==0) value = series[o]/series[o+1];
				else {
					if(value<0){//음수면
						value = Math.abs(value);
						value = value/series[o+1];
						value = 0-value;
					}
					else value = value/series[o+1];
				}
				break;
			}
		}
		//System.out.println("value는"+value);
		max = Math.max(max,value);
		min = Math.min(min,value);
	}
}
