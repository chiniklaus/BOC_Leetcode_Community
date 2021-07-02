// 937. Reorder Data in Log Files

// use priorityqueue to sort. or just sort

class Solution {
    public String[] reorderLogFiles(String[] logs) {
        PriorityQueue<String[]> letters = new PriorityQueue<>((a,b) -> a[0].equals(b[0]) ? a[1].compareTo(b[1]) : a[0].compareTo(b[0]));
        List<String> digits = new ArrayList<>();
        String[] ans = new String[logs.length];
        for (String s : logs) {
            String[] sarr = s.split(" ");
            char c = sarr[1].charAt(0);
            if (c >= '0' && c <= '9') {
                digits.add(s);
            } else {
                String[] arr = new String[3];
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < sarr.length; i++) {
                    sb.append(sarr[i]);
                    sb.append(" ");
                }
                arr[0] = sb.toString();
                arr[1] = sarr[0];
                arr[2] = s;
                letters.offer(arr);
            }
        }
        int pointer = 0;
        while (!letters.isEmpty()) {
            ans[pointer] = letters.poll()[2];
            pointer++;
        }
        for (String s : digits) {
            ans[pointer] = s;
            pointer++;
        }
        return ans;
    }
}