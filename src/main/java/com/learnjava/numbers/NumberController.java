package com.learnjava.numbers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learnjava.numbers.Response;
import com.learnjava.numbers.Number;
import com.learnjava.numbers.NumberService;

@RestController
public class NumberController {

	@Autowired
	private NumberService numberService;
	
	@RequestMapping(value = "/numbers", method = RequestMethod.GET, produces = "application/json")
	public Response list(@RequestParam(value = "order", defaultValue = "ASC", required = false) String direction) {
		
		Response response = new Response();
		
		Sort sort = new Sort(Sort.Direction.ASC, "value");
		if(direction.toUpperCase().equals("DESC")) {
			sort = new Sort(Sort.Direction.DESC, "value");
		} 
		
		List<Number> numbers = this.numberService.findAll(sort);
		if(numbers != null) {
			response.setData(numbers);
			return response;
		}
		
		response.setSuccess(Response.ERROR);
		response.setCode(HttpStatus.NO_CONTENT.value());
		response.setMessage("Nothing found");
		
		return response;
	}
	
	@RequestMapping(value="/numbers/create", method=RequestMethod.POST, produces = "application/json")
	public Response create(@RequestParam("value") Integer number) {
		
		Response response = new Response();
		
		if(this.numberService.findByValue(number) != null) {
			response.setSuccess(Response.ERROR);
			response.setCode(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Number already exists"); 
			return response;
		}
		
		Number newNumber = new Number(number, new Date());
		newNumber = this.numberService.saveNumber(newNumber);
		
		if(newNumber.getId() != null) {
			response.setData(newNumber);
			return response;
			
		} 
		
		response.setSuccess(Response.ERROR);
		response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setMessage("Couldn't create number");

		return response;
	}
	
	@RequestMapping(value="/numbers/delete", method=RequestMethod.DELETE)
	public Response delete(@RequestParam(value = "value", required = true) Integer number) {
		
		Response response = new Response();
		
		Number dbNumber = this.numberService.findByValue(number);
		
		if(this.numberService.findByValue(number) == null) {
			response.setCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Number not found");
			return response;
		}
		
		this.numberService.delete(dbNumber.getId());
		
		dbNumber = this.numberService.findOne(dbNumber.getId());
		
		if(dbNumber != null) {
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage("Couldn't delete");
		}
		
		return response;		
	}
	
	@RequestMapping(value="/numbers/biggest", method=RequestMethod.GET, produces = "application/json")
	public Response biggest() {
		
		Response response = new Response();
		
		Number biggest = this.numberService.findBiggest();
		
		if(biggest != null) {
			response.setData(biggest);
			return response;
		}
			
		response.setCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Nothing found");
		
		return response;
	}
	
	@RequestMapping(value="/numbers/smallest", method=RequestMethod.GET, produces = "application/json")
	public Response smallest() {
		Response response = new Response();
		
		Number smallest = this.numberService.findSmallest();
		
		if(smallest != null) {
			response.setData(smallest);
			return response;
			
		}
		
		response.setCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Nothing found");
		return response;
	}
	
}