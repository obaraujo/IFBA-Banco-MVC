import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Banco {

  private static String f(double num) {
    DecimalFormat formater = new DecimalFormat("##,###,###,##0.00");
    return formater.format(num);
  }

  private static boolean campoNaoPreenchido(String campo) {
    return campo.isEmpty();
  }

  private static boolean campoIgualNulo(String campo) {
    return campo == null;
  }

  private static boolean validarSenha(String senha) {
    return (senha.length() > 4 || senha.length() < 4);
  }
 
  public static void menuCadastro(ImageIcon icon) {
    Object[] cadastro = { "Cadastrar", "Sair" };
    int escolhaInicio = JOptionPane.showOptionDialog(null, "\tBem vindo ao Banco MVC \n Cadastre-se agora", "Banco MVC", 0, 0, icon, cadastro, cadastro);

    while (escolhaInicio != 0 && escolhaInicio != 1) {
      escolhaInicio = JOptionPane.showOptionDialog(null, "\tDesculpe, escolha errada \n Cadastre-se agora", "Banco MVC", 0, 0, icon, cadastro, cadastro);
    }

    if (escolhaInicio == 0) {
      cadastro(icon);
    } else {
      System.exit(0);
    }
  }

  public static void cadastro(ImageIcon icon) {
      String numDeContasString = JOptionPane.showInputDialog(null, "Quantas contas você quer criar?", "Cadastro", 3);
      if (campoIgualNulo(numDeContasString)) menuCadastro(icon);
      while (campoNaoPreenchido(numDeContasString)) {
        numDeContasString = JOptionPane.showInputDialog(null, "Este campo é obrigatório! \nQuantas contas você quer criar?", "Cadastro", 3);
        if (campoIgualNulo(numDeContasString)) menuCadastro(icon);
      }

      int numDeContas = Integer.parseInt(numDeContasString);
      Conta[] contas = new Conta[numDeContas];
      String nome;
      String CPF;
      String senha1;
      String saldo1;
      int senha;
      double saldo;

      for (int i = 0; i < contas.length; i++) {
        nome = JOptionPane.showInputDialog(null, "Digite seu nome", "Cadastro " + (i+1) + "/"+ numDeContas, 3);
        if (campoIgualNulo(nome)) {
          cadastro(icon);
          break;
        }
        while (campoNaoPreenchido(nome)) {
          nome = JOptionPane.showInputDialog(null, "Este campo não pode ficar vazio \nDigite seu nome", "Cadastro " + (i+1) + "/"+ numDeContas, 3);
          if (campoIgualNulo(nome)) {
            cadastro(icon);
            break;
          }
        }

        CPF = JOptionPane.showInputDialog(null, "Digite seu CPF", "Cadastro " + (i+1) + "/"+ numDeContas, 3);
        if (campoIgualNulo(CPF)) {
          cadastro(icon);
          break;
        }
        while (campoNaoPreenchido(CPF)) {
          CPF = JOptionPane.showInputDialog(null, "Este campo não pode ficar vazio \nDigite seu CPF", "Cadastro " + (i+1) + "/"+ numDeContas, 3);
          if (campoIgualNulo(CPF)) {
            cadastro(icon);
            break;
          }
        }
        
        senha1 = JOptionPane.showInputDialog(null, "Digite sua senha de 4 dígitos", "Cadastro " + (i+1) + "/"+ numDeContas, 3);
        if (campoIgualNulo(senha1)) {
          cadastro(icon);
          break;
        }

        while (validarSenha(senha1)) {
          senha1 = JOptionPane.showInputDialog(null, "Sua senha precisa ter 4 dígitos", "Cadastro " + (i+1) + "/"+ numDeContas, 3);
          while (campoNaoPreenchido(senha1)) {
            senha1 = JOptionPane.showInputDialog(null, "Este campo não pode ficar vazio \nDigite sua senha", "Cadastro " + (i+1) + "/"+ numDeContas, 3);
            if (campoIgualNulo(senha1)) {
              cadastro(icon);
              break;
            }
          }
          if (campoIgualNulo(senha1)) {
            cadastro(icon);
            break;
          }
        }
        
        senha = Integer.parseInt(senha1);
        
        saldo1 = JOptionPane.showInputDialog(null, "Quanto você vai depositar?", "Cadastro " + (i+1) + "/"+ numDeContas, 3);
        if (campoIgualNulo(saldo1)) {
          cadastro(icon);
          break;
        }
        if (campoNaoPreenchido(saldo1)) {
          saldo1 = "0";
        }
        saldo = Double.parseDouble(saldo1);
        
        contas[i] = new Conta(nome, CPF, senha, saldo);
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!!!", "Sucesso!", 1);
      }
      entrar(icon, contas, numDeContas);
  }

  public static void entrar(ImageIcon icon, Conta[] contas, int numDeContas) {
    Object[] botoesInicio = { "Entrar", "Sair" };
    int escolhaInicio = JOptionPane.showOptionDialog(null, "\tParabéns, agora você faz parte da familia MVC \n Faça login", "Conta(as) Criada(as) - Banco MVC", 0, 0, icon, botoesInicio, botoesInicio[0]);

    while (escolhaInicio != 0 && escolhaInicio != 1) {
      escolhaInicio = JOptionPane.showOptionDialog(null, "\tDesculpe, escolha errada \n Faça login agora", "Conta(as) Criada(as) - Banco MVC", 0, 0, icon, botoesInicio, botoesInicio);
    }

    if (escolhaInicio == 0) {
      login(icon, contas, numDeContas);
    } else {
      System.exit(0);
    }
  }
 
  public static void login(ImageIcon icon, Conta[] contas, int numDeContas) {
    int indiceDaConta = 0;
    Object[] nomeDaConta = new Object[numDeContas];

    for (int i = 0; i < contas.length; i++) {
      nomeDaConta[i] = contas[i].cliente.getNome();
    }

    Object conta1 = JOptionPane.showInputDialog(null, "Escolha a conta para entrar", "Login", 0, icon, nomeDaConta, nomeDaConta);
    if (conta1 == null) {
      entrar(icon, contas, numDeContas);
    }
    String conta = conta1.toString();

    for (int i = 0; i < contas.length; i++) {
      if (contas[i].cliente.getNome().equals(conta)) {
        indiceDaConta = i;
      }
    }

    String senha1 = JOptionPane.showInputDialog(null, "Digite sua senha", "Login", 3);
    if (campoIgualNulo(senha1)) {
      login(icon, contas, numDeContas);
    }
    int senha = Integer.parseInt(senha1);
    
    while (contas[indiceDaConta].cliente.getSenha() != senha) {
      senha1 = JOptionPane.showInputDialog(null, "Senha incorreta \nDigite novamente", "Login", 3);
      if (campoIgualNulo(senha1)) {
        login(icon, contas, numDeContas);
      }
      senha = Integer.parseInt(senha1);
    }

    Object[] botoesMenu = { "Sacar", "Transferir", "Depositar", "Consultar", "Troca de conta", "Sair" };
    Object[] botoesMenuErro = { "Tentar novamente", "Cancelar" };
    menuInicial(icon, botoesMenu, botoesMenuErro, conta, contas, indiceDaConta, numDeContas);
  }

  public static void menuInicial(ImageIcon icon, Object[] botoes, Object[] bErro, String nome, Conta[] contas, int indiceDaConta, int numDeContas) {
    int escolhaDoMenu = JOptionPane.showOptionDialog(null, "Bem vindo(a) " + contas[indiceDaConta].cliente.getNome() + " ao Banco MVC \n Selecione uma das opções abaixo", "Inicio - Banco MVC", 0, 0, icon, botoes, botoes);
    
    if (escolhaDoMenu == 0) {
      sacar(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
    } else if (escolhaDoMenu == 1) {
      transferir(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
    } else if (escolhaDoMenu == 2) {
      depositar(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
    } else if (escolhaDoMenu == 3) {
      consultar(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
    } else if (escolhaDoMenu == 4) {
      login(icon, contas, numDeContas);
    } else {
      System.exit(0);
    }
  }

  public static void sacar(ImageIcon icon, Object[] botoes, Object[] bErro, String nome, Conta[] contas, int indiceDaConta, int numDeContas) {
    String valor1 = JOptionPane.showInputDialog(null, "Digite o valor a sacar", "Sacar", 3);
    if (campoIgualNulo(valor1)) {
      menuInicial(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);;
    }

    while (valor1.isEmpty()) {
      valor1 = JOptionPane.showInputDialog(null, "Digite um valor a sacar", "Sacar", 3);
      if (campoIgualNulo(valor1)) {
        menuInicial(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
      }
    }
    double valor = Double.parseDouble(valor1);

    boolean resultado = contas[indiceDaConta].saque(valor);
    if (resultado) {
      JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!", "Sucesso :)", 1);
      menuInicial(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
    } else {
      int escolha = JOptionPane.showOptionDialog(null, "Saldo insuficiente :( \n Saldo = R$ " + f(contas[indiceDaConta].cliente.getSaldo()), "Falha :(", 0, 2, null, bErro, bErro);
      if (escolha == 0) {
        sacar(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
      } else {
        menuInicial(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
      }
    }
  }

  public static void transferir(ImageIcon icon, Object[] botoes, Object[] bErro, String nome, Conta[] contas, int indiceDaConta, int numDeContas) {
     if (numDeContas == 1) {
      JOptionPane.showMessageDialog(null, "Você não possui outras contas para transferir!", "Opa :(", 0);
      menuInicial(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
    } else {

      Object[] nomeDaConta = new Object[numDeContas-1];
    
      int p = 0;

      for (int i = 0; i < contas.length; i++) {
        
        if (contas[i].cliente.getNome().equals(contas[indiceDaConta].cliente.getNome())) {
           p = i;
        } else {
          nomeDaConta[p] = contas[i].cliente.getNome();
          p++;
        }
        
      }
    Object conta1 = JOptionPane.showInputDialog(null, "Escolha a conta para transferir", "Transferir", 0, icon, nomeDaConta, nomeDaConta);
    if (conta1 == null) {
      menuInicial(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
    }

    String valor1 = JOptionPane.showInputDialog(null, "Digite o valor a transferir \n Saldo = R$" + f(contas[indiceDaConta].cliente.getSaldo()), "Transferir", 3);
    if (campoIgualNulo(valor1)) {
      menuInicial(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);;
    }

    while (valor1.isEmpty()) {
      valor1 = JOptionPane.showInputDialog(null, "Digite um valor a transferir \n Saldo = R$" + f(contas[indiceDaConta].cliente.getSaldo()), "Transferir", 3);
      if (campoIgualNulo(valor1)) {
        menuInicial(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
      }
    }
    double valor = Double.parseDouble(valor1);
    String conta = conta1.toString();

    for (int i = 0; i < contas.length; i++) {
      if (contas[i].cliente.getNome().equals(conta)) {
        boolean resultado = contas[indiceDaConta].transferencia(valor, contas[i]);
        if (resultado) {
          JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!", "Sucesso :)", 1);
          menuInicial(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
        } else {
          int escolha = JOptionPane.showOptionDialog(null, "Saldo insuficiente :( \n Saldo = R$" + f(contas[indiceDaConta].cliente.getSaldo()), "Falha :(", 0, 2, null, bErro, bErro);
          if (escolha == 0) {
            transferir(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
          } else {
            menuInicial(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
          }
        }
      }
    }
  }
}

  public static void depositar(ImageIcon icon, Object[] botoes, Object[] bErro, String nome, Conta[] contas, int indiceDaConta, int numDeContas) {
    String valor1 = JOptionPane.showInputDialog(null, "Digite o valor a depositar", "Depósito", 3);
    if (campoIgualNulo(valor1)) {
      menuInicial(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
    }
 
    while (valor1.isEmpty()) {
      valor1 = JOptionPane.showInputDialog(null, "Digite um valor a depositar", "Depósito", 3);
      if (campoIgualNulo(valor1)) {
        menuInicial(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
      }
    }
    
    double valor = Double.parseDouble(valor1);
    String saldo1 = f(contas[indiceDaConta].cliente.getSaldo());

    contas[indiceDaConta].deposito(valor);
    
    JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso \n Saldo anterior: R$ " + saldo1 + "\n Novo Saldo: R$ " + f(contas[indiceDaConta].cliente.getSaldo()), "Depósito", 1);
    
    menuInicial(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
  }

  public static void consultar(ImageIcon icon, Object[] botoes, Object[] bErro, String nome, Conta[] contas, int indiceDaConta, int numDeContas) {
    Object[] botoesConsulta = { "Saldo", "Extrato", "Informações da conta", "Voltar" };
    int escolhaCosulta = JOptionPane.showOptionDialog(null, "O que quer consultar?", "Consulta", 0, 0, icon, botoesConsulta, botoesConsulta);
    
    if (escolhaCosulta == 0) {
      JOptionPane.showMessageDialog(null, "Seu saldo é: R$ " + f(contas[indiceDaConta].cliente.getSaldo()), "Saldo", 1);
      consultar(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
    } else if (escolhaCosulta == 1) {
      JTextArea extrato = new JTextArea(11, 20);
      Object[] ok = {"OK"};
      
      extrato.append("Processo \t Valor Total \n" + "\n Saldo inicial \t R$ " + f(contas[indiceDaConta].valorSaldoInicial) + "\n Saque \t - R$ " + f(contas[indiceDaConta].cliente.getValorEmSaques()) + "\n Depósito \t +R$ " + f(contas[indiceDaConta].cliente.getValorEmDepositos()) + "\n ¹TR \t +R$ " + f(contas[indiceDaConta].cliente.getValorEmTransferenciasRecebidas()) + "\n ²TE \t - R$ " + f(contas[indiceDaConta].cliente.getValorEmTransferenciasEnviadas())  + "\n Saldo atual \t R$ " + f(contas[indiceDaConta].cliente.getSaldo()) + "\n\n ¹TR = Transferência recebida \n ²TE = Transferência enviada");
      
      JOptionPane.showOptionDialog(null, extrato, "Extrato", 0, 0, icon, ok, ok);
      
      consultar(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
    } else if (escolhaCosulta == 2) {
      JTextArea dados = new JTextArea(3, contas.length);
      dados.append("Nº da conta \t Nome \t CPF \t Saldo \n");
      dados.append(contas[indiceDaConta].cliente.getNumeroDaConta() + "\t" + contas[indiceDaConta].cliente.getNome() + "\t" + contas[indiceDaConta].cliente.getCPF() + "\t R$ " + f(contas[indiceDaConta].cliente.getSaldo()) + "\n");
      Object[] ok = { "OK" };
      JOptionPane.showOptionDialog(null, dados, "Consultar dados", 0, 0, icon, ok, ok);
      escolhaCosulta = JOptionPane.showOptionDialog(null, "O que quer consultar", "Consulta", 0, 0, icon,botoesConsulta, botoesConsulta);
      consultar(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
    } else {
      menuInicial(icon, botoes, bErro, nome, contas, indiceDaConta, numDeContas);
    }
  }

  public static void main(String[] args) {
    ImageIcon icon = new ImageIcon("../images/logo.png");
    menuCadastro(icon);
  }
}
