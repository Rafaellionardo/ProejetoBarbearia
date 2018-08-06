/*
 * DateRange.java
 *
 * Created on 4 de Dezembro de 2002, 21:12
 */

package negocios;

import java.util.*;
import java.lang.*;
/**
 *
 * @author  regisb
 */
public class DateRange {
    
    private Date inicio;    
    private Date fim;    
    
    /** Creates a new instance of DateRange */
    public DateRange(Date dataInicio, Date dataFim) {
        inicio = dataInicio;
        fim = dataFim;
    }
    
    public Date getInicio () {
        return inicio;
    }
    
    public Date getFim () {
        return fim;
    }
    
    public boolean inclui(Date data) {
        return (data.before(fim) && data.after(inicio));
    }
    
    public boolean incluiCompleto(Date data)
    {
        return (!data.before(inicio) && !data.after(fim));
    }
    
    public boolean sobrepoe(DateRange dr) {
        return (dr.inclui (inicio) || dr.inclui (fim) || 
                this.inclui(dr.getInicio()) || this.inclui(dr.getFim()) ||
                this.inicio.equals(dr.getInicio()) || this.fim.equals(dr.getFim()));
    }
}
