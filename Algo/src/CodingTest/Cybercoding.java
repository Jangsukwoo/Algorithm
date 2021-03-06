package CodingTest;

import java.util.ArrayList;
import java.util.HashMap;


/*
 * 현카
 *  사이버코딩
 *  정당한 참가자수 구하기
 */
public class Cybercoding {
	static boolean[] check;
	static HashMap<String,Integer> ipmap;
	public static void main(String[] args) {
		
		System.out.println(solution(new String[] {"5.5.5.5"}
		,new String[] {"Java"}
		, new int[] {294}));
		
		
		System.out.println(solution(new String[] {"5.5.5.5", "155.123.124.111", "10.16.125.0", "155.123.124.111", "5.5.5.5", "155.123.124.111", "10.16.125.0", "10.16.125.0"}
		,new String[] {"Java", "C++", "Python3", "C#", "Java", "C", "Python3", "JavaScript"}
		, new int[] {294, 197, 373, 45, 294, 62, 373, 373}));
		
		System.out.println(solution(new String[] {"7.124.10.0", "7.124.10.0", "0.0.0.0", "7.124.10.0", "0.0.0.0", "7.124.10.0"}
		,new String[] {"C++", "Java", "C#", "C#", "C", "Python3"}
		, new int[] {314, 225, 45, 0, 155, 400}));
	}
    public static int solution(String[] ip_addrs, String[] langs, int[] scores) {
    	check = new boolean[ip_addrs.length];
    	ipmap = new HashMap<String,Integer>();
    	for(int i=0;i<ip_addrs.length;i++) {
    		if(!ipmap.containsKey(ip_addrs[i])) {
    			ipmap.put(ip_addrs[i],1);
    		}else {
    			int value = ipmap.get(ip_addrs[i]);
    			ipmap.put(ip_addrs[i],value+1);
    		}
    	}
    	for(int i=0;i<ip_addrs.length;i++){
    		int value = ipmap.get(ip_addrs[i]);
    		if(value>=4) {
    			check[i] = true;//부정
    		}
    	}
    	for(int i=0;i<ip_addrs.length;i++){ //3개 같으면
    		int value = ipmap.get(ip_addrs[i]);
    		if(value==3 && check[i]==false){
    			String currentIP = ip_addrs[i];
    			String currentLang = langs[i];
    			ArrayList<String> langlist =  new ArrayList<String>();
    			langlist.add(langs[i]);
    			for(int j=0;j<ip_addrs.length;j++){
    				if(i!=j && ip_addrs[j].equals(currentIP)){
    					langlist.add(langs[j]);
    				}
    			}
    			boolean flag=true;
    			if(currentLang.contains("C")){//c언어면
    				for(String data : langlist){
    					if(data.contains("C")) continue;
    					else {
    						flag = false;
    						break;
    					}
    				}
    				if(flag){//부정이면
    		   			for(int j=0;j<ip_addrs.length;j++){
    	    				if(ip_addrs[j]==currentIP){
    	    					check[j] = true;
    	    				}
    	    			}
    				}
    			}else {
    				if(langlist.get(0).equals(langlist.get(1)) && langlist.get(1).equals(langlist.get(2))) {
    		   			for(int j=0;j<ip_addrs.length;j++){
    	    				if(ip_addrs[j]==currentIP){
    	    					check[j] = true;
    	    				}
    	    			}
    				}
    			}
    		}
    	}
    	
    	for(int i=0;i<ip_addrs.length;i++){ //2개 같으면
    		int value = ipmap.get(ip_addrs[i]);
    		int a=0;
    		int b=0;
    		if(value==2 && check[i]==false){
    			a=i;
    			String currentIP = ip_addrs[i];
    			String currentLang = langs[i];
    			ArrayList<String> langlist =  new ArrayList<String>();
    			langlist.add(langs[i]);
    			for(int j=0;j<ip_addrs.length;j++){
    				if(i!=j && ip_addrs[j]==currentIP){
    					b = j;
    					langlist.add(langs[j]);
    					break;
    				}
    			}
    			boolean flag=true;
    			if(currentLang.contains("C")){//c언어면
    				if(langlist.get(0).contains("C") && langlist.get(1).contains("C")){
    					if(scores[a]==scores[b]) {
    						check[a] = true;
    						check[b] = true;
    					}
    				}
    			}else { 
    				if(langlist.get(0).equals(langlist.get(1))) {//언어가 같으면
       					if(scores[a]==scores[b]) {
    						check[a] = true;
    						check[b] = true;
    					}
    				}
    			}
    		}
    	}
    	int answer=0;
    	for(int i=0;i<ip_addrs.length;i++) {
    		if(check[i]==false) {
    			answer++;
    		}
    	}
        return answer;
    }
}
