package SDS_WEEK1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

/*
 * 후보는 추천에 의해 정해진 수만큼 선정
 * 후보의 수만큼의 사진틀이 있고 추천 받으면 사진틀이 게시됨
 * 비어있는 사진틀이 없는 경우 현재까지 추천받은 회수가 가장 적은 학생의 사진 삭제, 그 자리에 새롭게 추천받은
 * 학생 사진 게시
 * 현재까지 추천받은 회수가 가장 적은 학생이 둘 이상일 경우 게시된지 가장 오래된 사진 삭제
 * 기존 사진틀의 후보가 추천받으면 추천 횟수만 카운팅
 * 사진틀에 게시된 사진이 삭제될 경우 해당 학생의 추천회수 초기화
 * 사진 틀의 개수  N (1<=N<=20)
 * 전체 학생의 총 추천 회수
 * 
 * 학생 번호 1~100
 * 총 추천 회수 최대 1000
 */
class PictureFrame implements Comparable<PictureFrame>{
	int studentNumber;
	@Override
	public String toString() {
		return "s"+studentNumber + " "+recommendCount + " " + time;
	}
	int recommendCount;
	int time;
	public PictureFrame(int s,int r, int t) {
		this.studentNumber = s;
		this.recommendCount = r;
		this.time = t;
	}
	@Override
	public int compareTo(PictureFrame t) {
		if(this.recommendCount==t.recommendCount) return -Integer.compare(this.time, t.time);
		return Integer.compare(this.recommendCount, t.recommendCount);
	}
}
public class 후보추천하기_1일차 {
	static int N;
	static ArrayList<PictureFrame> frames = new ArrayList<>();
	static int recommendSize;
	static int[] recommends;
	static int[] answer;
	static HashSet<Integer> candidate = new HashSet<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		recommendSize = sc.nextInt();
		recommends = new int[recommendSize];
		answer= new int[N];
		for(int recommend=0;recommend<recommendSize;recommend++){
			recommends[recommend] = sc.nextInt();
		}
		recommend();
		
		for(int i=0;i<N;i++) {
			answer[i] = frames.get(i).studentNumber;
		}
		Arrays.sort(answer);
		for(int i=0;i<N;i++) {
			System.out.print(answer[i]+" ");
		}
	}
	private static void recommend() {
		for(int i=0;i<recommendSize;i++){
			int candidateNumber = recommends[i];
			if(candidate.contains(candidateNumber)){//후보군에 존재하는 경우
				for(int j=0;j<frames.size();j++){
					if(candidateNumber==frames.get(j).studentNumber) {
						frames.get(j).recommendCount+=1;
					}
				}
			}else {//존재하지 않는 경우
				if(candidate.size()==N) {//꽉차있으면 정렬하고 제거
					Collections.sort(frames);
					candidate.add(candidateNumber);
					candidate.remove(frames.get(0).studentNumber);
					frames.remove(0);
					frames.add(new PictureFrame(candidateNumber,1,1));
				}else {			
					candidate.add(candidateNumber);
					frames.add(new PictureFrame(candidateNumber,1,1));
				}
				
			}
			//넣었으니 시간 증가
			for(int j=0;j<frames.size();j++){
				frames.get(j).time+=1;
			}
		}
	}
}
