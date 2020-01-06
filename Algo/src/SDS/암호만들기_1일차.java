package SDS;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
 * 최소 한개의 모음 최소 두개의 자음
 */
public class 암호만들기_1일차 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int L,C;
	static char[] data;
	static char[] passward;
	static HashSet<Character> vowelSet = new HashSet<>();//모음집합
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		data = new char[C];
		passward = new char[L];
		setHashSet();
		for(int i=0;i<C;i++)  {
			data[i] = st.nextToken().charAt(0);
		}		
		Arrays.sort(data);
		nCr(0,0);//n = C, r = L
		bw.flush();
		bw.close();
		
	}
	private static void setHashSet() {
		for(int i=0;i<26;i++) {
			char cha = (char) ('a'+i);
			if(cha=='a' || cha=='e' || cha=='i' || cha=='o' || cha=='u') vowelSet.add(cha);
		}
	}
	private static void nCr(int idx, int depth) throws IOException {
		if(depth==L) {
			if(check()) bw.write(String.valueOf(passward)+"\n");
			return;
		}
		for(int i=idx;i<C;i++){
			passward[depth] = data[i];
			nCr(i+1,depth+1);
		}
	}
	private static boolean check() {
		int consonantCount = 0;
		int vowelCount = 0;
		for(int i=0;i<L;i++){
			if(vowelSet.contains(passward[i])) vowelCount++;
			else consonantCount++;
			if(consonantCount>=2 && vowelCount>=1) return true;
		}
		return false;
	}
}
