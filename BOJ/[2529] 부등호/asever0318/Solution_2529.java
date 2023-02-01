package BOJ;

import java.util.*;

//2529�� �ε�ȣ
public class Solution_2529 {
	private static int K;
	private static char[] c;
	private static int[] result; 
	private static boolean[] check;
	private static int index = 0; //result�� ��ġ ǥ�� 
	private static int count = 0; //���� ó�� �ϼ��� ���ڸ� ����ϱ� ���� ǥ��, count�� 0�� �ƴϸ� ��͸��߱�
	private static int depth = 0; //�ε�ȣ �迭�� ��ġ ǥ��(���° �ε�ȣ �������)
	
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
			//�湮���� �ʾҰ� �ε�ȣ�� �����ϸ�
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
			//ù ��° �����̰ų� �湮���� �ʾҰ� �ε�ȣ�� �����ϸ�
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
		//9�������� ������� �Ųٷ� ������ �� �ִ� ���� ū ���ڸ� �����ϸ� max��, �ݴ�� �ϸ� min�� 
		
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		c = new char[K];
		result = new int[K+1]; //�ε�ȣ �������� ���ڰ� 1�� ����
		check = new boolean[10]; //0~9���� �湮���� üũ
		
		
		for(int i = 0; i<K; i++) {
			//��ĳ�ʸ� �̿��� char �Է¹ޱ� 
			c[i] = sc.next().charAt(0);
		}
		
		//��Ʈ��ŷ
		recurMax(0, 0);
		count = 0;
		recurMin(0, 0);
	}
}
