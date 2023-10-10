class Solution {
    static int[] room; // 배열에 저장된 최대값이 최소 필요한 방의 수  
    static int max;
    
    public int solution(String[][] book_time) {
        room = new int[24*60+10]; // 24시간*60분+10분 -> 가장 늦게 끝나는 시간 
        
        for(int i = 0; i < book_time.length; i++){
            int start = getMinutes(book_time[i][0]);
            int end = getMinutes(book_time[i][1]);
            
            room[start] += 1; // 시작 시간에는 1 저장 
            room[end + 10] += -1; // 끝나는 시간 + 청소시간 10분에는 -1 저장
        }
        
        prefixSum(); // 누적합 
        
        return max;
    }
    
    static int getMinutes(String time){
        int total = 0;
        String[] temp = time.split(":");
        
        total = Integer.parseInt(temp[0])*60;
        total += Integer.parseInt(temp[1]);
        
        return total; 
    }
    
    static void prefixSum(){
        for(int i = 1; i < 24*60+10; i++){
            room[i] += room[i-1]; // 이전 값을 더하기 
            max = Math.max(max, room[i]); // 최대값 갱신(방의 수)
        }
    }
}

// 누적합 방법
// 1. 시작 위치에 1을 추가하고 끝위치에-1을 추가한다.
// 2. 앞에서부터 뒤로 진행하면서 이전의 값을 더한다.