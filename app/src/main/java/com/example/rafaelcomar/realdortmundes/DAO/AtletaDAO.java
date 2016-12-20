package com.example.rafaelcomar.realdortmundes.DAO;

import android.util.Log;

import com.example.rafaelcomar.realdortmundes.model.Atleta;

import java.util.List;

/**
 * Created by rafael.comar on 19/12/2016.
 */

public class AtletaDAO {


    public void salvarAtleta(Atleta at){
        Atleta.findById(Atleta.class, at.getId());
        if (pesquisarAtleta(at) == null){
            at.save();
            Log.i("TESTE", "atleta ja cadastrado");
        }else{
            updateAtleta(at);
            Log.i("TESTE", "atleta novo");
        }

    }

    public Atleta pesquisarAtleta(Atleta at){
//        = Atleta.findById(Atleta.class, at.getId());
        List<Atleta>  atletas =  Atleta.find(Atleta.class , "id = ?", String.valueOf(at.getId()));
        if (atletas.size() > 0){
            return atletas.get(1);
        }else{
            return null;
        }

    }

    public void updateAtleta(Atleta at){
        Atleta atleta = Atleta.findById(Atleta.class , at.getId());
        atleta.save();
    }

    public List<Atleta> listarAtletas(){
        List<Atleta> atletas = Atleta.listAll(Atleta.class);

        return atletas;
    }


}
