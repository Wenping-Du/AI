import java.io.Serializable;

public class DataNode implements Serializable {
    private double WC_TA;
    private double RE_TA;
    private double EBIT_TA;
    private double MVE_BVTD;
    private double S_TA;
    private String Rating;

    public double getWC_TA() {
        return WC_TA;
    }

    public void setWC_TA(double WC_TA) {
        this.WC_TA = WC_TA;
    }

    public double getRE_TA() {
        return RE_TA;
    }

    public void setRE_TA(double RE_TA) {
        this.RE_TA = RE_TA;
    }

    public double getEBIT_TA() {
        return EBIT_TA;
    }

    public void setEBIT_TA(double EBIT_TA) {
        this.EBIT_TA = EBIT_TA;
    }

    public double getMVE_BVTD() {
        return MVE_BVTD;
    }

    public void setMVE_BVTD(double MVE_BVTD) {
        this.MVE_BVTD = MVE_BVTD;
    }

    public double getS_TA() {
        return S_TA;
    }

    public void setS_TA(double s_TA) {
        S_TA = s_TA;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }
}
