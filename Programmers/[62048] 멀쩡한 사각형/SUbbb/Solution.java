class Solution {
    public long solution(int w, int h) {
        long convertW = (long) w;
        long convertH = (long) h;
        long answer = convertW * convertH;
        
        // 최대 공약수 구하기
        long gdc = computeGdc(convertW, convertH);
        
        convertW /= gdc;
        convertH /= gdc;
        
        // 최대 공약수 * (w, h를 최대 공약수로 나눈 크기의 못 쓰는 사각형 수 구하기)
        return answer - (gdc * computeUnavailableSquare(convertW, convertH));
    }
    
    private static long computeGdc(long w, long h) {
        if (w < h) {
            long swapTemp = w;
            w = h;
            h = swapTemp;
        }
        
        while (h != 0) {
            long divider = w % h;
            w = h;
            h = divider;
        }
        
        return w;
    }
    
    private static long computeUnavailableSquare(long w, long h) {
        if (w == 1 || h == 1) {
            return Math.max(w, h);
        }
        
        if (w == h) {
            return w;
        }
        
        return w + h - 1;
    }
}