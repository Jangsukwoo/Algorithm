package SDS_WEEK1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 어떤 데이터의 상태가 있고
 * 찾고자 하는 수가 있는지 탐색! <- 이분탐색
 */
public class 수찾기_1일차 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N;
	static int[] num;
	static int M;
	static int target;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		num = new int[N];
		for(int i=0;i<N;i++) num[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(num);//우선 정렬
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			target = Integer.parseInt(st.nextToken());
			binarySearch();
		}
		bw.flush();
		bw.close();
	}
	private static void binarySearch() throws IOException {
		int left = 0;
		int right = N-1;
		while(left<=right){
			int mid = (left+right)/2; //중간값
			if(num[mid]>target) right = mid-1;
			else if(num[mid]<target) left = mid+1;
			else {//타겟값이면
				bw.write("1"+"\n");
				return;
			}
		}
		bw.write("0"+"\n");
	}
}
