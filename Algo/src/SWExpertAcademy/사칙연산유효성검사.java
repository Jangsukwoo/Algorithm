package SWExpertAcademy;
import java.util.Scanner;


public class 사칙연산유효성검사{
    public static void main(String[] args) {
        tree t = new tree();
        Scanner sc = new Scanner(System.in);
        for(int test=1;test<=10;test++) {
            //입력
            int ans =1;
            int V = sc.nextInt()+1;
            Node[] n = new Node[V];
            sc.nextLine();
            for(int i=1; i<V;i++) n[i] = new Node();
            for(int i=1; i<V;i++) {
                String[] input = sc.nextLine().trim().split(" ");
                if(input.length==4) {
                    n[i].number = i;
                    if(input[1].charAt(0)!='-'&&input[1].charAt(0)!='+'&&input[1].charAt(0)!='*'&&input[1].charAt(0)!='/') {
                        ans=0;
                    }
                    n[i].data = input[1].charAt(0);
                    n[i].left = n[Integer.parseInt(input[2])];
                    n[i].right = n[Integer.parseInt(input[3])]; 
                }
                else if(input.length==2) {
                    n[i].number = i;
                    if(input[1].charAt(0)=='-'||input[1].charAt(0)=='+'||input[1].charAt(0)=='*'||input[1].charAt(0)=='/') {
                        ans=0;
                        //n[i].value = Integer.parseInt(input[1]);
                    }
                }
            }//노드 다 만듬
            //처리
            System.out.println("#"+test+" "+ans);
        }
    }
}