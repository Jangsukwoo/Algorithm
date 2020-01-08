package SDS;
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
import java.util.HashMap;
import java.util.TreeSet;

public class 생태학_3일차 {
	static HashMap<String, Double> dictionary = new HashMap<>();
	static TreeSet<String> dictionaryTree = new TreeSet<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static double entireSize;
	public static void main(String[] args) throws IOException {
		while(true) {
			String name = br.readLine();
			if(name.equals("")) break;
			if(dictionary.containsKey(name)) {
				double cnt = dictionary.get(name);
				dictionary.put(name,cnt+1);
			}else dictionary.put(name,1.0);
			dictionaryTree.add(name);
			entireSize++;
		}
		double treeSize = dictionaryTree.size();
		for(int i=0;i<treeSize;i++) {
			String popname = dictionaryTree.pollFirst();
			double portion = (dictionary.get(popname)/entireSize);
			portion = Math.round(portion*1000000)/10000.0;
			bw.write(popname+" "+portion+"\n");
		}
		bw.flush();
		bw.close();
	}
}
