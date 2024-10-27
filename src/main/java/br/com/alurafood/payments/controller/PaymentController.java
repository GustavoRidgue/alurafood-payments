package br.com.alurafood.payments.controller;

import br.com.alurafood.payments.dto.PaymentDTO;
import br.com.alurafood.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping
    public Page<PaymentDTO> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return service.findAll(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> detalhar(@PathVariable @NotNull Long id) {
        PaymentDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> cadastrar(@RequestBody @Valid PaymentDTO dto, UriComponentsBuilder uriBuilder) {
        PaymentDTO pagamento = service.createPayment(dto);
        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();

        return ResponseEntity.created(endereco).body(pagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentDTO dto) {
        PaymentDTO atualizado = service.updatePayment(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentDTO> remover(@PathVariable @NotNull Long id) {
        service.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/confirm")
    public void confirmPayment(@PathVariable @NotNull Long id){
        service.confirmPayment(id);
    }
}
