///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package eapli.ecafeteria.persistence.inmemory;
//
//import eapli.ecafeteria.persistence.CanteenShiftRepository;
//import eapli.framework.domain.Designation;
//import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
//import java.util.Date;
//import java.util.Spliterator;
//import java.util.function.Consumer;
//
///**
// *
// * @author pedro
// */
//public class InMemoryCanteenShiftRepository extends InMemoryRepository<Date, Designation> implements CanteenShiftRepository{
//
//    @Override
//    protected Designation newKeyFor(Date entity) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void forEach(Consumer<? super Date> cnsmr) {
//        super.forEach(cnsmr); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Spliterator<Date> spliterator() {
//        return super.spliterator(); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//}
