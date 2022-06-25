package br.com.taugs.calendario.compromisso.filter;

import java.util.ArrayList;
import java.util.List;

import br.com.taugs.calendario.compromisso.entity.Compromisso;
import br.com.taugs.calendario.data.entity.Data;
import br.com.taugs.calendario.horario.entity.Horario;

public class CompromissoMapper {

	private static CompromissoResponse toResponse(Compromisso from) {
		CompromissoResponse to = new CompromissoResponse();
		to.setId(from.getId());
		to.setDescricao(from.getDescricao());
		to.setTitulo(from.getTitulo());
		to.setTextoData(gerarTextoData(from.getData()));
		to.setTextoHorario(gerarTextoHorario(from.getHorario()));
		return to;
	}

	public static List<CompromissoResponse> toResponse(List<Compromisso> from) {
		List<CompromissoResponse> to = new ArrayList<CompromissoResponse>();
		for (Compromisso c : from) {
			to.add(toResponse(c));
		}
		return to;
	}

	private static String gerarTextoData(Data data) {
		String texto = "";
		texto = String.format("%02d", data.getPosicaoMes()) + "/" + String.format("%02d", data.getMes().getPosicao()) //
		        + "/" + data.getAno().getNome();
		return texto;
	}

	private static String gerarTextoHorario(Horario horario) {
		String texto = "";
		texto = String.format("%02d", horario.getHora()) + ":" + String.format("%02d", horario.getMinutos());
		return texto;
	}

}
