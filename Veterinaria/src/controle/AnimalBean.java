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
import modelo.Cliente;
import service.AnimalService;
import service.ClienteService;

@ViewScoped
@ManagedBean
public class AnimalBean {
	private Animal animal = new Animal();
	private List<Animal> animais = new ArrayList<Animal>();
	
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private Long idDono = 0L;
	
	
	
	@EJB
	private AnimalService animalService;
	@EJB
	private ClienteService clienteService;
	
	
	@PostConstruct
	public void init(){
		clientes = clienteService.listAll();
		autalizarListaAnimais();
	}
	
	public void autalizarListaAnimais(){
		animais = animalService.listAll();
	}
	
	public void gravarAnimal(){
		Cliente c = clienteService.obtemPorId(idDono);
		getAnimal().setDono(c);
		
		animalService.create(getAnimal());
		
		setAnimal(new Animal());
		autalizarListaAnimais();
		
		autalizarListaAnimais();
		idDono = 0L;
		FacesContext.getCurrentInstance().addMessage("Sucesso",
				new FacesMessage("Animal salvo com sucesso!"));
	}

	/*
	 * G & S
	 * */
	
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
	

	
}
