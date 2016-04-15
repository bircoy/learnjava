package com.learnjava.numbers;

import java.util.List;
import org.springframework.data.domain.Sort;
import com.learnjava.numbers.Number;

public interface NumberService {
	
	public List<Number> findAll(Sort sort);
	
	public Number saveNumber(Number number);
	
	public Number findOne(Integer id);
	
	public void delete(Integer id);
	
	public Number findByValue(Integer value);
	
	public Number findBiggest();
	
	public Number findSmallest();
}