/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaovenda;

import com.mycompany.gestaovenda.modelo.dao.UsuarioDao;
import com.mycompany.gestaovenda.modelo.dominio.Perfil;
import com.mycompany.gestaovenda.modelo.dominio.Usuario;

/**
 *
 * @author felip
 */
public class UsuarioTest {
    public static void main(String[] args) {
        Usuario usuario = new Usuario(1L, "FelipeKreulich", "fefek098", "KreulichDev", Perfil.ADMIN, null, null);
        
        UsuarioDao usuarioDao = new UsuarioDao();
        String mensagem = usuarioDao.salvar(usuario);
        System.out.println(mensagem);
    }
}
