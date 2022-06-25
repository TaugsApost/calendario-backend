package br.com.taugs.calendario.calendario.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taugs.calendario.ano.entity.Ano;
import br.com.taugs.calendario.calendario.entity.Calendario;
import br.com.taugs.calendario.calendario.repository.CalendarioRepositorio;
import br.com.taugs.calendario.data.entity.Data;
import br.com.taugs.calendario.utils.BaseServiceBean;
import br.com.taugs.calendario.vinculoDiaData.entity.VinculoDiaData;
import br.com.taugs.calendario.vinculoDiaData.service.VinculoDiaDataService;
import br.com.taugs.calendario.vinculoMesData.entity.VinculoMesData;
import br.com.taugs.calendario.vinculoMesData.service.VinculoMesDataService;

@Service
public class CalendarioServiceBean extends BaseServiceBean<Calendario, Long> implements CalendarioService {

	@Autowired
	CalendarioRepositorio repositorio;

	@Autowired
	VinculoDiaDataService vDService;

	@Autowired
	VinculoMesDataService vMService;

	private List<Data> datas;

	@Override
	public List<Calendario> listar() {
		return this.listarTodos(repositorio);
	}

	@Override
	public Calendario salvar(Calendario entity) {
		return this.salvarEntity(entity, repositorio);

	}

	@Override
	public Calendario detalhar(Long id) {
		Calendario c = this.retornarEntity(id, repositorio);
		return c != null ? c : new Calendario();
	}

	@Override
	public void deletar(Long id) {
		Calendario c = detalhar(id);
		c.getConfiguracao().getDias().clear();
		c.getConfiguracao().getMeses().clear();
		this.repositorio.delete(c);
	}

	@Override
	protected void beforeSave(Calendario entity) {
		ordenarDias(entity);
		Collections.sort(entity.getConfiguracao().getMeses(), compararPosicaoesMes);
		List<Data> datas = new ArrayList<Data>();
		final int numeroDias = numeroDiasAno(entity.getConfiguracao().getMeses());
		final int numeroMeses = entity.getConfiguracao().getMeses().size();
		int posicaoDia = entity.getConfiguracao().getDias().get(0).getPosicao();
		for (int i = entity.getConfiguracao().getAnoInicial(), posicaoAno = 1; i <= entity.getConfiguracao().getAnoFinal(); i++, posicaoAno++) {
			int posicaoEmRelacaoAoAno = 1;
			boolean bissexto = (isBissexto(i) && entity.getConfiguracao().getBissexto());
			String nome = String.valueOf(i);
			Ano ano = new Ano(posicaoAno, numeroMeses, (bissexto ? numeroDias + 1 : numeroDias), bissexto, nome); // criacao do ano
			for (VinculoMesData mV : entity.getConfiguracao().getMeses()) {
				int numDiasMes = mV.getNumDias();
				if (bissexto && mV.getBissexto()) {
					numDiasMes += 1;
				}
				for (int j = 1; j <= numDiasMes; j++, posicaoDia++) {
					int indexDv = indexDiaPorPosicao(entity.getConfiguracao().getDias(), posicaoDia);
					VinculoDiaData dV = entity.getConfiguracao().getDias().get(indexDv);
					Data d = new Data(dV, mV, ano, j, posicaoEmRelacaoAoAno);
					d.setCalendario(entity);
					datas.add(d); // criacao da data
					posicaoEmRelacaoAoAno++;
					if (posicaoDia == entity.getConfiguracao().getDias().size()) {
						posicaoDia = 0;
					}
				}
			}
		}

		entity.setDatas(datas);
		this.datas = entity.getDatas();
		super.beforeSave(entity);
	}

	@Override
	protected void afterSave(Calendario entity) {
		for (Data d : entity.getDatas()) {
			d.setIdCalendario(entity.getId());
		}
		entity.getConfiguracao().setIdCalendario(entity.getId());
		repositorio.save(entity);
		super.afterSave(entity);
	}

	private Comparator<VinculoDiaData> compararPosicaoesDia = new Comparator<VinculoDiaData>() {
		@Override
		public int compare(VinculoDiaData d1, VinculoDiaData d2) {
			int p1 = d1.getPosicao();
			int p2 = d2.getPosicao();
			return p1 - p2;
		}
	};

	private Comparator<VinculoMesData> compararPosicaoesMes = new Comparator<VinculoMesData>() {
		@Override
		public int compare(VinculoMesData m1, VinculoMesData m2) {
			int p1 = m1.getPosicao();
			int p2 = m2.getPosicao();
			return p1 - p2;
		}
	};

	private void ordenarDias(Calendario entity) {
		VinculoDiaData primeiroDia = obterPrimeiroDia(entity.getConfiguracao().getDias());
		VinculoDiaData ultimoDia = obterUltimoDia(entity.getConfiguracao().getDias());
		List<VinculoDiaData> lista = new ArrayList<VinculoDiaData>();
		if (primeiroDia == ultimoDia) {
			int index = indexDiaPorPosicao(entity.getConfiguracao().getDias(), 1);
			ultimoDia = entity.getConfiguracao().getDias().get(index);
		}
		if (entity.getConfiguracao().getDias().size() > 1) {
			lista.add(primeiroDia);
			lista.add(ultimoDia);
			entity.getConfiguracao().getDias().remove(primeiroDia);
			entity.getConfiguracao().getDias().remove(ultimoDia);
		} else {
			lista.add(primeiroDia);
			entity.getConfiguracao().getDias().remove(primeiroDia);
		}
		Collections.sort(entity.getConfiguracao().getDias(), compararPosicaoesDia);
		ordenarRestante(entity.getConfiguracao().getDias(), lista, primeiroDia, ultimoDia);
		entity.getConfiguracao().setDias(lista);
	}

	private int indexDiaPorPosicao(List<VinculoDiaData> dias, int posicao) {
		for (int i = 0; i < dias.size(); i++) {
			VinculoDiaData d = dias.get(i);
			if (d.getPosicao() == posicao) {
				return i;
			}
		}
		return 0;
	}

	private VinculoDiaData obterPrimeiroDia(List<VinculoDiaData> dias) {
		for (int i = 0; i < dias.size(); i++) {
			VinculoDiaData d = dias.get(i);
			if (d.getDiaInicialCalendario()) {
				return d;
			}
		}
		return dias.get(indexDiaPorPosicao(dias, 1));
	}

	private VinculoDiaData obterUltimoDia(List<VinculoDiaData> dias) {
		VinculoDiaData ultimoDia = dias.get(indexDiaPorPosicao(dias, 1));
		for (int i = 0; i < dias.size(); i++) {
			VinculoDiaData d = dias.get(i);
			if (d.getPosicao() > ultimoDia.getPosicao()) {
				ultimoDia = d;
			}
		}
		return ultimoDia;
	}

	private int indexDia(VinculoDiaData dia, List<VinculoDiaData> lista) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i) == dia) {
				return i;
			}
		}
		return 0;
	}

	private void ordenarRestante(List<VinculoDiaData> lista, List<VinculoDiaData> listaFinal, VinculoDiaData primeiroDia, VinculoDiaData ultimoDia) {
		while (!lista.isEmpty()) {
			VinculoDiaData d = lista.get(0);
			int indexUltimoDia = indexDia(ultimoDia, listaFinal);
			if (d.getPosicao() > primeiroDia.getPosicao() && d.getPosicao() < ultimoDia.getPosicao()) {
				listaFinal.add(indexUltimoDia, d);
			} else {
				listaFinal.add(d);
			}
			lista.remove(d);
		}
		lista = listaFinal;
		// testeSort(lista, null);
	}

	private int numeroDiasAno(List<VinculoMesData> lista) {
		int numDias = 0;
		for (VinculoMesData v : lista) {
			numDias += v.getNumDias();
			// numDias+=30;
		}
		return numDias;
	}

	private boolean isBissexto(int ano) {
		if (ano % 4 == 0) {
			if (ano % 100 == 0) {
				if (ano % 400 == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public List<Calendario> buscarTodos(Long id) {
		List<Calendario> resultado = new ArrayList<Calendario>();
		resultado = repositorio.buscarTodos(id);
		resultado.addAll(repositorio.buscarOutros(id));
		return resultado;
	}

	@Override
	public List<Calendario> buscarPorUsuario(Long id) {
		List<Calendario> resultado = new ArrayList<Calendario>();
		resultado = repositorio.buscarTodos(id);
		return resultado;
	}
}
