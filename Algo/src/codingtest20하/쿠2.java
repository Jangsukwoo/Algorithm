package codingtest20하;

import java.util.StringTokenizer;

public class 쿠2 {
	static StringTokenizer st;
	static int[] months = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	static Kiosk[] kiosks;
	static class Kiosk implements Comparable<Kiosk>{
		int id;
		int match;
		int doneTime;
		boolean use;
		public Kiosk(int id, int match, int doneTime , boolean use) {
			this.id = id;
			this.match = match;
			this.doneTime = doneTime;
			this.use = use;
		}
		@Override
		public int compareTo(Kiosk o) {
			return Integer.compare(this.doneTime,o.doneTime);
		}
	}
	public static void main(String[] args) {
	      int n = 3;
	      String[] customers = { "10/01 23:20:25 30", "10/01 23:25:50 26", "10/01 23:31:00 05", "10/01 23:33:17 24", "10/01 23:50:25 13", "10/01 23:55:45 20", "10/01 23:59:39 03", "10/02 00:10:00 10" };
	      System.out.println(solution(3, customers));

	} 
	public static int solution(int n, String[] customers) {
        int answer = 0;
        kiosks = new Kiosk[n+1];
        for(int id=1;id<=n;id++) {
        	kiosks[id] = new Kiosk(id,0,0,false);
        }
        //고객 도착 시간 전부 초로 정량화
        for(int i=0;i<customers.length;i++) {
        	int second = 0;
        	int takeSecond =0;
        	st = new StringTokenizer(customers[i]);
        	String arrivalDateInfo = st.nextToken();
        	String arrivalTimeInfo = st.nextToken();
        	String takeTimeInfo= st.nextToken();
        	String[] date = arrivalDateInfo.split("/");
        	String month_String = date[0];
        	String day_String = date[1];
        	
        	int month = Integer.parseInt(month_String);
        	int day = Integer.parseInt(day_String);
        	
        	for(int m=1;m<month;m++) day+=months[m];
        	
        	String[] arrivalTime = arrivalTimeInfo.split(":");
        	String hour_string = arrivalTime[0];
        	String minute_string = arrivalTime[1];
        	String second_string = arrivalTime[2];
        	int hour = Integer.parseInt(hour_string)+(day*24);
        	int minute = Integer.parseInt(minute_string)+(hour*60);
        
        	second = Integer.parseInt(second_string)+minute*60;
        	takeSecond = Integer.parseInt(takeTimeInfo)*60;
        	
          	for(int id=1;id<=n;id++){//키오스크 중에 끝난 애가있다면
        		if(kiosks[id].use) {
        			if(kiosks[id].doneTime<=second) kiosks[id].use=false;
        		}
        	}
          	boolean possible = false;
        	for(int id=1;id<=n;id++){//키오스크 중에
        	
        		if(kiosks[id].use==false){
        			possible = true;
        			kiosks[id].doneTime = second+takeSecond;
        			kiosks[id].match+=1;
        			kiosks[id].use = true;
        			System.out.println("매치아이디"+id);
        			break;
        		}
        	}
        	if(possible==false){
        		int maxdone = 0;
            	for(int id=1;id<=n;id++){//키오스크 중에
            		maxdone = Math.max(kiosks[id].doneTime,maxdone);
            	}
            	int idx =0;
            	for(int id=1;id<=n;id++){//키오스크 중에
            		if(kiosks[id].doneTime==maxdone) {
            			idx= id;
            			break;
            		}
            	}
            	System.out.println(idx);
    			kiosks[idx].doneTime = second+takeSecond;
    			kiosks[idx].match+=1;
    			kiosks[idx].use = true;
        	}
        }
        
     	for(int id=1;id<=n;id++){//키오스크 중에
     		answer = Math.max(answer,kiosks[id].match);
    	}
        
        return answer;
    }
}
