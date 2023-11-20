package inteface;

import Servico.ClienteServico;
import Servico.ProdutoServico;
import Servico.VendaServico;
import java.util.Scanner;
import modelo.Cliente;
import modelo.Produto;
import modelo.Venda;


public class Principal {

    
   public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Cliente clientes[] = new Cliente[100];
        Produto produtos[] = new Produto[100];
        Venda vendas[] = new Venda[200];
        String usuario, senha;
        int opc;
        System.out.println("==============================================");
        System.out.println("Bem Vindo ao Sistema de Controle de Refeição");
        System.out.println("==============================================");
        System.out.println("Usuario: ");
        usuario = entrada.next();
        System.out.println("Senha: ");
        senha = entrada.next();
        if(!usuario.equals("jj") && !senha.equals("jj")){
            System.out.println("Usuario e/ou senha inválidos!");
            System.exit(0);
        }
        do {            
            mostraMenuPrincipal();
            opc = entrada.nextInt();  
            switch(opc){
               
                 case 0: //sair do programa
                 System.exit(0);
                   break;
                case 1: //clientes
                    ClienteServico clienteServico = new ClienteServico();
                    clienteServico.desenvolvimentoCliente();
                    break;
                case 2: //produtos
                    ProdutoServico produtoServico = new ProdutoServico(produtos);
                    produtoServico.desenvolvimentoProduto();
                    break;
                case 3: // registro de venda
                    new VendaServico(vendas,clientes,produtos).desenvolvimentoVenda();
                    break;
                case 4://listagem de clientes
                    System.out.println("==============================================");
                    System.out.println("Listagem dos clientes cadastrados");
                    System.out.println("==============================================");
                    new ClienteServico().listaClientes();
                    break;
                case 5://listagem de produtos
                    System.out.println("==============================================");
                    System.out.println("Listagem dos produtos cadastrados");
                    System.out.println("==============================================");
                    new ProdutoServico(produtos).listaProdutos();
                    break;
                case 6://listagem de vendas
                    System.out.println("==============================================");
                    System.out.println("Listagem dos vendas registradas");
                    System.out.println("==============================================");
                    new VendaServico(vendas,clientes,produtos).listaVendas();
                    break;
                case 7://nota fiscal
                    System.out.println("==============================================");
                    System.out.println("Nota FISCAL");
                    System.out.println("==============================================");
                    new VendaServico(vendas,clientes,produtos).notaFiscal();
                    break;
                case 8://Limpa BD
                    System.out.println("==============================================");
                    System.out.println("DELETAR BANCO DE DADOS");
                    System.out.println("==============================================");
                    System.out.println("Deseja realmente imprimir o relatório (S\\N)");
                    if(entrada.next().equals("S")){
                        //para limpar o BD apenas cria nova instancia dos arrays 
                        clientes = new Cliente[100];
                        produtos = new Produto[100];
                        vendas = new Venda[200];
                    }
                    break;
                
                case 9: System.out.println("=================================================");
                        System.out.println("Relatorio da maior refeição cadastrda do cliente");
                        System.out.println("=================================================");
                        new VendaServico(vendas,clientes,produtos).maiorValorVenda();
                        break;
                
      
                case 10:System.out.println("=================================================");
                        System.out.println("Relatorio da menor refeição cadastrda do cliente");
                        System.out.println("=================================================");
                        new VendaServico(vendas,clientes,produtos).menorValorVenda();
                        break;
        
                case 11:System.out.println("=================================================");
                        System.out.println("Relatorio do total das refeições cadastrdas do cliente");
                        System.out.println("=================================================");
                        new VendaServico(vendas,clientes,produtos).totalVendaCliente();
                     break;
                default:
                    System.out.println("Digite uma opção válida");
            }
        } while (true);
    }
    
    public static void mostraMenuPrincipal(){
        System.out.println("===========Menu de Opções===========");
        System.out.println("1- Cadrasto de Cliente");
        System.out.println("2- Cadastro de Produto ");
        System.out.println("3- Lançamento de Vendas");       
        System.out.println("4- Listar dados dos Cliente");
        System.out.println("5- Listar dados dos Produtos");
        System.out.println("6- Listar vendas cadastradas");
        System.out.println("7- Emitir nota fiscal");
        System.out.println("8- Limpar banco de dados");
        System.out.println("9- Relatorio- Maior valor da refeição do cliente");
        System.out.println("10- Relatorio- Menor valor da refeição do cliente");
        System.out.println("11- Relatorio- tonalizar das refeições do cliente");
        System.out.println("Digite Zero para terminar");
    }

    
        
        
        
        
        
        
        
    
    
}
