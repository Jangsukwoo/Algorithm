package SDS_WEEK2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 부분증가수열이라는건 연속이 아니여도 커지는 부분 수열을 말함
 * A<=100만 -> O(N^2)으로는 절대 풀 수 없다. O(NlogN)으로 줄여야함
 * -> LIS와 이분탐색으로 코드를 구성하면 된다.
 * 수열을 이루는 수 A의 값은
 * -10억~10억
 * 
 * i번째 위치에서 di에 어떤 값을 저장해야하는가?
 * 
 * 기존 N^2 방식의 구현은 앞에서부터 알아나가는 방식임
 * 
 * 이분탐색으로 LIS를 구하는 법
 * step 1. O(N)으로 수열의 처음부터 확인한다.
 * step 2. LIS를 유지하기 위한 최적의 위치에 수를 삽입한다. (lower_bound를 찾는다.)
 * 
 * 
 * 
 * -> 
 * 어레이리스트를 하나 만들어서 나올수 없는 가장 작은 값을 삽입해놓는다.
 * 수열의 한 숫자를 볼 때마다 어레이리스트의 맨 뒤의 원소와 비교하여
 * 수열의 원소가 더 클경우 값을 어레이리스트에 넣어준 뒤 LIS값을 +1 한다.
 * 수열의 원소가 더 작을 경우 lower_bound를 이용해서 
 * 최적의 자리를 찾고 그 자리의 값을 해당 이 값으로 교체해버린다.
 * 
 * Ex) 현재 어레이리스트에
 * 10 40 70으로 저장되어 있을 때 
 * 확인하고자 하는 수열의 값이 50 일 떄는
 * 70보다 작으므로 최적의 자리를 찾아야한다. ->lowerbound
 * 
 * 최적의 자리는 70이므로
 * 70을 50으로 교체해버린다.
 * 
 */
public class 동적계획법_4일차_가장긴증가하는부분수열3 {
	static final int INF = Integer.MAX_VALUE;
	static int N;
	static int[] serires;
	static int[] seq;
	static ArrayList<Integer> dp;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		dataSetting();
		output();
	}
	private static void output() throws IOException {
		bw.write(dp.size()+"\n");
		bw.flush();
		bw.close();
	}
	private static void dataSetting() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		serires = new int[N];
		st = new StringTokenizer(br.readLine());
		dp = new ArrayList<>();
		dp.add(-INF);
		for(int i=0,lastIdx,lowerBound;i<N;i++) {
			serires[i] = Integer.parseInt(st.nextToken());
			if(i>0) {
				lastIdx = dp.size()-1;
				if(dp.get(lastIdx)<serires[i]) {
					dp.add(serires[i]);//수열의 원소가 더 큰 경우
				}
				else {//수열의 원소가 더 작은 경우
					lowerBound = binarySearch(serires[i]);
					dp.set(lowerBound,serires[i]);
				}
			}else {
				dp.set(0,serires[i]);
			}
		}	
	}
	private static int binarySearch(int target){
		int left = 0;
		int mid=0;
		int right = dp.size()-1;
		while(left<right){
			mid = (left+right)/2;
			if(dp.get(mid)>=target){//찍은값이 더 클 때
				right = mid;
			}else if(dp.get(mid)<target){//찍은 값이 작을 떄
				left=mid+1;
			}
		}//오른쪽으로 밀고나가야 가장 끝에 right를 발견한다. -> lowerBound
		return right;
	}

}
