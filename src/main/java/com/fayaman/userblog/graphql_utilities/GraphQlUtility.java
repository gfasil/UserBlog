package com.fayaman.userblog.graphql_utilities;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class GraphQlUtility {
    @Value("classpath:models.graphqls")
    private Resource resource;
    private GraphQL graphQL;
    private AllUsersDataFetcher allUsersDataFetcher;
    private UserDataFetcher userDataFetcher;
    private ArticleDataFetcher articleDataFetcher;

    public GraphQlUtility(AllUsersDataFetcher allUsersDataFetcher, UserDataFetcher userDataFetcher, ArticleDataFetcher articleDataFetcher) throws IOException {
        this.allUsersDataFetcher = allUsersDataFetcher;
        this.userDataFetcher = userDataFetcher;
        this.articleDataFetcher = articleDataFetcher;
    }
    @PostConstruct
    public GraphQL createGraphQlObject() throws IOException{
        File schemas=resource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry=new SchemaParser().parse(schemas);
        RuntimeWiring runtimeWiring=buildRuntimeWiring();
        GraphQLSchema graphQLSchema=new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry,runtimeWiring);
        return GraphQL.newGraphQL (graphQLSchema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring().
                type("Query",typeWiring->typeWiring
                        .dataFetcher("users",allUsersDataFetcher)
                        .dataFetcher("user",userDataFetcher))
                .type("User",typeWiring->typeWiring
                        .dataFetcher("articles",articleDataFetcher)
                        .dataFetcher("friends",allUsersDataFetcher))
                .build();
    }
}
