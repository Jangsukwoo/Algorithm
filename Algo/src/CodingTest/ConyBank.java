package CodingTest;

import java.util.HashMap;
import java.util.TreeMap;

public class ConyBank {
	public static void main(String[] args) {
		solution(new String[][] {
			{"ACCOUNT1", "100"}, 
			{"ACCOUNT2", "150"}
		},
			new String[][] {{"1", "SAVE", "ACCOUNT2", "100"},
			{"2", "WITHDRAW", "ACCOUNT1", "50"}, 
			{"1", "SAVE", "ACCOUNT2", "100"}, 
			{"4", "SAVE", "ACCOUNT3", "500"}, 
			{"3", "WITHDRAW", "ACCOUNT2", "30"}
		});
	}
	public static String[][] solution(String[][] snapshots, String[][] transactions) {
		String[][] answer = {};
		TreeMap<String,Integer> snapShotMap = new TreeMap<String, Integer>();
		HashMap<Integer,String[]> transactionMap = new HashMap<Integer, String[]>();
		for(int i=0;i<snapshots.length;i++) snapShotMap.put(snapshots[i][0],Integer.parseInt(snapshots[i][1]));
		//계좌 이름이 다름
		//스냅샷은 계좌 이름과 잔액
		for(int i=0;i<transactions.length;i++){ //id 중복인것 다 제거
			int id = Integer.parseInt(transactions[i][0]);
			if(transactionMap.containsKey(id)) continue;
			else transactionMap.put(id,transactions[i]);
		}
		for(Integer id : transactionMap.keySet()) {
			String[] transaction = transactionMap.get(id);
			String transactionKind = transaction[1];
			String account = transaction[2];
			String amount = transaction[3];
			if(snapShotMap.containsKey(account)){//존재하는 경우
				int money=0;
				switch (transactionKind) {
				case "SAVE":
					money = snapShotMap.get(account);
					money+= Integer.parseInt(amount);
					snapShotMap.put(account,money);
					break;
				case "WITHDRAW":
					money = snapShotMap.get(account);
					money-= Integer.parseInt(amount);
					snapShotMap.put(account,money);
					break;
				}				
			}else {
				snapShotMap.put(account,Integer.parseInt(amount));
			}
		}
		answer = new String[snapShotMap.size()][2];
		int idx = 0;
		for(String data : snapShotMap.keySet()){
			answer[idx][0] = data;
			answer[idx][1] = Integer.toString(snapShotMap.get(data));
			idx++;
		}
		return answer;
	}
}
