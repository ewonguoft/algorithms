import java.util.*;

class Algorithms{

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
    

    public static void main(String[] args) {

        Algorithms alg = new Algorithms();

        int arr[] = {1, 4, 2, 1, 4, 1, 4};
        int num = alg.maxSpan(arr);
        System.out.println(num);
    }
    
}