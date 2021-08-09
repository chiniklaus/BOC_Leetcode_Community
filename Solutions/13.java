// 13. Roman to Integer

// we can just create a map of symbols and values. Iterate through the given String. If we found the previous character represents
// smaller value than the current one, it means we encountered a special situation. We just minus two times the previous value.

// Time O(1), space O(1)
class Solution {
    public int romanToInt(String s) {
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        map.put('@',10000);
        char prev = '@';
        int ans = 0;
        for (char c : s.toCharArray()) {
            ans += map.get(c);
            if (map.get(c) > map.get(prev)) ans -= 2 * map.get(prev);
            prev = c;
        }
        return ans;
    }
}