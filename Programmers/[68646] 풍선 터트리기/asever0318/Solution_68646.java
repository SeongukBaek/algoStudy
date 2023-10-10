import java.util.*;

class Solution {
    static int[] left, right;
    static int leftMin, rightMin, size;
    
    public int solution(int[] a) {
        
        size = a.length;
        left = new int[size]; // 각 인덱스 기준 왼쪽 최소값 저장
        right = new int[size]; // 각 인덱스 기준 오른쪽 최소값 저장 
        leftMin = a[0]; // 왼쪽 최소값 초기화 
        rightMin = a[size-1]; // 오른쪽 최소값 초기화 
        
        for(int i = 1; i < size-1; i++){ // i원소의 왼쪽에 있는 값들 중 최소값을 저장
            if(leftMin > a[i]){
                leftMin = a[i];
            }
            left[i] = leftMin;
        }
        
        for(int i = size-2; i > 0; i--){ // (오른쪽에서부터) i원소의 오른쪽에 있는 값들 중 최소값을 저장 
            if(rightMin > a[i]){ // 만약 더 작은 수면 최소값 갱신
                rightMin = a[i];
            }
            right[i] = rightMin; // 최소값 저장 
        }
        
        if(size == 1){ // 원소가 1개면 무조건 남음
            return 1;
        }
        
        return getBalloonCount(a);
    }
    
    static int getBalloonCount(int[] a){
        int count = 2; // 가장 왼, 오른쪽은 무조건 남을 수 있다(마지막에 사용하면)
        
        for(int i = 1; i < size-1; i++){
            if(a[i] <= left[i] || a[i] <= right[i]){ // 하나라도 나보다 같거나 크면, 그 수가 삭제되기 때문에 나는 남을 수 있음 
                count++;
            }
        }
        
        return count;
    }
}