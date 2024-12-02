package model;
public class Gear {
    private int gearId;
    private int campsiteOwner;
    private int price;
    private String name;
    private String description;
    private String image;

    // Getters and Setters
    public int getGearId() { return gearId; }
    public void setGearId(int gearId) { this.gearId = gearId; }

    public int getCampsiteOwner() { return campsiteOwner; }
    public void setCampsiteOwner(int campsiteOwner) { this.campsiteOwner = campsiteOwner; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public Gear(int gearId, int campsiteOwner, int price, String name, String description, String image) {
        this.gearId = gearId;
        this.campsiteOwner = campsiteOwner;
        this.price = price;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Gear() {

    }
}
