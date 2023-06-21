import java.sql.*;
import java.util.Scanner;

public class Restaurante {

    static Connection conexao = null;
    static Scanner ler = new Scanner(System.in);
    static Scanner nomeScanner = new Scanner(System.in);

    private static void exibirMenu(Garçom garcom) throws SQLException, ClassNotFoundException {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n-- Menu --");
            System.out.println("1. Inserir Garçom");
            System.out.println("2. Atualizar Garçom");
            System.out.println("3. Deletar Garçom");
            System.out.println("4. Buscar Garçom");
            System.out.println("0. Sair");
            System.out.println("Escolha uma opção:");

            opcao = ler.nextInt();

            switch (opcao) {
                case 1:
                    inserirGarçom(garcom);
                    break;
                case 2:
                    atualizarGarcom(garcom);
                    break;
                case 3:
                    deletarGarcom(garcom.getId_garcom());
                    break;
                case 4:
                    buscarGarcom(garcom.getId_garcom());
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        int id_garcom, Telefone;
        String nome, Sexo, Turno, Email, Data_de_Nascimento, CPF;
        Float Salario;

        System.out.println("-- Menu Principal --");
        System.out.println("1. Inserir Garçom");
        System.out.println("2. Atualizar Garçom");
        System.out.println("3. Deletar Garçom");
        System.out.println("4. Buscar Garçom");
        System.out.println("0. Sair");
        System.out.println("Escolha uma opção:");

        int opcao = ler.nextInt();
        ler.nextLine();

        switch (opcao) {

            case 1:
                System.out.println("Digite os dados do Garçom");
                System.out.println("Digite o ID:");
                id_garcom = ler.nextInt();
                System.out.println("Digite o Nome:");
                nome = nomeScanner.nextLine();
                System.out.println("Digite a Data de Nascimento:");
                Data_de_Nascimento = ler.next();
                System.out.println("Digite o Sexo:");
                Sexo = ler.next();
                System.out.println("Digite o CPF:");
                CPF = ler.next();
                System.out.println("Digite o Telefone:");
                Telefone = ler.nextInt();
                System.out.println("Digite o Turno:");
                Turno = ler.next();
                System.out.println("Digite o Email:");
                Email = ler.next();
                System.out.println("Digite o Salário:");
                Salario = Float.parseFloat(ler.next());

                Garçom g = new Garçom(id_garcom, nome, Data_de_Nascimento, Sexo, Salario, Turno, Telefone, Email, CPF);
                inserirGarçom(g);
                break;



            case 2:
                System.out.println("Digite o ID do Garçom que deseja atualizar:");
                id_garcom = ler.nextInt();

                Garçom garcomExistente = buscarGarcom(id_garcom);
                if (garcomExistente == null) {
                    boolean sair = false;

                    while (!sair){
                    System.out.println("Digite os novos dados do Garçom");
                    System.out.println("Digite o Nome:");
                    nome = nomeScanner.nextLine();
                    System.out.println("Digite a Data de Nascimento:");
                    Data_de_Nascimento = ler.next();
                    System.out.println("Digite o Sexo:");
                    Sexo = ler.next();
                    System.out.println("Digite o CPF:");
                    CPF = ler.next();
                    System.out.println("Digite o Telefone:");
                    Telefone = ler.nextInt();
                    System.out.println("Digite o Turno:");
                    Turno = ler.next();
                    System.out.println("Digite o Email:");
                    Email = ler.next();
                    System.out.println("Digite o Salário:");
                    Salario = Float.parseFloat(ler.next());

                    Garçom gAtualizado = new Garçom(id_garcom, nome, Data_de_Nascimento, Sexo, Salario, Turno, Telefone, Email, CPF);
                    atualizarGarcom(gAtualizado);
                    System.out.println("Deseja atualizar mais informações do garçom? (S/N)");
                    String opcaoSair = ler.next();
                    if (opcaoSair.equalsIgnoreCase("N")){
                        sair = true;
                    }
                    }
                }
                else {
                    System.out.println("Nenhum garçom encontrado com o ID informado.");
                }
                break;



            case 3:
                System.out.println("Digite o ID do Garçom que deseja deletar:");
                id_garcom = ler.nextInt();

                deletarGarcom(id_garcom);
                break;



            case 4:
                System.out.println("Digite o ID do Garçom que deseja buscar:");
                id_garcom = ler.nextInt();

                Garçom garcomBuscado = buscarGarcom(id_garcom);
                if (garcomBuscado != null) {
                    System.out.println("Garçom encontrado:");
                    System.out.println("Nome: " + garcomBuscado.getNome());
                    // ... exibir outros dados do garçom ...
                } else {
                    System.out.println("Nenhum garçom encontrado com o ID informado.");
                }
                break;



            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    //MÉTODOS CRUD...
    private static void inserirGarçom(Garçom garcom) throws SQLException, ClassNotFoundException {
        try {
            conexao = (Connection) ConexaoBD.getInstance();
            //Inserir as informações do Garçom:
            String query = "insert into garçom (id_garcom, nome, Data_de_Nascimento, Sexo, Salario, Turno, Telefone, Email, CPF, Data_da_Insercao) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, garcom.getId_garcom());
            stmt.setString(2, garcom.getNome());
            stmt.setString(3, garcom.getData_de_Nascimento());
            stmt.setString(4, garcom.getSexo());
            stmt.setFloat(5, garcom.getSalario());
            stmt.setString(6, garcom.getTurno());
            stmt.setInt(7, garcom.getTelefone());
            stmt.setString(8, garcom.getEmail());
            stmt.setString(9, garcom.getCPF());
            //Data da inserção:
            Timestamp Data_da_Insercao = new Timestamp(System.currentTimeMillis());
            stmt.setTimestamp(10, Data_da_Insercao);

            stmt.execute();
            stmt.close();
            System.out.println("Garçom Cadastrado com Sucesso");
        } catch (Exception e) {
            System.out.println("Não foi possível cadastrar o Garçom");
            e.printStackTrace();
        }
    }

    // Método para atualizar os dados de um garçom
    private static void atualizarGarcom(Garçom garcom) throws SQLException, ClassNotFoundException {
        try {
            conexao = (Connection) ConexaoBD.getInstance();
            String query = "UPDATE garçom SET nome = ?, Data_de_Nascimento = ?, Sexo = ?, Salario = ?, Turno = ?, Telefone = ?, Email = ?, CPF = ? WHERE id_garcom = ?";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, garcom.getNome());
            stmt.setString(2, garcom.getData_de_Nascimento());
            stmt.setString(3, garcom.getSexo());
            stmt.setFloat(4, garcom.getSalario());
            stmt.setString(5, garcom.getTurno());
            stmt.setInt(6, garcom.getTelefone());
            stmt.setString(7, garcom.getEmail());
            stmt.setString(8, garcom.getCPF());
            stmt.setInt(9, garcom.getId_garcom());

            stmt.executeUpdate();

            System.out.println("Garçom atualizado com sucesso");
            stmt.close();
        } catch (Exception e) {
            System.out.println("Não foi possível atualizar o garçom");
            e.printStackTrace();
        }
    }

    // Método para deletar um garçom
    private static void deletarGarcom(int idGarcom) throws SQLException, ClassNotFoundException {
        try {
            conexao = (Connection) ConexaoBD.getInstance();
            String query = "DELETE FROM garçom WHERE id_garcom = ?";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, idGarcom);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Garçom deletado com sucesso");
            } else {
                System.out.println("Nenhum garçom encontrado com o ID informado");
            }

            stmt.close();
        } catch (Exception e) {
            System.out.println("Não foi possível deletar o garçom");
            e.printStackTrace();
        }
    }

    // Método para buscar o Garçom pelo ID:
    private static Garçom buscarGarcom(int idGarcom) throws SQLException, ClassNotFoundException {

        try {
            conexao = (Connection) ConexaoBD.getInstance();
            String query = "SELECT * FROM garçom WHERE id_garcom = ?";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, idGarcom);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_garcom");
                String nome = rs.getString("nome");
                String dataNascimento = rs.getString("Data_de_Nascimento");
                String sexo = rs.getString("Sexo");
                float salario = rs.getFloat("Salario");
                String turno = rs.getString("Turno");
                int telefone = rs.getInt("Telefone");
                String email = rs.getString("Email");
                String cpf = rs.getString("CPF");
                Timestamp Data_da_Insercao = rs.getTimestamp("Data_da_Insercao");

                // Crie um objeto Garcom com os dados retornados
                Garçom garcom = new Garçom(id, nome, dataNascimento, sexo, salario, turno, telefone, email, cpf);

                // Faça o que desejar com o objeto Garcom retornado, por exemplo, imprimir os dados
                System.out.println("Garçom encontrado:");
                System.out.println("Nome:" + garcom.getNome());
            } else {
                System.out.println("Nenhum garçom encontrado com o ID informado");
            }

            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Não foi possível buscar o garçom");
            e.printStackTrace();
        }
        return null;
    }

}
