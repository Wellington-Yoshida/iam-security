package com.issami.iam.repository;

import com.issami.iam.entity.Cliente;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository extends CassandraRepository<Cliente, UUID> {

    Cliente findByEmail(String email);

}
