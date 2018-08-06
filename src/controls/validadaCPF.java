/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rafael
 */
public class validadaCPF {
    public  boolean validadaCPF(String num_cpf) {
		num_cpf = num_cpf.replace(".","");
		num_cpf = num_cpf.replace(",","");
		num_cpf = num_cpf.replace("/","");
		num_cpf = num_cpf.replace("-","");
		if(num_cpf.length() < 11) {
			return false;
		} else {
			Pattern pat = Pattern.compile("\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d");
			Matcher mtc = pat.matcher(num_cpf);
			if(!mtc.find()) {
				return false;
			} else {
				int[] validador = new int[] {11,10,9,8,7,6,5,4,3,2}; // Sequencia de validacao
				int primeiro = 0, segundo = 0; // Digitos validadores
				// Separa os digitos do cpf, multiplica esses digitos
				// pelo validador e soma os multiplicados
				int soma = 0;
				int[] cpf = new int[num_cpf.length()];
				for(int i = 0; i < cpf.length; i++) {
					cpf[i] = Integer.parseInt(String.valueOf(num_cpf.charAt(i)));
					if(i < (cpf.length-2)) {
						soma += (cpf[i] * validador[i+1]);
					}
				}
				// Pega o resto da divisao da soma por 11 
				// Se for menor que dois o primeiro digito eh zero
				// Se for maior que dois o primeiro digito eh igual a (11 - resto)
				if((soma % 11) < 2 ) {
					primeiro = 0;
				} else {
					primeiro = (11 - (soma % 11));
				}
				if(primeiro != cpf[9]) {
					return false;
				} else {
					// Multiplica os digitos do cpf + o primeiro validador pelo validador e soma os multiplicados
					soma = 0;
					for(int i = 0; i < cpf.length-1; i++) {
						soma += (cpf[i] * validador[i]);
					}
					// Pega o resto da divisao da soma por 11 
					// Se for menor que dois o segundo digito eh zero
					// Se for maior que dois o segundo digito eh igual a (11 - resto)
					if((soma % 11) < 2 ) {
						segundo = 0;
					} else {
						segundo = (11 - (soma % 11));
					}
					if(segundo != cpf[10]) {
						return false;
					} else {
						return true;
					}
				}
			}
		}
	}
}
