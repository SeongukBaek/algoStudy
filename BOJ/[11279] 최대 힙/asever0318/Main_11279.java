import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N;
	static List<Integer> heap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		heap = new ArrayList<>();
		heap.add(-1); // 0번째는 사용하지 않을 것이라서 미리 값 0으로 초기화 
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(num != 0) { // 0이 아니면 값을 삽입 
				insert(num);
			}
			
			if(num == 0) { // 0이면 최대 값(루트)를 출력 
				
				if(heap.size() == 1) { // 힙이 비어있으면 0 출력 
					System.out.println(0);
				}
				
				if(heap.size() > 1) { // 힙이 비어있지 않으면 delete 수행 			
					System.out.println(delete());
				}
			}
		}
	}
	
	// 힙에 삽입 
	public static void insert(int num) {
		
		heap.add(num); // 맨 마지막에 삽입 
		
		int currentPos = heap.size()-1; // 삽입한 값 위치 
		
		while(currentPos > 1) {
			
			int parentsPos = currentPos/2; // 현재 위치 기준 부모 위치
			int parentsNum = heap.get(parentsPos); // 현재 위치 기준 부모 값	
			
			// 부모가 해당 숫자보다 크면 멈춤 
			if(parentsNum > num) {
				break;
			}
			
			// 부모보다 해당 숫자가 크면 교환 
			int temp = parentsNum;
			heap.set(parentsPos, num);
			heap.set(currentPos, temp);
			
			currentPos = parentsPos; // 현재 위치 바꿔주기 
		}
	}
	
	// 힙에서 삭제 
	public static int delete() {
		int max = heap.get(1); // 최대값 저장 
		
		int num = heap.get(heap.size()-1);
		heap.set(1, num); // 가장 끝에 있는 값을 루트로 옮김
		heap.remove(heap.size()-1); // 끝에 있는 값 삭제 
		
		int currentPos = 1; // 현재 숫자 위치 
		
		// 루트에서부터 자리 찾아주기 
		while((currentPos*2) < heap.size()) { // 자식노드가 힙의 범위를 벗어나지 않는다면
			
			int childPos = currentPos*2;
			int childNum = heap.get(childPos);
			
			// 오른쪽 자식이 존재하고 왼쪽 자식보다 크면 오른쪽 자식으로 바꿔주기
			if((childPos+1) < heap.size()) { // 만약 오른쪽 자식이 존재한다면 
				if(childNum < heap.get(childPos+1)) { // 왼쪽 자식과 비교 
					childPos = childPos+1;
					childNum = heap.get(childPos);
				}
			}
			
			if(childNum < num) {
				break;
			}
			
			// 자식이 현재 숫자보다 크면 바꿔주기 
			int temp = childNum;
			heap.set(childPos, num);
			heap.set(currentPos, temp);
			currentPos = childPos; // 자식 위치로 현재 위치 변경 
		}
		
		return max;
	}
	
}


// x가 자연수 -> 배열에 x 값 추가
// x가 0 	-> 가장 큰 값 출력 후 배열에서 제거 --> 루트 삭제 

// 최대 힙 : 각 노드의 값이 그 자식의 값보다 크거나 같은 완전 이진 트리 
// 완전 이진 트리 : 노드를 삽입할 때 왼쪽부터 차례대로 삽입하는 트리 