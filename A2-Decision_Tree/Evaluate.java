import java.util.ArrayList;

public class Evaluate {

    /**
     * Algorithm 1
     * @param trainNodes
     * @param minleaf
     * @return
     */
    public LeafNode DTL(ArrayList<DataNode> trainNodes, int minleaf){

        boolean isAllXEqual = true;
        boolean isAllYEqual = true;
        for (int i = 0, j = i + 1;
             i < trainNodes.size() && j < trainNodes.size();
             i ++, j ++){
            DataNode n1 = trainNodes.get(i);
            DataNode n2 = trainNodes.get(j);
            if(!n1.getRating().equals(n2.getRating())){
                isAllYEqual = false;
            }
            if(n1.getWC_TA() != n2.getWC_TA()
                    || n1.getEBIT_TA() != n2.getEBIT_TA()
                    || n1.getMVE_BVTD() != n2.getMVE_BVTD()
                    || n1.getRE_TA() != n2.getRE_TA()
                    || n1.getS_TA() != n2.getS_TA()){
                isAllXEqual = false;
            }
        }

        if(trainNodes.size() <= minleaf || isAllXEqual || isAllYEqual){
            LeafNode node = new LeafNode();
            int n1 = 0, n2 = 0, n3 = 0, n4 = 0, n5 = 0, n6 = 0, n7 = 0;
            for (DataNode n : trainNodes){
                if("AAA".equals(n.getRating())){
                    n1 ++;
                }else if("AA".equals(n.getRating())){
                    n2 ++;
                }else if("A".equals(n.getRating())){
                    n3 ++;
                }else if("BBB".equals(n.getRating())){
                    n4 ++;
                }else if("BB".equals(n.getRating())){
                    n5 ++;
                }else if("B".equals(n.getRating())){
                    n6 ++;
                }else if("CCC".equals(n.getRating())){
                    n7 ++;
                }
            }
            int[] arrays = {n1, n2, n3, n4, n5, n6, n7};
            int max = 0;
            int maxIndex = 0;

            boolean isUnique = true;
            for (int i = 0; i < arrays.length; i ++){
                if(max < arrays[i]){
                    isUnique = true;
                    max = Math.max(max, arrays[i]);
                    maxIndex = i;
                }
                if(max == arrays[i] && maxIndex != i){
                    isUnique = false;
                }
            }

            if(!isUnique){
                node.setLable("unknown");
                return node;
            }
            switch (maxIndex){
                case 0:
                    node.setLable("AAA");
                    break;
                case 1:
                    node.setLable("AA");
                    break;
                case 2:
                    node.setLable("A");
                    break;
                case 3:
                    node.setLable("BBB");
                    break;
                case 4:
                    node.setLable("BB");
                    break;
                case 5:
                    node.setLable("B");
                    break;
                case 6:
                    node.setLable("CCC");
                    break;
            }
//            System.out.println("return " + node.getLable());
            return node;
        }
        LeafNode ln = chooseSplit(trainNodes);
        ArrayList<DataNode> dataLeft = new ArrayList<>();
        ArrayList<DataNode> dataRight = new ArrayList<>();
        System.out.println(ln.getSplitval());
        for (DataNode dn : trainNodes){
            double value = 0;
            String itemName = "";
            switch (ln.getAttr()){
                case 0 :
                    value = dn.getWC_TA();
                    itemName = "WC_TA";
                    break;
                case 1:
                    value = dn.getRE_TA();
                    itemName = "RE_TA";
                    break;
                case 2:
                    value = dn.getEBIT_TA();
                    itemName = "EBIT_TA";
                    break;
                case 3:
                    value = dn.getMVE_BVTD();
                    itemName = "MVE_BVTD";
                    break;
                case 4:
                    value = dn.getS_TA();
                    itemName = "S_TA";
                    break;
            }
//            System.out.println("if " + itemName + " <=  " + ln.getSplitval());
            if(value <= ln.getSplitval()){
                dataLeft.add(dn);
            }else{
                dataRight.add(dn);
            }
        }
        ln.setLeft(DTL(dataLeft, minleaf));
        ln.setRight(DTL(dataRight, minleaf));
        return ln;
    }


    public void sortAttrs(ArrayList<DataNode> trainNodes, int sortNum){
        sortList(trainNodes, sortNum);
    }


    public LeafNode chooseSplit(ArrayList<DataNode> trainNodes){
        double bestGain = 0;
        LeafNode ln = new LeafNode();
        for (int t = 0; t < 5 ; t ++){
            sortAttrs(trainNodes, t);

            for (int i = 0; i < trainNodes.size() - 1; i ++){
                //calculate the gain of x <= split value
                //need to count the number of AAA AA A BBB BB B CCC
                double sv1 = 0, g1 = 0;
                switch (t){
                    case 0:
                        sv1 = 0.5 * (trainNodes.get(i).getWC_TA() + trainNodes.get(i + 1).getWC_TA());
                        break;
                    case 1:
                        sv1 = 0.5 * (trainNodes.get(i).getRE_TA() + trainNodes.get(i + 1).getRE_TA());
                        break;
                    case 2:
                        sv1 = 0.5 * (trainNodes.get(i).getEBIT_TA() + trainNodes.get(i + 1).getEBIT_TA());
                        break;
                    case 3:
                        sv1 = 0.5 * (trainNodes.get(i).getMVE_BVTD() + trainNodes.get(i + 1).getMVE_BVTD());
                        break;
                    case 4:
                        sv1 = 0.5 * (trainNodes.get(i).getS_TA() + trainNodes.get(i + 1).getS_TA());
                        break;
                }
                g1 = getGainOfSplit(trainNodes, sv1, t);
                System.out.println(sv1 + "---" + g1);
                if(g1 > bestGain){
                    bestGain = g1;
                    ln.setAttr(t);
                    ln.setSplitval(sv1);
                }
            }
        }



        return ln;
    }


    private double getGainOfSplit(ArrayList<DataNode> trainNodes, double splitVal, int attr){
        double r1 = 0, r2 = 0, r3 = 0, r4 = 0, r5 = 0, r6 = 0, r7 = 0, r8 = 0, rTotal = 0;
        double left1 = 0, left2 = 0, left3 = 0, left4 = 0, left5 = 0, left6 = 0, left7 = 0, left8 = 0, leftTotal = 0;
        double right1 = 0, right2 = 0, right3 = 0, right4 = 0, right5 = 0, right6 = 0, right7 = 0, right8 = 0, rightTotal = 0;
        for (DataNode n : trainNodes){
            double value = 0;
            switch (attr){
                case 0 :
                    value = n.getWC_TA();
                    break;
                case 1:
                    value = n.getRE_TA();
                    break;
                case 2:
                    value = n.getEBIT_TA();
                    break;
                case 3:
                    value = n.getMVE_BVTD();
                    break;
                case 4:
                    value = n.getS_TA();
                    break;
            }
            rTotal ++;
            if(value <= splitVal) {
                leftTotal ++;
            }else{
                rightTotal ++;
            }
            if("AAA".equals(n.getRating())){
                if(value <= splitVal) {
                    left1 ++;
                }else{
                    right1 ++;
                }
                r1 ++;
            }else if("AA".equals(n.getRating())){
                if(value <= splitVal){
                    left2 ++;
                }else{
                    right2 ++;
                }
                r2 ++;
            }else if("A".equals(n.getRating())){
                if(value <= splitVal){
                    left3 ++;
                }else{
                    right3 ++;
                }
                r3 ++;
            }else if("BBB".equals(n.getRating())){
                if(value <= splitVal){
                    left4 ++;
                }else{
                    right4 ++;
                }
                r4 ++;
            }else if("BB".equals(n.getRating())){
                if(value <= splitVal){
                    left5 ++;
                }else{
                    right5 ++;
                }
                r5 ++;
            }else if("B".equals(n.getRating())){
                if(value <= splitVal){
                    left6 ++;
                }else{
                    right6 ++;
                }
                r6 ++;
            }else if("CCC".equals(n.getRating())){
                if(value <= splitVal){
                    left7 ++;
                }else{
                    right7 ++;
                }
                r7 ++;
            }else{
                if(value <= splitVal){
                    left8 ++;
                }else{
                    right8 ++;
                }
                r8 ++;
            }
        }

        double[] root = {r1, r2, r3, r4, r5, r6, r7, r8};
        double I_root = 0;
        for (double r : root){
            if(r != 0){
                I_root += - (r / rTotal) * Math.log(r / rTotal) / Math.log(2);
            }
        }

        double[] left = {left1, left2, left3, left4, left5, left6, left7, left8};
        double I_left = 0;
        for (double l : left){
            if(l != 0){
                I_left += - (l / leftTotal) * Math.log(l / leftTotal) / Math.log(2);
            }
        }

        double[] right = {right1, right2, right3, right4, right5, right6, right7, right8};
        double I_right = 0;
        for (double r : right){
            if(r != 0){
                I_right += - (r / rightTotal) * Math.log(r / rightTotal) / Math.log(2);
            }
        }

//        System.out.println(I_root + "--" + I_left + "--" + I_right);

        return I_root -  (leftTotal / rTotal) * I_left - (rightTotal / rTotal) * I_right;

    }

    /**
     * predict
     * @return
     */
    public String predict(LeafNode ln, DataNode data){
        while (ln.getLeft() != null || ln.getRight() != null){
            double value = 0;
            switch (ln.getAttr()){
                case 0 :
                    value = data.getWC_TA();
                    break;
                case 1:
                    value = data.getRE_TA();
                    break;
                case 2:
                    value = data.getEBIT_TA();
                    break;
                case 3:
                    value = data.getMVE_BVTD();
                    break;
                case 4:
                    value = data.getS_TA();
                    break;
            }
            if(value <= ln.getSplitval()){
                ln = ln.getLeft();
            }else{
                ln = ln.getRight();
            }
        }
        return ln.getLable();
    }

    private ArrayList<DataNode> sortList(ArrayList<DataNode> data, int attr){
        for (int i = 0; i < data.size(); i ++){
            for (int j = i + 1; j < data.size(); j ++){
                DataNode d = data.get(i);
                DataNode d2 = data.get(j);
                double value1 = 0, value2 = 0;
                switch (attr){
                    case 0 :
                        value1 = d.getWC_TA();
                        value2 = d2.getWC_TA();
                        break;
                    case 1:
                        value1 = d.getRE_TA();
                        value2 = d2.getRE_TA();
                        break;
                    case 2:
                        value1 = d.getEBIT_TA();
                        value2 = d2.getEBIT_TA();
                        break;
                    case 3:
                        value1 = d.getMVE_BVTD();
                        value2 = d2.getMVE_BVTD();
                        break;
                    case 4:
                        value1 = d.getS_TA();
                        value2 = d2.getS_TA();
                        break;
                }
                if(value1 > value2){
                    data.set(i, d2);
                    data.set(j, d);
                }
            }
        }

        return data;

    }
}
