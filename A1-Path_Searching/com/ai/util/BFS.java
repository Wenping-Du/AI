package com.ai.util;

import java.util.*;

public class BFS {
    //4 directions: up down left right
    private int dx[] = {-1, 1, 0, 0};
    private int dy[] = {0, 0, -1, 1};

    class Node{
        int row;
        int col;
        String paths;
        int cost;
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
    }

    /**
     * @param map
     * @param start_r,start_c initial position
     * @param end_r,end_c final position
     * @param row map row
     * @param col map col
     */
    public String bfsSearch(int[][] map, int start_r, int start_c, int end_r, int end_c, int row, int col){
        Node initialNode = new Node(start_r - 1, start_c - 1);
        initialNode.setPaths("0,0");
        Queue<Node> pathQueue = new LinkedList<>();
        pathQueue.add(initialNode);
        while(!pathQueue.isEmpty()){
            Node n = pathQueue.poll();//top
            if(n.row == end_r-1 && n.col == end_c-1){
//                System.out.println("path: " + n.paths);
//                System.out.println("cost: " + n.cost);
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
                        int pathCost;
                        if(map[x][y] > map[n.row][n.col]){
                            pathCost = 1 + map[x][y] - map[n.row][n.col];
                        }else{
                            pathCost = 1;
                        }
                        Node newNode = new Node(x, y);
                        newNode.setCost(n.getCost() + pathCost);
                        newNode.setPaths(n.getPaths() + "---" + x + "," + y);
                        pathQueue.add(newNode);
                    }
                }
            }
        }
        return null;
    }

}
