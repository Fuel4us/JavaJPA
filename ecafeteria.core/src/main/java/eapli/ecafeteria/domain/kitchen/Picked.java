package eapli.ecafeteria.domain.kitchen;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Picked {
    private int pickedQuantity;

    public Picked(int pickedQuantity) {
        this.pickedQuantity = pickedQuantity;
    }

    public Picked(){
        this.pickedQuantity=0;
    }

    public int getPickedQuantity() {
        return pickedQuantity;
    }

    public void setPickedQuantity(int pickedQuantity) {
        this.pickedQuantity = pickedQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picked picked = (Picked) o;
        return pickedQuantity == picked.pickedQuantity;
    }

    @Override
    public int hashCode() {

        return Objects.hash(pickedQuantity);
    }

    @Override
    public String toString() {
        return "Picked{" +
                "pickedQuantity=" + pickedQuantity +
                '}';
    }
}
