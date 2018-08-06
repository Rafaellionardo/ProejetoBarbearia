/*
 * Questionario.java
 *
 * Created on 3 de Dezembro de 2002, 09:24
 */

package negocios;

/**
 *
 * @author  regisb
 */
public class Questionario {
    
    private String questao;
    private int id;    
    private static int total = 0;    
    private Questionario prox = null;    
    private static Questionario prim = null;
    
    /** Creates a new instance of Questionario */
    public Questionario(String pquestao) {
        questao = pquestao;
        total++;
        id = total;
        if (prim == null) {
            prim = this;
        } else {
            Questionario q = prim;
            Questionario t;
            t = q.getProxima();
            while (t != null) {
                q = t;
                t = q.getProxima();
            }
            q.setProxima (this);
        }
    }
             
    public String getQuestao() {
        return questao;
    }
    
    public int getId() {
        return id;
    }
 
    public int getTotal () {
        return total;
    }
    
    public Questionario getProxima() {
        return prox;
    }
    
    private void setProxima(Questionario q) {
        this.prox = q;
    }
    
    public static Questionario getPrimeira () {
        return prim;
    }
    
}

