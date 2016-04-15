package com.learnjava.numbers;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NumberRepository extends JpaRepository<Number, Integer> {
	
	public Number findByValue(Integer value);
	
	public Number findTopByOrderByValueDesc();
	
	public Number findTopByOrderByValueAsc();
}
