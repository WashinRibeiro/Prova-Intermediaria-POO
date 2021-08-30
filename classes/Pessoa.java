package classes;

import java.util.InputMismatchException;

public class Pessoa {
    private String nome;
    private String cpf;

    //Get e Set
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        if(verificarCpf(cpf)){
            this.cpf = cpf;
        }
    }

    //Métodos
    boolean verificarCpf(String cpf){
        if (!cpf.matches("[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}")) {
            throw new InputMismatchException("\nO padrão digitado está errado!! o Correto é: ###.###.###-###");
        } else {
            return true; 
        }
    }


}