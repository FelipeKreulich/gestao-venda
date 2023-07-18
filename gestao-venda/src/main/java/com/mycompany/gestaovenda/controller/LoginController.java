/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaovenda.controller;

import com.mycompany.gestaovenda.modelo.dao.AutenticacaoDao;
import com.mycompany.gestaovenda.modelo.dominio.Usuario;
import com.mycompany.gestaovenda.modelo.view.formulario.Login;
import com.mycompany.gestaovenda.modelo.view.modelo.LoginDto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author felip
 */
public class LoginController implements ActionListener {

    private final Login login;
    private final AutenticacaoDao autenticacaoDao;

    public LoginController(Login login) {
        this.login = login;
        this.autenticacaoDao = new AutenticacaoDao();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String accao = ae.getActionCommand().toLowerCase();

        switch (accao) {
            case "login" ->
                login();
            case "cancelar" ->
                cancelar();

        }
    }

    private void login() {
        String usuario = this.login.getTxtLoginUsuario().getText();
        String senha = this.login.getTxtLoginSenha().getText();

        if (usuario.equals("") || senha.equals("")) {
            this.login.getLabelLoginMensagem().setText("Usuario e Senha devem ser Preenchidos.");
        }
        
        LoginDto loginDto = new LoginDto(usuario, senha);
        
        Usuario usuarioTemp = this.autenticacaoDao.login(loginDto);
        
        if(usuarioTemp != null) {
            JOptionPane.showConfirmDialog(null, usuarioTemp.getNome());
            limparTela();
        } else {
            this.login.getLabelLoginMensagem().setText("Usuário ou senha incorretos.");
        }
    }

    private void cancelar() {
        int confirmar = JOptionPane.showConfirmDialog(login, "Tem certeza que deseja sair?", "Sair do Sistema", JOptionPane.YES_NO_OPTION);
        
        if(confirmar == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    private void limparTela() {
        this.login.getTxtLoginUsuario().setText("");
        this.login.getTxtLoginSenha().setText("");
        this.login.getLabelLoginMensagem().setText("");
    }
}
