/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.Scanner;

/**
 *
 * @author Rafael
 */


class Utilitario {
     
    
    public static boolean validaCep(String cep){
        Scanner input = new Scanner(System.in);
        String entrada;
        int operador = 0;
        String tratamento=cep;
        int numletras;
        
        operador=numletras=tratamento.length();//define quantidade de caracteres
        

        verificaLetrasString(tratamento);//verifica se na string possui letras

        if (numletras == 8 ){
            
            return true;
        }
        
        if (numletras > 8 ){

            System.out.println("Excedido limite de caracteres");
            return false;
        }// devolve erro se a string for maior que oito
        if (operador==1){
            System.out.println("Seu cep nao pode conter letras nem tracos");
            return false;
        }// resolve o algoritmo que testa se ha letras para ser armazenado
        else 
        
            System.out.println("Cep Incorreto. Digite o cep sem tracos!");
    
        return false;
        
    }
    
   
        
        public static boolean validarCpf(String sAux)
    {
      if (sAux.length() == 11 )
      {
          int    d1, d2; 
          int    digito1, digito2, resto; 
          int    digitoCPF; 
          String  nDigResult; 
          d1 = d2 = 0; 
          digito1 = digito2 = resto = 0; 
          for (int nCount = 1; nCount < sAux.length() -1; nCount++) 
          { 
              digitoCPF = Integer.valueOf (sAux.substring(nCount -1, nCount)).intValue(); 
//--------- Multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante. 
              d1 = d1 + ( 11 - nCount ) * digitoCPF; 
//--------- Para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior. 
              d2 = d2 + ( 12 - nCount ) * digitoCPF; 
          }; 
//--------- Primeiro resto da divisÃ£o por 11. 
          resto = (d1 % 11); 
//--------- Se o resultado for 0 ou 1 o digito Ã© 0 caso contrÃ¡rio o digito Ã© 11 menos o resultado anterior. 
          if (resto < 2)
              digito1 = 0;
          else 
              digito1 = 11 - resto; 
          d2 += 2 * digito1; 
//--------- Segundo resto da divisÃ£o por 11. 
          resto = (d2 % 11); 
//--------- Se o resultado for 0 ou 1 o digito Ã© 0 caso contrÃ¡rio o digito Ã© 11 menos o resultado anterior. 
          if (resto < 2) 
              digito2 = 0; 
          else 
              digito2 = 11 - resto; 
//--------- Digito verificador do CPF que estÃ¡ sendo validado. 
          String nDigVerific = sAux.substring (sAux.length()-2, sAux.length()); 
//--------- Concatenando o primeiro resto com o segundo. 
          nDigResult = String.valueOf(digito1) + String.valueOf(digito2); 
//--------- Comparar o digito verificador do cpf com o primeiro resto + o segundo resto. 
          return nDigVerific.equals(nDigResult); 
        }
        return false;
    }
        public static int testaStringVazia(String var){
        if (var.equals("")){
                System.out.println("vazia");
                return 1;//retorna 1 se estiver vazia
        }
        return 0;
        
        }
        public static int verificaLetrasString(String var){
            int numletras=var.length();//define quantidade de caracteres
        
        for (int i = 0; i < var.length(); i++) {
          if (Character.isDigit(var.charAt(i))==false){
	    return 1; //retorna 1 se hover letras			
          }
        }
        return 0;
        }
        
        
         public static String converteVirgula(String valor){
             
            char[] celula = valor.toCharArray();
             boolean comp;
             char aux;
                for(int i=0;i<valor.length();i++){
                    
                    if (celula[i]==','){
                        
                    celula[i]='.';
                 
                    }
                }
                
                 valor=String.copyValueOf(celula);  
 
 
 
            return valor;
         }
        
        
        public static boolean validaEstado(String est){
        String es[]= new String [27];
        int a=1;
        es[0]="AC";
        es[1]="AL";
        es[2]="AM";
        es[3]="AP";
        es[4]="BA";
        es[5]="CE";
        es[6]="DF";
        es[7]="ES";
        es[8]="GO";
        es[9]="MA";
        es[10]="MT";
        es[11]="MS";
        es[12]="MG";
        es[13]="PA";
        es[14]="PB";
        es[15]="PR";
        es[16]="PE";
        es[17]="PI";
        es[18]="RJ";
        es[19]="RN";
        es[20]="RS";
        es[21]="RO";
        es[22]="RR";
        es[23]="SC";
        es[24]="SP";
        es[25]="SE";
        es[26]="TO";
        //{"AC","AL", "AM", "AP", "BA","CE","DF","ES""GO", "MA", "MT","MS","MG,","PA", "PB","PR","PE","PI","RJ","RN","RS","RO",
        //"RR""SC","SP","SE","TO" };
           for (int i=0; i<26;i++){
               if (es[i].equalsIgnoreCase(est) ){

                   a=0;
                   return true;

               }

           }
               if (a==0){
               System.out.print("Estado nÃ£o Encontrado na base.");
               return false;

               }
               return false;

        }
       }
 

