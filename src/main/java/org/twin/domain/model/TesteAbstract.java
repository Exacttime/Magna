package org.twin.domain.model;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;

public class TesteAbstract {
    public static void main(String[] args) {
        String message = "Hello World!";
        String newMessage = message.substring(6,12)
                + message.substring(12,6);
        System.out.println(newMessage);
    }
}
