package model;


public class Campsite {
    private int campId;
    private int campPrice;
    private int campOwner;
    private String campAddress;
    private String campName;
    private String campDescription;
    private String campImage;
    private boolean campStatus;
    private int limite;
    private String campsiteOwnerName;
    
    public Campsite (){
        
    }

    public Campsite(int campId, int limite) {
        this.campId = campId;
        this.limite = limite;
    }

    public Campsite(int campId, int campPrice, int campOwner, String campAddress, String campName, String campDecription,String campImage, boolean campStatus, String campsiteOwnerName) {
        this.campId = campId;
        this.campPrice = campPrice;
        this.campOwner = campOwner;
        this.campAddress = campAddress;
        this.campName = campName;
        this.campDescription = campDecription;
        this.campImage= campImage;
        this.campStatus = campStatus;
        this.campsiteOwnerName = campsiteOwnerName;
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

    public int getCampOwner() { return campOwner; }

    public void setCampOwner(int campOwner) { this.campOwner = campOwner; }

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

    public String getCampsiteOwnerName() {
        return campsiteOwnerName;
    }

    public void setCampsiteOwnerName(String campsiteOwnerName) {
        this.campsiteOwnerName = campsiteOwnerName;
    }
}
