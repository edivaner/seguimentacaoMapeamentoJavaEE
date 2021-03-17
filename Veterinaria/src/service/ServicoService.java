package service;

import javax.ejb.Stateless;

import modelo.Servico;

@Stateless
public class ServicoService extends GenericService<Servico>{
	public ServicoService(){
		super(Servico.class);
	}
}
