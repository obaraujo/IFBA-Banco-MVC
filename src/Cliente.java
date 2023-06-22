import java.util.Random;

public class Cliente {

  Random gerarNum = new Random();
  private String nome;
  private int numeroDaConta = gerarNum.nextInt(100);
  private String CPF;
  private int senha;
  private double saldo;
  private double valorEmSaques;
  private double valorEmDepositos;
  private double valorEmTransferenciasRecebidas;
  private double valorEmTransferenciasEnviadas;

  public Cliente() {

  }

  public String getNome() {
    return this.nome.toUpperCase();
  }

  public void setNome(String NovoNome) {
    this.nome = NovoNome;
  }

  public String getCPF() {
    return this.CPF;
  }

  public void setCPF(String NovoCPF) {
    this.CPF = NovoCPF;

  }

  public int getSenha() {
    return this.senha;
  }

  public void setSenha(int Novasenha) {
    this.senha = Novasenha;
  }

  public double getSaldo() {
    return this.saldo;
  }

  public void setSaldo(double NovaSaldo) {
    this.saldo = NovaSaldo;
  }

  public int getNumeroDaConta() {
    return this.numeroDaConta;
  }

  public void setValorEmDepositos(double valor) {
    this.valorEmDepositos += valor;
  }

  public double getValorEmDepositos() {
    return this.valorEmDepositos;
  }

  public void setValorEmSaques(double valor) {
    this.valorEmSaques += valor;
  }

  public double getValorEmSaques() {
    return this.valorEmSaques;
  }

  public void setValorEmTransferenciasRecebidas(double valor) {
    this.valorEmTransferenciasRecebidas += valor;
  }

  public double getValorEmTransferenciasRecebidas() {
    return this.valorEmTransferenciasRecebidas;
  }

  public void setValorEmTransferenciasEnviadas(double valor) {
    this.valorEmTransferenciasEnviadas += valor;
  }

  public double getValorEmTransferenciasEnviadas() {
    return this.valorEmTransferenciasEnviadas;
  }
}