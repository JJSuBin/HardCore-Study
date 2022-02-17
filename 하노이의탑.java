import java.util.*;

public class 하노이의탑 {
	static ArrayList<int[]> arr;
	public int[][] solution(int n) {
        arr=new ArrayList<>();
        
        Hanoi(n,1,2,3);
        
        int[][] answer=new int[arr.size()][2];
        for(int i=0;i<arr.size();i++) {
        	int[] temp=arr.get(i);
        	answer[i][0]=temp[0];
        	answer[i][1]=temp[1];
        }
        return answer;
    }
	
	static void Hanoi(int n, int start, int mid, int end) {
		int[] seq= {start,end};
		
		if(n==1)
			arr.add(seq); // 출발지의 원판을 도착지로 옮긴다
		
		else{
			Hanoi(n-1,start,end,mid); // 출발지에 있는 n-1개의 원판을 mid로 이동
			arr.add(seq); // 출발지에 있는 한 개의 원판(크기가 제일 큰 원판)을 도착지로 이동
			Hanoi(n-1,mid,start,end); // mid에 있는 n-1개의 원판을 end로 이동
		}
		
	}
}
