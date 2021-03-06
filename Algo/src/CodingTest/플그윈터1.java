package CodingTest;

import java.util.Arrays;

public class 플그윈터1 {
	public static void main(String[] args) {
		solution(7, new int[][]{{5,6,0},{1,3,1},{1,5,0},{7,6,0},{3,7,1},{2,5,0}});
	}
    public static String solution(int n, int[][] delivery) {
        String answer = "";
        char[] checklist = new char[n+1];
        for(int i=0;i<delivery.length;i++) {
        	int productNumber1 = delivery[i][0];
        	int productNumber2 = delivery[i][1];
        	int check = delivery[i][2];
        	switch (check) {
			case 0:
				if(checklist[productNumber1]=='O') {
					checklist[productNumber2]='X';
				}else if(checklist[productNumber2]=='O') {
					checklist[productNumber1]='X';
				}
				break;
			case 1:
				checklist[productNumber1] = 'O';
				checklist[productNumber2] = 'O';
				break;
			}
        }
        for(int i=0;i<delivery.length;i++) {
        	int productNumber1 = delivery[i][0];
        	int productNumber2 = delivery[i][1];
        	int check = delivery[i][2];
        	switch (check) {
			case 0:
				if(checklist[productNumber1]=='O') {
					checklist[productNumber2]='X';
				}else if(checklist[productNumber2]=='O') {
					checklist[productNumber1]='X';
				}
				break;
			case 1:
				checklist[productNumber1] = 'O';
				checklist[productNumber2] = 'O';
				break;
			}
        }
        
        for(int productNumber = 1;productNumber<=n; productNumber++) {
        	if(checklist[productNumber]!='O'&& checklist[productNumber]!='X' ) {
        		checklist[productNumber] = '?';
        	}
        	answer+=checklist[productNumber];
        }
        return answer;
    }
}
