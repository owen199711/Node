package org.example;

public class mubiaohe {

  public static void main(String []args){

  }

    /**
     * 一个数组，和一个target 求 使用 + - 组合的个数
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        if(target<0) target= -target;
        for(int i:nums) sum+=i;
        if(sum<target||(sum+target)%2!=0) return 0;
        target = (sum+target) / 2;

        //使用背包：求装满target容量的物品的
        int [] f= new int [target+1];
        f[0]=1;
        for (int i=0;i<nums.length;i++){
            for (int j=target;j>=nums[i];j--){
                f[j] += f[j-target];
            }
        }
        return f[target];
    }
}
