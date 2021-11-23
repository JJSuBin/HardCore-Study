import java.util.*;

public class BaekJoon_13023 {
	static int n,m,result=0;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		visited=new boolean[n];
		
		for(int i=0;i<n;i++)
			graph.add(new ArrayList<>());
		
		for(int i=0;i<m;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			
			// 양방향
			graph.get(a).add(b); 
			graph.get(b).add(a);
		}
		
		for(int i=0;i<n;i++) {
			if(result==0) 
				find_friend(1,i); // i번째 노드와 연결된 노드 찾기
		}
		
		System.out.println(result);
	}
	
	static void find_friend(int depth, int start) {
		if(depth==5) { // 5개 이상 이어진 노드를 찾아야하기 때문에 깊이는 5까지 검사
			result=1; 
			return;
		}
		
		visited[start]=true; // 방문처리
		// start 노드와 연결된 모든 노드 탐색
		for(int i=0;i<graph.get(start).size();i++) {
			int next=graph.get(start).get(i); // 다음 노드
			
			if(!visited[next])
				find_friend(depth+1,next); // 방문하지 않았다면 다음 노드를 시작으로 연결된 노드 탐색
		}
		visited[start]=false; // 초기화
	}
}
