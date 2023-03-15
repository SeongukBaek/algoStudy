class Solution {
    public long solution(int w, int h) {
        int temp, num;
        if (h > w) {
            temp = h;
            h = w;
            w = temp;
        }

        num = gcd(w, h);
        return (long) w * h - (((w / num) + (h / num) - 1) * num);
    }

    public int gcd(int a, int b) {
        int temp;
        while (b > 0) {
            temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}