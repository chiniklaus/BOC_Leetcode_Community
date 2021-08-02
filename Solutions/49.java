// 49. Group Anagrams

// use a map to group them

// time O(n * log(l)), space O(n*l)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String ns = new String(arr);
            if (!map.containsKey(ns)) map.put(ns, new ArrayList<>());
            map.get(ns).add(s);
        }
        for (List<String> l : map.values()) ans.add(l);
        return ans;
    }
}
