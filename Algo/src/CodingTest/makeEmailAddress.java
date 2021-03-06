package CodingTest;
/*
 * est
 */
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * ;로 구분된 문자열 S가 제공됌
 * 세미콜론 뒤에 공백있음
 * C는 회사이름
 * 하이픈 들어간건 없앰 
 * 
 */
public class makeEmailAddress {
	public static void main(String[] args) {
		String name = solution("John Doe; Peter Benjamin Parker; Mary Jane Watson-Parker; John Elvis Doe; John Evan Doe; Jane Doe; Peter Brian Parker","Example");
	}
    public static String solution(String S, String C) {
    	String[] tempnameList = S.split(";");
    	String[] namelist = new String[tempnameList.length]; 
    	String[] emailNamelist = new String[tempnameList.length];
    	String companyEmail = "@"+C.toLowerCase()+".com>";
    	String answer = "";
    	HashMap<String,Integer> nameMap = new HashMap<String, Integer>();
    	for(int i=0;i<namelist.length;i++) {
    		namelist[i]="";
    		emailNamelist[i] ="";
    	}
    	for(int i=0;i<tempnameList.length;i++) {
    		StringTokenizer st = new StringTokenizer(tempnameList[i]);
    		int nameTokenSize = st.countTokens();
    		for(int j=0;j<nameTokenSize;j++){
    			if(j==nameTokenSize-1) namelist[i]+=st.nextToken();
    			else namelist[i]+=st.nextToken()+" ";
    		}
    	}
    	//이메일 이름 리스트 만들기 
    	for(int i=0;i<namelist.length;i++){
    		StringTokenizer st = new StringTokenizer(namelist[i]);
    		int nameTokenSize = st.countTokens();
    		if(nameTokenSize==2){
    			String firstName = st.nextToken();
    			String lastName = st.nextToken();
    			firstName = firstName.toLowerCase();
    			lastName = lastName.toLowerCase();
    			firstName = firstName.replaceAll("-","");
    			lastName = lastName.replaceAll("-","");
    			String makeName = lastName+"_"+firstName;
    			if(nameMap.containsKey(makeName)){
    				int number = nameMap.get(makeName);
    				nameMap.put(makeName,number+1);
    				makeName+=Integer.toString(number+1);
    				emailNamelist[i] = makeName;
    			}else {
    				nameMap.put(makeName,1);
    				emailNamelist[i] = makeName;
    			}
    		}else if(nameTokenSize==3){//3개면
    			String firstName = st.nextToken();
    			String middleName = st.nextToken();
    			String lastName = st.nextToken();
    			firstName = firstName.toLowerCase();
    			lastName = lastName.toLowerCase();
    			firstName = firstName.replaceAll("-","");
    			lastName = lastName.replaceAll("-","");
    			String makeName = lastName+"_"+firstName;
    			if(nameMap.containsKey(makeName)){
    				int number = nameMap.get(makeName);
    				nameMap.put(makeName,number+1);
    				makeName+=Integer.toString(number+1);
    				emailNamelist[i] = makeName;
    			}else {
    				nameMap.put(makeName,1);
    				emailNamelist[i] = makeName;
    			}
    		}
    	}
    	for(int i=0;i<emailNamelist.length;i++){
    		if(i==emailNamelist.length-1) answer+=namelist[i]+" "+"<"+emailNamelist[i]+companyEmail;
    		else answer+=namelist[i]+" "+"<"+emailNamelist[i]+companyEmail+";"+" ";
    	}
    	System.out.println(answer);
    	return answer;
    }
}
