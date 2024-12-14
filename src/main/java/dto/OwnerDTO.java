package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Owner;
import model.User;

import java.math.BigDecimal;
import java.util.ArrayList;


public class OwnerDTO {
    private Owner owner;
    private User user;
    private BigDecimal totalRevenue;

    public OwnerDTO() {
    }

    public OwnerDTO(Owner owner, User user, BigDecimal totalRevenue) {
        this.owner = owner;
        this.user = user;
        this.totalRevenue = totalRevenue;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
