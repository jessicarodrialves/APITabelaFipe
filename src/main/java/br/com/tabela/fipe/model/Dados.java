package br.com.tabela.fipe.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(@JsonAlias("code") String codigo, @JsonAlias("name") String nome) {
}
