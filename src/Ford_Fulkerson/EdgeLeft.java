package Ford_Fulkerson;

public class EdgeLeft {
    private int v1;
    private int v2;
    private int flow;
    public EdgeLeft(int v1,int v2,int flow){
        this.v1=v1;
        this.v2=v2;
        this.flow=flow;
    }
    public int getV1(){
        return this.v1;
    }
    public int getV2(){
        return this.v2;
    }
    public void setFlow(int flow){
        this.flow=flow;
    }
    public int getFlow(){
        return this.flow;
    }
}
