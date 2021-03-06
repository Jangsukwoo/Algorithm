package DP;
/*
 * knapSack
 */
import java.util.Scanner; 
public class 수강과목{ 
	static class Subject{
		private int important; 
		private int time; 
		Subject(int important, int time){ 
			this.important=important; 
			this.time=time; 
		} 
		public int getimportant(){ 
			return important; 
		} 
		public int gettime(){ 
			return time; 
		} 
	}
	public static void main(String[] args){ 
		Scanner sc = new Scanner(System.in);
		int maxtime=sc.nextInt(); 
		int K=sc.nextInt(); 
		int[][] arr=new int[K+1][maxtime+1]; 
		Subject[] subject=new Subject[K+1]; 
		for(int i=1;i<=K;i++) 
			subject[i]=new Subject(sc.nextInt(),sc.nextInt()); 
		for(int i=1;i<=K;i++){
			for(int j=1;j<=maxtime;j++){ 
				   if(subject[i].gettime()<=j && arr[i-1][j]<=arr[i-1][j-subject[i].gettime()]+subject[i].getimportant()) 
					arr[i][j]=arr[i-1][j-subject[i].gettime()]+subject[i].getimportant(); 
				else 
					arr[i][j]=arr[i-1][j]; 
			} 
		} 
		System.out.println(arr[K][maxtime]);
	} 
} 
