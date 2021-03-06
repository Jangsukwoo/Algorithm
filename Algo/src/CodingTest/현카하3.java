package CodingTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class 현카하3 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[] {"[403]James", "[404]Azad,Louis,Andy", "[101]Azad,Guard"}, 403)));
		System.out.println(Arrays.toString(solution(new String[] {"[101]Azad,Guard", "[202]Guard", "[303]Guard,Dzaz"}, 202)));
		System.out.println(Arrays.toString(solution(new String[] {"[1234]None,Of,People,Here", "[5678]Wow"}, 1234)));
	}
	public static String[] solution(String[] rooms, int target) {
		String[] answer = {};
		
		class PositionData{
			String name;
			ArrayList<Integer> positions;
			int minDistPosition;
			boolean remove;
			public PositionData() {
				this.name = "";
				this.positions = new ArrayList<Integer>();
				this.minDistPosition = Integer.MAX_VALUE;
				this.remove = false;
			}
			@Override
			public String toString() {
				return "PositionData [name=" + name + ", positions=" + positions + ", minDistPosition="
						+ minDistPosition + "]";
			}
		}
		HashMap<String,PositionData> map = new HashMap<String,PositionData>();
		
		for(int room=0;room<rooms.length;room++) {
			String data = rooms[room];
			String[] token = data.split(",");
			int roomNumber = 0;
			String roomNumber_string = "";
			String name = "";

			boolean nameFlag = false;
		
			for(int j=0;j<token.length;j++) {
				if(j==0) {
					for(int i=0;i<token[0].length();i++) {
						if(nameFlag) name+=token[0].charAt(i);
						else if(token[0].charAt(i)>='0' && token[0].charAt(i)<='9') {
							roomNumber_string+=token[0].charAt(i);
						}else if(token[0].charAt(i)==']') {
							nameFlag = true;
						}
					}
					roomNumber = Integer.parseInt(roomNumber_string);
				}
				else {
					name= token[j];
				}
				if(map.containsKey(name)) {
					PositionData positionData = map.get(name);
					positionData.positions.add(roomNumber);
					if(roomNumber==target) positionData.remove = true;
					map.put(name, positionData);
				}else {
					PositionData positionData = new PositionData();
					positionData.name = name;
					positionData.positions.add(roomNumber);
					if(roomNumber==target) positionData.remove = true;
					map.put(name,positionData);	
				}
			}
		}
		
		PriorityQueue<PositionData> pq = new PriorityQueue<PositionData>(new Comparator<PositionData>() {
			@Override
			public int compare(PositionData o1, PositionData o2) {
				if(o1.positions.size()==o2.positions.size()) {
					if(o1.minDistPosition==o2.minDistPosition) {
						return o1.name.compareTo(o2.name);
					}else return Integer.compare(o1.minDistPosition,o2.minDistPosition);
				}
				return Integer.compare(o1.positions.size(),o2.positions.size());
			}
		});
		
		for(String name : map.keySet()) {
			PositionData positionData = map.get(name);
			if(positionData.remove) continue;
			for(int i=0;i<positionData.positions.size();i++) {
				int number = positionData.positions.get(i);
				int absValue = Math.abs(number-target);
				positionData.minDistPosition = Math.min(positionData.minDistPosition,absValue);
			}
			pq.add(positionData);
		}

		
		ArrayList<String> answerlist=new ArrayList<String>();
		while(!pq.isEmpty()) answerlist.add(pq.poll().name);
		answer = new String[answerlist.size()];
		for(int i=0;i<answerlist.size();i++) {
			answer[i] = answerlist.get(i);
		}
		return answer;
	}
}
