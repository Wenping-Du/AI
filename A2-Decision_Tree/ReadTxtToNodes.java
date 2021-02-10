import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadTxtToNodes {

    public ArrayList<DataNode> getCreditNodes(String path){
        ArrayList<DataNode> creditNodes = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)),
                    "UTF-8"));
            String lineTxt = null;
            int line = 0;
            while ((lineTxt = br.readLine()) != null) {
                lineTxt = lineTxt.trim();
                line++;
                if(line >= 2){
                    String[] attrs = lineTxt.split("\\s+");
                    DataNode node = new DataNode();
                    node.setWC_TA(Double.parseDouble(attrs[0].trim()));
                    node.setRE_TA(Double.parseDouble(attrs[1].trim()));
                    node.setEBIT_TA(Double.parseDouble(attrs[2].trim()));
                    node.setMVE_BVTD(Double.parseDouble(attrs[3].trim()));
                    node.setS_TA(Double.parseDouble(attrs[4].trim()));
                    if(attrs.length == 6){
                        node.setRating(attrs[5].trim());
                    }
                    creditNodes.add(node);
                }
            }
        }catch (Exception e){

        }
        return creditNodes;

    }
}


