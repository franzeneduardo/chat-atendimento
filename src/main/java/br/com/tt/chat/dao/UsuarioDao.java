package br.com.tt.chat.dao;

import br.com.tt.chat.model.Perfil;
import br.com.tt.chat.model.Sexo;
import br.com.tt.chat.model.Usuario;
import br.com.tt.chat.util.Conexao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Usuario
 *
 * @since 2021
 * DAO para busca de usuários!
 */
public class UsuarioDao {

    Conexao conexao;

    public UsuarioDao(Conexao conexao) {
        this.conexao = conexao;
    }

    /**
     * Lista os usuários
     */
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios;

       try(Statement statement = conexao.getConexao().createStatement()){
            try(ResultSet resultSet = statement.executeQuery("select * from usuario")) {
                usuarios = montarUsuarios(resultSet);
            }
        }

/*
        Statement statement;
        ResultSet resultSet;
        try {
            statement = conexao.getConexao().createStatement();
            resultSet = statement.executeQuery("select * from usuario"));
            usuarios = montarUsuarios(resultSet);
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na consulta:" + e.getMessage());

        }finally {
            if(resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
            if(statement != null && !statement.isClosed()){
                statement.close();
            }
        }
*/

        return usuarios;
    }

    private List<Usuario> montarUsuarios(ResultSet resultSet) throws SQLException {
        List<Usuario> usuarios = new LinkedList<>();

        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String nome = resultSet.getString("nome");
            String cpf = resultSet.getString("cpf");
            String telefone = resultSet.getString("telefone");
            String email = resultSet.getString("email");
            String endereco = resultSet.getString("endereco");
            Date nascimento = resultSet.getDate("nascimento");

            Sexo sexo = Optional.ofNullable(resultSet.getString("sexo"))
                    .map(sexoString -> Sexo.valueOf(sexoString))
                    .orElse(Sexo.NAO_INFORMADO);

            Perfil perfil = Optional.ofNullable(resultSet.getString("perfil"))
                    .map(Perfil::valueOf) // .map(p -> Perfil.valueOf(p))
                    .orElse(Perfil.NORMAL);

            usuarios.add(new Usuario(id, nome, cpf, sexo, perfil,
                    telefone, email, endereco, nascimento.toLocalDate()));
        }
        return usuarios;
    }

    public void salvar(Usuario usuario) throws SQLException {

        String sql = "insert into usuario (nome, cpf, email, " +
                "endereco, telefone, sexo, perfil, nascimento) " +
                " values(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(sql);
        preparedStatement.setString(1, usuario.getNome());
        preparedStatement.setString(2, usuario.getCpf());
        preparedStatement.setString(3, usuario.getEmail());
        preparedStatement.setString(4, usuario.getEndereco());
        preparedStatement.setString(5, usuario.getTelefone());
        preparedStatement.setString(6, usuario.getSexo().name());
        preparedStatement.setString(7, usuario.getPerfil().name());
        preparedStatement.setDate(8, Date.valueOf(usuario.getNascimento()));
        preparedStatement.execute();
    }
}
