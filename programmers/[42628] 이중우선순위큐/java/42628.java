import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        List<Integer> ascList = new ArrayList<>();
        List<Integer> descList = new ArrayList<>();
        int size = 0;
        
        for (String operation : operations) {
            String[] splittedOp = operation.split(" ");
            String op = splittedOp[0];
            int num = Integer.parseInt(splittedOp[1]);
            
            if ("D".equals(op)) {
                if (size == 0) {
                    continue;
                }
                
                ascList.sort(Comparator.naturalOrder());
                descList.sort(Comparator.reverseOrder());
                size--;
                
                if (num < 0) {
                    ascList.remove(0);
                    descList.remove(size);
                } else {
                    descList.remove(0);
                    ascList.remove(size);
                }
            } else {
                ascList.add(num);
                descList.add(num);
                size++;
            }
        }
        
        if (size == 0) {
            return new int[] {0, 0};
        }
        
        ascList.sort(Comparator.naturalOrder());
        descList.sort(Comparator.reverseOrder());
        return new int[] {descList.get(0), ascList.get(0)};
    }
}