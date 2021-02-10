package com.ai.util;

import java.util.*;

public class UCS {
    //4 directions: up down left right
    private int dx[] = {-1, 1, 0, 0};
    private int dy[] = {0, 0, -1, 1};

    class Node{
        int row;
        int col;
        String paths;
        int cost;

        long time;//if cost is equal, enter first out first
        Node(int row, int col){
            this.row = row;
            this.col = col;
        }

        public String getPaths() {
            return paths;
        }

        public void setPaths(String paths) {
            this.paths = paths;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }

    //ucs use PriorityQueue to choose path
    private PriorityQueue<Node> queue = new PriorityQueue<Node>(10,
            new Comparator<Node>(){
                public int compare(Node i, Node j){
                    if(i.cost > j.cost){
                        return 1;
                    } else if (i.cost < j.cost){
                        return 0;
                    } else{
                        if(i.time < j.time){
                            return 1;
                        }else
                            return 0;
                    }
                }
            }
    );

    /**
     * @param map
     * @param start_r,start_c initial position
     * @param end_r,end_c final position
     * @param row map row
     * @param col map col
     */
    public String ucsSearch(int[][] map, int start_r, int start_c, int end_r, int end_c, int row, int col){
        Node initialNode = new Node(start_r - 1, start_c - 1);
        initialNode.setPaths("0,0");

        queue.add(initialNode);
        while(!queue.isEmpty()){
            Node n = queue.poll();//top
//            System.out.println("path: " + n.paths);
//            System.out.println("cost: " + n.cost);
            if(n.row == end_r-1 && n.col == end_c-1){
                return n.paths;
            }

            //4 direction
            for(int t = 0; t < 4; t ++) {
                int x = n.row + dx[t];
                int y = n.col + dy[t];
                //boundary/obstacle
                if(x >= 0 && y >= 0 && x < row && y < col && map[x][y] != 0) {
                    String thisPath = x + "," + y;
                    if(!n.getPaths().contains(thisPath)){
                        int nodeCost;
                        if(map[x][y] > map[n.row][n.col]){
                            nodeCost = 1 + map[x][y] - map[n.row][n.col];
                        }else{
                            nodeCost = 1;
                        }
                        Node newNode = new Node(x, y);
                        newNode.setCost(n.getCost() + nodeCost);
                        newNode.setPaths(n.getPaths() + "---" + x + "," + y);
                        newNode.setTime(System.currentTimeMillis());
                        queue.offer(newNode);
                    }
                }
            }
        }
        return null;
    }

}
