package BOJ;

import java.util.*;

public class Solution_1759 {
	
	private static int L; //����
	private static int C; //������ ���� ��
	private static char[] clist; //�־��� ���� ���� �迭
	private static char[] code; //���� ��ȣ ���� �迭
	
	//L�� �̱�
	public static void recur(int num, int depth) {
		
		if(depth == L) { //L�� �� ������ 
			//������ ���� ���� Ȯ�� 
			if(check(code) == true) {
				System.out.println(code);
				//return; //���� ��ġ ����! ArrayindexOutOfBounds ���� - ������ ����� �� �߻�
			}
			return; //��������� ���������� �迭 ó������ ä�� �� ����
		}
		
		for(int i = num; i < C; i++) {

			code[depth] = clist[i];
			recur(i + 1, depth + 1);
		}
	}
	
	public static boolean check(char[] code) {
		int c = 0; //����
		int v = 0; //����
		
		for(int a = 0; a < code.length; a++){
			switch(code[a]){
				case 'a': 
				case 'e': 
				case 'i': 
				case 'o':
				case 'u': v++; break;
				default : c++; break;
			}
		}
		
		//������ 1�� �̻����� ������ 2�� �̻��̸� ��ȣ ���
		if(v >= 1 && c >= 2)
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		L = sc.nextInt();
		C = sc.nextInt();
		
		code = new char[L];
		clist = new char[C];
		
		for(int i = 0; i < C; i++) {
			clist[i] = sc.next().charAt(0);
		}
	
		//���ĺ� ������ �°� ����� ������ ���� �������ֱ� 
		Arrays.sort(clist);
		recur(0, 0);
		
	}
}
