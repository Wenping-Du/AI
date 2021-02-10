import java.util.ArrayList;

public class kfoldcross {

    public static void main(String[] args){
//         [train] [K] [minleaf1] [minleaf2] ... [minleafM]
        String[] b = {"train06", "5", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100",
                "110", "120", "130", "140", "150", "160", "170", "180", "190", "200"};
        args = b;
        ReadTxtToNodes rtn = new ReadTxtToNodes();
        ArrayList<DataNode> train = rtn.getCreditNodes(args[0]);
        int h[] = new int[args.length - 2];
        for (int i = 0; i < args.length - 2; i ++){
            h[i] = Integer.parseInt(args[i + 2]);
        }
        System.out.println(new kfoldcross().getLowestError(train, Integer.parseInt(args[1]), h));
    }

    private int getLowestError(ArrayList<DataNode> train, int k, int[] h){
        int num = train.size();
        int z = num / k;
        ArrayList<ArrayList<DataNode>> separates = new ArrayList<>();
        int t = 0;
        while(t < k){
            ArrayList<DataNode> temp = new ArrayList<>();
            for (int i = 0; i < z; i ++){
                if(t * k + i < num){
                    temp.add(train.get(t * k + i));
                }
            }
            separates.add(temp);
            t ++;
        }
        Evaluate e = new Evaluate();
        double[] avgerrm = new double[h.length];
        for (int i = 0; i < h.length; i ++){
            double accerrm = 0;
            LeafNode leafNode;
            for (int j = 0; j < k; j ++){
                ArrayList<DataNode> train_test = separates.get(j);
                ArrayList<DataNode> train_train = new ArrayList<>(train);
                train_train.removeAll(train_test);

                leafNode = e.DTL(train_train, h[i]);
                int error_record = 0;
                for (int di = 0; di < train_test.size(); di ++){
                    DataNode d = train_test.get(di);
                    String lb = e.predict(leafNode, d);

                    if(!d.getRating().equals(lb)){
                        error_record ++;
                    }
                }
                double err = (double) error_record / train_test.size();
                accerrm += err;
//                System.out.println(error_record + "/" + train_test.size() + "----" + h[i]);
            }
            avgerrm[i] =  accerrm / k;
//            System.out.println(h[i] + ": " + avgerrm[i]);
        }

        double minAvg = 1000;
        int minIndex = 0;
        for (int i = 0; i < h.length; i ++){
            System.out.println(avgerrm[i] + "----" + h[i]);
            if(minAvg > avgerrm[i]){
                minAvg = avgerrm[i];
                minIndex = i;
            }
        }


        System.out.print("[");
        for (int i = 0; i < h.length; i ++){
            System.out.print((avgerrm[i] * 100) + ", ");
        }
        System.out.println("]");
        return h[minIndex];
    }
}
