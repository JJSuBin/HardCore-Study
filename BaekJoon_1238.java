import java.util.*;

class Node_1238 implements Comparable<Node_1238> {
	int index, distance; // 노드 번호, 소요시간
	
	public Node_1238(int index, int distance) {
		this.index=index;
		this.distance=distance;
	}
	
	public int compareTo(Node_1238 o) { 
		return this.distance-o.distance;
	}
}

public class BaekJoon_1238 {
	/*
	 * go_party : 각 마을에서 파티가 열리는 마을로 가는 경우 거쳐가는 노드를 저장한 연결리스트
	 * back_party : 파티가 열리는 마을에서 각 마을로 가는 경우 거쳐가는 노드를 저장한 연결리스트
	 */
	static ArrayList<ArrayList<Node_1238>> go_party, back_home; 
	static int[] go,back; // 다익스트라 알고리즘 결과를 저장하는 배열
	static int n,m,x;
	static final int INF=(int)1e9;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		x=sc.nextInt(); // 파티가 열리는 마을
		
		go_party=new ArrayList<>();
		back_home=new ArrayList<>();
		
		for(int i=0;i<=n;i++) {
			go_party.add(new ArrayList<>());
			back_home.add(new ArrayList<>());
		}
		
		go=new int[n+1];
		back=new int[n+1];
		
		// 최댓값으로 초기화
		Arrays.fill(go,INF);
		Arrays.fill(back,INF);
		
		for(int i=0;i<m;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int c=sc.nextInt();
			
			go_party.get(a).add(new Node_1238(b,c));
			back_home.get(b).add(new Node_1238(a,c)); // 파티에서 되돌아가는 경우는 파티로 가는 길의 반대로 저장하면 됨!
		}
		
		// x에서 모든 정점 사이의 최단거리 구하기
		dijkstra(go_party,x,go); // 파티가 열리는 마을로 가는 경우
		dijkstra(back_home,x,back); // 파티가 열리는 마을에서 돌아오는 경우
		
		// 오고 가는데 가장 많은 시간을 소비한 값 찾기
		int max=0;
		for(int i=1;i<=n;i++) 
			max=Math.max(max, go[i]+back[i]);
		
		System.out.println(max);
	}
	
	// 다익스트라 알고리즘
	static void dijkstra(ArrayList<ArrayList<Node_1238>> arr, int start, int[] distance) {
		boolean[] visited=new boolean[n+1]; // 방문 여부 체크 배열
		PriorityQueue<Node_1238> pq=new PriorityQueue<>(); // 우선순위 큐 사용
		pq.add(new Node_1238(start, 0)); // 시작 시점 큐에 삽입
		distance[start]=0; // 방문처리
		
		while(!pq.isEmpty()) {
			int now=pq.poll().index;
			
			if(visited[now]) continue; // 이미 방문했던 곳이라면 넘어간다.
			
			visited[now]=true; // 방문처리
			
			for(Node_1238 node:arr.get(now)) {
				if(distance[node.index]>distance[now]+node.distance) {
					distance[node.index]=distance[now]+node.distance; // 거리 값 갱신
					pq.offer(new Node_1238(node.index,distance[node.index]));
				}
			}
		}
	}
}
