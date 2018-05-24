package eapli.ecafeteria.domain.kitchen;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class NotPicked {
    private int notPickedQuantity;

    public NotPicked(int notPickedQuantity) {
        this.notPickedQuantity = notPickedQuantity;
    }





    public int getNotPickedQuantity() {

        return notPickedQuantity;
    }

    public void setNotPickedQuantity(int notPickedQuantity) {
        this.notPickedQuantity = notPickedQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotPicked notPicked = (NotPicked) o;
        return notPickedQuantity == notPicked.notPickedQuantity;
    }

    @Override
    public int hashCode() {

        return Objects.hash(notPickedQuantity);
    }

    @Override
    public String toString() {
        return "NotPicked{" +
                "notPickedQuantity=" + notPickedQuantity +
                '}';
    }
}
