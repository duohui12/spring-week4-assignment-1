package com.codesoom.assignment.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProductSpringDataRepository extends JpaRepository<ProductEntity, Long> {
}
