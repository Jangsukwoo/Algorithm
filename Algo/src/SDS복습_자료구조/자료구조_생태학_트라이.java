package SDS복습_자료구조;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
/*
 * 1000000그루의 나무가 주어짐
 * log(2)1000000 = 약 19
 * 
 * 트라이로 풀기!!
 * 
 * insert로 트라이 트리 만들어준 뒤
 * dfs로 탐색하면서 출력
 */
public class 자료구조_생태학_트라이 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static Trie trie = new Trie();
	static StringTokenizer st;
	static double entireSize;
	static class TrieNode{
		TrieNode[] letter = new TrieNode[128]; 
		boolean isHit;//dfs탐색을 위한 flag
		boolean isEnd;
		double cnt;//개수
	}
	
	
	static class Trie{
		TrieNode root;
		public Trie() {
			root = new TrieNode();
		}
		public void insert(String species){
			TrieNode current = root;
			for(int i=0;i<species.length();i++){
				
				int ascii = species.charAt(i);
				
				if(current.letter[ascii]==null){//null이면
					current.letter[ascii] = new TrieNode();
				}//없으면 무조건 만드니까 if 조건문 걸고
				current = current.letter[ascii];//문자열 연결해준다.
				//없으면 만들어주기때문에 무조건 다음으로 연결이 된다.
				
			}//문자열의 맨 끝 글자까지 다 처리 했으면
			current.isEnd = true;
			current.cnt+=1;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		setPrintData();
		bw.flush();
		bw.close();
	}
	private static void setPrintData() throws IOException {
		TrieNode current = trie.root;
		for(int i=0;i<128;i++) {
			if(current.letter[i]!=null){
				char character = (char) i;
				dfs(current.letter[i],Character.toString(character));
			}
		}

	}
	private static void dfs(TrieNode current,String letter) throws IOException{
		if(current.isEnd) {
			double portion = current.cnt;
			//String stringportion = String.format("%.4f", portion/entireSize*100);
			//위에거로하면 맞는다.. 원인이 뭘까
			portion /=entireSize;
			portion = Math.floor(portion*100000000);
			portion/=1000000;
			bw.write(letter+" "+portion+"\n");
			return;
		}
		for(int i=0;i<128;i++) {
			if(current.letter[i]!=null){
				char character = (char) i;
				dfs(current.letter[i],letter+Character.toString(character));
			}
		}
	}
	private static void setData() throws IOException {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()){
			String species = sc.nextLine();
			if(species.length()==0 || species==null) break;
			else {
				trie.insert(species);
				entireSize++;
			}
		}
	}
}
