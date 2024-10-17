package br.com.alurafood.payments.model;

import br.com.alurafood.payments.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    private BigDecimal valor;

    @javax.validation.constraints.NotBlank
    @Size(max=100)
    private String nome;

    @Size(max=19)
    private String numero;

    @Size(max=7)
    private String expiracao;

    @Size(min=3, max=3)
    private String codigo;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Long pedidoId;

    private Long formaDePagamentoId;
}