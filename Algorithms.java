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
        String arr3[] = {"ax", "bx", "cx", "cy", "by", "ay", "aaa", "azz"};
        String result[] = alg.firstSwap(arr3);

        for(int i=0; i<arr3.length; i++){
            System.out.println(arr3[i]);
        }

    }
    
}