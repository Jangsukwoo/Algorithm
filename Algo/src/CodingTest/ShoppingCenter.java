package CodingTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/*
 * 현카
 * 10:06~
 * 0~만
 * 만~2만
 * 2만~5만
 * 5만~10만
 * 10만~
 */
public class ShoppingCenter {
	static StringTokenizer st;
	static int bronze,silver,gold,platinum,diamond;
	static int[] day = new int[366];
	public static void main(String[] args) throws ParseException {
		solution(new String[] {"2019/01/01 5000", "2019/01/20 15000", "2019/02/09 90000"});
		//solution(new String[] {"2019/01/30 5000", "2019/04/05 10000", "2019/06/10 20000", "2019/08/15 50000", "2019/12/01 100000"});
	}
	public static int[] solution(String[] purchase) throws ParseException {
		String[] todate = new String[purchase.length];
		Date[] dates = new Date[purchase.length];
		String[] price = new String[purchase.length];

		int startday = 1;
		for(int i=0;i<purchase.length;i++) {
			st = new StringTokenizer(purchase[i]);
			String tempdate = st.nextToken();
			String temp = tempdate.replace('/','-').trim();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			todate[i] = formatter.format(new Date());
			System.out.println(temp);
			dates[i] = formatter.parse(temp);
			price [i]= st.nextToken();
		}
		SimpleDateFormat start = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = start.parse("2019-01-01");
		for(int i=0;i<purchase.length;i++){
			long diff = dates[i].getTime()-startDate.getTime();
			long diffDays = (diff/(24*60*60*1000));
			int diffday = (int) diffDays;
			for(int j=diffday;j<(diffday+30);j++) {
				if(j<=365) {
					day[j]+=Integer.parseInt(price[i]);
				}
			}
		}
		for(int i=1;i<=365;i++) {
			if(day[i]>=0 && day[i]<10000) bronze++;
			else if(day[i]>=10000 && day[i]<20000) silver++;
			else if(day[i]>=20000 && day[i]<50000) gold++;
			else if(day[i]>=50000 && day[i]<100000) platinum++;
			else if(day[i]>=100000) diamond++;
			System.out.println(i+"일" + "누적금액:"+day[i]);
		}
		
		int[] answer = new int []{bronze,silver,gold,platinum,diamond};
		return answer;
	}
}
