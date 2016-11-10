/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import views.View;

/**
 *
 * @author Alex
 */
public class Controller {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        View zooView = new View();
        MotorController motorController = new MotorController();        
        EventHandler eventController = new EventHandler(zooView);
        
        zooView.setMotorController(motorController);
        
        motorController.addEscuchador(eventController);
        
        motorController.ejecutar();
        
    }
    
}
