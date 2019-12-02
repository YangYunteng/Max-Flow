package Ford_Fulkerson;

//@author yyt
public class Edge {
    private int v1;
    private int v2;
    private int capacity;
    private int flow;

    public Edge(int v1, int v2, int flow, int capacity) {
        this.v1 = v1;
        this.v2 = v2;
        this.flow = flow;
        this.capacity = capacity;
    }

    public int getV1() {
        return this.v1;
    }


    public int getV2() {
        return this.v2;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public int getFlow() {
        return this.flow;
    }
}
