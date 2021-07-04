// 1041. Robot Bounded In Circle

// simulation and check end state. if different direction then circle. if same direction same position then circle

// time O(n), space O(1)

class Solution {
    public boolean isRobotBounded(String instructions) {
        // 0 up, 1 left, 2 down, 3 right
        int[] state = new int[]{0,0,0};
        
        for (char c : instructions.toCharArray()) {
            int dir = state[2];
            if (c == 'G') {
                if (dir == 0) {
                    state[0]--;
                } else if (dir == 1) {
                    state[1]--;
                } else if (dir == 2) {
                    state[0]++;
                } else {
                    state[1]++;
                }
            } else if (c == 'L') {
                if (dir == 0) {
                    state[2] = 1;
                } else if (dir == 1) {
                    state[2] = 2;
                } else if (dir == 2) {
                    state[2] = 3;
                } else {
                    state[2] = 0;
                }
            } else {
                if (dir == 0) {
                    state[2] = 3;
                } else if (dir == 1) {
                    state[2] = 0;
                } else if (dir == 2) {
                    state[2] = 1;
                } else {
                    state[2] = 2;
                }
            }
        }
        
        return state[2] != 0 || (state[0] == 0 && state[1] == 0);
        
    }
}