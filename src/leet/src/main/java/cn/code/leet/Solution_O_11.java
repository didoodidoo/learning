package cn.code.leet;

//剑指offer 11
public class Solution_O_11 {
//    二分查找 如果不会的话直接遍历就行了
//    public int minArray(int[] numbers) {
//        int len = numbers.length;
//        if(len == 0)
//            return 0;
//        int low = 0, high = len - 1;
//        while(low < high){
//            int mid = low + (high - low) / 2;
//            if(numbers[mid] > numbers[high]){
//                low = mid + 1;
//            }else if(numbers[mid] == numbers[high]){
//                high = high - 1;
//            }else{
//                high = mid;
//            }
//        }
//        return numbers[low];
//    }

    public int minArray(int[] numbers) {
        int len = numbers.length;
        if (len == 0)
            return 0;
        if (len == 1)
            return numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < numbers[i - 1])
                return numbers[i];
        }
        return numbers[0];
    }
}
