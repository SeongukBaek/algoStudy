import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
	class bookInfo {
		int time;
		int type;

		bookInfo(int time, int type) {
			this.time = time;
			this.type = type;
		}
	}

	public int solution(String[][] book_time) {
		int minCount = 0;
		int count = 0;
        // 시간 오름차순으로 정렬, 시간이 같다면 퇴실에 대한 정보가 앞으로
		Queue<bookInfo> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.time == o2.time) {
				return o2.type - o1.type;
			}
			return o1.time - o2.time;
		});

		for (int i = 0; i < book_time.length; i++) {
			String startTime = book_time[i][0];
			String endTime = book_time[i][1];
            //입실 정보
			pq.add(new bookInfo(convertToMinute(startTime), 1));
            //퇴실 정보
			pq.add(new bookInfo(convertToMinute(endTime) + 10, 2));
		}

		while (!pq.isEmpty()) {
			bookInfo cur = pq.poll();
            //입실에 대한 정보라면
			if (cur.type == 1) {
                //현재 사용중인 방 갯수++
				count++;
                //최소 객실 수 갱신
				minCount = Math.max(count, minCount);
				continue;
			}
            //퇴실에 대한 정보라면
			if (cur.type == 2) {
                //현재 사용중인 방 갯수--
				count--;
			}
		}

		return minCount;
	}

    /*
    * HH:MM -> M으로 변환하는 메소드
    */
	int convertToMinute(String time) {
		String[] str = time.split(":");
		return Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
	}
}