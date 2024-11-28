package model;


public class Campsite {
    private int campId;
    private int campPrice;
    private String campAddress;
    private String campName;
    private String campDescription;
    private String campImage;
    private boolean campStatus;
    private int limite;
    
    public Campsite (){
        
    }

    public Campsite(int campId, int limite) {
        this.campId = campId;
        this.limite = limite;
    }

    public Campsite(int campId, int campPrice, String campAddress, String campName, String campDecription,String campImage, boolean campStatus) {
        this.campId = campId;
        this.campPrice = campPrice;
        this.campAddress = campAddress;
        this.campName = campName;
        this.campDescription = campDecription;
        this.campImage= campImage;
        this.campStatus = campStatus;
    }

    public int getCampId() {
        return campId;
    }

    public void setCampId(int campId) {
        this.campId = campId;
    }

    public int getCampPrice() {
        return campPrice;
    }

    public void setCampPrice(int campPrice) {
        this.campPrice = campPrice;
    }

    public String getCampAddress() {
        return campAddress;
    }

    public void setCampAddress(String campAddress) {
        this.campAddress = campAddress;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getCampDescription() {
        return campDescription;
    }

    public void setCampDescription(String campDecription) {
        this.campDescription = campDecription;
    }

    public String getCampImage() {
        return campImage;
    }

    public void setCampImage(String campImage) {
        this.campImage = campImage;
    }

    public boolean isCampStatus() {
        return campStatus;
    }

    public void setCampStatus(boolean campStatus) {
        this.campStatus = campStatus;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }
    
    
}
