/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import jess.JessEvent;
import jess.JessException;
import jess.Rete;

/**
 *
 * @author edwin
 */
public class MotorController{
    Rete motor;
      private int numFase;
    public MotorController(int i) {
        try {
            motor = new Rete();
            
            motor.reset();
            
            switch(i){
            case 1:  motor.batch("base/planta-grafico01.clp");
            break;
            case 2: motor.batch("base/planta-grafico02.clp");
            break;
            case 3:motor.batch("base/planta-grafico03.clp");
            break;
            default:
           }
            
           
        } catch (JessException ex) {
            Logger.getLogger(MotorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void afirmar(String hecho) throws JessException{
        motor.assertString(hecho);
        motor.run();
    }
    
    public void addEscuchador(EventHandler eventController){
        motor.addJessListener(eventController);
        motor.setEventMask(JessEvent.DEFRULE_FIRED);
    }
    
    public void ejecutar(){
        try {
            this.motor.run();
        } catch (JessException ex) {
            Logger.getLogger(MotorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setFase(int fase) {
        numFase=fase;
    }
    
}
