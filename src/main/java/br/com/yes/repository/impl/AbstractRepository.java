package br.com.yes.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import java.util.Collection;
import java.util.Optional;

public abstract class AbstractRepository {

    private final DynamoDBMapper mapper;

    public AbstractRepository() {
        final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        this.mapper = new DynamoDBMapper(client);
    }

    public <T> T save(final T entity) {
        this.mapper.save(entity);
        return entity;
    }

    public <T> Optional<T> findById(final String id, final Class<T> clazz) {
        return Optional.ofNullable(this.mapper.load(clazz, id));
    }

    public <T> Collection<T> findAll(final Class<T> clazz) {
        return this.mapper.scan(clazz, new DynamoDBScanExpression());
    }
}
