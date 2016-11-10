package util;

import jess.Fact;
import jess.JessException;
import jess.Rete;
import jess.Value;

import java.util.Iterator;

/**
 * Created edwin
 */
public class Helper {
    Rete motor;

    public Helper(Rete motor) {
        this.motor = motor;
    }


    /**
     * Busca un hecho con estructura de un deftemplate dado
     * @param templateName : nombre de un template
     * @return : primer hecho con estructura de @param templateName o null si no existe
     */
    public Fact findFactByTempleteName(String templateName){
        Fact f = null;
        Iterator iterator = motor.listFacts();

        while (iterator.hasNext()){
            Fact faux = (Fact)iterator.next();
            if(faux.getName().equals(templateName)){
                f = faux;
                break;
            }
        }
        return f;
    }


    /**
     * Busca un hecho con estructura de un deftemplate dado y si cumple una condición para un slot
     * @param templateName : nombre de un template
     * @param slotName : nombre de slot
     * @param slotValue : valor esperado para @param slotName
     * @return : primer elemento que cumple con la condición o null si no existe
     */
    public Fact findFactByTemplateName(String templateName, String slotName, String slotValue){
        Fact f = null;
        Iterator iterator = motor.listFacts();

        while (iterator.hasNext()){
            Fact faux = (Fact)iterator.next();
            if(faux.getName().equals(templateName)){
                String slotV;
                try {
                    Value value = faux.getSlotValue(slotName);
                    slotV = value.stringValue(motor.getGlobalContext());
                } catch (JessException e) {
                    return null;
                }

                if(slotV.equals(slotValue)){
                    f = faux;
                    break;
                }

            }

        }
        return f;
    }
}
