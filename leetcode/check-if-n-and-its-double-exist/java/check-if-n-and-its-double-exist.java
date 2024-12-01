import java.util.*;

class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> numSet = new HashSet<>();

        for (int index = 0; index < arr.length; index++) {
            int current = arr[index];

            if (numSet.contains(current)) {
                return true;
            }

            if (current % 2 == 0) {
                numSet.add(current / 2);
            }
            numSet.add(current * 2);
        }

        return false;
    }
}