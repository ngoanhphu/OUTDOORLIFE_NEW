package model;


public class Gear {
    private int gearId;
    private int gearPrice;
    private int gearOwner;
    private String gearName;
    private String gearDecription;
    private String gearImage;
    
    public Gear(){
        
    }

    public Gear(int gearId, int gearPrice, int gearOwner, String gearName, String gearDecription, String gearImage) {
        this.gearId = gearId;
        this.gearPrice = gearPrice;
        this.gearOwner = gearOwner;
        this.gearName = gearName;
        this.gearDecription = gearDecription;
        this.gearImage = gearImage;
    }

    public int getGearId() {
        return gearId;
    }

    public void setGearId(int gearId) {
        this.gearId = gearId;
    }

    public int getGearPrice() {
        return gearPrice;
    }

    public void setGearPrice(int gearPrice) {
        this.gearPrice = gearPrice;
    }

    public int getGearOwner() {
        return gearOwner;
    }

    public void setGearOwner(int gearOwner) {
        this.gearOwner = gearOwner;
    }

    public String getGearName() {
        return gearName;
    }

    public void setGearName(String gearName) {
        this.gearName = gearName;
    }

    public String getGearDecription() {
        return gearDecription;
    }

    public void setGearDecription(String gearDecription) {
        this.gearDecription = gearDecription;
    }

    public String getGearImage() {
        return gearImage;
    }

    public void setGearImage(String gearImage) {
        this.gearImage = gearImage;
    }

    
    
}
