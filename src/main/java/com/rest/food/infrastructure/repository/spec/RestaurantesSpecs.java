package com.rest.food.infrastructure.repository.spec;


import com.rest.food.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantesSpecs {

    public static Specification<Restaurante> comFreteGratis(){

        return (root, query, builder) ->
                builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> comNomeSemelhante(String nome){

        return (root, query, builder) ->
                builder.like(root.get("nome"), "%" + nome + "%");
    }


}
