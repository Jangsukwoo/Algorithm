package SDS복습_자료구조;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.TreeSet;

public class 자료구조_생태학_해시맵트리셋{
	static HashMap<String, Double> dictionary = new HashMap<>();
	static TreeSet<String> dictionaryTree = new TreeSet<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static double entireSize;
	public static void main(String[] args) throws IOException {
		while(true) {
			String name = null;
            name = br.readLine();
			if(name==null) break;
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
			double portion = dictionary.get(popname);
			bw.write(popname+" "+String.format("%.4f", portion/entireSize*100)+"\n");
		}
		bw.flush();
		bw.close();
	}
}