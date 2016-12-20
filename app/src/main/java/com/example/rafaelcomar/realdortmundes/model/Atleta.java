package com.example.rafaelcomar.realdortmundes.model;

import com.orm.SugarRecord;

/**
 * Created by rafael.comar on 13/12/2016.
 */

public class Atleta extends SugarRecord {

     Long id;
     String nome;
     String thumbnail;
     String dataNascimento;
     String posicao;

    public Atleta() {
    }


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }
}
