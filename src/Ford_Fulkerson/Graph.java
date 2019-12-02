package Ford_Fulkerson;

import java.util.LinkedList;

public class Graph {

    private int vNumber;
    private int eNumber;
    private GraphLeft graphLeft;
    private LinkedList<Edge>[] GLists;

    public Graph(int n) {
        this.vNumber = n;
        this.eNumber = 0;
        this.GLists = new LinkedList[n];

        for (int i = 0; i < n; i++)
            GLists[i] = new LinkedList<>();
    }

    public void insertEdge(Edge e) {
        int v1 = e.getV1();
        GLists[v1].add(e);
        eNumber++;
    }

    public LinkedList<Integer> augmentingPath() {
        return graphLeft.augmentingPath();
    }

    public int flowChange(LinkedList<Integer> list) {
        return graphLeft.flowChange(list);
    }

    public GraphLeft getGraphLeft() {
        return this.graphLeft;
    }

    private void produceGraphLeft() {
        this.graphLeft = new GraphLeft(vNumber);

        for (int i = 0; i < vNumber; i++) {
            //LinkedList<Edge> list = GLists[i];
            LinkedList<Edge> list=(LinkedList<Edge>)GLists[i].clone();

            while (list.size() > 0) {

                Edge edge = list.pop();
                int v1 = edge.getV1();
                int v2 = edge.getV2();
                int flow = edge.getFlow();
                int capacity = edge.getCapacity();

                if (flow == 0) {
                    graphLeft.insertEdge(new EdgeLeft(v1, v2, capacity));
                } else {
                    if (flow == capacity)
                        graphLeft.insertEdge(new EdgeLeft(v2, v1, capacity));
                    else {
                        graphLeft.insertEdge(new EdgeLeft(v1, v2, capacity - flow));
                        graphLeft.insertEdge(new EdgeLeft(v2, v1, flow));
                    }
                }
            }
        }
    }

    public void maxFlow() {
        produceGraphLeft();
        graphLeft.transverse();
        LinkedList<Integer> list = augmentingPath();

        while (list.size() > 0) {

            int flowChange = flowChange(list);

            int v1 = 0;
            for (int i = 1; i < list.size(); i++) {
                int v2 = list.get(i);
                if (!GLists[v1].isEmpty()) {
                    int j = 0;
                    Edge edge = GLists[v1].get(j);
                    while (edge.getV2() != v2 && j < GLists[v1].size()) {
                        edge = GLists[v1].get(j);
                        j++;
                    }
                    if (edge.getV2() != v2 && j == GLists[v1].size()) {
                        j = 0;
                        edge = GLists[v2].get(j);
                        while (edge.getV2() != v1 && j < GLists[v2].size()) {
                            edge = GLists[v2].get(j);
                            j++;
                        }
                    }
                    edge.setFlow(edge.getFlow() + flowChange);
                }
                v1 = v2;

            }
            transverse();
            produceGraphLeft();
            graphLeft.transverse();
            list = augmentingPath();
        }

    }

    public void transverse() {
        System.out.println("共有 " + vNumber + " 个顶点 " + eNumber + " 条边");
        for (int i = 0; i < vNumber; i++) {
            if (GLists[i].size() == 0)
                continue;
            for (int j = 0; j < GLists[i].size(); j++) {
                Edge e = GLists[i].get(j);
                System.out.println("[ " + e.getV1() + " , " + e.getV2() + " , " + e.getFlow() + " , " + e.getCapacity() + " ]");
            }
        }
    }

    public void showResult() {
        transverse();
        int maxflow = 0;

        for (int i = 0; i < vNumber; i++) {
            if (GLists[i].size() > 0) {
                for (int j = 0; j < GLists[i].size(); j++) {
                    if (GLists[i].get(j).getV2() == vNumber - 1) {
                        maxflow += GLists[i].get(j).getFlow();
                    }
                }
            }
        }
        System.out.println("最大流为 " + maxflow);
    }
}
