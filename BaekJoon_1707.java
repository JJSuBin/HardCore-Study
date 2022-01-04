import java.util.*;

public class BaekJoon_1707 {
	static int[] colors;
	static ArrayList<ArrayList<Integer>> graph;
	static int RED=1, BLUE=-1;
	static boolean check; // 이분그래프를 만족하는지 체크하는 변수
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int k=sc.nextInt();
		
		while(k-->0) {
			int v=sc.nextInt(); // 정점의 개수
			int e=sc.nextInt(); // 간선의 개수
			
			graph=new ArrayList<>();
			colors=new int[v+1];
			check=true;
			
			for(int i=0;i<=v;i++)
				graph.add(new ArrayList<>());
			
			for(int i=0;i<e;i++) {
				int a=sc.nextInt();
				int b=sc.nextInt();
				// 양방향
				graph.get(a).add(b);
				graph.get(b).add(a);
			}
			
			// 모든 정점을 탐색하면서 색칠되지 않은 정점을 시작으로 색 채우기
			for(int i=1;i<=v;i++) {
				if(!check) // 이분그래프가 아니라면 탐색 종료
					break;
				// 아직 색이 채워지지 않은 정점부터 색 채우기 시작
				if(colors[i]==0) 
					BinaryGraph(i,RED);
			}
			
			System.out.println(check?"YES":"NO");
		}
	}
	
	static void BinaryGraph(int index, int color) {
		colors[index]=color; // 색 채우기
		
		// index와 연결된 정점은 이전 색과 반대되는 색으로 채우기
		for(int i=0;i<graph.get(index).size();i++) {
			// 이때 연결된 정점과 같은 색으로 이미 채워져 있다면 이분그래프가 될 수 없음
			if(colors[index]==colors[graph.get(index).get(i)]) {
				check=false;
				return;
			}
			
			if(colors[graph.get(index).get(i)]==0) 
				BinaryGraph(graph.get(index).get(i),-color);
			
		}
  }
}
