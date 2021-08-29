package classes;

import java.text.ParseException;
import javax.swing.text.MaskFormatter;

public abstract class Pessoa {
    protected String nome, cpf;

    private String formata(String cpf) {
        try {
          final MaskFormatter formatador = new MaskFormatter("###.###.###-##");
          formatador.setValidCharacters(cpf);
          formatador.setValueContainsLiteralCharacters(false);
          return formatador.valueToString(cpf);
        } catch (ParseException e) {
          throw new RuntimeException("Valor gerado não bate com o padrão: " + cpf, e);
        }
      }

    //private boolean verificarCpf(String cpf) {
    //    if(cpf.length() != 14) {
    //        throw new InputMismatchException("\nO tamanho do CPF deve conter 14 digitos, como o padrão: 000.000.000-00");
    //    } else if(!cpf.matches("[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}")) {
    //        throw new InputMismatchException("O padrão digitado está errado !! O correto seria 000.000.000-00");
    //    } else {
    //        return true;
    //    }
    //}

    //Get e Set
    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;    
    }
    public String getCpf() {
        return this.cpf;
    }
    public void setCpf(String cpf) {
        //verificarCpf(this.cpf); 
        formata(this.cpf);
        this.cpf = cpf;  
    }
}
