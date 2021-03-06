package CodingTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class 이투1 {
	static class RankData implements Comparable<RankData>{
		String name;
		int courseCount;
		int maxDifficultyCourse;
		int goldMedal;
		int silverMedal;
		int bronzeMedal;
		int raceTime;
		@Override
		public int compareTo(RankData o) {
			if(this.courseCount==o.courseCount) {//코스 카운트 같으면
				if(this.maxDifficultyCourse==o.maxDifficultyCourse) {//어려운 코스가 같으면
					if(this.goldMedal==o.goldMedal) {
						if(this.silverMedal==o.silverMedal) {
							if(this.bronzeMedal==o.bronzeMedal) {
								if(this.raceTime==o.raceTime) {
									return this.name.compareTo(o.name);
								}
								return Integer.compare(this.raceTime,o.raceTime);
							}
							return -Integer.compare(this.bronzeMedal,o.bronzeMedal);
						}
						return -Integer.compare(this.silverMedal,o.silverMedal);
					}
					return -Integer.compare(this.goldMedal,o.goldMedal);
				}
				return -Integer.compare(this.maxDifficultyCourse,o.maxDifficultyCourse);
			}
			return -Integer.compare(this.courseCount,o.courseCount);
		}
		@Override
		public String toString() {
			return "RankData [name=" + name + ", courseCount=" + courseCount + ", maxDifficultyCourse="
					+ maxDifficultyCourse + ", goldMedal=" + goldMedal + ", silverMedal=" + silverMedal
					+ ", bronzeMedal=" + bronzeMedal + ", raceTime=" + raceTime + "]";
		}
	}
	static class CourseData implements Comparable<CourseData>{
		String name;
		int time;
		@Override
		public int compareTo(CourseData o) {
			return Integer.compare(this.time,o.time);
		}
	}
	public static void main(String[] args) {
		solution(new String[] {"jack:9,10,13,9,15","jerry:7,7,14,10,17","jean:0,0,11,20,0","alex:21,2,7,11,4","kevin:8,4,5,0,0","brown:3,5,16,3,18","ted:0,8,0,0,8","lala:0,12,0,7,9","hue:17,16,8,6,10","elsa:11,13,10,4,13"});
	}
    public static String[] solution(String[] record) {
        String[] answer = {};
        PriorityQueue<RankData> pq = new PriorityQueue<RankData>();
        HashMap<String,RankData> map = new HashMap<String,RankData>();
        PriorityQueue<CourseData>[] coursePQ = new PriorityQueue[5];
        for(int i=0;i<5;i++) coursePQ[i] = new PriorityQueue<CourseData>();
        
        for(int i=0;i<record.length;i++) {
        	String data = record[i];
        	String[] token1 = data.split(":");
        	String name = token1[0];
        	String[] timeToken = token1[1].split(",");
        	System.out.println(name);
        	int allTime = 0;
        	int maxDifficultyCourse = 0;
        	int courseCount = 0;
        	for(int j=0;j<timeToken.length;j++) {
        		int time = Integer.parseInt(timeToken[j]);
        		if(time!=0) {
        			maxDifficultyCourse = j+1;
        			courseCount++;
        			CourseData courseData = new CourseData();
        			courseData.name = name;
        			courseData.time = time;
        			coursePQ[j].add(courseData);//코스에 정보 추가
        		}
        		allTime+=time;
        	}
        	RankData rankData = new RankData();
        	rankData.name = name;
        	rankData.maxDifficultyCourse = maxDifficultyCourse;
        	rankData.raceTime = allTime;
        	rankData.courseCount = courseCount;
        	map.put(name,rankData);
        }
        for(int course=0;course<5;course++){//메달 정하기
            int rank = 1;
            int medalLine = 0;
        	double rankerSize = coursePQ[course].size();
        	double goldMedalLine = rankerSize/12;
        	int goldRank = (int) Math.ceil(goldMedalLine);
        	medalLine=goldRank;
        	for(int i=rank;i<=goldRank;i++) {
        		CourseData courseData = coursePQ[course].poll();
        		String name = courseData.name;
        		RankData rankData = map.get(name);
        		rankData.goldMedal+=1;
        		map.put(name,rankData);
        	}
        	double silverMedalLine = rankerSize/6;
        	int silverRank = (int) Math.ceil(silverMedalLine);
        	rank = goldRank+1;
        	medalLine = (medalLine+silverRank);
        	for(int i=rank;i<=silverRank;i++) {
        		CourseData courseData = coursePQ[course].poll();
        		String name = courseData.name;
        		RankData rankData = map.get(name);
        		rankData.silverMedal+=1;
        		map.put(name,rankData);
        	}
        	double bronzeMedalLine =  rankerSize/2;
        	int bronzeRank = (int) Math.ceil(bronzeMedalLine);
        	rank = silverRank+1;
        	for(int i=rank;i<=bronzeRank;i++) {
        		CourseData courseData = coursePQ[course].poll();
        		String name = courseData.name;
        		RankData rankData = map.get(name);
        		rankData.bronzeMedal+=1;
        		map.put(name,rankData);
        	}
        }
        for(String name : map.keySet()) {
        	RankData rankData = map.get(name);
        	pq.add(rankData);
        }
        ArrayList<String> answerlist = new ArrayList<String>();
        while(!pq.isEmpty()) {
        	answerlist.add(pq.poll().name);
        }
        answer = new String[answerlist.size()];
        for(int i=0;i<answer.length;i++) {
        	answer[i] = answerlist.get(i);
        }
        return answer;
    }
}
