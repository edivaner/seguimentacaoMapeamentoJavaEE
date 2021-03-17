package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Servico;
import service.ServicoService;

@ViewScoped
@ManagedBean
public class ServicoBean {
	private Servico servico = new Servico();
	private List<Servico> servicos = new ArrayList<Servico>();
	
	@EJB
	private ServicoService servicoService;
	
	@PostConstruct
	public void init(){
		atualizarServicos();
	}
	
	public void atualizarServicos(){
		servicos = servicoService.listAll();
	}
	
	public void gravarServicos(){
		servicoService.create(servico);
		servico = new Servico();
		FacesContext.getCurrentInstance().addMessage("Sucesso",
				new FacesMessage("Serviço salvo com sucesso!"));
		atualizarServicos();
	}

	/*
	 * G & S
	 * */
	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	
	
}
