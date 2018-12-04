package com.example;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.microsoft.azure.functions.annotation.*;
import lombok.Data;
import com.microsoft.azure.functions.*;

public class Function {
    
    @FunctionName("createcidade")
    public Cidade create(@HttpTrigger(
                                        name = "criacidade",
                                        methods = {HttpMethod.POST},
                                        route = "cidade"
                                    ) Cidade c)
    {
        return c;
    }
    
    @FunctionName("lecidades")
    public List<Cidade> read(@HttpTrigger(
                                        name = "lecidade",
                                        methods = {HttpMethod.GET},
                                        route = "cidade"
                                    ) String x)
    {
        Estado parana = new Estado(new Long(0),"Paraná");
        return Stream.of(new Cidade(new Long(0),"Cornélio Procópio",parana),
                         new Cidade(new Long(1),"Londrina",parana)
                        ).collect(Collectors.toList());
    }

    @FunctionName("atualizacidade")
    public Cidade update(@HttpTrigger(
                                        name = "attcidade",
                                        methods = {HttpMethod.PUT},
                                        route = "cidade"
                                     ) Cidade c)
    {
        return c;
    }
    
    @FunctionName("deletacidade")
    public int delete(@HttpTrigger(
                                    name = "delcidade",
                                    methods = {HttpMethod.DELETE},
                                    route = "cidade/{id}"
                                  ) @BindingName("id") Long id)
    {
        return 200;  
    }
}

@Data
class Estado {
    private Long id;
    private String nome;

    public Estado(){}
    public Estado(Long id,String nome)
    {
        this.id = id;
        this.nome = nome;
    }
}

@Data
class Cidade {
    private Long id;
    private String nome;
    private Estado estado;

    public Cidade(){}
    public Cidade(Long id,String nome,Estado estado)
    {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }
}