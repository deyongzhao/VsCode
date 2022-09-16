import java.util.*;
public class Test{   

    public static void main(String[] args){

        System.out.println(fib(100,new HashMap<>()));
        System.out.println(gridTravel(3, 3, new HashMap<>()));
        System.out.println(gridTravel(18, 18,new HashMap<>()));
        System.out.println(canSum(7,new int[]{5,3,4,7}, new HashMap<>()));
        System.out.println(canSum(300,new int[]{7,14},new HashMap<>()));
        System.out.println(howSum(7,new int[]{5,3,4,7},new HashMap<>()));       
        System.out.println(howSum(8,new int[]{2,3,5},new HashMap<>()));      
        System.out.println(bestSum(8,new int[]{1,2,3},new HashMap<>()));
        System.out.println(bestSum(100,new int[]{1,2,5,25},new HashMap<>()));


    }
    
    
    static long fib( int v, Map<Integer, Long> memo){
        
        if (memo.get(v)!=null ) return memo.get(v);
        if (v==1 || v==2 ) return 1; 
        long r = fib(v-1,memo)+fib(v-2,memo);
        memo.put(v,r);
        return r;

    }

    //grid travel on a 2D grid. top left to bottom right corner, you only move down or right. 
    //In how many ways can you travel to the goal on a grid with dimension m*n?
    static long gridTravel(int m, int n, HashMap<String, Long> memoT){
        String key = m+","+n;
        if (memoT.get(key)!=null) return memoT.get(key);
        if (m==1&& n==1 ) return 1; 
        if (m==0 || n==0) return 0;
        memoT.put(key, gridTravel(m-1, n,memoT)+ gridTravel(m, n-1,memoT));
        return memoT.get(key);
    }

    //canSum(targetSum, numbers), return a boolean to indicating whether or not it is possible 
    //to generate the target sum using numbers from the array, you may use an element of the 
    //array as many times as needed. assume all numners are nonnegtive. 
    static boolean canSum(int targetSum, int[] numbers,HashMap<Integer, Boolean> memoCan){
        if (memoCan.get(targetSum)!=null) return memoCan.get(targetSum);
        if (targetSum ==0) return true;
        if(targetSum<0) return false;
        for (int a:numbers){
            int b=targetSum-a;            
            if(canSum(b,numbers,memoCan)==true) {
                memoCan.put(targetSum,true);
                return true;
            }
        memoCan.put(targetSum,false);        
    }
        return false;
    }

    //howSum(targetSum, numbers)
    //it should return an array containing any combination of elements that add up to exactly the targetSum.
    //Return null  if there is no this kind of combination. If there are multiple combinations, you may return 
    //any single one. 
    static ArrayList<Integer> howSum(int targetSum, int[] numbers,HashMap<Integer,ArrayList<Integer>> memoHC){
        if (memoHC.containsKey(targetSum)) return memoHC.get(targetSum);
        if (targetSum==0) return new ArrayList<>();
        if (targetSum<0) return null;
        for (int a:numbers){
            int r = targetSum-a;
            ArrayList<Integer> t = howSum(r, numbers,memoHC);
            if (t!=null) {
                t.add(a);
                memoHC.put(targetSum, t);
                return t;
            }
        }
        memoHC.put(targetSum,null);
        return null;
    }

    //bestSum(targetSum, numbers[]) this should return shortest combination of numbers that add up to exactly the targetSum. 
    //if there is a tire for the shortest combination, return any one of the shortest. 
    static ArrayList<Integer> bestSum(int targetSum, int[] numbers, HashMap<Integer,ArrayList<Integer>> memeo){
        if (memeo.get(targetSum)!=null) 
            return memeo.get(targetSum);
        if (targetSum== 0) return new ArrayList<Integer>();
        if(targetSum<0 ) return null;
        ArrayList<Integer> shortestR=null;

        for (int x :numbers){
            int r = targetSum-x;
            ArrayList<Integer> t =bestSum(r, numbers, memeo);
            if(t!=null) {
              t.add(x);
               if (shortestR==null|| t.size()<shortestR.size()){
                   if (shortestR!=null) 
                   {shortestR.clear();} else {shortestR= new ArrayList<>();}
                   for(int j :t)   {
                       shortestR.add(j);
                   }
                   
               }
            }
        }
        memeo.put(targetSum, shortestR);
        return shortestR;
    }


}