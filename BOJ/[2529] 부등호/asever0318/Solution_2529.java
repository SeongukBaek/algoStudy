package BOJ;

import java.util.*;

//2529번 부등호
public class Solution_2529 {
	private static int K;
	private static char[] c;
	private static int[] result; 
	private static boolean[] check;
	private static int index = 0; //result의 위치 표시 
	private static int count = 0; //가장 처음 완성된 숫자만 출력하기 위해 표시, count가 0이 아니면 재귀멈추기
	private static int depth = 0; //부등호 배열의 위치 표시(몇번째 부등호 사용할지)
	
	public static void recurMin(int num, int depth) {
		
		if(count != 0)
			return;
		
		if(num == K + 1) {
			String str = Arrays.stream(result)
	                .mapToObj(String::valueOf)
	                .reduce((x, y) -> x + y)
	                .get();
			System.out.println(str);
			/*for(int i = 0; i<index; i++)
				System.out.print(result[i]);
			System.out.println();*/
			count = 1;
			return;
		}
		
		for(int i = 0; i<=9; i++) {
			//방문하지 않았고 부등호가 성립하면
			if(depth == 0 || check[i] == false && compare(result[num - 1], i, c[depth-1])) {
				check[i] = true;
				result[index] = i;
				index++;
				recurMin(num+1, depth + 1);
				check[i] = false;
				index--;
			}
		}
	}
	
	
	
	public static void recurMax(int num, int depth) {
		
		if(count != 0)
			return;
		
		if(num == K + 1) {
			String str = Arrays.stream(result)
	                .mapToObj(String::valueOf)
	                .reduce((x, y) -> x + y)
	                .get();
			System.out.println(str);
			/*for(int i = 0; i<index; i++)
				System.out.print(result[i]);
			System.out.println();*/
			count = 1;
			return;
		}
		
		for(int i = 9; i>=0; i--) {
			//첫 번째 숫자이거나 방문하지 않았고 부등호가 성립하면
			if(depth == 0 || check[i] == false && compare(result[num - 1], i, c[depth-1])) {
				check[i] = true;
				result[index] = i;
				index++;
				recurMax(num+1, depth + 1);
				check[i] = false;
				index--;
			}
		}
	}
	
	public static boolean compare(int a, int b, char c) {
		if(c == '<')
			return a < b;
		else
			return a > b;
	}
	
	public static void main(String[] args) {
		//9에서부터 순서대로 거꾸로 선택할 수 있는 가장 큰 숫자를 선택하면 max값, 반대로 하면 min값 
		
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		c = new char[K];
		result = new int[K+1]; //부등호 갯수보다 숫자가 1개 많음
		check = new boolean[10]; //0~9까지 방문여부 체크
		
		
		for(int i = 0; i<K; i++) {
			//스캐너를 이용해 char 입력받기 
			c[i] = sc.next().charAt(0);
		}
		
		//백트래킹
		recurMax(0, 0);
		count = 0;
		recurMin(0, 0);
	}
}

