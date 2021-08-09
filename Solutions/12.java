// 12. Integer to Roman

// One thing we can do is hardcode all the possible mapping of number to Roman, then just
// list out the answer. I used a map to do it.
class Solution {
    Map<Integer,String> map = new HashMap<>();
    public String intToRoman(int num) {
        map.put(0,"");
        map.put(1,"I");
        map.put(2,"II");
        map.put(3,"III");
        map.put(4,"IV");
        map.put(5,"V");
        map.put(6,"VI");
        map.put(7,"VII");
        map.put(8,"VIII");
        map.put(9,"IX");
        map.put(10,"X");
        map.put(20,"XX");
        map.put(30,"XXX");
        map.put(40,"XL");
        map.put(50,"L");
        map.put(60,"LX");
        map.put(70,"LXX");
        map.put(80,"LXXX");
        map.put(90,"XC");
        map.put(100,"C");
        map.put(200,"CC");
        map.put(300,"CCC");
        map.put(400,"CD");
        map.put(500,"D");
        map.put(600,"DC");
        map.put(700,"DCC");
        map.put(800,"DCCC");
        map.put(900,"CM");
        map.put(1000,"M");
        map.put(2000,"MM");
        map.put(3000,"MMM");
        StringBuilder sb = new StringBuilder();
        int curr = num;
        int first = curr % 10;
        sb.insert(0,map.get(first));
        curr -= first;
        int second = curr % 100;
        sb.insert(0,map.get(second));
        curr -= second;
        int third = curr % 1000;
        sb.insert(0,map.get(third));
        curr -= third;
        sb.insert(0,map.get(curr));
        return sb.toString();
    }
}

// I guess a smarter way to do it is loop through the possible values instead of loop through the given number

class Solution {
    private static final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};    
    private static final String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        // Loop through each symbol, stopping if num becomes 0.
        for (int i = 0; i < values.length && num > 0; i++) {
            // Repeat while the current symbol still fits into num.
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
}

// Time complexity : 

// O(1).


// As there is a finite set of roman numerals, there is a hard upper limit on how many times the loop can iterate. This upper limit is 15 times, and it occurs for the number 3888, which has a representation of MMMDCCCLXXXVIII. Therefore, we say the time complexity is constant, i.e. 


// Space complexity : 
// o(1)