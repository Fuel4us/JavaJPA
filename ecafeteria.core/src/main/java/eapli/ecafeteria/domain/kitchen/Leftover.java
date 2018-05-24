package eapli.ecafeteria.domain.kitchen;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Leftover {
    private int leftoverQuantity;

    public Leftover(){
        this.leftoverQuantity=0;
    }

    public Leftover(int leftOverQuantity){
        if(leftoverQuantity>0){
            this.leftoverQuantity=leftoverQuantity;
        }
    }

    public int getQuantity() {
        return leftoverQuantity;
    }

    public void setQuantity(int leftoverQuantity) {
        this.leftoverQuantity = leftoverQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leftover leftover = (Leftover) o;
        return leftoverQuantity == leftover.leftoverQuantity;
    }

    @Override
    public int hashCode() {

        return Objects.hash(leftoverQuantity);
    }
}
