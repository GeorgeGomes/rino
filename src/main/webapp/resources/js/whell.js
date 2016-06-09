function randomImageSelect(anguloAtual, anguloInicial, anguloFinal, nomeImagem) {
	if (anguloAtual >= anguloInicial && anguloAtual <= anguloFinal) {
		$('.container-winner-produto').html(
				'<img src="/rino/uploads/images/' + nomeImagem + '" style="width:80px;margin:0 auto;display:block"/>');
	}
}

function endWhell(codJogada, imagemEscolhida){
	location = "/rino/app/whell/endWhell.xhtml?codJogada=" + codJogada + "&imgBrinde=" + imagemEscolhida; 
}