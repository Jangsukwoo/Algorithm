package CodingTest;
/*
 * est
 */
import java.util.ArrayList;

/*
 * 인접한 두 나무의 크기는 같을 수 없다.
 * 나무를 자르는 방법의수 
 * 나무는 최대 한개 까지 자를 수 있다.
 * 
 * 4<=N<=200
 * A는 int 범위 1<=A[K]<=1000
 * 
 * 이미 지그재그면 자를필요 없으니 0
 * 지그재그를 못만들면 -1
 * 지그재그를 만드는 방법의 수 return
 */
public class gardenZigzagTree {
	public static void main(String[] args) {
		int answer = solution(new int[] {1,2,1,3,5,1});
		System.out.println(answer);
	}
    public static int solution(int[] tree) {
    	int zigzag=0;
    	
    	if(treeZigzagCheck(tree)) return 0;
    	for(int i=0;i<tree.length;i++){//0번째부터 하나씩 짤라보기
    		if(zigzagCheck(i,tree)) zigzag++;
    	}
    	if(zigzag==0) return -1;//짤라봤는데도 0이면 불가능하니깐 -1
    	else return zigzag;
    }
	private static boolean zigzagCheck(int k, int[] tree){
		ArrayList<Integer> aliveTrees = new ArrayList<Integer>();
    	for(int i=0;i<tree.length;i++){//0번째부터 하나씩 짤라보기
    		if(i==k) continue;
    		else aliveTrees.add(tree[i]);
    	}
    	int[] testTreeArray = new int[tree.length-1];
    	for(int i=0;i<(tree.length-1);i++) testTreeArray[i] = aliveTrees.get(i);
    	if(treeZigzagCheck(testTreeArray)) return true;
		return false;
	}
	private static boolean treeZigzagCheck(int[] tree){
		boolean positive = false;
		if((tree[0]-tree[1])>0) positive = true;
		else if((tree[0]-tree[1])<0) positive = false;
		else if((tree[0]-tree[1])==0) return false;//높이가 같으면 지그재그가 아님
		if(positive){//양수로 시작하면
			for(int i=0;i<(tree.length-1);i++){
				if(i%2==0){//짝수일떄는 양수여야함
					if(tree[i]-tree[i+1]>0) continue;
					else return false;//아닌경우는 지그재그가 아님
				}else if(i%2==1){//홀수일땐 음수여야함
					if(tree[i]-tree[i+1]<0) continue;
					else return false;//아닌경우는 지그재그가 아님
				}
			}	
		}else {//음수로 시작하면
			for(int i=0;i<(tree.length-1);i++){
				if(i%2==0){//짝수일떄는 음수여야함
					if(tree[i]-tree[i+1]<0) continue;
					else return false;//아닌경우는 지그재그가 아님
				}else if(i%2==1){//홀수일땐 양수여야함
					if(tree[i]-tree[i+1]>0) continue;
					else return false;//아닌경우는 지그재그가 아님
				}
			}		
		}
		return true;
	}

}
