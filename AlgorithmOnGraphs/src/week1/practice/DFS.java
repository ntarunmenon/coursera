package week1.practice;

public class DFS {

	private boolean visitied[];
	private Graph graph;
	private int[] ccNum;

	public DFS(Graph graph) {
		visitied = new boolean[graph.n];
		ccNum = new int[graph.n];
		this.graph = graph;
	}

	private void explore(Integer startNode, int cc) {
		System.out.println("Exploring Node " + startNode);
		visitied[startNode] = true;
		ccNum[startNode] = cc;
		for (Integer nighbour : graph.adj[0]) {
			if (!visitied[nighbour]){
				explore(startNode,cc);
			}
		}
	}

	public void computeCC() {
		int cc = 1;
		for (int n = 0; n < graph.n; n++) {
			if(!visitied[n]){
				explore(n,cc);
				cc = cc + 1;
			}
		}
	}
}
