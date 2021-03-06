package SDS_WEEK1;
/*
 * 자료구조 잘 써서 풀면 되는줄 알았는데
 * 
 * 트라이로 푸는거였다
 * 
 * 문제 잘못건드렸다다다아아아아다ㅏㅇ다
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeMap;

public class 생태학_3일차 {
	static TreeMap<String, Integer> dictionary = new TreeMap();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int entireSize;
	public static void main(String[] args) throws IOException {
		while(true) {
			String name = br.readLine();
			
			if(name.equals("")) break; //EOF
			
			if(dictionary.containsKey(name)){   //포함되어있으면 
				int cnt = dictionary.get(name); 
				dictionary.put(name,cnt+1); //카운팅
			}
			
			else dictionary.put(name,1); //포함 안되어있으면 사전에 추가 
	
			entireSize++; //전체 종 수 
		}//입력
		
		
		
		for(String popname : dictionary.keySet()) { //키 집합 돌면서 종 이름 하나씩 확인
			
			double portion =dictionary.get(popname);
			
			bw.write(popname+" "+String.format("%.4f", portion/entireSize*100)+"\n"); 
		}
		bw.flush();
		bw.close();
	}
}