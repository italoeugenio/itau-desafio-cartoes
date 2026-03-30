package italo.santana.itau_desafio_cartoes.controller;

import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import italo.santana.itau_desafio_cartoes.models.dtos.SolicitacaoResponseDTO;
import italo.santana.itau_desafio_cartoes.models.services.CartoesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cartoes")
public class CartoesController {

    @Autowired
    private CartoesService cartoesService;

    @PostMapping("")
    public ResponseEntity<SolicitacaoResponseDTO> solicitarCartoes(@Valid @RequestBody ClienteRequestDTO data){
        return new ResponseEntity<>(cartoesService.solicitarCartoes(data), HttpStatus.OK);
    }
}
