package CodingTest;


public class 플그인턴1 {
	static boolean[] year = new boolean[10001];
	static boolean[] number;
	public static void main(String[] args) {
		System.out.println(solution(2015));
	}
    public static int solution(int p){
        int answer = 0;
        number = new boolean[10];
        boolean flag=true;
        for(int i=1000;i<=10000;i++){
        	number = new boolean[10];
        	flag = true;
        	String strNumber = Integer.toString(i);
        	for(int j=0;j<strNumber.length();j++){
        		int num = Character.getNumericValue(strNumber.charAt(j));
        		if(number[num]==false) {
        			number[num] = true;
        		}else {
        			flag = false;
        			break;
        		}
        	}
        	if(flag) year[i] = true;
        }
        for(int i=p+1; i<=10000;i++){
        	if(year[i]) {
        		answer = i;
        		break;
        	}
        }
        return answer;
    }
}