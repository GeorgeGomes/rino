function randomImageSelect(anguloAtual, anguloInicial, anguloFinal, nomeImagem) {
	if (anguloAtual >= anguloInicial && anguloAtual <= anguloFinal) {
		$('.container-winner-produto').html(
				'<img src="/img/' + nomeImagem
						+ '" style="width:80px;margin:0 auto;display:block"/>');
	}
}