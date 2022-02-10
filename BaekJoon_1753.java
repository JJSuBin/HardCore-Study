import java.io.*;
import java.util.*;

class Node_1753 implements Comparable<Node_1753>{
	int node, weight;
	
	public Node_1753(int node, int weight) {
		this.node=node;
		this.weight=weight;
	}

	@Override
	public int compareTo(Node_1753 o) {
		// TODO Auto-generated method stub
		return this.weight-o.weight;
	}
}

public class BaekJoon_1753 {
	static ArrayList<ArrayList<Node_1753>> graph=new ArrayList<>();
	static int v,e,k;
	static final int INF=(int)1e9;
	static int[] dist;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		v=Integer.parseInt(st.nextToken()); // 정점의 개수
		e=Integer.parseInt(st.nextToken()); // 간선의 개수
		
		k=Integer.parseInt(br.readLine()); // 시작 노드
		
		dist=new int[v+1];
		// 초기화
		for(int i=0;i<=v;i++) {
			graph.add(new ArrayList<>());
			dist[i]=INF;
		}
		
		// 입력
		for(int i=0;i<e;i++) {
			st=new StringTokenizer(br.readLine());
			
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int cost=Integer.parseInt(st.nextToken());
			
			graph.get(a).add(new Node_1753(b,cost));
		}
		
		dijkstra(k);
		
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<=v;i++) {
			if(dist[i]==INF)
				sb.append("INF").append('\n');
			else
				sb.append(dist[i]).append('\n');
		}
		
		System.out.println(sb);
	}
	
	// 최단경로 알고리즘 -> 다익스트라
	static void dijkstra(int start) {
		PriorityQueue<Node_1753> pq=new PriorityQueue<>();
		pq.offer(new Node_1753(start,0));
		dist[start]=0;
		
		while(!pq.isEmpty()) {
			Node_1753 n=pq.poll();
			int now=n.node;
			int weight=n.weight;
			
			// 이미 최솟값을 가진 경우 갱신 X
			if(dist[now]>weight)
				continue;
			
			for(Node_1753 next:graph.get(now)) {
				if(dist[next.node]>dist[now]+next.weight) {
					dist[next.node]=dist[now]+next.weight;
					pq.offer(new Node_1753(next.node,dist[now]+next.weight));
				}
			}
		}
	}
}
