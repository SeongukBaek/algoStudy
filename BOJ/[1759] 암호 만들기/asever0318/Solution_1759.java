package BOJ;

import java.util.*;

public class Solution_1759 {
	
	private static int L; //개수
	private static int C; //문자의 종류 수
	private static char[] clist; //주어진 문자 담을 배열
	private static char[] code; //만든 암호 넣을 배열
	
	//L개 뽑기
	public static void recur(int num, int depth) {
		
		if(depth == L) { //L개 다 뽑으면 
			//모음과 자음 갯수 확인 
			if(check(code) == true) {
				System.out.println(code);
				//return; //리턴 위치 조심! ArrayindexOutOfBounds 에러 - 범위를 벗어났을 때 발생
			}
			return; //리턴해줘야 빠져나가서 배열 처음부터 채울 수 있음
		}
		
		for(int i = num; i < C; i++) {

			code[depth] = clist[i];
			recur(i + 1, depth + 1);
		}
	}
	
	public static boolean check(char[] code) {
		int c = 0; //자음
		int v = 0; //모음
		
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
		
		//모음이 1개 이상히고 자음이 2개 이상이면 암호 출력
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
	
		//알파벳 순서에 맞게 만들기 때문에 먼저 정렬해주기 
		Arrays.sort(clist);
		recur(0, 0);
		
		
	}
}
