package SDS_WEEK1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

/*
 * 
 * 
 * Boggle(9202) 백준
 * 
 * 단어 사전에 들어있는 단어의 수 w
 * 1<w<300000
 * 
 * Boggle 보드의 개수 b
 * 1<b<30
 * 
 * 단어는 최대 8글자, 알파벳 대문자만 구성
 * 1글자,2글자 = 0점
 * 3글자,4글자= 1점
 * 5글자 2점
 * 6글자 3점
 * 7글자 5점
 * 8글자 11점
 * 
 * 각각의 Boggle에 대해 얻을 수 있는 최대점수, 가장 긴 단어, 찾은 단어의 개수를 출력
 * 한 Boggle에서 같은 단어를 여러 번 찾은 경우 한 번만 찾은것으로 센다
 * 가장 긴 단어가 여러개인 경우 사전 순으로 앞서는 것을 출력한다.
 * 
 * 
 * 각 문제별로 자식들의 hit정보를 전부 없애는 clearHit라는걸 만듬
 * 
 * 
 * 단어가 들어오면  root부터 시작
 * 단어의 길이만큼 반복문을 돌면서
 * 알파벳은 각각 인덱스로 하고
 * 알파벳의 존재를 체크
 * 
 * 출력 
 * 최대 점수, 가장 긴 단어, 찾은 단어의수 
 * 
 * if(current.children[wordIndex] ==null) 
 * current.children[wordIndex] = new TrieNode처럼.
 * 
 * isEnd를 통해 문자는 있는데 
 * 
5
ICPC
ACM
CONTEST
GCPC
PROGRAMM

1
ACMA
APCA
TOGI
NEST
 */
class TrieNode{
	TrieNode[] children = new TrieNode[26];//26개의 알파벳 하나하나의 인덱스를 뜻하는 배열
	boolean isEnd;
	boolean isHit;
}
class Trie{ //트라이, 문자검색
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
		current.isEnd = true; //단어의 맨 끝
	}
	boolean containsNode(String word){//어떤 단어를 받았을 때 이 단어가 확인이 끝난 단어인지?
		TrieNode current = root;
		for(int i=0;i<word.length();i++) {
			int wordIndex = word.charAt(i)-'A';
			if(current.children[wordIndex]==null) return false; //이 글자는 없다는 걸 false로 반환 			
			current = current.children[wordIndex];
		}
		return current.isEnd; //단어의 맨 끝단어인가?
	}
	void clearHit() {
		for(int i=0;i<26;i++) {
			if(root.children[i]!=null){
				root.children[i].isHit = false;
				dfsclear(root.children[i]);
			}
		}
		
	}
	private void dfsclear(TrieNode trieNode) {
		for(int i=0;i<26;i++) {
			if(trieNode.children[i]!=null){
				trieNode.children[i].isHit=false;
				dfsclear(trieNode.children[i]);
			}
		}
	}

}
public class Boggle_4일차 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int w;//단어집의 단어 수
	static int b;//보글 게임판 수 
	static Trie BoggleTrie = new Trie();
	static char[][] boggleBoard = new char[4][4];
	static boolean[][] visit = new boolean[4][4];
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	//최대 점수, 가장 긴 단어, 찾은 단어의수 
	static int maxSum;
	static int maxLength;
	static int findWordCount;
	static ArrayList<String> answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		w = Integer.parseInt(br.readLine());
		for(int i=0;i<w;i++){//단어집 트라이 생성
			String word = br.readLine();
			BoggleTrie.insert(word); //입력받은 단어들로 구성된 트라이 생성 
		}
		br.readLine();
		b = Integer.parseInt(br.readLine());

		for(int round=1;round<=b;round++){
			//각 라운드 보글맵 받기
			maxSum = 0;
			findWordCount = 0;
			maxLength = 0;
			answer = new ArrayList<>();
			BoggleTrie.clearHit();
			//클리어히트처리도 해야함
			for(int row=0;row<4;row++) boggleBoard[row] = br.readLine().toCharArray();
			for(int row=0;row<4;row++) {//받은 보글맵 탐색 
				for(int col=0;col<4;col++){
					if(BoggleTrie.root.children[boggleBoard[row][col]-'A']!=null){//갈 수 있으면 
						visit[row][col] = true;
						String word = Character.toString(boggleBoard[row][col]);
						dfs(row,col,0,BoggleTrie.root.children[boggleBoard[row][col]-'A'],word); //트라이 노드를 줘서 자식을 직접 확인해봄
						visit[row][col] = false;
					}
				}
			}
			Collections.sort(answer);
			bw.write(maxSum+" ");
			for(int i=0;i<answer.size();i++){
				if(answer.get(i).length()==maxLength) {
					bw.write(answer.get(i)+" ");
					break;
				}
			}
			if(round!=b) br.readLine();
			bw.write(findWordCount+"\n");
		}
		bw.flush();
		bw.close();
	}
	private static void dfs(int row, int col, int depth, TrieNode current, String word) {//어떻게 탐색할까...
		if(current.isEnd && current.isHit==false){//맨 끝 노드면서 아직 Hit 안된거면
			current.isHit = true;
			if(word.length()>8) System.out.println("긴거나옴");
			switch (word.length()) {
			case 3:
				maxSum+=1;
				break;
			case 4:	
				maxSum+=1;
				break;
			case 5:				
				maxSum+=2;
				break;
			case 6:
				maxSum+=3;
			case 7:
				maxSum+=5;
				break;
			case 8:
				maxSum+=11;
				break;
			}
			maxLength = Math.max(maxLength,word.length());
			findWordCount+=1;
			answer.add(word);
			//여기서 리턴하면 안되고 더 끝까지 가봐야한다.
		}
		if(depth<8){//단어는 최대 8글자
			for(int dir=0;dir<8;dir++) {
				int nr = row+dr[dir];
				int nc = col+dc[dir];
				if(rangeCheck(nr,nc)){
					if(visit[nr][nc]==false && current.children[boggleBoard[nr][nc]-'A']!=null) {
						visit[nr][nc] = true;
						//자식이 존재할 때 
						String nextPick = Character.toString(boggleBoard[nr][nc]);
						String nextWord = word+nextPick;
						dfs(nr,nc,depth+1,current.children[boggleBoard[nr][nc]-'A'],nextWord);
						
						visit[nr][nc] = false;
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<4 && nc>=0 && nc<4) return true;
		return false;
	}
}
