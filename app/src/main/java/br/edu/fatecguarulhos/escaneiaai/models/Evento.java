package br.edu.fatecguarulhos.escaneiaai.models;

import android.graphics.Bitmap;

import com.dantsu.escposprinter.textparser.PrinterTextParserImg;
import com.google.type.DateTime;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatecguarulhos.escaneiaai.interfaces.Imprimivel;
import br.edu.fatecguarulhos.escaneiaai.util.ImpressoraTermica;
import br.edu.fatecguarulhos.escaneiaai.util.QrCodeManager;

public class Evento implements Imprimivel {
    private String titulo, id;
    private List<Participante> participantes = new ArrayList<>();
    private DateTime dataInicio, dataFim;
    private Bitmap qrCode;

    public Bitmap getQrCode() {
        return qrCode;
    }

    public void setQrCode(Bitmap qrCode) {
        this.qrCode = qrCode;
    }

    public Evento(String titulo){

        qrCode = QrCodeManager.gerarQrCode(id);
        this.titulo = titulo;
    }

    public Evento() {
        qrCode = QrCodeManager.gerarQrCode(id);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(DateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public DateTime getDataFim() {
        return dataFim;
    }
    private void gerarQrCode(){}
    public void setDataFim(DateTime dataFim) {
        this.dataFim = dataFim;
    }
    private String imagemFormatadaParaImpressao(ImpressoraTermica impressora){
        return "[C]<img>"+ PrinterTextParserImg.bitmapToHexadecimalString(impressora.getImpressora(), qrCode) + "</img>\n" ;
    }
    @Override
    public String dadosASeremImpressos(ImpressoraTermica impressora) {
        return TITULO_APP +
                "[L]" + titulo +

                DATA_E_HORA_IMPRESSAO;
    }
}
