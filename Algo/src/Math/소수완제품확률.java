package Math;
/*
 * SWExpertAcademy 1266번 소수 완제품 확류 문제
 * 요구사항대로 문제를 풀되
 * 순열조합의 Combination n!/r!(n-r)! 공식을 사용했다.
 */
import java.util.Scanner;
public class 소수완제품확률 {
    public static double Pa,Pb;
    public static int[] primeNumber = {2,3,5,7,11,13,17};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        double CombinationA=1;
        double CombinationB=1;
        for(int testcase=1;testcase<=T;testcase++) {
            double skillOfMasterA = (sc.nextInt())/(double)100;
            double skillOfMasterB = (sc.nextInt())/(double)100;
                for(int i=0;i<7;i++) {
                    for(int j=0;j<primeNumber[i];j++) {
                        CombinationA=CombinationA*(18-j)*skillOfMasterA/(primeNumber[i]-j);           
                        CombinationB=CombinationB*(18-j)*skillOfMasterB/(primeNumber[i]-j);  
                    }//n!/r!(n-r)!*p^n
                    CombinationA*=Math.pow((1-skillOfMasterA),18-primeNumber[i]);
                    CombinationB*=Math.pow((1-skillOfMasterB),18-primeNumber[i]);
                    Pa+=CombinationA;Pb+=CombinationB;CombinationA=1;CombinationB=1;
                }
                System.out.printf("#"+testcase+" "+"%.6f\n",Math.round(((1-((1-Pa)*(1-Pb)))*1000000))/1000000d);
                Pa=0;Pb=0;
        }
    }
}