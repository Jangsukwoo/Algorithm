package CodingAcademy;
/*
 * https://math.stackexchange.com/questions/1391673/number-of-edges-in-the-hasse-diagram-for-the-subseteq-relation-on-the-set-m
 * 
 * 원소개수 n개인 집합의 멱집합을 표현하는 격자구조에서
 *해당 격자구조에 존재하는 모든 간선의 개수를 구하기
 *
 */
public class getEdgeAtHasseDiagram {
	public static void main(String[] args) {
		System.out.println((numEdges(4)));
	}

	private static int numEdges(int n) {
		int result = 1;
		for(int i=1;i<=(n-1);i++) result*=2;
		result*=n;
		return result;
	}
}
