package com.learnjava.numbers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.learnjava.numbers.NumberRepository;
import com.learnjava.numbers.Number;

@Service
public class NumberServiceImpl implements NumberService {
	
	@Autowired
	private NumberRepository numberRepository;

	public List<Number> findAll(Sort sort){
		return this.numberRepository.findAll(sort);
	}
	
	public Number findByValue(Integer value){
		return this.numberRepository.findByValue(value);
	}
	
	public Number saveNumber(Number Number){
		return this.numberRepository.save(Number);
	}
	
	public Number findOne(Integer id){
		return this.numberRepository.findOne(id);
	}
	
	public void delete(Integer id){
		this.numberRepository.delete(id);
	}
	
	public List<Number> findAll() {
		return this.numberRepository.findAll();
	}
	
	public Number findBiggest() {
		Number number = this.numberRepository.findTopByOrderByValueDesc();
		if(number != null) {
			return number;
		}
		return null;
	}

	public Number findSmallest() {
		Number number = this.numberRepository.findTopByOrderByValueAsc();
		if(number != null) {
			return number;
		}
		return null;
	}
}