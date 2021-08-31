package programa;

import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Scanner;
import classes.Aeronave;
import classes.Piloto;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {
        
        final int MAX_ELEMENTOS = 2;
        int opcao = 0; 
        int qtdCadastrados = 0;
        Scanner in = new Scanner(System.in);

        Piloto[] piloto; //Declaração dos vetores, para poder reinstanciar a qualquer momento se for preciso
        Aeronave[] nave;

        piloto = new Piloto[MAX_ELEMENTOS];
        nave = new Aeronave[MAX_ELEMENTOS];        
                
        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaço de armazenamento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            try {
                opcao = in.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Por favor, digite apenas números!!");
            }

            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora
                if (qtdCadastrados == piloto.length) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos");
                    voltarMenu(in);
                    continue;
                }

                //Cadastre seu piloto aqui
                else if (qtdCadastrados < piloto.length) {
                    piloto[qtdCadastrados] = new Piloto();
                    nave[qtdCadastrados] = new Aeronave();

                    System.out.print("\nNome: ");
                    piloto[qtdCadastrados].setNome(in.nextLine()); //Colocar esse cara para ser o piloto que pilota a nave

                    System.out.print("CPF do Piloto (###.###.###-##): ");
                    boolean cadastrado = false; // Validar se o cpf esta corretamente digitado
                    do {
                        try {
                            piloto[qtdCadastrados].setCpf(in.nextLine());
                            cadastrado = true;
                        } catch (InputMismatchException ex) {
                            System.out.println(ex.getMessage());
                            System.out.print("Digite Novamente: ");
    
                        }
                    } while (cadastrado == false);
                    
                    System.out.print("Brevê: ");
                    piloto[qtdCadastrados].setBreve(in.nextLine());

                    System.out.print("Número de série da Aeronave: ");
                    nave[qtdCadastrados].setNumSerie(in.nextLine());

                    qtdCadastrados++;
                }

                System.out.println("\nPILOTO CADASTRADO COM SUCESSO");
                voltarMenu(in);

            } else if (opcao == 2) {
                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há motoristas cadastrados para exibir.");
                    voltarMenu(in);
                }

                // Mostrar pilotos cadastrados
                else if (qtdCadastrados > 0) {
                    System.out.println("\n-------------LISTA DE PILOTOS CADASTRADOS-------------");
                    for (int i = 0; i < qtdCadastrados; i++) {                         
                        System.out.printf("\nPiloto nº%d >>> Nome: %s - CPF: %s - Brevê: %s - Aeronave: %s", (i + 1), piloto[i].getNome(), piloto[i].getCpf(), piloto[i].getBreve(), nave[i].getNumSerie());
                    }
                }

                voltarMenu(in);

            } else if (opcao == 3) {
                System.out.print("Digite o CPF que deseja buscar: ");
                String cpf = in.nextLine(); //que vai ser pedido para o usuário
                
                for (int i = 0; i < qtdCadastrados; i++) {
                    if (cpf.equals(piloto[i].getCpf())) {
                        System.out.printf("\nCPF correspondente ao piloto nº%d: %s - Brevê: %s - Aeronave: %s", (i+1), piloto[i].getNome(), piloto[i].getBreve(), nave[i].getNumSerie());
                    } else if (i == qtdCadastrados) {
                        System.out.printf("\nCPF não corresponde a nenhum piloto cadastrado.");
                    }
                }

                voltarMenu(in);
            
            } else if (opcao == 4) {

                int novaCapacidade = 0;
                boolean cadastrado = false;
                System.out.print("Entre com a nova capacidade desejada: ");
                    
                do {
                    try {
                        novaCapacidade = in.nextInt();
                        in.nextLine(); // Remove o entre preso no Buffer
    
                        if(novaCapacidade <= piloto.length) {
                            System.out.printf("Para aumentar a capacidade, o tamanho informado necessita ser maior do que %d!! ", piloto.length);
                            System.out.print("\nDigite Novamente: ");
    
                        } else {
                            cadastrado = true;
                        }
    
                            
                    } catch (InputMismatchException ex) {
                        System.out.println("Por favor, digite apenas números!! ");
                        in.nextLine(); 
                            
                    } 
                } while(!cadastrado);
    
                Piloto[] armazenarPiloto = new Piloto[piloto.length];
                Aeronave[] armazenarAeronave = new Aeronave[nave.length];
    
                armazenarPiloto = piloto;
                armazenarAeronave = nave;
    
                piloto = new Piloto[novaCapacidade];
                nave = new Aeronave[novaCapacidade];
    
                for (int i = 0; i < qtdCadastrados; i++) {
                    piloto[i] = new Piloto();
                    piloto[i] = armazenarPiloto[i];
                }
    
                for (int i = 0; i < qtdCadastrados; i++) {
                    nave[i] = new Aeronave();
                    nave[i] = armazenarAeronave[i];
                }
    
                System.out.println("\n CAPACIDADE ALTERADA COM SUCESSO!! ");
                voltarMenu(in);
            
            } else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
               
        } while (opcao != 0);

        System.out.println("Fim do programa!");
        in.close();
    }


    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\n\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}