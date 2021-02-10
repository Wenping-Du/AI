import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class creditrating {
    public static void main(String[] args){
        /**
         * args: [train] [test] [minleaf]
         * train: training data(.txt)
         * test: test data(.txt)
         * minleaf: the 2nd param of DTL algorithm
         */
        String[] b = {"/Users/deb/Desktop/tutorial/AI/useful_files/train02",
                "/Users/deb/Desktop/tutorial/AI/useful_files/test02",
                "30"};
        args = b;
        ReadTxtToNodes rtn = new ReadTxtToNodes();
        ArrayList<DataNode> train = rtn.getCreditNodes(args[0]);
        ArrayList<DataNode> test = rtn.getCreditNodes(args[1]);

        Evaluate e = new Evaluate();
        LeafNode lf = e.DTL(train, Integer.parseInt(args[2]));

        ArrayList<String> eps = getExpectNodes("/Users/deb/Desktop/tutorial/AI/useful_files/expected02");
        int err = 0;
        for (int i = 0; i < test.size();i ++){
            DataNode d  = test.get(i);
            String lb = e.predict(lf, d);
            if(!lb.equals(eps.get(i))){
                err ++;
            }
            System.out.println(lb);
        }
        System.out.println("Error： " + err);
    }


    public static ArrayList<String> getExpectNodes(String path){
        ArrayList<String> creditNodes = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)),
                    "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                lineTxt = lineTxt.trim();
                creditNodes.add(lineTxt);
            }
        }catch (Exception e){

        }
        return creditNodes;

    }


}
