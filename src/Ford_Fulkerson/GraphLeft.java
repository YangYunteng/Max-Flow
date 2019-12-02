package Ford_Fulkerson;

import java.util.LinkedList;
import java.util.Queue;

public class GraphLeft {
    private int vNumber;
    private int eNumber;
    private LinkedList<EdgeLeft>[] GLists;

    public GraphLeft(int n) {
        this.vNumber = n;
        this.eNumber = 0;
        GLists = new LinkedList[n];

        for (int i = 0; i < n; i++)
            GLists[i] = new LinkedList<>();
    }

    public void insertEdge(EdgeLeft e) {
        int v1 = e.getV1();
        GLists[v1].add(e);
        eNumber++;
    }

    /*
    返回增广路径
    @return
     */
    public LinkedList<Integer> augmentingPath() {

        LinkedList<Integer> list = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        int[] reached = new int[vNumber];
        int[] preNode = new int[vNumber];
        for (int i = 0; i < vNumber; i++) {
            reached[i] = 0;
            preNode[i] = -1;
        }
        preNode[0] = -1;

        reached[0] = 1;
        queue.add(0);
        while (!queue.isEmpty()) {
            int now = queue.poll();

            LinkedList<EdgeLeft> inList = (LinkedList<EdgeLeft>) GLists[now].clone();

            while (!inList.isEmpty()) {

                EdgeLeft e = inList.pop();
                int v2 = e.getV2();

                if (reached[v2] == 0) {
                    queue.add(v2);
                    reached[v2] = 1;
                    preNode[v2] = now;
                }
            }
        }

        for (int i = 0; i < vNumber; i++) {
            System.out.println(reached[i] + " " + preNode[i]);
        }

        if (reached[vNumber - 1] == 0) {
            return list;
        }

        int pointNum = vNumber - 1;
        while (pointNum != -1) {
            list.add(0, pointNum);//将相应的位置追加在list的开头
            pointNum = preNode[pointNum];
        }

        return list;
    }

    /*
    根据增广路调整相应的值
     @param list
     @return
     获取改变的相应的流量大小
     */
    public int flowChange(LinkedList<Integer> list) {
        if (list.equals(null))
            return 0;
        int minChange = Integer.MAX_VALUE;
        int v1 = 0;
        for (int i = 1; i < list.size(); i++) {
            int v2 = list.get(i);
            LinkedList<EdgeLeft> elist = (LinkedList<EdgeLeft>) GLists[v1].clone();
            EdgeLeft edge = elist.pop();
            while (edge.getV2() != v2) {
                edge = elist.pop();
            }
            if (minChange > edge.getFlow())
                minChange = edge.getFlow();

            v1 = v2;
        }

        return minChange;
    }

    public void transverse() {
        System.out.println("残存网络 共 " + vNumber + "个顶点, " + eNumber + " 条边");
        for (int i = 0; i < vNumber; i++) {
            if (GLists[i].size() == 0) {
                System.out.println(i + "没有后继");
                continue;
            }
            for (int j = 0; j < GLists[i].size(); j++) {
                EdgeLeft e = GLists[i].get(j);
                System.out.println("[ " + e.getV1() + " , " + e.getV2() + " , " + e.getFlow() + " ]");
            }
        }
    }
}
