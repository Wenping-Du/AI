package com.ai.util;

import java.io.*;

/**
 * txt file read utils
 */
public class MapParseUtils {

    //map length
    private int rows = 0;
    private int cols = 0;

    // start position: real operation needs -1
    private int start_r = 0;
    private int start_c = 0;

    //end position: real operation needs -1
    private int end_r = 0;
    private int end_c = 0;


    private int[][] mapArray = {};

    public void readFile(String filePath) {


        /* read data from a txt file */
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)),
                    "UTF-8"));
            String lineTxt = null;
            int line = 0;
            while ((lineTxt = br.readLine()) != null) {
                line ++;

                switch (line){
                    case 1:
                        String[] line1 = lineTxt.split(" ");
                        rows = Integer.parseInt(line1[0]);
                        cols = Integer.parseInt(line1[1]);
                        break;
                    case 2:
                        String[] line2 = lineTxt.split(" ");
                        start_r = Integer.parseInt(line2[0]);
                        start_c = Integer.parseInt(line2[1]);
                        break;
                    case 3:
                        String[] line3 = lineTxt.split(" ");
                        end_r = Integer.parseInt(line3[0]);
                        end_c = Integer.parseInt(line3[1]);
                        break;
                    default:
                        if(line < 3){
                            return;
                        }
                        if(mapArray.length == 0){
                            mapArray = new int[rows][cols];
                        }
                        for (int i = 0; i < lineTxt.length(); i ++){
                            if(lineTxt.charAt(i) != 'X'){
                                mapArray[line - 4][i] = lineTxt.charAt(i) - '0';
                            }else{
                                mapArray[line - 4][i] = 0;
                            }

                        }
                }

            }
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }

//        System.out.println("rows: " + rows + "--cols: " + cols);
//        System.out.println("start_r: " + start_r + "--start_c: " + start_c);
//        System.out.println("end_r: " + end_r + "--end_c: " + end_c);
        /* output file to an array */
//        for (int i = 0; i < rows; i ++) {
//            for (int j = 0; j < cols; j ++)
//                System.out.print(mapArray[i][j]);
//            System.out.println();
//        }
    }

    /**
     * return map array
     * @return
     */
    public int[][] getMapArray(){
        return mapArray;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getStart_r() {
        return start_r;
    }

    public int getStart_c() {
        return start_c;
    }

    public int getEnd_r() {
        return end_r;
    }

    public int getEnd_c() {
        return end_c;
    }
}