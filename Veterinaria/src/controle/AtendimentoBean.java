package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Animal;
import modelo.Atendimento;
import modelo.Cliente;
import modelo.Servico;
import service.AnimalService;
import service.AtendimentoService;
import service.ClienteService;
import service.ServicoService;

@ViewScoped
@ManagedBean
public class AtendimentoBean {
	private Atendimento atendimento = new Atendimento();
	private List<Atendimento> atendimentos = new ArrayList<Atendimento>();
	
	private Animal animal = new Animal();
	private List<Animal> animais = new ArrayList<Animal>();
	private Long idAnimal = 0L;
	
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private Long idDono = 0L;
	
	private List<Servico> servicos = new ArrayList<Servico>();
	private Long idServico = 0L;
	
	@EJB
	private ServicoService servicoService;
	@EJB
	private AnimalService animalService;
	@EJB
	private ClienteService clienteService;
	@EJB
	private AtendimentoService atendimentoService;
	
	
	@PostConstruct
	public void init(){
		clientes = clienteService.listAll();
		animais = animalService.listAll();
		servicos = servicoService.listAll();
		autalizarListaAtendimentos();
	}
	
	
	public void autalizarListaAtendimentos(){
		atendimentos = atendimentoService.listAll();
	}
	
	public void gravarAtendimento(){
		//Se for null é novo
		if(getAtendimento().getId()!=null){
			FacesContext.getCurrentInstance().addMessage("Sucesso",
					new FacesMessage("Animal salvo com sucesso!"));
		}else{
			Animal a = animalService.obtemPorId(idAnimal);
			getAtendimento().setAnimal(a);
			
			Cliente c = clienteService.obtemPorId(a.getDono().getId());
			getAtendimento().setDono(c);
			
			atendimentoService.create(getAtendimento());
			setAtendimento(new Atendimento());
			
			FacesContext.getCurrentInstance().addMessage("Sucesso",
					new FacesMessage("Atendimento salvo com sucesso!"));
			
			autalizarListaAtendimentos();
			idAnimal=0L;
			idServico=0L;
		}
		
	}
	
	public void addServico(){
		if(idServico!=0){
			Servico s = servicoService.obtemPorId(idServico);
			atendimento.getServicos().add(s);
			idServico = 0L;
		}else{
			FacesContext.getCurrentInstance().addMessage("Erro",
					new FacesMessage("Selecione pelo menos um servico"));
		}
	}
	
	public void finalizar(Atendimento a){
		setAtendimento(a);
		/*idAnimal = a.getAnimal().getId();
		
		Animal ani= animalService.obtemPorId(idAnimal);
		idDono = ani.getDono().getId();
		
		Servico s = servicoService.obtemPorId(idServico);
		atendimento.getServicos().add(s);*/
	}

/**
 * G & S
 * 
 * **/
	public Atendimento getAtendimento() {
		return atendimento;
	}


	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}


	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}


	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}


	public Animal getAnimal() {
		return animal;
	}


	public void setAnimal(Animal animal) {
		this.animal = animal;
	}


	public List<Animal> getAnimais() {
		return animais;
	}


	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}


	public Long getIdAnimal() {
		return idAnimal;
	}


	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}


	public List<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}


	public Long getIdDono() {
		return idDono;
	}


	public void setIdDono(Long idDono) {
		this.idDono = idDono;
	}


	public List<Servico> getServicos() {
		return servicos;
	}


	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}


	public Long getIdServico() {
		return idServico;
	}


	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}
	
	


}
