// 772. Basic Calculator III

// this problem is the combination of basic calculator one and two. we solve this by
// divide and conquer. basically when we encounter a left parenthesis, we recursively
// find the solution in the subproblem and treat the subproblem as a number

// time O(n), space O(n)

class Solution {
    public int calculate(String s) {
        int number = 0;
        int pointer = 0;
        int len = s.length();
        Stack<Integer> st = new Stack<>();
        char opr = '+';
        while (pointer < len) {
            char c = s.charAt(pointer);
            if (Character.isDigit(c)) {
                number = number * 10 + (c-'0');
            }
            
            if (c == '(') {
                pointer++;
                int begin = pointer;
                int left = 1;
                while (left != 0) {
                    if (s.charAt(pointer) == '(') {
                        left++;
                    } else if (s.charAt(pointer) == ')') {
                        left--;
                    }
                    pointer++;
                }
                number = calculate(s.substring(begin,pointer));
                pointer--;
            }
            
            if (!Character.isDigit(c) && c != ' ' || pointer == len-1) {
                if (opr == '+') {
                    st.push(number);
                } else if (opr == '-') {
                    st.push(-number);
                } else if (opr == '*') {
                    st.push(st.pop() * number);
                } else if (opr == '/') {
                    st.push(st.pop() / number);
                }
                number = 0;
                opr = c;
            }
            pointer++;
        }
        if (number != 0) {
            if (opr == '+') {
                st.push(number);
            } else if (opr == '-') {
                st.push(-number);
            } else if (opr == '*') {
                st.push(st.pop() * number);
            } else if (opr == '/') {
                st.push(st.pop() / number);
            }
        }
        int ans = 0;
        while (!st.isEmpty()) ans += st.pop();
        return ans;
    }
}