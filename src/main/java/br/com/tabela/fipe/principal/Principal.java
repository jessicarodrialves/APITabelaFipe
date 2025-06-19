package br.com.tabela.fipe.principal;
import br.com.tabela.fipe.model.Dados;
import br.com.tabela.fipe.service.ConsumoApi;
import br.com.tabela.fipe.service.ConverteDados;
import java.util.Comparator;
import java.util.Scanner;

public class Principal {
    Scanner recebeDados = new Scanner(System.in);
    ConsumoApi consumoApi = new ConsumoApi();
    ConverteDados converteDados = new ConverteDados();
    String tipoVeiculo = null;
    private final String URL_BASE = "https://fipe.parallelum.com.br/api/v2/" ;

    public void exibeMenu() {
            System.out.println("Informe o tipo de veículo (CARRO - MOTO - CAMINHÃO: ");
            tipoVeiculo = recebeDados.nextLine();

        if(tipoVeiculo.equals("carro")){
            tipoVeiculo = "cars";
        } else if (tipoVeiculo.equals("moto")) {
            tipoVeiculo = "motorcycles";
        }else if(tipoVeiculo.equals("caminhão")){
            tipoVeiculo = "trucks";
        }else{
            System.out.println("Veiculo não encontrado");
        }

        String endereco = URL_BASE + tipoVeiculo+ "/brands";

        var json = consumoApi.obterDados(endereco);
         System.out.println(json);

         var marcas = converteDados.obterLista(json, Dados.class);
         marcas.stream()
                 .sorted(Comparator.comparing(Dados::codigo))
                 .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta");
        var codigoMarca = recebeDados.nextInt();

        endereco = endereco +"/" + codigoMarca +"/models";
        json = consumoApi.obterDados(endereco);

        var modeloLista = converteDados.obterLista(json, Dados.class);
        modeloLista.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);
    }



}
