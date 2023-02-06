package BOJ;

import java.util.*;
import java.io.*;


//우선순위 큐 : 들어간 순서 상관없이 우선순위기 높은 데이터가 먼저 나오는 것 
public class Main_11000 {

	
	static List<Lecture> llist;
	static PriorityQueue<Integer> queue = new PriorityQueue<Integer>(); // 우선순위큐 기본형 - 낮은 숫자가 우선
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		String[] str = new String[2];
		int cnt;
		
		llist = new ArrayList<Lecture>();

		for(int i = 0; i < N; i++) {
			str = bf.readLine().split(" ");
			llist.add(new Lecture(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
		}
		
		// 강의실을 최소로 쓰도록 
		// 강의시간이 겹치지 않는 강의를 최대한 많이 뽑는 것 
		// 강의 시작시간이 빠른 순으로 정렬 
		Collections.sort(llist);
		
		cnt = classroom(N);
		
		System.out.println(cnt);

		bf.close();
		
	}
	
	// 우선순위 큐를 사용하여 가장 먼저 끝나는 시간을 계속 최신화 유지
	public static int classroom(int N) {
		// 가장 처음 시작하는 강의는 무조건 강의실 할당 
		queue.offer(llist.get(0).getEnd()); // 수업 종료시간을 큐에 넣어서 강의실 배정 
		
		for(int i = 1; i < N; i++) {
			// 배정 받은 강의실의 가장 빠른 종료시간과 비교해서 
			if(llist.get(i).getStart() >= queue.peek())
				queue.poll(); // 강의실에 넣을 수 있으면 넣고 이전 종료시간은 큐에서 삭제 
							  // poll()은 우선순위가 가장 우선인 값을 꺼냄 
			
			queue.offer(llist.get(i).getEnd()); // 새 종료시간 큐에 넣기 or 새 강의실 배정 
		}
		return queue.size(); // 큐에 들어있는 요소 수가 배정된 강의실 수 
	}
	
	/*
	public static int classroom(int N) {
		int rcount = 0; // 강의실 수 
		
		
		// O(N^2) 방법으로는 해결X...
		while(!llist.isEmpty()) {
			// 이전 강의 담을 객체 
			Lecture lecture = new Lecture(llist.get(0).getStart(), llist.get(0).getEnd());
			llist.remove(0);

			for(int i = 1; i < llist.size(); i++) {
				if(llist.get(i).getStart() >= lecture.getEnd()) {
					lecture = llist.get(i);
					llist.remove(i);
				}
			}
			rcount++;
		}
		return rcount;
	}*/
	
	
	public static class Lecture implements Comparable<Lecture>{
		int start;
		int end;
		
		public Lecture(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		// 시작 시간이 같으면 종료 시간이 더 빠른 것으로 정렬하도록 재정의 
		@Override
		public int compareTo(Lecture o) {
			// TODO Auto-generated method stub
			
			if(start == o.start)
				return end - o.end;
			
			return start - o.start;
		}
	}
}
