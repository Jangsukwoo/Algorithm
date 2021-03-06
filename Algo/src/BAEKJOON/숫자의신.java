package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
/*
3 5
1000000000
1000000000
999999999
output

9999999991000000000100000000010000000001000000000
 */
public class 숫자의신 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,K;
	static int max;
	static String[] numlist;
	static String answer ="";
	public static void main(String[] args) throws IOException {
		setData();
		getMaxValue();
		System.out.println(answer);
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		numlist = new String[K];
		for(int i=0;i<K;i++) {
			String sv = br.readLine();
			int value = Integer.parseInt(sv);
			/*
			 * 입력받으며 K개의 숫자들 중 가장 큰 숫자를 max에 저장
			 */
			if(max<value) max = value;
			numlist[i] = sv;
		}
		
		/*
		 * 두 String 숫자를 앞으로 분인것과 뒤로 붙인것을 비교하는 정렬 기준을 세운 뒤 정렬 
		 */
		//Collection Sort에서 Array Sort로 변경
		Arrays.sort(numlist,new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String o=o1+o2;
				return -o.compareTo(o2+o1);
			}
		});
	}

	private static void getMaxValue() { 
		
		/*
		 * 입력받을 때 구한 K개의 숫자중 가장 큰 숫자가 자리수도 크고(붙이게되면 가장 길어지고) 가장 큰 숫자이므로 
		 * 해당 max값에 해당하는 숫자가 나타난 위치부터 복사해서 이어 붙인다.
		 * 여기서 boolean을 둔 이유는
		 * 문제에서 숫자가 중복으로 들어올 수 있다는 조건 떄문에
		 * K개의 숫자 중 2222,2222로 중복으로 들어온다면 또 다시 이어붙이게 되니 이를 방지하기 위함이다. 
		 */
		boolean flag=false;
		for(int i=0;i<K;i++) {
			answer+=numlist[i];
			if(max==Integer.parseInt(numlist[i]) && flag==false){
				flag = true;
				for(int j=0;j<(N-K);j++) {
					answer+=numlist[i];
				}
			}
		}
	}
}
