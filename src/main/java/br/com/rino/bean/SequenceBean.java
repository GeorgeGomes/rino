package br.com.rino.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.rino.dao.SequenceDAO;
import br.com.rino.entity.Sequence;
import br.com.rino.util.JSFUtil;

@ManagedBean(name = "sequenceBean")
@ApplicationScoped
public class SequenceBean {

	private Sequence sequence = new Sequence();
	private SequenceDAO sequenceDAO = new SequenceDAO();
	private List<Sequence> sequenceList;
	private Integer sequenceInt;

	@PostConstruct
	public void init() {
		this.sequenceList = sequenceDAO.getList();
		this.sequenceInt = 0;
	}

	public void initAppAlone(String url) {
		try {
			JSFUtil.initAppAlone(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initAppSequence(String url){
	try {
		this.init();
		JSFUtil.initAppSequence(url);
	} catch (Exception e) {
		e.printStackTrace();
	}
}

	public void getNextApp() {
		String url = "";
		if (sequenceInt <= (sequenceList.size() - 1)) {
			url = this.sequenceList.get(sequenceInt).getUrl();
			sequenceInt++;
		} else {
			sequenceInt = 0;
			url = this.sequenceList.get(sequenceInt).getUrl();
		}

		JSFUtil.redirect(url);
	}

	public void newSequence() {
		Sequence sequence = new Sequence();
		sequence.setCodSequence(0l);
		this.setSequence(sequence);
	}

	public void editSequence(Long codSequence) {
		this.setSequence(sequenceDAO.edit(codSequence));
	}

	public void deleteSequence(Sequence sequence) {
		sequenceDAO.delete(sequence);
		
		this.init();
		
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
				"Registro deletado com sucesso!");
		context.addMessage("messages", message);
	}

	public void saveSequence(Sequence sequence) {
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext request = RequestContext.getCurrentInstance();

		if (sequence.getCodSequence() == 0) {
			sequenceDAO.insert(sequence);
		} else {
			sequenceDAO.update(sequence);
		}

		this.init();
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!");
		context.addMessage("messages", message);

		request.execute("PF('modalEdit').hide();");
		request.update("form:dataTable");
	}

	public List<Sequence> listSequence() {
		return sequenceDAO.getList();
	}

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

}
