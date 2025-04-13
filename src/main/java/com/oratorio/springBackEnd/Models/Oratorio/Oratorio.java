package com.oratorio.springBackEnd.Models.Oratorio;


import java.time.LocalDate;
import java.util.*;

public class Oratorio {
    //private final int id;
    private final LocalDate dataInicio;
    private final LocalDate dataFim;
    private final Set<Dia> dias;
    private final Set<Oratoriano> oratorianos;
    private final Set<Voluntario> voluntarios;

    public Oratorio(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio == null || dataFim == null) throw new IllegalArgumentException("Data nula");
        if (!dataInicio.isBefore(dataFim)) throw new IllegalArgumentException("Datas invalidas");
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;

        dias = new HashSet<>();
        oratorianos = new HashSet<>();
        voluntarios = new HashSet<>();

        setDatas(this.dataInicio, this.dataFim);
    }
    private void setDatas(LocalDate dataInicio, LocalDate dataFim) {
        LocalDate currentDate = dataInicio;
        while (!currentDate.isAfter(dataFim)) {
            dias.add(new Dia(currentDate));
            currentDate = currentDate.plusDays(7);
        }
    }
    public void removeDia(LocalDate date) {
        if(date == null) throw new IllegalArgumentException("Data nula");
        Dia diaToRemove = null;
        for (Dia dia : dias) {
            if(dia.getDate().equals(date)) diaToRemove = dia; break;
        }
        if(diaToRemove == null) throw new IllegalArgumentException("Data não registrada");

        dias.remove(diaToRemove);
    }
    public void getAndSetPreviousOratorianos(Oratorio previousOratorio) {
        this.oratorianos.addAll(previousOratorio.getOratorianos());
    }
    public void registraOratoriano(String nome) {
        oratorianos.add(new Oratoriano(nome));
    }
    public void registraOratoriano(String nome,LocalDate dataNascimento) {
        oratorianos.add(new Oratoriano(nome,dataNascimento));
    }
    public void removeOratoriano(Oratoriano oratoriano) {
        if(oratoriano == null) throw new IllegalArgumentException("Oratoriano nulo");

        if(!oratorianos.remove(oratoriano)) throw new IllegalArgumentException("Oratoriano nao registrado");
    }
    public void getAndSetPreviousVoluntarios(Oratorio previousOratorio) {
        this.voluntarios.addAll(previousOratorio.getVoluntarios());
    }
    public void registraVoluntario(String nome) {
        voluntarios.add(new Voluntario(nome));
    }
    public void registraVoluntario(String nome,LocalDate dataNascimento) {
        voluntarios.add(new Voluntario(nome, dataNascimento));
    }
    public void removeVoluntario(Voluntario voluntario) {
        if(voluntario == null) throw new IllegalArgumentException("voluntario nulo");

        if(!voluntarios.remove(voluntario)) throw new IllegalArgumentException("Voluntario nao registrado");
    }
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public LocalDate getDataFim() {
        return dataFim;
    }
    public List<Dia> getDias() {
        return new ArrayList<>(dias);
    }
    public Oratoriano getOratorianoById(int id) {
        for(Oratoriano oratoriano: oratorianos) {
            if(oratoriano.getId() == id) return oratoriano;
        }
        return null;
    }
    public List<Oratoriano> searchOratorianosByNome(String nome) {
        List<Oratoriano> oratorianosNome = new ArrayList<>();
        for(Oratoriano oratoriano: oratorianos) {
            if(oratoriano.getNome().toLowerCase().contains(nome.toLowerCase())) {
                oratorianosNome.add(oratoriano);
            }
        }

        return oratorianosNome;
    }
    public List<Oratoriano> getOratorianos() {
        return new ArrayList<>(oratorianos);
    }
    public List<Oratoriano> getOratorianosSortedAlfabeto(boolean reversed) {
        List<Oratoriano> oratorianosAlfabeto = new ArrayList<>(oratorianos);
        Comparator<Oratoriano> comparator = Comparator.comparing(o -> o.getNome().toLowerCase());

        if(reversed){
            oratorianosAlfabeto.sort(comparator.reversed());
        }else{
            oratorianosAlfabeto.sort(comparator);
        }

        return oratorianosAlfabeto;
    }
    public List<Oratoriano> getOratorianosSortedAlfabeto(){
        return getOratorianosSortedAlfabeto(false);
    }
    public List<Oratoriano> getOratorianosSortedId(boolean reversed) {
        List<Oratoriano> oratorianosId = new ArrayList<>(oratorianos);

        if(reversed){
            oratorianosId.sort(Comparator.comparing(Oratoriano::getId).reversed());
        }else{
            oratorianosId.sort(Comparator.comparing(Oratoriano::getId));
        }

        return oratorianosId;
    }
    public List<Oratoriano> getOratorianosSortedId(){
        return getOratorianosSortedId(false);
    }
    public List<Oratoriano> getOratorianosSortedIdade(boolean reversed) {
        List<Oratoriano> oratorianosIdade = new ArrayList<>(oratorianos);

        if(reversed){
            oratorianosIdade.sort(Comparator.comparing(Oratoriano::idade));
        }else{
            oratorianosIdade.sort(Comparator.comparing(Oratoriano::idade).reversed());
        }

        return oratorianosIdade;
    }
    public List<Oratoriano> getOratorianosSortedIdade(){
        return getOratorianosSortedIdade(false);
    }
    public List<Oratoriano> getOratorianosSortedPresencas(boolean reversed) {
        List<Oratoriano> oratorianosPresencas = new ArrayList<>(oratorianos);

        if(reversed){
            oratorianosPresencas.sort(Comparator.comparing(Oratoriano::getPresencas));
        }else{
            oratorianosPresencas.sort(Comparator.comparing(Oratoriano::getPresencas).reversed());
        }

        return oratorianosPresencas;
    }
    public List<Oratoriano> getOratorianosSortedPresencas(){
        return getOratorianosSortedPresencas(false);
    }
    public List<Voluntario> getVoluntarios() {
        return new ArrayList<>(voluntarios);
    }
    public List<Voluntario> searchVoluntariosByNome(String nome) {
        List<Voluntario> voluntariosNome = new ArrayList<>();
        for(Voluntario voluntario: voluntarios) {
            if(voluntario.getNome().toLowerCase().contains(nome.toLowerCase())) {
                voluntarios.add(voluntario);
            }
        }

        return voluntariosNome;
    }
    public List<Voluntario> getVoluntariosSortedAlfabeto(boolean reversed) {
        List<Voluntario> voluntariosAlfabeto = new ArrayList<>(voluntarios);
        Comparator<Voluntario> comparator = Comparator.comparing(o -> o.getNome().toLowerCase());

        if(reversed){
            voluntariosAlfabeto.sort(comparator.reversed());
        }else{
            voluntariosAlfabeto.sort(comparator);
        }

        return voluntariosAlfabeto;
    }
    public List<Voluntario> getVoluntariosSortedAlfabeto(){
        return getVoluntariosSortedAlfabeto(false);
    }
    public List<Voluntario> getVoluntariosSortedId(boolean reversed) {
        List<Voluntario> voluntariosId = new ArrayList<>(voluntarios);

        if(reversed){
            voluntariosId.sort(Comparator.comparing(Voluntario::getId).reversed());
        }else{
            voluntariosId.sort(Comparator.comparing(Voluntario::getId));
        }

        return voluntariosId;
    }
    public List<Voluntario> getVoluntariosSortedId(){
        return getVoluntariosSortedId(false);
    }
    public List<Voluntario> getVoluntariosSortedIdade(boolean reversed) {
        List<Voluntario> voluntariosIdade = new ArrayList<>(voluntarios);

        if(reversed){
            voluntariosIdade.sort(Comparator.comparing(Voluntario::idade));
        }else{
            voluntariosIdade.sort(Comparator.comparing(Voluntario::idade).reversed());
        }

        return voluntariosIdade;
    }
    public List<Voluntario> getVoluntariosSortedIdade(){
        return getVoluntariosSortedIdade(false);
    }
    public List<Voluntario> getVoluntariosSortedPresencas(boolean reversed) {
        List<Voluntario> voluntariosPresencas = new ArrayList<>(voluntarios);

        if(reversed){
            voluntariosPresencas.sort(Comparator.comparing(Voluntario::getPresencas));
        }else{
            voluntariosPresencas.sort(Comparator.comparing(Voluntario::getPresencas).reversed());
        }

        return voluntariosPresencas;
    }
    public List<Voluntario> getVoluntariosSortedPresencas(){
        return getVoluntariosSortedPresencas(false);
    }
    public int getDiasCount() {
        return dias.size();
    }
    public int getOratorianosCount() {
        return oratorianos.size();
    }
    public int getVoluntariosCount() {
        return voluntarios.size();
    }

}
