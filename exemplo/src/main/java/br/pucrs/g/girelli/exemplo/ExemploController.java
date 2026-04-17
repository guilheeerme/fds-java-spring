package br.pucrs.g.girelli.exemplo;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/biblioteca")
public class ExemploController {

    private List<Livro> livros;

    public ExemploController() {
        livros = new ArrayList<>();

        livros.add(new Livro(110, "Aprendendo Java", "Maria da Silva", 2015));
        livros.add(new Livro(120, "Spring-Boot", "Jose de Souza", 2020));
        livros.add(new Livro(130, "Principios SOLID", "Pedro da Silva", 2023));
        livros.add(new Livro(140, "Padroes de Projeto", "Joana Moura", 2023));
        livros.add(new Livro(150, "Teste Unitario", "Pedro da Silva", 2024));
    }

    @GetMapping("/")
    public String getMensagemInicial() {
        return "Aplicacao Spring-Boot funcionando!";
    }

    @GetMapping("/livros")
    public List<Livro> getLivros() {
        return livros;
    }

    @GetMapping("/titulos")
    public List<String> getTitulos() {
        return livros.stream()
                .map(livro -> livro.getTitulo())
                .toList();
    }

    @GetMapping("/autores")
    public List<String> getListaAutores() {
        return livros.stream()
                .map(l -> l.getAutor())
                .distinct()
                .toList();
    }

    @GetMapping("/livrosautor")
    public List<Livro> getLivrosDoAutor(@RequestParam(value = "autor") String autor) {
        return livros.stream()
                .filter(livro -> livro.getAutor().equals(autor))
                .toList();
    }

    @GetMapping("/livrosautorano/{autor}/ano/{ano}")
    public List<Livro> getLivrosDoAutor(@PathVariable(value = "autor") String autor,
            @PathVariable(value = "ano") int ano) {
        return livros.stream()
                .filter(livro -> livro.getAutor().equals(autor))
                .filter(livro -> livro.getAno() == ano)
                .toList();
    }

    @PostMapping("/novolivro")
    public boolean cadastraLivroNovo(@RequestBody final Livro livro) {
        livros.add(livro);
        return true;
    }

    @GetMapping("/livrotitulo/{titulo}")
    public ResponseEntity<Livro> getLivroTitulo(@PathVariable("titulo") String titulo) {
        Livro resp = livros.stream()
                .filter(livro -> livro.getTitulo().equals(titulo))
                .findFirst()
                .orElse(null);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resp);
    }
}
