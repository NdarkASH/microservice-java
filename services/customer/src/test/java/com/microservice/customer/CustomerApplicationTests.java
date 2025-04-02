package com.microservice.customer;

import com.microservice.customer.model.Customer;
import com.microservice.customer.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
class CustomerApplicationTests {

	@Autowired
	private CustomerRepository customerRepository;


	@BeforeEach
	void setUp() throws Exception {
		customerRepository.save(new Customer());
	}
	@AfterEach
	void tearDown() {
		customerRepository.deleteAll();
	}

	@Test
	void shouldBeNotNull()  {
		assertThat(customerRepository.findAll()).isNotNull();
	}

}
