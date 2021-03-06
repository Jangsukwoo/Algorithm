package CodingAcademy;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
 * inflate + dflate 활용해보기
 */
public class 단어퍼즐 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static char[][] puzzle = new char[5][5];
	static boolean[][] visit = new boolean[5][5];
	static StringTokenizer st;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1}; //8방향
	static int answer;
	static String[] wordlist;
	static Trie Trie = new Trie();
	static boolean findFlag;
	static class TrieNode{
		TrieNode[] children = new TrieNode[26];//26개의 알파벳 하나하나의 인덱스를 뜻하는 배열
	}
	static class Trie{ //트라이, 문자검색
		TrieNode root = new TrieNode(); //가장 루트는 빈문자열
		void insert(String word){//어떤 단어를 받아서 처리
			TrieNode current = root;
			for(int i=0;i<word.length();i++){//들어온 단어의 길이만큼 첫글자부터 확인
				int wordIndex = word.charAt(i)-'A'; //글자 알파벳의 인덱스
				if(current.children[wordIndex]==null){//글자 알파벳을 확인해 봤을 때 null이라는건 존재하지않는다는것
					current.children[wordIndex] =new TrieNode();//없으니까 새로 생성해준다.
				}
				current = current.children[wordIndex];// 다음 레벨로 넘어가기 위해 생성된 글자가 현재 노드가 된다
			}
		}
	}
	public static void main(String[] args) throws IOException{
		getDictionaryAndsetTrie();
		setData();
		if(wordlist!=null) find();
		System.out.println(answer);
	}
	private static void find(){
		//System.out.println("다운로드하여 추출한 단어의 개수 "+wordlist.length);//단어개수
		for(int i=0;i<wordlist.length;i++){
			String data = wordlist[i];
			findFlag = false;
			for(int row=0;row<5;row++) {//퍼즐 탐색
				for(int col=0;col<5;col++){
					if(Trie.root.children[puzzle[row][col]-'A']!=null && data.charAt(0)==puzzle[row][col]){//갈 수 있으면 
						visit[row][col] = true;
						dfs(row,col,1,Trie.root.children[puzzle[row][col]-'A'],data); //트라이 노드를 줘서 자식을 직접 확인해봄
						visit[row][col] = false;
					}
					if(findFlag) break;
				}
				if(findFlag) break;
			}
		}
	}
	private static void dfs(int row, int col, int depth, TrieNode current,String data){
		if(findFlag) return;
		if(depth==data.length() && findFlag==false){
			answer++;
			findFlag = true;
			return;
		}
		if(depth < data.length()) {
			for(int dir=0;dir<8;dir++) {
				int nr = row+dr[dir];
				int nc = col+dc[dir];
				if(rangeCheck(nr,nc)){
					if(visit[nr][nc]==false && current.children[puzzle[nr][nc]-'A']!=null && data.charAt(depth)==puzzle[nr][nc]) {
						visit[nr][nc] = true;
						//자식이 존재할 때 
						dfs(nr,nc,depth+1,current.children[puzzle[nr][nc]-'A'],data);
						visit[nr][nc] = false;
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<5 && nc>=0 && nc<5) return true;
		return false;
	}
	private static void getDictionaryAndsetTrie() throws UnknownHostException, IOException {
		/*
		 * "https://d2gd6pc034wcta.cloudfront.net/data/1165.zip"
		 * "/TESTDATA/coggle.zip"
		 * 66.113.2.158
		 * 
		 */
		
		URL url = new URL("https://d2gd6pc034wcta.cloudfront.net/data/1165.zip");
		//URL url = new URL("http://66.113.2.158/TESTDATA/coggle.zip");
		StringBuilder sb = new StringBuilder();
		InputStream in = new BufferedInputStream(url.openStream(), 1024*4);
		ZipInputStream stream = new ZipInputStream(in);
		byte[] buffer = new byte[1024*4];
		ZipEntry entry;
		while ((entry = stream.getNextEntry()) != null) {
			int read;
			String data ="";
			while ((read = stream.read(buffer, 0, 1024*4)) >= 0) {
				data = new String(buffer, 0, read);
				sb.append(data);
				//System.out.println(data);
			}
		}
		wordlist = sb.toString().split("\n");
		for(String data : wordlist) Trie.insert(data);
	}
	private static void setData() throws IOException {
		for(int row=0;row<5;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<5;col++) {
				puzzle[row][col] = st.nextToken().charAt(0);
			}
		}
	}	
}
