package br.com.rino.bean;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import com.google.common.collect.Lists;

import br.com.rino.dao.WhellDAO;
import br.com.rino.entity.Whell;
import br.com.rino.util.FileUtil;

@ManagedBean(name = "whellBean")
@SessionScoped
public class WhellBean {

	private Whell whell = new Whell();
	private WhellDAO whellDAO = new WhellDAO();
	private Long qtdeJogadas;

	public Long getQtdeJogadas() {
		return qtdeJogadas;
	}

	public void setQtdeJogadas(Long qtdeJogadas) {
		this.qtdeJogadas = qtdeJogadas;
	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
				"Arquivo adicionado com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		RequestContext request = RequestContext.getCurrentInstance();

		try {
			String extension = event.getFile().getContentType().replace("image/", "");
			String fileName = FileUtil.generateUniqueFileName() + "." + extension;

			FileUtil.copyFile(fileName, event.getFile().getInputstream());

			whell.setNomeImagem(fileName);
			whellDAO.update(whell);
			request.update("formModalEdit");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteFileUpload() {
		if (FileUtil.deleteFile(whell.getNomeImagem())) {
			whell.setNomeImagem("");
			whellDAO.update(whell);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
					"Arquivo exclu�do com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao deletar arquivo!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public Double totalPremios() {
		List<Whell> whellList = whellDAO.getList();
		Double total = 0.0;

		Iterator<Whell> whellIterator = whellList.iterator();

		while (whellIterator.hasNext()) {
			Whell whell = whellIterator.next();
			if (whell.getPremios() != null) {
				total += whell.getPremios();
			}
		}
		return total;
	}

	public void newWhell() {
		Whell whell = new Whell();
		whell.setCodWhell(0l);
		whell.setVitorias(0);
		this.setWhell(whell);
	}

	public void editWhell(Long codWhell) {
		this.setWhell(whellDAO.edit(codWhell));
	}

	public void deleteWhell(Whell whell) {
		whellDAO.delete(whell);
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
				"Registro deletado com sucesso!");
		context.addMessage("messages", message);
	}
	
	public void saveJogadas(Whell whell) {
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext request = RequestContext.getCurrentInstance();

		if (whell.getCodWhell() == 0) {
			whellDAO.insert(whell);
		} else {
			whellDAO.update(whell);
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!");
		context.addMessage("messages", message);

		request.execute("PF('modalEdit').hide();");
		request.update("form:dataTable");
	}

	public void saveWhell(Whell whell) {
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext request = RequestContext.getCurrentInstance();

		if (whell.getCodWhell() == 0) {
			whellDAO.insert(whell);
		} else {
			whellDAO.update(whell);
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!");
		context.addMessage("messages", message);

		request.execute("PF('modalEdit').hide();");
		request.update("form:dataTable");
	}

	public List<Whell> listWhell() {
		return whellDAO.getList();
	}
	
	public List<Whell> listWhellGroupOne() {
		List<Whell> bigList = whellDAO.getList();
		List<List<Whell>> smallerLists = Lists.partition(bigList, 4);
		
		return smallerLists.get(0);
	}
	
	public List<Whell> listWhellGroupTwo() {
		List<Whell> bigList = whellDAO.getList();
		List<List<Whell>> smallerLists = Lists.partition(bigList, 4);
		
		List<Whell> list = smallerLists.get(1);
		Collections.reverse(list);
		
		return list;
	}

	public Whell getWhell() {
		return whell;
	}

	public void setWhell(Whell whell) {
		this.whell = whell;
	}

}
