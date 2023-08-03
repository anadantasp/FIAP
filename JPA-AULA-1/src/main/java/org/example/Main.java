package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.domain.entity.Produto;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        Produto produto = new Produto(null, "Danone", new BigDecimal(15.90));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");

        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();

        manager.persist(produto);

        manager.getTransaction().commit();

        manager.close();
        factory.close();

        System.out.println(produto);
    }
}