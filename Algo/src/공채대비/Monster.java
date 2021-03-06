package 공채대비;

import java.util.HashMap;

public class Monster {
	/*
	 * 사냥꾼은 0,0에 위치
	 * 
	 */
	static HashMap<Double,Integer> target;
	public static void main(String[] args) {
		 System.out.println(solution(new int[][] {{2,3},{4,5},{3,-3},{2,-4},{3,-6},{-3,-3},{-5,0},{-4,4}}, new int[][] {{4,1},{4,6},{1,-2},{-4,-4},{-3,0},{-4,4}}));
		 System.out.println(solution(new int[][] {{-4,4},{-2,2},{6,2},{0,-2}}, new int[][] {{3,1},{-1,1},{-1,1},{0,-4},{2,-3}}));
		 System.out.println(solution(new int[][] {{1,2},{-2,-1},{1,-2},{3,-1}}, new int[][] {{1,0},{2,1}}));
	}
    public static int solution(int[][] monsters, int[][] bullets) {
        int answer = 0;
        target= new HashMap<Double, Integer>();
        for(int i=0;i<monsters.length;i++){//몬스터 기울기 좌표 전부 저장
        	int x = monsters[i][0];
        	int y = monsters[i][1];
        	double degree = Math.atan2(y, x); //아크탄젠트로 각도값 구하기
        	if(target.containsKey(degree)){//이미 존재하는 각도에 몬스터가 있으면
        		int population = target.get(degree);
        		target.put(degree,population+1);
        	}else target.put(degree,1);
        }
        
        for(int i=0;i<bullets.length;i++){//사격
        	int x = bullets[i][0];
        	int y = bullets[i][1];
        	double degree = Math.atan2(y, x);
        	if(target.containsKey(degree)){//각도에 뭔가 존재 
        		int population = target.get(degree);
        		if(population>0){//한마리 이상 존재 
        			answer++;
        			target.put(degree,population-1);
        		}else continue;//아닌 경우 그냥 건너 뜀 
        	}
        }
        if(answer==0) answer=-1;//한마리도 사냥 못했으면 -1
        return answer;
    }
}
