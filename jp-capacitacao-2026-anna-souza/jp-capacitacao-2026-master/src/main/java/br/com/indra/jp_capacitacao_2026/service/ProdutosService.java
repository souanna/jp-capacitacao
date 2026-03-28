package br.com.indra.jp_capacitacao_2026.service;

import br.com.indra.jp_capacitacao_2026.model.HistoricoPreco;
import br.com.indra.jp_capacitacao_2026.model.Produtos;
import br.com.indra.jp_capacitacao_2026.repository.CategoriaRepository;
import br.com.indra.jp_capacitacao_2026.repository.HistoricoPrecoRepository;
import br.com.indra.jp_capacitacao_2026.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutosService {

    private final ProdutosRepository produtosRepository;
    private final HistoricoPrecoRepository historicoPrecoRepository;
    private final CategoriaRepository categoriaRepository;

    public List<Produtos> getAll() {
        return produtosRepository.findAll();
    }

    public Produtos createdProduto(Produtos produto) {
        produto.setAtivo(true);
        return produtosRepository.save(produto);
    }

    public Produtos atualiza(Produtos produto) {
        return produtosRepository.save(produto);
    }

    public void deletarProduto(Long id) {
        produtosRepository.deleteById(id);
    }

    public Produtos getById(Long id) {
        return produtosRepository.findById(id).get();
    }

    public Produtos atualizaPreco(Long id, BigDecimal preco) {
//        Produtos produto = produtosRepository.findById(id).get();
        final var produto = produtosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setPreco(preco);

        final var historico = new HistoricoPreco();
        historico.setPrecoAntigo(produto.getPreco());
        historico.setProdutos(produto);
        historico.setPrecoNovo(preco);
        historicoPrecoRepository.save(historico);
        return produtosRepository.saveAndFlush(produto);

    }

    public List<Produtos> buscarPorNome(String nome) {
        return produtosRepository.findByNomeContainingIgnoreCase(nome);
    }
    public List<Produtos> buscarPorCategoria(Long categoriaId) {
        return produtosRepository.findByCategoriaId(categoriaId);
    }


    public List<Produtos> buscarProdutosValidos() {
        return produtosRepository.findByAtivoTrueAndDataDeleteIsNull();
    }
}
