package com.blog.api.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BeitragViewId implements Serializable {
    private String user;
    private Long beitrag;
    private Date date;

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        BeitragViewId beitragViewId = (BeitragViewId) obj;
        return beitragViewId.getUser() == getUser() &&
                beitragViewId.getBeitrag() == getBeitrag() &&
                beitragViewId.getDate() == getDate();
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, beitrag, date);
    }
}
