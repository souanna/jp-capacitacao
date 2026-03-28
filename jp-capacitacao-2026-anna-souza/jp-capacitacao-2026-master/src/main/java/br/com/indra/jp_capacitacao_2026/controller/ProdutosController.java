package br.com.indra.jp_capacitacao_2026.controller;

import br.com.indra.jp_capacitacao_2026.model.Produtos;
import br.com.indra.jp_capacitacao_2026.repository.ProdutosRepository;
import br.com.indra.jp_capacitacao_2026.service.ProdutosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos")
public class ProdutosController {

    private final ProdutosService produtosService;

    @Operation(
            description = "Endpoint para criar um novo produto",
            summary = "Criação de novo produto"
    )

    @PostMapping("/cria")
    public ResponseEntity<Produtos> criarProduto(@RequestBody Produtos produto){
        return ResponseEntity.ok(produtosService.createdProduto(produto));
    }

    @GetMapping
    public ResponseEntity<List<Produtos>> getAll(){
        return ResponseEntity.ok(produtosService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produtos> getById(@PathVariable Long id){
        return ResponseEntity.ok(produtosService.getById(id));
    }

    @PutMapping("/atualiza")
    public ResponseEntity<Produtos> atualizarProduto(@RequestParam Long id,
                                                     @RequestBody Produtos produto){
        return ResponseEntity.ok(produtosService.atualiza(produto));
    }

    @PatchMapping("/atualiza-preco/{id}")
    public ResponseEntity<Produtos> atualizarProdutoParcial(@PathVariable Long id,
                                                     @RequestParam BigDecimal preco) {
        return ResponseEntity.ok(produtosService.atualizaPreco(id, preco));
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtosService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Produtos>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(produtosService.buscarPorNome(nome));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Produtos>> buscarPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(produtosService.buscarPorCategoria(categoriaId));
    }

    @GetMapping("/validos")
    public ResponseEntity<List<Produtos>> buscarProdutosValidos() {
        return ResponseEntity.ok(produtosService.buscarProdutosValidos());
    }
}
