/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import dao.ContaReceberDao;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author renan
 */
public class Pagamento {

    ArrayList<ContaReceber> contas = new ArrayList();

    public static ArrayList<ContaReceber> gerarParcelasPagamentoAgendamento(double valorTotal, int nroParcelas, Agendamento a, Date data) {
        ArrayList<ContaReceber> contas = new ArrayList();
        
        String tamanhoValorParcela = null; //String que pega o tamanho total de caracteres do valor da parcela
        //numero de dias a mais  
        int dias = 30;
        DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        
        double valorParcela = a.getValorTotal() / nroParcelas;
        System.out.println("Valor parcela:" +valorParcela);
        tamanhoValorParcela = valorParcela+""; 
        //verifica quantas casas tem antes do primeiro ponto. 
        int tamanhoPrimeiraParte =  tamanhoValorParcela.indexOf(".");
        //verifica quantas casas decimais tem o valor 
        int tamanhoSegundaParte =  tamanhoValorParcela.length() - tamanhoPrimeiraParte - 1; 
        System.out.println(tamanhoPrimeiraParte +" - " +tamanhoSegundaParte);
        if (tamanhoSegundaParte<3){
            for (int x = 0; x < nroParcelas; x++) {
                ContaReceber conta = new ContaReceber();
                conta.setValor(valorParcela);
                conta.setStatus('a');
                conta.setParcela(x + 1);
                conta.setValorPago(0.0);
                if (x == 0) {
                    conta.setVencimento(data);
                } else {
                    Calendar d = new GregorianCalendar();
                    d.setTime(data);
                    d.add(Calendar.DATE, +dias);

                    dias = dias + 30;

                    conta.setVencimento(new Date(d.getTime().getTime()));
                }
                conta.setDataPagamento(null);
                conta.setAgendamento(a);
                contas.add(conta);
            }
        } else if(tamanhoSegundaParte>=3) {

           contas = arrendondarParcelas(valorTotal, nroParcelas, a, data);
        }

        return contas;

    }

    public static ArrayList<ContaReceber> arrendondarParcelas(Double valorTotal, int nroParcelas, Agendamento a, Date data) {
        ArrayList<ContaReceber> contas = new ArrayList();
        //numero de dias a mais  
        int dias = 30;
            double valorParcela = a.getValorTotal() / nroParcelas;
            BigDecimal big = new BigDecimal(valorParcela);
            Double valorTmp =  Double.valueOf(String.format(Locale.US, "%.2f", valorParcela));
           
            
            for (int x = 0; x < nroParcelas; x++) {
                ContaReceber conta = new ContaReceber();
                if (x+1 == nroParcelas) {
                    big = big.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    conta.setVencimento(data);
                    
                    Double soma = 0.0; 
                   
                    for(int i =0; i < contas.size(); i++){
                        soma = soma + contas.get(i).getValor(); 
                    }
                        System.out.println("Soma é:" +soma);
                   // if((soma + valorTmp + 0.01) == valorTotal){
                        Double valorTmp2 = Double.valueOf(String.format(Locale.US, "%.2f", valorTotal - soma));
                        conta.setValor(valorTmp2);
                    //}else{
                       
                      // conta.setValor(valorTmp + 0.01);
                    //}
    
                    
                } else {
                    Calendar d = new GregorianCalendar();
                    d.setTime(data);
                    d.add(Calendar.DATE, +dias);
                    conta.setValor(valorTmp);
                    dias = dias + 30;
                    conta.setVencimento(new Date(d.getTime().getTime()));
                }

                conta.setStatus('a');
                conta.setParcela(x + 1);
                conta.setValorPago(0.0);

                conta.setDataPagamento(null);
                conta.setAgendamento(a);

                contas.add(conta);

            }
    return contas;

    }
    
   public static boolean consultarAgendamentoPago(Agendamento a){
       boolean ok = true; 
       ArrayList<ContaReceber> contas = new ArrayList(); 
       ContaReceberDao dao = new ContaReceberDao(); 
               
       contas = dao.consultarPorAgendamento(a.getCodigo()) ; 
        
       for(int i =0; i < contas.size(); i++){
           if(contas.get(i).getStatus() != 'p'){
               ok = false; 
           }
       }
       
       return ok; 
   }

}
