package italo.santana.itau_desafio_cartoes.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class ClienteValidator {

    public static boolean isValidCpf(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        return cpf.matches("^[0-9]{11}$");
    }

    public static boolean isValidUf(String uf){
        String[] ufBrasil = {
                "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
                "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
                "RS", "RO", "RR", "SC", "SP", "SE", "TO"
        };

        for(String ufBrasilItem : ufBrasil){
            if(uf.toUpperCase().trim().equals(ufBrasilItem)){
                return true;
            }
        }

        return false;
    }

    public static boolean isIdadeCompativelComDataNascimento(int idadeCliente, LocalDate dataNascimento){
        int idadeCalculo = Period.between(dataNascimento, LocalDate.now()).getYears();
        return idadeCliente == idadeCalculo;
    }
}
