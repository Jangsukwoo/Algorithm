package CodingTest;

import java.util.HashSet;
import java.util.Scanner;


public class 느흐느20_1 {
	 static class Tagger{
		 char name;
		 int cnt;
		public Tagger(char name, int cnt) {
			this.name = name;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return "Tagger [name=" + name + ", cnt=" + cnt + "]";
		}
	 }
	 static Tagger[] taggers;
	 private static void solution(int numOfAllPlayers, int numOfQuickPlayers, char[] namesOfQuickPlayers, int numOfGames, int[] numOfMovesPerGame){
		  taggers = new Tagger[numOfAllPlayers];
		  for(int i=0;i<numOfAllPlayers;i++)  taggers[i] = new Tagger((char) ('A'+i),0);
		  taggers[0].cnt=1;//A는 처음술래
		 
		  
		  char taggerName = 'A';
		  int taggerIdx = 0;//시작 술래 idx
		  int nextTaggerIdx = 0;		  
		  HashSet<Character> quickSet = new HashSet<Character>();
		  char[] seat = new char[numOfAllPlayers-1];
		  
		  for(int i=0;i<(numOfAllPlayers-1);i++) seat[i] = (char) ('B'+i);
		  
		  for(int i=0;i<namesOfQuickPlayers.length;i++) quickSet.add(namesOfQuickPlayers[i]);
		  
		  for(int round=0;round<numOfMovesPerGame.length;round++) {
			  
			  
			  int wise = numOfMovesPerGame[round];
			  nextTaggerIdx = getNextTaggerIdx(taggerIdx,wise,numOfAllPlayers);
			  if(!quickSet.contains(seat[nextTaggerIdx])){//다음에 앉으려고 하는 자리가 달리기가 빠른애가 아니면
				  //술래 교체
				  char temp = seat[nextTaggerIdx];
				  seat[nextTaggerIdx] = taggerName;
				  taggerName = temp;
			  }
			  taggerIdx = nextTaggerIdx;
			  taggers[taggerName-'A'].cnt++;
		  }
		  
		  
		  for(int i=0;i<numOfAllPlayers-1;i++) {
			  System.out.println(seat[i]+" "+taggers[seat[i]-'A'].cnt);
		  }
		  System.out.println(taggerName+" "+taggers[taggerName-'A'].cnt);
	  }
	 
	 
	  private static int getNextTaggerIdx(int taggerIdx,int wise, int numOfAllPlayers) {
		int nextTaggerIdx = taggerIdx;
		if(wise>0) {
			for(int i=1;i<=wise;i++) {
				nextTaggerIdx+=1;
				if(nextTaggerIdx==(numOfAllPlayers-1)) nextTaggerIdx = 0;
			}
		}else if(wise==0) {
			nextTaggerIdx = taggerIdx;
		}else if(wise<0){
			for(int i=1;i<=Math.abs(wise);i++) {
				nextTaggerIdx-=1;
				if(nextTaggerIdx==-1) nextTaggerIdx = numOfAllPlayers-2;
			}
		}
		return nextTaggerIdx;
	}
	  
	  
	private static class InputData {
	    int numOfAllPlayers;
	    int numOfQuickPlayers;
	    char[] namesOfQuickPlayers;
	    int numOfGames;
	    int[] numOfMovesPerGame;
	  }
	
	
	  private static InputData processStdin() {
	    InputData inputData = new InputData();

	    try (Scanner scanner = new Scanner(System.in)) {
	      inputData.numOfAllPlayers = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

	      inputData.numOfQuickPlayers = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
	      inputData.namesOfQuickPlayers = new char[inputData.numOfQuickPlayers];
	      System.arraycopy(scanner.nextLine().trim().replaceAll("\\s+", "").toCharArray(), 0, inputData.namesOfQuickPlayers, 0, inputData.numOfQuickPlayers);

	      inputData.numOfGames = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
	      inputData.numOfMovesPerGame = new int[inputData.numOfGames];
	      String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
	      for(int i = 0; i < inputData.numOfGames ; i++){
	        inputData.numOfMovesPerGame[i] = Integer.parseInt(buf[i]);
	      }
	    } catch (Exception e) {
	      throw e;
	    }

	    return inputData;
	  }

	  public static void main(String[] args) throws Exception {
	    InputData inputData = processStdin();

	    solution(inputData.numOfAllPlayers, inputData.numOfQuickPlayers, inputData.namesOfQuickPlayers, inputData.numOfGames, inputData.numOfMovesPerGame);
	  }
}
