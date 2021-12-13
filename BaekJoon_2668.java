import java.util.*;

public class BaekJoon_2668 {
	static ArrayList<Integer> arr=new ArrayList<Integer>();
	static int n,num[];
	static boolean[] visited;
	static int ciycle=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		num=new int[n+1];
		visited=new boolean[n+1];
		
		for(int i=1;i<=n;i++) 
			num[i]=sc.nextInt();
		
		// 순서대로 사이클이 발생하는지 확인
		for(int i=1;i<=n;i++) {
			visited[i]=true;
			dfs(i,i);
			visited[i]=false;
		}
		
		Collections.sort(arr);
		System.out.println(arr.size());
		for(int i=0;i<arr.size();i++)
			System.out.println(arr.get(i));
	}
	
	// 해당 문제는 싸이클을 이루는 숫자들을 선택하는 문제와 동일하다.
	static void dfs(int start, int target) {
		if(!visited[num[start]]) {
			visited[num[start]]=true;
			dfs(num[start],target);
			visited[num[start]]=false;
		}
		
		// 숫자가 돌고 돌아 target과 같아진다면 싸이클 발생, 해당 숫자를 리스트에 저장한다.
		if(num[start]==target)
			arr.add(target);
	}

}
