package com.ai.util;

public class TransferUtils {


    /**
     * transfer path into map
     * @param path
     * @return
     */
    public String transferPathToGraph(String path, int[][] map, int row, int col){
        if(path == null){
            return null;
        }
        String result = "";
        String[] node = path.split("---");
        for(String xy : node){
            String[] xy_int = xy.split(",");
            int x = Integer.parseInt(xy_int[0]);
            int y = Integer.parseInt(xy_int[1]);
            map[x][y] = -1;
        }

        for (int i = 0; i < row; i ++){
            for (int j = 0; j < col; j ++){
                if(map[i][j] == 0){
                    result += "X";
                }else if(map[i][j] == -1){
                    result += "*";
                }else
                    result += map[i][j];

            }
            if(i != row -1){
                result += "\n";
            }
        }
        return result;
    }
}
