package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Atendimento {
	@Id @GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraEntrada;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraSaida;
	
	private String tipoEntrega;
	
	@ManyToMany (fetch = FetchType.EAGER)
	private List<Servico> servicos = new ArrayList<Servico>();
	
	@ManyToOne
	private Animal animal;
	@ManyToOne
	private Cliente dono;
	
	
	
	public Atendimento(){}

	public String getResumo(){
		return "Animal: "+animal+", Dono: "+dono+"  Entrega: "+tipoEntrega;
	}
	
	
	
	@Override
	public String toString() {
		return "tipoEntrega:" + tipoEntrega + ", Pet:" + animal + ", Dono:" + dono.getRua() +", "+dono.getNumero();
	}

/**
 * G & S
 * */


	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataHoraEntrada() {
		return dataHoraEntrada;
	}

	public void setDataHoraEntrada(Date dataHoraEntrada) {
		this.dataHoraEntrada = dataHoraEntrada;
	}

	public Date getDataHoraSaida() {
		return dataHoraSaida;
	}

	public void setDataHoraSaida(Date dataHoraSaida) {
		this.dataHoraSaida = dataHoraSaida;
	}

	public String getTipoEntrega() {
		return tipoEntrega;
	}

	public void setTipoEntrega(String tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}


	public Cliente getDono() {
		return dono;
	}


	public void setDono(Cliente dono) {
		this.dono = dono;
	}
	

	
}
