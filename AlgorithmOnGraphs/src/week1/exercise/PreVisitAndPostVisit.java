package week1.exercise;

public class PreVisitAndPostVisit {

	private boolean visitied[];
	private Graph graph;
	private int[] ccNum;
	private int clock = 1;
	private int pre[];
	private int post[];

	public PreVisitAndPostVisit(Graph graph) {
		visitied = new boolean[graph.n];
		pre = new int[graph.n];
		post = new int[graph.n];
		ccNum = new int[graph.n];
		this.graph = graph;
	}

	private void explore(Integer startNode, int cc) {
		previsit(startNode);
		System.out.println("Exploring Node " + startNode);
		visitied[startNode] = true;
		ccNum[startNode] = cc;
		for (Integer nighbour : graph.adj[0]) {
			if (!visitied[nighbour]) {
				explore(startNode, cc);
			}
		}
		postVisit(startNode);
	}

	private void postVisit(Integer startNode) {
		post[startNode] = clock;
		clock = clock + 1;

	}

	private void previsit(Integer startNode) {
		pre[startNode] = clock;
		clock = clock + 1;

	}

	public void computeCC() {
		int cc = 1;
		for (int n = 0; n < graph.n; n++) {
			if (!visitied[n]) {
				explore(n, cc);
				cc = cc + 1;
			}
		}
	}
}
