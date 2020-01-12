package CodingTest;

public class NathansProgram {
	static int N; //1<=N<=300
	static boolean[] groups = new boolean[301]; //그룹 존재 flag
	static int[] groupEntireCnt = new int[301];
	static int[] groupOKFrequencyCnt = new int[301];
	public static void main(String[] args) {
//		String[] T = new String[] {"test1a","test2","test1b","test1c","test3"};
//		String[] R = new String[] {"Wrong answer","OK","Runtime error","OK","Time limit exceeded"};
//		String[] T2 = new String[] {"codility1","codility3","codility2","codility4b","codility4a"};
//		String[] R2 = new String[] {"Wrong answer","OK","OK","Runtime error","OK"};
		
//		System.out.println(solution(T2,R2));
	}

	private static int solution(String[] T, String[] R) {
		int tSize =T.length;
		double groupSize=0;
		double okGroup=0;
		for(int i=0;i<tSize;i++){
			String data = T[i];//데이터
			String groupStringNumber = "";
			int groupNumber=0;
			for(int j=0;j<data.length();j++){
				if(data.charAt(j)>='0' && data.charAt(j)<='9') {
					groupStringNumber+=data.charAt(j);
				}
			}
			groupNumber = Integer.parseInt(groupStringNumber);
			groupEntireCnt[groupNumber]+=1; //해당 그룹 출현
			//현재 쳐다보고있는 그룹 소속 넘버
			if(groups[groupNumber]==false) {
				groups[groupNumber]=true;
				groupSize++;
			}
			//Ok인경우
			if(R[i].equals("OK"))groupOKFrequencyCnt[groupNumber]+=1;
		}
		for(int i=1;i<=300;i++) {
			if(groupEntireCnt[i]!=0 && groupEntireCnt[i]==groupOKFrequencyCnt[i]) okGroup++;
		}
		double score = (okGroup/groupSize)*100;
		System.out.println(groupSize);
		return (int) score;
	}
}
