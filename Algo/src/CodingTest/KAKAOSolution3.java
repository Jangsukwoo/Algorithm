package CodingTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KAKAOSolution3 {
	static int[][][][][] scoreTable;
	static ArrayList<Integer> answerList;
	static StringTokenizer st;
	public static void main(String[] args){
		int[] answer = solution(new String[] 
				{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"},
				new String[] {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150",
						"- and - and - and chicken 100","- and - and - and - 150"});
		System.out.println(Arrays.toString(answer));
	}
	public static int[] solution(String[] info, String[] query){
		int[] answer = {};
		answerList = new ArrayList<Integer>();
		initailizeScoreTable(info); //scoreTable setting
		excuteQuery(query);
		answer = getAnswer();
		return answer;
	}
	private static int[] getAnswer() {
		int[] answer = new int[answerList.size()];
		for(int i=0;i<answer.length;i++) answer[i] = answerList.get(i);
		return answer;
	}
	private static void excuteQuery(String[] querys) {
		for(int query = 0; query < querys.length; query++){
			st = new StringTokenizer(querys[query]);
			int languageKey = 0;
			int positionKey = 0;
			int careerKey = 0;
			int soulFoodKey = 0;
			int score = 0;
			languageKey = getLanguageKey(st.nextToken());
			st.nextToken(); //and
			positionKey = getPositionKey(st.nextToken());
			st.nextToken(); //and
			careerKey = getCareerKey(st.nextToken());
			st.nextToken(); //and
			soulFoodKey = getSoulFoodKey(st.nextToken());
			score = Integer.parseInt(st.nextToken());
			int sum = 0;
			for(int lang=0;lang<3;lang++) { //답 검색
				for(int pos=0;pos<2;pos++) {
					for(int ca = 0; ca<2 ; ca++) {
						for(int sf = 0; sf<2; sf++) {
							if(languageKey==-1) {
								if(positionKey==-1){
									if(careerKey==-1) {
										if(soulFoodKey==-1) {
												sum+=scoreTable[lang][pos][ca][sf][score]; // -1 -1 -1 -1
										}else {
											if(soulFoodKey==sf) {
												sum+=scoreTable[lang][pos][ca][sf][score]; //-1 -1 -1 0 
											}
										}
									}else {
										if(soulFoodKey==-1) {
											if(careerKey==ca) {
												sum+=scoreTable[lang][pos][ca][sf][score]; //-1 -1 0 -1
											}
										}else {
											if(careerKey==ca && soulFoodKey==sf) {
												sum+=scoreTable[lang][pos][ca][sf][score]; //-1 -1 0 0
											}
										}
									}
								}else { //pos
									if(careerKey==-1) {
										if(soulFoodKey==-1) {
											if(positionKey==pos) {
												sum+=scoreTable[lang][pos][ca][sf][score]; //-1 0 -1 -1
											}
										}else {
											if(positionKey==pos && soulFoodKey==sf) {
												sum+=scoreTable[lang][pos][ca][sf][score]; //-1 0 -1 0
											}
										}
									}else {
										if(soulFoodKey==-1) {
											if(positionKey==pos && careerKey==ca) {
												sum+=scoreTable[lang][positionKey][careerKey][sf][score]; //-1 0 0 -1
											}
										}else {
											if(positionKey==pos && careerKey==ca && soulFoodKey==sf) {
												sum+=scoreTable[lang][positionKey][careerKey][soulFoodKey][score]; //-1 0 0 0
											}
										}
									}
								}
							}else {
								if(positionKey==-1){
									if(careerKey==-1) {
										if(languageKey==lang && soulFoodKey==-1) {
												sum+=scoreTable[lang][pos][ca][sf][score];
										}else {
											if(languageKey==lang && soulFoodKey==sf) {
												sum+=scoreTable[lang][pos][ca][sf][score];
											}
										}
									}else {
										if(languageKey==lang && soulFoodKey==-1) {
											if(careerKey==ca) {
												sum+=scoreTable[lang][pos][ca][sf][score]; 
											}
										}else {
											if(languageKey==lang && careerKey==ca && soulFoodKey==sf) {
												sum+=scoreTable[lang][pos][ca][sf][score];
											}
										}
									}
								}else { //pos
									if(careerKey==-1) {
										if(soulFoodKey==-1) {
											if(languageKey==lang && positionKey==pos) {
												sum+=scoreTable[lang][pos][ca][sf][score];
											}
										}else {
											if(languageKey==lang && positionKey==pos && soulFoodKey==sf) {
												sum+=scoreTable[lang][pos][ca][sf][score]; 
											}
										}
									}else {
										if(languageKey==lang && soulFoodKey==-1) {
											if(positionKey==pos && careerKey==ca) {
												sum+=scoreTable[lang][positionKey][careerKey][sf][score]; 
											}
										}else {
											if(languageKey==lang && positionKey==pos && careerKey==ca && soulFoodKey==sf) {
												sum+=scoreTable[lang][positionKey][careerKey][soulFoodKey][score];
											}
										}
									}
								}
							}
						}
					}
				}
			}
			answerList.add(sum);
		}
	}
	private static void initailizeScoreTable(String[] info) {
		scoreTable = new int[3][2][2][2][100001];
		for(int i=0;i<info.length;i++){
			/*
			 * cpp : 0, java : 1, python : 2
			 * b : 0, f : 1
			 * j : 0, s : 1
			 * c : 0, p : 1
			 *  
			 */

			int languageKey = 0;
			int positionKey = 0;
			int careerKey = 0;
			int soulFoodKey = 0;
			int score = 0;
			st = new StringTokenizer(info[i]);
			languageKey = getLanguageKey(st.nextToken());
			positionKey = getPositionKey(st.nextToken());
			careerKey = getCareerKey(st.nextToken());
			soulFoodKey = getSoulFoodKey(st.nextToken());
			score = Integer.parseInt(st.nextToken());
			scoreTable[languageKey][positionKey][careerKey][soulFoodKey][score]++;
		}

		calculationScoreTable();
	}
	private static void calculationScoreTable() {
		for(int lang=0;lang<3;lang++) {
			for(int pos=0;pos<2;pos++) {
				for(int ca = 0; ca<2 ; ca++) {
					for(int sf = 0; sf<2; sf++) {
						for(int score=99999; score>=0; score--) {
							scoreTable[lang][pos][ca][sf][score]+=scoreTable[lang][pos][ca][sf][score+1];
						}
					}
				}
			}
		}
	}
	private static int getCareerKey(String nextToken) {
		int careerKey = 0;
		switch (nextToken) {
		case "junior":
			careerKey = 0;
			break;
		case "senior":
			careerKey = 1;
			break;
		case "-":
			careerKey = -1;
			break;	
		}
		return careerKey;
	}
	private static int getSoulFoodKey(String nextToken) {
		int soulFoodKey = 0;
		switch (nextToken) {
		case "chicken":
			soulFoodKey = 0;
			break;
		case "pizza":
			soulFoodKey = 1;
			break;
		case "-":
			soulFoodKey = -1;
			break;	
		}
		return soulFoodKey;
	}
	private static int getPositionKey(String nextToken) {
		int positionKey = 0;
		switch (nextToken) {
		case "backend":
			positionKey = 0;
			break;
		case "frontend":
			positionKey = 1;
			break;
		case "-":
			positionKey = -1;
			break;	
		}
		return positionKey;
	}
	private static int getLanguageKey(String nextToken){
		int languageKey = 0;
		switch (nextToken) {
		case "cpp":
			languageKey = 0;
			break;
		case "java":
			languageKey = 1;
			break;
		case "python":
			languageKey = 2;
			break;
		case "-":
			languageKey = -1;
			break;
		}
		return languageKey;
	}
}
