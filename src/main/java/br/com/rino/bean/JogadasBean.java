package br.com.rino.bean;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.rino.dao.JogadasDAO;
import br.com.rino.dao.WhellDAO;
import br.com.rino.entity.Jogadas;
import br.com.rino.entity.Whell;
import br.com.rino.util.MathUtil;

@ManagedBean(name = "jogadasBean")
@SessionScoped
public class JogadasBean {
	
	private JogadasDAO jogadasDAO = new JogadasDAO();
	private WhellDAO whellDAO = new WhellDAO();
	private Jogadas jogadas;
	
	public void gerarJogadas(){
		FacesContext context = FacesContext.getCurrentInstance();
		Iterator<Whell> whellIterator = whellDAO.getList().iterator();
		Long totalJogadas;
		
		jogadasDAO.deleteAll();
		
		while(whellIterator.hasNext()){
			Whell whell = whellIterator.next();
			totalJogadas = whell.getPremios();
			
			for(int x = 1; x<= totalJogadas;x++){
				Jogadas jogadas = new Jogadas();
				jogadas.setCodRoleta(whell.getCodWhell());
				jogadas.setAnguloInicial(whell.getAnguloInicial());
				jogadas.setAnguloFinal(whell.getAnguloFinal());
				jogadas.setNomeImagem(whell.getNomeImagem());
				
				jogadasDAO.insert(jogadas);
			}
		}
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogadas geradas com sucesso!");
		context.addMessage("messages", message);
		
	}
	
	public void retirarPremio(Long codJogada){
		Jogadas jogada = jogadasDAO.edit(codJogada);
		jogadasDAO.delete(jogada);
	}
	
	public void selecionarBrinde(){
		Jogadas jogadas = null;
		List<Jogadas> jogadasList = jogadasDAO.getList();
		Integer totalJogadas = jogadasList.size();
		if(totalJogadas > 0){
			Integer jogadaEscolhida = MathUtil.rand(1, totalJogadas);
			jogadas = jogadasList.get(jogadaEscolhida - 1);
		}
		
		this.jogadas = jogadas;
	}
	

	public List<Jogadas> listJogadas() {
		return jogadasDAO.getList();
	}

	public Jogadas getJogadas() {
		return jogadas;
	}

	public void setJogadas(Jogadas jogadas) {
		this.jogadas = jogadas;
	}
	
}