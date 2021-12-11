public class Leetcode_Ways_to_Split_Array_Into_Three_Subarrays {
	class Solution {
	    public int waysToSplit(int[] nums) {
	       int n=nums.length;
	       
	       for(int i=1;i<n;i++)
	    	   nums[i]=nums[i-1]+nums[i];
	       
	       int count=0;
	       for(int i=0,j=0,k=0;i<n-1;i++) {
	    	   int first=nums[i];
	    	   
	    	   while(j<=i||(j<n-1&&nums[j]<2*nums[i])) j++;
	    	   while(k<j||(k<n-1&&nums[n-1]-nums[k]>=nums[k]-nums[i])) k++;
	    	   
	    	   count=(count+k-j)%1000000007;
	       }
	       return count;
	    }
	}
}
