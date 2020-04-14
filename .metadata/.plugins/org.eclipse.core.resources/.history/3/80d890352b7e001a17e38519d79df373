package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class K번째수 {
	static ArrayList<Integer> numberlist = new ArrayList<Integer>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		String read = br.readLine();
		int N,K;
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			int number = Integer.parseInt(st.nextToken());
			numberlist.add(number);
		}
		Collections.sort(numberlist);
		System.out.println(numberlist.get(K-1));
	}
}
 
/* C++ 코드
#include <iostream>
#include <algorithm>
using namespace std;
 
int N, K;
int arr[5000001];
 
int main(int argc, const char * argv[]) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);cout.tie(NULL);
    cin >> N >> K;
    for(int i = 0; i < N; i++)
        cin >> arr[i];
    
    sort(arr, arr + N);
    
    cout << arr[K - 1] << '\n';
    
    return 0;
}
 */

 