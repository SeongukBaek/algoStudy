class Solution {
    public static long solution(int w, int h) {
        long answer = 1;
        long gcd = calGcd(w, h);
        answer = ((long) w * h) - (((w / gcd) + (h / gcd) - 1) * gcd);
        return answer;
    }

    public static long calGcd(int w, int h) {
        if (h == 0) {
            return w;
        }
        return calGcd(h, w % h);
    }
}