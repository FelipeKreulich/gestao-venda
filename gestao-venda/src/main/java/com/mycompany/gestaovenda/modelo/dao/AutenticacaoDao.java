/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaovenda.modelo.dao;

import com.mycompany.gestaovenda.modelo.dominio.Perfil;
import com.mycompany.gestaovenda.modelo.dominio.Usuario;
import com.mycompany.gestaovenda.modelo.exception.NegocioException;
import com.mycompany.gestaovenda.modelo.view.modelo.LoginDto;
import javax.swing.JOptionPane;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author felip
 */
public class AutenticacaoDao {
    private final UsuarioDao usuarioDao;
    
    public AutenticacaoDao() {
        this.usuarioDao = new UsuarioDao();
    }
    
    public boolean temPermissao(Usuario usuario) {
        try {
            permissao(usuario);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Usuário sem Permissão!", 0);
            return false;
        }
    }
    
    private void permissao(Usuario usuario) {
        if(!Perfil.ADMIN.equals(usuario.getPerfil())) {
            throw new NegocioException("Sem Permissão para Realizar essa Ação!");
        }
    }
    
    public Usuario login(LoginDto login) {
        Usuario usuario = usuarioDao.buscarusuarioPeloUsuario(login.getUsuario());
        
        if(usuario == null || !usuario.isEstado())
            return null;
        
        if(usuario.isEstado() && validaSenha(usuario.getSenha(), login.getSenha())) {
            return usuario;
        }
        
        return null;
    }

    private boolean validaSenha(String senhaUsuario, String senhaLogin) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(senhaLogin, senhaUsuario);
    }
    
}
