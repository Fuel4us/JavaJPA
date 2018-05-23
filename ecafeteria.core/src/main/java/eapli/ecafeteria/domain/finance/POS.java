package eapli.ecafeteria.domain.finance;

import eapli.ecafeteria.domain.authz.SystemUser;
import static eapli.ecafeteria.domain.finance.POSState.CLOSED;
import static eapli.ecafeteria.domain.finance.POSState.OPEN;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class POS implements AggregateRoot<Long>, Serializable{

    private static final long serialVersionUID = 1L;
    
    // ORM primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;
    @OneToOne
    private SystemUser cashier;
   
    private POSState posState;
    //ponham aqui os atributos que faltarem de acordo com o modelo de dominio
    
    public POS() {
        // for ORM
        this.posState = POSState.CLOSED;
        this.cashier = null;
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

    public SystemUser currentCashier() {
        return cashier;
    }
    
    public void setCashier(SystemUser cashier){
        this.cashier = cashier;
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
