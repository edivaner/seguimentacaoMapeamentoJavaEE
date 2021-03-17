package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Cliente;
import service.ClienteService;

@ViewScoped
@ManagedBean
public class ClienteBean {
	private Cliente cliente = new Cliente();
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	@EJB
	private ClienteService clienteService;
	
	@PostConstruct
	public void init(){
		autalizarListaClientes();
	}
	
	public void autalizarListaClientes(){
		clientes = clienteService.listAll();
	}
	
	public void gravarCliente(){
		clienteService.create(cliente);
		cliente = new Cliente();
		FacesContext.getCurrentInstance().addMessage("Sucesso",
				new FacesMessage("Cliente salvo com sucesso!"));
		autalizarListaClientes();
	}
	
	/**
	 * 
	 * G&S
	 * */
		
		

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	

	
}
