package agenda.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/**  Módulo de Conexão *. */
	// Parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://localhost:3306/agenda_db?useTimeZone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "root";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	// Método de conexão
	private Connection conectar() {
		Connection con = null; // Conexão com o DB

		try {
			Class.forName(driver);// Lê o driver do DB
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/**
	 * Teste conexao.
	 */
	// teste de conexão
	public void testeConexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 *  CRUD CREATE *.
	 *
	 * @param contato the contato
	 */

	public void inserirContato(JavaBeans contato) {
		String sql = "insert into contatos (nome,fone,email) values (?,?,?)";

		try {
			// abrir conexão com o banco
			Connection con = conectar();
			// Preparar a query para execução no BD
			PreparedStatement ps = con.prepareStatement(sql);
			// Preencher os parâmetros (?) pelos atributos JavaBeans
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getFone());
			ps.setString(3, contato.getEmail());
			// Executar a query
			ps.executeUpdate();
			// Encerrar a conexão com o banco
			ps.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 *  CRUD READ *.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContatos() {
		//Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String sql = "select * from contatos order by id";
		
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// laço executado enquanto houver contatos
			while (rs.next()) {
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				//armazenando no arraylist
				contatos.add(new JavaBeans(id,nome,fone,email));
			}
			ps.close();
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	/**
	 *  CRUD UPDATE *.
	 *
	 * @param contato the contato
	 */
	//selecionar contato
	public void selecionarContato(JavaBeans contato) {
		String sql = "select * from contatos where id = ?";
		
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, contato.getId());
			ResultSet rs = ps.executeQuery(); //Traz informações do banco para exibir as informações
			while(rs.next()) {
				//setar as variáveis JavaBeans
				contato.setId(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	
	}
	
	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	//** CRUD UPDATE **//
	public void alterarContato(JavaBeans contato) {
		String sql = "update contatos set nome=?,fone=?,email=? where id=?";
		
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getFone());
			ps.setString(3, contato.getEmail());
			ps.setString(4, contato.getId());
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 *  CRUD DELETE *.
	 *
	 * @param contato the contato
	 */
	public void deletarContato(JavaBeans contato) {
		String sql = "delete from contatos where id=?";
		
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, contato.getId());
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}


