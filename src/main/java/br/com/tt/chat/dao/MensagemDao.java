package br.com.tt.chat.dao;

import br.com.tt.chat.model.Mensagem;
import br.com.tt.chat.util.Conexao;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

public class MensagemDao {

    private Conexao conexao;

    public MensagemDao(Conexao conexao) {
        this.conexao = conexao;
    }

    public void adicionar(Mensagem mensagem) throws SQLException {
        String sql = "insert into mensagem (texto, dataHoraEnvio) values (?, ?)";
        PreparedStatement st = conexao.getConexao().prepareStatement(sql);
        st.setString(1, mensagem.getTexto());
        st.setTimestamp(2, converteParaTimestamp(mensagem.getDataHoraEnvio()));
        st.execute();
    }

    private static Timestamp converteParaTimestamp(LocalDateTime dataHora) {
        if (dataHora != null) {
            return Timestamp.from(dataHora.toInstant(ZoneOffset.UTC));
        }
        return null;
    }

    public List<Mensagem> listar() throws SQLException {

        try(Statement statement = conexao.getConexao().createStatement()) {
            try(ResultSet resultSet = statement.executeQuery("select * from mensagem")) {

                List<Mensagem> lista = new LinkedList<>();
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String texto = resultSet.getString("texto");
                    LocalDateTime dataHoraEnvio = converteParaLocalDateTime(resultSet.getTimestamp("dataHoraEnvio"));
                    lista.add(new Mensagem(id, texto, dataHoraEnvio));
                }

                return lista;
            }
        }
    }

    public LocalDateTime converteParaLocalDateTime(Timestamp timestamp){
//        return Optional.ofNullable(timestamp).map(Timestamp::toLocalDateTime)
//                .orElse(null);

        if(timestamp != null){
            return timestamp.toLocalDateTime();
        }
        return null;
    }

}
