/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.swing.JOptionPane;
import util.Helper;
import views.View;
import jess.*;

/**
 *
 * @author edwin
 */
public class EventHandler implements JessListener{

    View vista;
    public EventHandler(View vista) {
        this.vista = vista;
    }

    @Override
    public void eventHappened(JessEvent je){
        int type = je.getType();
        Rete rete = (Rete)je.getSource();
        Context context = je.getContext();
        Helper helper = new Helper(rete);

        if(type == JessEvent.DEFRULE_FIRED){
           Fact nodoActual = helper.findFactByTempleteName("MAIN::nodo-actual");
            if (nodoActual != null){
                String slotV;
                Fact nodo = null;
                try {
                    slotV = nodoActual.get(0).toString();
                    nodo = helper.findFactByTemplateName("MAIN::Nodo", "nombre", slotV);
                    // desde aqui comienza la parte donde se muestra el contenido de la variable instrucctivo, con un showmessagge
                    String descripcion = nodo.getSlotValue("descrip").stringValue(context);
                    if(!descripcion.equals("null"))
                            JOptionPane.showMessageDialog(null,descripcion,"Instructivo",JOptionPane.INFORMATION_MESSAGE);
                    if (nodo != null){
                        String tipo = nodo.getSlotValue("tipo").stringValue(context);
                        if (tipo.equals("pregunta")){
                            String pregunta = nodo.getSlotValue("pregunta").stringValue(context);
                            vista.cambiarPregunta(pregunta);
                        }else if(tipo.equals("respuesta")){
                            String respuesta = nodo.getSlotValue("respuesta").stringValue(context);
                            vista.darRespuesta("TERMINADO LA FASE : "+respuesta);
                        }
                    }
                } catch (JessException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
}
