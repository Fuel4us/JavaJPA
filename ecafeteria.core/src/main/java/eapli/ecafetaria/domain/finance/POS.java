package eapli.ecafetaria.domain.finance;

import static eapli.ecafetaria.domain.finance.POSState.CLOSED;
import static eapli.ecafetaria.domain.finance.POSState.OPEN;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class POS implements AggregateRoot<Long>, Serializable{

    private static final long serialVersionUID = 1L;
    
    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;
    
    @Id
    private Long id;
    
    @OneToOne
    private POSState posState;
    //ponham aqui os atributos que faltarem de acordo com o modelo de dominio
    
    
    protected POS() {
        // for ORM
    }
    
    public POS(Long id){
        this.id = id;
    }
    
    @Override
    public boolean sameAs(Object other) {
        final POS pos = (POS) other;
        return id().equals(pos.id());
    }

    @Override
    public boolean is(Long otherId) {
        return AggregateRoot.super.is(otherId);
    }

    @Override
    public Long id() {
        return this.id;
    }
    
    public POSState posState() {
        return this.posState;
    }
    
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
    
    public boolean isOpen(){
        return this.posState == OPEN;
    }
    
    public boolean open(){
        if(this.posState == CLOSED){
            this.posState = OPEN;
            return true;
        }
        return false;
    }
    
    public boolean close(){
        if(this.posState == OPEN){
            this.posState = CLOSED;
            return true;
        }
        return false;
    }
}
