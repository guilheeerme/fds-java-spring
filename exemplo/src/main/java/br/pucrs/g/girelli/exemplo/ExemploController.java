package br.pucrs.g.girelli.exemplo;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/biblioteca")
public class ExemploController {
    public List<Livro> livros;

    public ExemploController() {
        this.livros = new ArrayList<Livro>();

        this.livros.add(new Livro(1, "Livro 1", "Autor 1", 2020));
        this.livros.add(new Livro(2, "Livro 2", "Autor 2", 2021));
        this.livros.add(new Livro(3, "Livro 3", "Autor 3", 2022));
        this.livros.add(new Livro(4, "Livro 4", "Autor 4", 2023));
    }

    @GetMapping("/")
    public String getMensagemInicial() {
        return "Aplicacao Spring-Boot funcionando!";
    }

    @GetMapping("/livros")
    public List<Livro> getLivros() {
        return this.livros;
    }

    @GetMapping("/titulos")
    public List<String> getTitulos() {
        List<String> titulos = new ArrayList<String>();

        for (Livro livro : this.livros) {
            titulos.add(livro.titulo);
        }
        
        return titulos;
    }

    @GetMapping("/autores")
    public List<String> getAutores() {
        List<String> autores = new ArrayList<String>();

        for (Livro livro : this.livros) {
            autores.add(livro.autor);
        }
        
        return autores;
    }

}
