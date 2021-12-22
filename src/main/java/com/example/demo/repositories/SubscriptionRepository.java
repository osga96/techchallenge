package com.example.demo.repositories;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

    Subscription findByEmail(String email);
}