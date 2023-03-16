class Solution { 
	static int time;
	static int alpMax, copMax;
	static int[][] minTime; // 해당 [알고력][코딩력]에 도달하는 최소 시간 저장 
	
	static int solution(int alp, int cop, int[][] problems) {
		int answer = 0;
        alpMax = alp;
        copMax = cop;
        
		// alp, cop 최대값 찾기 
		for(int i = 0; i < problems.length; i++) {
            alpMax = Math.max(alpMax, problems[i][0]);
            copMax = Math.max(copMax, problems[i][1]);
		}
        
        // 이미 문제를 다 풀 수 있는 알고력, 코딩력 가지고 있으면 
        if(alp == alpMax && cop == copMax) {
			return answer;
		}
        
		// 최소값을 저장할 거니까 큰 값으로 초기화 
		minTime = new int[450][450];
		for(int i = 0; i <= alpMax; i++) {
			for(int j = 0; j <= copMax; j++) {
				minTime[i][j] = Integer.MAX_VALUE;
			}
		}
		// 처음에 이미 가지고 있는 알고력, 코딩력이므로 걸리는 시간 = 0
		minTime[alp][cop] = 0; 
		
		for(int i = alp; i <= alpMax; i++) {
			for(int j = cop; j <= copMax; j++) {
				// 1의 시간을 들여 알고력을 1 얻기
				// 현재 위치에서 알고력을 1얻었을 때의 시간과 이미 저장되어 있는 알고력+1까지의 최단시간을 비교 --> 더 적은 시간 저장  
				if(i+1 <= alpMax) {
					minTime[i+1][j] = Math.min(minTime[i+1][j], minTime[i][j] + 1);					
				}
				
				if(j+1 <= copMax) {
					minTime[i][j+1] = Math.min(minTime[i][j+1], minTime[i][j] + 1); // 1의 시간을 들여 코딩력 1얻기 --> 똑같이 비교 더 적은 시간 저장 					
				}
				
				// 각 문제에 대해서 문제를 풀 수 있으면 최단 시간 구해주기 
				for(int p = 0; p < problems.length; p++) {
					// 문제를 풀 수 있으면 --> i = alp / j = cop이므로 problem이 요구하는 alp, cop값 이상이면 가능
					if(problems[p][0] <= i && problems[p][1] <= j) {
						int alpRwd = problems[p][2];
						int copRwd = problems[p][3];
						int time = problems[p][4];
                        
                        // 문제를 풀고 난 후 알고력, 코딩력이 Max(목표값)를 넘으면 Max값에 넣어줘야 됨 
						// alpMax = 20, i+alpRwd = 23이라면 20에 최소값을 넣어줘야 함!
						alpRwd = Math.min(i+alpRwd, alpMax);
						copRwd = Math.min(j+copRwd, copMax);
						
						minTime[alpRwd][copRwd] = Math.min(minTime[alpRwd][copRwd], minTime[i][j]+time);
					}
				}
			}
		}
		
		// alpMax, copMax 값을 가지게 되는 최소 시간 반환 
		answer = minTime[alpMax][copMax];
		
		return answer;
	}
}