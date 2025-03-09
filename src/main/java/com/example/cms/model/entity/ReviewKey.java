package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter

public class ReviewKey implements Serializable {

    @Column(name= "userId")
    String userId;

    @Column(name = "productId")
    Long productId;

    @Override
    public int hashCode() {
        String concatString = String.valueOf(userId.hashCode()) + String.valueOf(productId.hashCode());
        return concatString.hashCode();
    }

    public ReviewKey() {}

    public ReviewKey(String userId, Long productId) {
        this.setUserId(userId);
        this.setProductId(productId);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null){
            return false;
        }
        if (o == this)
            return true;
        if (!(o instanceof ReviewKey))
            return false;
        ReviewKey other = (ReviewKey) o;
        return userId.equals(other.userId) && productId.equals(other.productId);
    }

}



