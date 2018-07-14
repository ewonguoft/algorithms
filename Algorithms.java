import java.util.*;
import java.util.stream.*;

class Algorithms{

    /*
    Consider the leftmost and rightmost appearances of some value in an array. 
    We'll say that the "span" is the number of elements between the two inclusive. 
    A single value has a span of 1. Returns the largest span found in the given array.
    */
    public int maxSpan(int[] nums) {
        boolean uniqueFlag = false;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                int curr_count = map.get(nums[i]);
                map.put(nums[i], curr_count+1);
            }else{
                map.put(nums[i], 1);
            }
        }
        System.out.println("Collection view is: "+ map.values());

        Integer one = new Integer(1);
        for(Integer i : map.values()){
            if(!one.equals(i)){
                System.out.println("span is at least greater than 1");
                uniqueFlag = true;
                break;
            }
        }

        if(uniqueFlag){
            int max = 1;
            HashMap<Integer, Integer> first = new HashMap<Integer, Integer>();
            for(int i=0; i<nums.length; i++){
                if(first.containsKey(nums[i])){
                    int curr_max = i - first.get(nums[i]) + 1;
                    if(curr_max > max){
                        max = curr_max;
                    } 
                }else{
                    //doesn't contain the key, so insert current position
                    first.put(nums[i],i);
                }
            }

            return max;
        }
        return 1;
    }

    /*
    Given a non-empty array, return true if there is a place to split the 
    array so that the sum of the numbers on one side is equal to the sum 
    of the numbers on the other side.
    */
    public boolean canBalance(int[] nums) {
        
        int rightSum = IntStream.of(nums).sum();
        int leftSum = 0;

        for(int i=0; i<nums.length; i++){
            if(leftSum > rightSum) return false;
            leftSum = leftSum + nums[i];
            rightSum = rightSum - nums[i];
            if(rightSum == leftSum) return true;
        }

        return false;
    }
    /*
    2 strings "match" if they are non-empty and their first chars are the same. 
    Loop over and then return the given array of non-empty strings as follows: 
    if a string matches an earlier string in the array, swap the 2 strings in the array. 
    A particular first char can only cause 1 swap, so once a char has caused a swap, 
    its later swaps are disabled.
    */
    public String[] firstSwap(String[] strings) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        
        for(int i=0; i<strings.length; i++){
          String key = String.valueOf(strings[i].charAt(0));
          if(!map.containsKey(key)){
            map.put(key, i);
          }else if(map.containsKey(key) && map.get(key)!=-1 ){
            int oldValue = map.get(key);
            String tmp = strings[i];
            strings[i] = strings[oldValue];
            strings[oldValue] = tmp;
            map.put(key, -1);
      
          }
        }
        return strings;
      }
    /*
    Given an array of strings, return a Map<String, Boolean> where 
    each different string is a key and its value is true if that 
    string appears 2 or more times in the array.
    */
    public Map<String, Boolean> wordMultiple(String[] strings) {
    Map<String, Boolean> map=new HashMap<String,Boolean>();
    
    for (String currentString:strings) {
        if (map.containsKey(currentString)) {
            map.put(currentString, true);
        } else {
            map.put(currentString,false);
        }
    
    }
    return map;
    }
    
    /*
    Loop over the given array of strings to build a result string like this: 
    when a string appears the 2nd, 4th, 6th, etc. time in the array, append 
    the string to the result. Return the empty string if no string appears a 2nd time.
    */
    public String wordAppend(String[] strings) {
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        String final_word = "";
        for(int i=0; i<strings.length; i++){
          String word = strings[i];
          if(map.containsKey(word)){
            map.put(word, map.get(word)+1 );
            if(map.get(word)%2 == 0){
              final_word = final_word+word;
            }
          }else{
            map.put(word, 1);
          }
        }
        return final_word;
      }
    //Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

    public ArrayList<String> genParenth(ArrayList<String> ans, String cur, int max, int open, int close){
        if(cur.length()==max*2){
            ans.add(cur);
            return ans;
        }

        if(open<max){
            genParenth(ans, cur+"(", max, open+1, close);
        }
        if(close<open){
            genParenth(ans, cur+")", max, open, close+1);
        }

        return ans;
    }

    //Given an array of strings, group anagrams together.
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> sol = new HashMap<String,List<String>>();
        
        for(int i = 0; i<strs.length; i++){
            String input = strs[i];
            char[] charArray = input.toCharArray();
            Arrays.sort(charArray);
            String sortedString = new String(charArray);
            if(sol.containsKey(sortedString)){
                List<String> in = sol.get(sortedString);
                in.add(input);
                sol.put(sortedString, in );
            }else{
                List<String> in = new ArrayList<String>();
                in.add(input);
                sol.put(sortedString, in);
            }
        }
        return new ArrayList(sol.values());
    }

    public static void main(String[] args) {

        Algorithms alg = new Algorithms();
        //maxSpan
        /*
        int arr[] = {1, 4, 2, 1, 4, 1, 4};
        int num = alg.maxSpan(arr);
        System.out.println(num);
        */

        //canBalance
        /*
        int arr2[] = {1, 1, 1, 2, 1};
        boolean result = alg.canBalance(arr2);
        System.out.println("return "+result);
        */

        //firstSwap
        /*
        String arr3[] = {"ax", "bx", "cx", "cy", "by", "ay", "aaa", "azz"};
        String result[] = alg.firstSwap(arr3);

        for(int i=0; i<arr3.length; i++){
            System.out.println(arr3[i]);
        }
        */

        //wordMultiple
        /*
        String arr4[] = {"a", "b", "a", "c", "b"};
        HashMap<String,Boolean> map = alg.wordMultiple(arr4);
        */

        //wordAppend
        /*
        String arr5[] = {"not", "and", "or", "and", "this", "and", "or", "that", "not"};
        String result = alg.wordAppend(arr5);
        System.out.println("The final word is: " + result);
        */
        //genParenth
        /*
        ArrayList<String> result = new ArrayList<String>();
        result = alg.genParenth(result, "", 4, 0, 0);
        for (String var : result) {
            System.out.println(var);
        }
        */
    }
    
}