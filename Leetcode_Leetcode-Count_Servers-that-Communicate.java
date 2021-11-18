package HardCore;

class Leetcode_Count_Servers_that_Communicate {
    public int countServers(int[][] grid) {
        int n=grid.length; // 행 
        int m=grid[0].length; // 열
        int[] row=new int[n];
        int[] col=new int[m];
        int count=0;
        
        for(int i=0;i<n;i++) {
        	for(int j=0;j<m;j++) {
        		if(grid[i][j]==1) {
        			count++;
        			row[i]++;
        			col[j]++;
        		}
        	}
        }
        
        for(int i=0;i<n;i++) {
        	for(int j=0;j<m;j++) {
        		if(grid[i][j]==1&&row[i]==1&&col[j]==1)
        			count--;
        	}
        }
        return count;
    }
}
