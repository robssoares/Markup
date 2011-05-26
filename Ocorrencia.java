package markup;

import java.util.Date;

public class Ocorrencia {

	private int idmarkup_ocorrencias;
	private Date data;
	private Date hora;
	private String cliente;
	private String cpf;
	private String nrCartao;
	private String empresa;
	private String classificacao;
	private String fila;
	private String operador;
	private String telefone;
	private String observacao;
	private String uniqueId;
	private int id_clientes;
	private int id_classificacao;
	
	
	
	public int getIdmarkup_ocorrencias() {
		return idmarkup_ocorrencias;
	}
	public void setIdmarkup_ocorrencias(int idmarkup_ocorrencias) {
		this.idmarkup_ocorrencias = idmarkup_ocorrencias;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNrCartao() {
		return nrCartao;
	}
	public void setNrCartao(String nrCartao) {
		this.nrCartao = nrCartao;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	public String getFila() {
		return fila;
	}
	public void setFila(String fila) {
		this.fila = fila;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public int getId_clientes() {
		return id_clientes;
	}
	public void setId_clientes(int id_clientes) {
		this.id_clientes = id_clientes;
	}
	public int getId_classificacao() {
		return id_classificacao;
	}
	public void setId_classificacao(int id_classificacao) {
		this.id_classificacao = id_classificacao;
	}
	
	
	

}
