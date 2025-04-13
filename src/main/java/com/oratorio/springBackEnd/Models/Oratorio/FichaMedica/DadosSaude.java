package com.oratorio.springBackEnd.Models.Oratorio.FichaMedica;

public class DadosSaude {
    private String acompanhamentoMedico;
    private String restricaoAtividadeFisica;
    private String medicamentos;
    private String alergias;
    private boolean teveConvulsoes;
    private boolean temDesmaios;
    private String problemasCardiacos;
    private String outrosProblemas;
    private String infoAdicionais;

    public DadosSaude(String acompanhamentoMedico, String restricaoAtividadeFisica, String medicamentos,
                      String alergias, boolean teveConvulsoes, boolean temDesmaios, String problemasCardiacos,
                      String outrosProblemas, String infoAdicionais) {
        this.acompanhamentoMedico = acompanhamentoMedico;
        this.restricaoAtividadeFisica = restricaoAtividadeFisica;
        this.medicamentos = medicamentos;
        this.alergias = alergias;
        this.teveConvulsoes = teveConvulsoes;
        this.temDesmaios = temDesmaios;
        this.problemasCardiacos = problemasCardiacos;
        this.outrosProblemas = outrosProblemas;
        this.infoAdicionais = infoAdicionais;
    }

    public void setAcompanhamentoMedico(String acompanhamentoMedico) {
        this.acompanhamentoMedico = acompanhamentoMedico;
    }

    public void setRestricaoAtividadeFisica(String restricaoAtividadeFisica) {
        this.restricaoAtividadeFisica = restricaoAtividadeFisica;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public void setTeveConvulsoes(boolean teveConvulsoes) {
        this.teveConvulsoes = teveConvulsoes;
    }

    public void setTemDesmaios(boolean temDesmaios) {
        this.temDesmaios = temDesmaios;
    }

    public void setProblemasCardiacos(String problemasCardiacos) {
        this.problemasCardiacos = problemasCardiacos;
    }

    public void setOutrosProblemas(String outrosProblemas) {
        this.outrosProblemas = outrosProblemas;
    }

    public void setInfoAdicionais(String infoAdicionais) {
        this.infoAdicionais = infoAdicionais;
    }

    public String getAcompanhamentoMedico() {
        return acompanhamentoMedico;
    }

    public String getRestricaoAtividadeFisica() {
        return restricaoAtividadeFisica;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public String getAlergias() {
        return alergias;
    }

    public boolean isTeveConvulsoes() {
        return teveConvulsoes;
    }

    public boolean isTemDesmaios() {
        return temDesmaios;
    }

    public String getProblemasCardiacos() {
        return problemasCardiacos;
    }

    public String getOutrosProblemas() {
        return outrosProblemas;
    }

    public String getInfoAdicionais() {
        return infoAdicionais;
    }
}
