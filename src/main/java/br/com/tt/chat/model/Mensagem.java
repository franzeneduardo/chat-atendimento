package br.com.tt.chat.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mensagem {
    private Long id;
    private String texto;
    private LocalDateTime dataHoraEnvio;

    private String remetente;
    private String destinatario;
    private LocalDateTime dataHoraLeitura;//Date,Calendar,ZonedDateTime

    public Mensagem(String texto) {
        this.texto = texto;
        this.dataHoraEnvio = LocalDateTime.now();
    }

    public Mensagem(Long id, String texto, LocalDateTime dataHoraEnvio) {
        this.id = id;
        this.texto = texto;
        this.dataHoraEnvio = dataHoraEnvio;
    }

    public String getTexto() {
        return texto;
    }

    public LocalDateTime getDataHoraEnvio() {
        return dataHoraEnvio;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public LocalDateTime getDataHoraLeitura() {
        return dataHoraLeitura;
    }

    public String getDescricao() {
        String dataHoraFormatada = dataHoraEnvio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy mm:ss"));
        return String.format("%s (%s)", texto, dataHoraFormatada);
    }
}
