/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markup;

import java.util.Date;

/**
 *
 * @author robssoares
 */
public class Cliente {
    
    private int idmarkup_clientes;
    private String cpf;
    private String nome;
    private Date dtNascto;
    private String telefone;
    private String nrCartao;
    private Date dtDesbloqueioUra;
    private int idmarkup_empresas;

    /**
     * @return the idmarkup_clientes
     */
    public int getIdmarkup_clientes() {
        return idmarkup_clientes;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the dtNascto
     */
    public Date getDtNascto() {
        return dtNascto;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @return the nrCartao
     */
    public String getNrCartao() {
        return nrCartao;
    }

    /**
     * @return the dtDesbloqueioUra
     */
    public Date getDtDesbloqueioUra() {
        return dtDesbloqueioUra;
    }

    /**
     * @return the idmarkup_empresas
     */
    public int getIdmarkup_empresas() {
        return idmarkup_empresas;
    }

    /**
     * @param idmarkup_clientes the idmarkup_clientes to set
     */
    public void setIdmarkup_clientes(int idmarkup_clientes) {
        this.idmarkup_clientes = idmarkup_clientes;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param dtNascto the dtNascto to set
     */
    public void setDtNascto(Date dtNascto) {
        this.dtNascto = dtNascto;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @param nrCartao the nrCartao to set
     */
    public void setNrCartao(String nrCartao) {
        this.nrCartao = nrCartao;
    }

    /**
     * @param dtDesbloqueioUra the dtDesbloqueioUra to set
     */
    public void setDtDesbloqueioUra(Date dtDesbloqueioUra) {
        this.dtDesbloqueioUra = dtDesbloqueioUra;
    }

    /**
     * @param idmarkup_empresas the idmarkup_empresas to set
     */
    public void setIdmarkup_empresas(int idmarkup_empresas) {
        this.idmarkup_empresas = idmarkup_empresas;
    }
    
    
    
    
}
