import java.util.*;

class Solution {
    int[] union = new int[2501];
    String[] value = new String[2501];
    
    int find(int a) {
        if(union[a] == a) {
            return a;
        }
        else {
            return union[a] = find(union[a]);
        }
    }
    
    void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            union[b] = a;
        }
    }
    
    int convert(int x, int y) {
        int result = 50 * (x - 1);
        return result + y;
    }
    
    public String[] solution(String[] commands) {
        List<String> result = new ArrayList<>();
        
        for (int i = 1; i <= 2500; i++) {
            union[i] = i;
            value[i] = "";
        }
        
        for (int i = 0; i < commands.length; i++) {
            String[] command = commands[i].split(" ");
            String type = command[0];
            
            //명령어가 UPDATE인 경우
            if (type.equals("UPDATE")) {
                //"UPDATE value1 value2"인 경우
                if (command.length == 3) {
                    String before = command[1];
                    String after = command[2];
                    for (int j = 1; j <= 2500; j++) {
                        if (value[j].equals(before)) {
                            value[j] = after;
                        }
                    }
                    continue;
                } 
                //"UPDATE r c value"인 경우
                if (command.length == 4) {
                    int x = Integer.parseInt(command[1]);
                    int y = Integer.parseInt(command[2]);
                    String after = command[3];
                    int idx = convert(x, y);
                    value[find(idx)] = after;
                }
                continue;
            }
            //명령어가 MERGE인 경우
            if(type.equals("MERGE")) {
                int x1 = Integer.parseInt(command[1]);
                int y1 = Integer.parseInt(command[2]);
                int x2 = Integer.parseInt(command[3]);
                int y2 = Integer.parseInt(command[4]);
                
                int idx1 = convert(x1, y1);
                int idx2 = convert(x2, y2);
                
                int root1 = find(idx1);
                int root2 = find(idx2);
                
                if(root1 == root2) {
                    continue;
                }
                
                if(value[root1].equals("")) {
                    value[root1] = value[root2];
                }
                value[root2] = "";
                union(root1, root2);
                continue;
            }
            //명령어가 UNMERGE인 경우
            if (type.equals("UNMERGE")) {
                int x = Integer.parseInt(command[1]);
                int y = Integer.parseInt(command[2]);
                
                int idx = convert(x, y);
                int root = find(idx);
                
                String rootString = value[root];
                value[root] = "";
                value[idx] = rootString;
                
                
                List<Integer> deletes = new ArrayList<>();
                for (int j = 1; j <= 2500; j++) {
                    if(find(j) == root) {
                        deletes.add(j);
                    }
                }
                
                for (int deleteIdx : deletes) {
                    union[deleteIdx] = deleteIdx;
                }
                continue;
            }
            //명령어가 PRINT인 경우
            if (type.equals("PRINT")) {
                int x = Integer.parseInt(command[1]);
                int y = Integer.parseInt(command[2]);
                int idx = convert(x, y);
                int root = find(idx);
                
                if(value[root].equals("")) {
                    result.add("EMPTY");
                } else {
                    result.add(value[root]);
                }
            }
        }
        
        return result.toArray(new String[0]);
    }
}