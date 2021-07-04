// 227. Basic Calculator II

// for this one, since we don't have parenthesis, we just go through all the characters once.
// if it's a number, record it. if it is + -, we put number in stack with the oprator. if it is
// * /, we pop the last number from stack and merge this number and last number using * /.

// Time O(n), space O(n)

class Solution {
    public int calculate(String s) {
        Stack<Integer> st = new Stack<>();
        int pointer = 0;
        int len = s.length();
        int number = 0;
        char opr = '+';
        while (pointer < len) {
            char c = s.charAt(pointer);
            if (Character.isDigit(c)) {
                number = number * 10 + (c-'0');
            }
            if (!Character.isDigit(c) && c != ' ' || pointer == len - 1) {
                if (opr == '+' || opr == '-') {
                    number = opr == '+' ? number : -number;
                    st.push(number);
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
        int ans = 0;
        while (!st.isEmpty()) ans += st.pop();
        return ans;
    }
}

// Time O(n), space O(1)
class Solution {
    public int calculate(String s) {
        int running_ans = 0;
        int pointer = 0;
        int len = s.length();
        int last = 0;
        int number = 0;
        char opr = '+';
        while (pointer < len) {
            char c = s.charAt(pointer);
            if (Character.isDigit(c)) {
                number = number * 10 + (c-'0');
            }
            if (!Character.isDigit(c) && c != ' ' || pointer == len - 1) {
                if (opr == '+' || opr == '-') {
                    running_ans += last;
                    last = opr == '+' ? number : -number;
                } else if (opr == '*') {
                    last = last * number;
                } else if (opr == '/') {
                    last = last / number;
                }
                number = 0;
                opr = c;
            }
            pointer++;
        }
        return running_ans + last;
    }
}