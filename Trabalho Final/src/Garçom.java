public class Garçom {

  private int id_garcom;



  public Garçom(int id_garcom, String nome, String data_de_Nascimento, String sexo, Float salario, String turno, int telefone, String email, String CPF) {
    this.id_garcom = id_garcom;
    this.nome = nome;
    this.Data_de_Nascimento = data_de_Nascimento;
    this.Sexo = sexo;
    this.Salario = salario;
    this.Turno = turno;
    this.Telefone = telefone;
    this.Email = email;
    this.CPF = CPF;
  }


  public int getId_garcom() {
    return id_garcom;
  }

  public void setId_garcom(int id_garcom) {
    this.id_garcom = id_garcom;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getData_de_Nascimento() {
    return Data_de_Nascimento;
  }

  public void setData_de_Nascimento( String data_de_Nascimento) {
    Data_de_Nascimento = data_de_Nascimento;
  }

  public String getSexo() {
    return Sexo;
  }

  public void setSexo(String sexo) {
    Sexo = sexo;
  }

  public Float getSalario() {
  return Salario;
  }

  public void setSalario(Float salario) {
    Salario = salario;
  }

  public String getTurno() {
    return Turno;
  }

  public void setTurno(String turno) {
    Turno = turno;
  }

  public int getTelefone() {
    return Telefone;
  }

  public void setTelefone(int telefone) {
    Telefone = telefone;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String email) {
    Email = email;
  }

  public String getCPF() {
    CPF = CPF;
    return CPF;
  }



  public void setCPF(String CPF) {
    this.CPF = CPF;
  }

  public  String nome;

  public String Data_de_Nascimento;
  public String Sexo;
  public Float Salario;
  public String Turno;
  public int Telefone;
  public String Email;
  public String CPF;


}


