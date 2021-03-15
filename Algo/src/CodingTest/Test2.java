package CodingTest;

import java.util.HashSet;

public class Test2 {
	static HashSet<String> possibleWordSet = new HashSet<String>();
	static int[] pick;
	static boolean[] visit;
	static String inputString1;
	static String inputString2;
	static int r;
	public static void main(String[] args) {
		
		inputString1 = "abcdbadcabcabadkmcbabc";
		inputString2 = "abc";

		removeNonCharacter();
		
		setPossibleWordSet(inputString2);
		
		for(String data : possibleWordSet) System.out.println(data);
		
		int answer = getAnagramValue();
		
		System.out.println(answer);
	}

	private static void removeNonCharacter() {
		String[] temp1 = inputString1.split(" ");
		String[] temp2 = inputString2.split(" ");
		String temp3 = "";
		String temp4 = "";
		for(int i=0;i<temp1.length;i++) temp3+=temp1[i];
		for(int i=0;i<temp2.length;i++) temp4+=temp2[i];
		temp1 = temp3.split(",");
		temp2 = temp4.split(",");
		inputString1="";
		inputString2="";
		for(int i=0;i<temp1.length;i++) inputString1+=temp1[i];
		for(int i=0;i<temp1.length;i++) inputString2+=temp2[i];
		
	}

	private static void setPossibleWordSet(String inputString2) {
		r = inputString2.length();
		visit = new boolean[r];
		pick = new int[r];
		nPr("",0);
	}

	private static void nPr(String word,int depth) {
		if(depth==r) {
			possibleWordSet.add(word);
			return;
		}
		for(int i=0;i<r;i++) {
			if(visit[i]==false) {
				visit[i] = true;
				nPr(word+inputString2.charAt(i), depth+1);
				visit[i] = false;
			}
		}
	}

	private static int getAnagramValue() {
		int anagram = 0;
		for(int i=0;i<=(inputString1.length()-r);i++) {
			String checkWord = "";
			for(int j=i;j<i+r;j++) {
				checkWord+=inputString1.charAt(j);
			}
			if(possibleWordSet.contains(checkWord)) {
				anagram++;
			}
		}
		return anagram;
	}
}
