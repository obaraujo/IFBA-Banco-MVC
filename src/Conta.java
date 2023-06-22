public class Conta {

  public double valorSaldoInicial;
  Cliente cliente = new Cliente();

  public Conta(String nome, String CPF, int senha, double saldo) {
    cliente.setNome(nome);
    cliente.setCPF(CPF);
    cliente.setSenha(senha);
    cliente.setSaldo(saldo);
    valorSaldoInicial = cliente.getSaldo();
  }

  public boolean saque(double valor) {
    if (cliente.getSaldo() < valor) {
      return false;
    } else {
      double novoSaldo = cliente.getSaldo() - valor;
      cliente.setSaldo(novoSaldo);
      cliente.setValorEmSaques(valor);
      return true;
    }
  }

  public void deposito(double valor) {
    double novoSaldo = cliente.getSaldo() + valor;
    cliente.setSaldo(novoSaldo);
    cliente.setValorEmDepositos(valor);
  }

  public boolean transferencia(double valor, Conta contaTransfere) {
    if (valor > cliente.getSaldo()) {
      return false;
    } else {
      double novoSaldoT = cliente.getSaldo() - valor;
      double novoSaldoR = contaTransfere.cliente.getSaldo() + valor;
      contaTransfere.cliente.setSaldo(novoSaldoR);
      cliente.setSaldo(novoSaldoT);
      contaTransfere.cliente.setValorEmTransferenciasRecebidas(valor);
      cliente.setValorEmTransferenciasEnviadas(valor);
      return true;
    }
  }
}