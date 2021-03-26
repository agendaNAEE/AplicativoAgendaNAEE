package com.ifmg.agendanaee;

public class Agendamento {

    private String servidor;
    private String dataHora;

    public Agendamento(String servidor, String dataHora) {
        this.servidor = servidor;
        this.dataHora = dataHora;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
