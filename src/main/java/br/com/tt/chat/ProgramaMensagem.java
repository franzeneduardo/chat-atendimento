package br.com.tt.chat;

import br.com.tt.chat.dao.MensagemDao;
import br.com.tt.chat.dao.UsuarioDao;
import br.com.tt.chat.excecoes.MensagemException;
import br.com.tt.chat.model.Atendente;
import br.com.tt.chat.model.Mensagem;
import br.com.tt.chat.model.Usuario;
import br.com.tt.chat.util.Conexao;
import br.com.tt.chat.util.UserInterface;

import java.sql.SQLException;

public class ProgramaMensagem {

    public static final int LIMITE_MINIMO_DE_CARACTERES = 5;
    public static final int LIMITE_MAXIMO_DE_CARACTERES = 200;

    private static MensagemDao mensagemDao;
    private static UsuarioDao usuarioDao;

    private static Usuario usuarioLogado;
    private static Atendente atendenteEscolhido;

    public static void main(String[] args) throws SQLException {
        Conexao conexao = new Conexao();
        conexao.conecta();//TODO
        mensagemDao = new MensagemDao(conexao);
        usuarioDao = new UsuarioDao(conexao);

        login();
        adicionarMensagem();
        listarMensagens();
    }

    public static void login(){
        // Pedir pro usuario seu id
        // Buscar usuários na base (usuarioDao.listar()) e checa se o usuario existe com esse ID.
        // se não existir usuário, damos uma exceção
        // existir, loga

//        List<Usuario> lista;
//        long id;
//        lista.stream().filter(u -> u.getId() == id).findFirst(); // findFirst retorna um Optional<Usuario>

        //usuarioLogado = ;//usuario que vem do Dao!
    }

    public static void escolherAtendente() {
        //pedir o nome do atendente
        // injetar o DAO do atendente e filtar pelo nome.
        // atribuir ao atendenteEscolhido o atendente encontrado
    }


    public static void adicionarMensagem() throws SQLException {
        String texto = UserInterface
                .pedirTexto("Informe a mensagem")
                .orElseThrow(() -> new MensagemException("A mensagem deve ser informada!"));

        Mensagem mensagem = new Mensagem(texto); //TODO modificar para receber o remetente (usuario), destinatario (atendente)
        validaMensagem(mensagem);
        mensagemDao.adicionar(mensagem);
        UserInterface.imprimir("Mensagem adicionada!");
    }

    public static void validaMensagem(Mensagem mensagem) {
        if(mensagem.getTexto().length() < LIMITE_MINIMO_DE_CARACTERES){
            throw new MensagemException(
                    String.format("A mensagem deve ter ao menos %s caracteres!", LIMITE_MINIMO_DE_CARACTERES));
        }
        if(mensagem.getTexto().length() > LIMITE_MAXIMO_DE_CARACTERES) {
            throw new MensagemException(
                    String.format("A mensagem deve no máximo %s caracteres!", LIMITE_MAXIMO_DE_CARACTERES));
        }
    }

    public static void listarMensagens() {
        //Listar as mensagens do usuário
    }
}
