// 224. Basic Calculator

// Since there are parenthesis, we need to change the calculation order
// we use a stack to do that. we go through the numbers from left to right
// if we see a parenthesis, we push current answer and operator to stack,
// after dealing with the parenthesis sub problem, we treat the sub problem
// like a new number we just read.

// Time O(n), space O(n)

// using stack
class Solution {
    public int calculate(String s) {
        Stack<Integer> st = new Stack<>();
        int number = 0;
        int pointer = 0;
        int running_ans = 0;
        int len = s.length();
        int opr = 1;
        while (pointer < len) {
            char c = s.charAt(pointer);
            if (Character.isDigit(c)) {
                number = number * 10 + (c-'0');
            } else if (c == '(') {
                st.push(running_ans);
                st.push(opr);
                running_ans = 0;
                opr = 1;
            } else if (c == ')') {
                if (number != 0) {
                    running_ans += opr * number;
                    number = 0;
                }
                running_ans = running_ans * st.pop() + st.pop();
            } else if (c == '+' || c == '-') {
                running_ans += opr * number;
                opr = c == '+' ? 1 : -1;
                number = 0;
            }
            pointer++;
        }
        return running_ans + opr * number;
    }
}

// using recursion
class Solution {
    int pointer = 0;
    public int calculate(String s) {
        int number = 0;
        int running_ans = 0;
        int len = s.length();
        int opr = 1;
        while (pointer < len) {
            char c = s.charAt(pointer);
            if (Character.isDigit(c)) {
                number = number * 10 + (c-'0');
            } else if (c == '(') {
                pointer++;
                number = calculate(s);
            } else if (c == ')') {
                running_ans += opr * number;
                return running_ans;
            } else if (c == '+' || c == '-') {
                running_ans += opr * number;
                opr = c == '+' ? 1 : -1;
                number = 0;
            }
            pointer++;
        }
        return running_ans + opr * number;
    }
}