// 135. Candy

// initial thought is go through the array to find relative relations between points
// put all the reverse peek into a heap, and start from lowest point to give candy out
// to nearby kids. Time O(nlogn).

class Solution {
    public int candy(int[] ratings) {
        if (ratings.length == 1) return 1;
        int cnt = 0;
        boolean[] candies = new boolean[ratings.length];
        int[] relatives = new int[ratings.length];
        relatives[0] = 1;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> a[1]-b[1]);
        
        if (ratings[1] > ratings[0]) {
            relatives[1] = 2;
            heap.add(new int[]{0, 1});
        } else if (ratings[1] == ratings[0]) {
            relatives[1] = 1;
        } else {
            relatives[1] = 0;
        }
        
        for (int i = 2; i < ratings.length; i++) {
            if (ratings[i] > ratings[i-1]) {
                relatives[i] = relatives[i-1] + 1;
            } else if (ratings[i] == ratings[i-1]) {
                relatives[i] = 1;
            } else {
                relatives[i] = relatives[i-1] - 1;
            }
            
            if (ratings[i-2] >= ratings[i-1] && ratings[i] >= ratings[i-1]) {
                heap.add(new int[]{i-1, relatives[i-1]});
            }
        }
        
        if (relatives[ratings.length-1] <= relatives[ratings.length-2]) {
            heap.add(new int[]{ratings.length-1, relatives[ratings.length-1]});
        }
        
        while (heap.size() != 0) {
            int[] point = heap.poll();
            int curr = point[0];
            if (!candies[curr]) {
                candies[curr] = true;
                cnt++;
                int pl = curr-1;
                int count = 2;
                while (pl >= 0 && ratings[pl] > ratings[pl+1] && !candies[pl]) {
                    cnt += count;
                    count++;
                    candies[pl] = true;
                    pl--;
                }
                count = 2;
                int pr = curr + 1;
                while (pr < ratings.length && ratings[pr] > ratings[pr-1] && !candies[pr]) {
                    cnt += count;
                    count++;
                    candies[pr] = true;
                    pr++;
                }
            }
        }
        for (boolean b : candies) {
            if (!b) cnt++;
        }
        return cnt;
        
    }
}

// greedy left to right and right to left go through. time O(n)

public class Solution {
    public int count(int n) {
        return (n * (n + 1)) / 2;
    }
    public int candy(int[] ratings) {
        if (ratings.length <= 1) {
            return ratings.length;
        }
        int candies = 0;
        int up = 0;
        int down = 0;
        int oldSlope = 0;
        for (int i = 1; i < ratings.length; i++) {
            int newSlope = (ratings[i] > ratings[i - 1]) ? 1 
                : (ratings[i] < ratings[i - 1] ? -1 
                : 0);

            if ((oldSlope > 0 && newSlope == 0) || (oldSlope < 0 && newSlope >= 0)) {
                candies += count(up) + count(down) + Math.max(up, down);
                up = 0;
                down = 0;
            }
            if (newSlope > 0) {
                up++;
            } else if (newSlope < 0) {
                down++;
            } else {
                candies++;
            }

            oldSlope = newSlope;
        }
        candies += count(up) + count(down) + Math.max(up, down) + 1;
        return candies;
    }
}