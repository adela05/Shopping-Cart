package com.company.ShoppingCart.Dao;

import com.company.ShoppingCart.Dto.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Integer> {
}
