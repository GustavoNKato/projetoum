package com.kato.projetoum.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kato.projetoum.domain.Cidade;
import com.kato.projetoum.domain.Estado;
import com.kato.projetoum.dto.CidadeDTO;
import com.kato.projetoum.dto.EstadoDTO;
import com.kato.projetoum.services.CidadeService;
import com.kato.projetoum.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> listEstado = estadoService.findAll();
		List<EstadoDTO> listEstadoDto = listEstado.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listEstadoDto);
	}
	
	@RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
		List<Cidade> listCidade = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listCidadeDto = listCidade.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listCidadeDto);
	}
}
