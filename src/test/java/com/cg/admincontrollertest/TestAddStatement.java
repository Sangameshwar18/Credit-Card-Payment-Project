package com.cg.admincontrollertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.controller.AdminController;
import com.cg.service.IAdminService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TestAddStatement {

	@InjectMocks
	AdminController adminController;

	@Mock
	IAdminService adminService;

	@DisplayName("Add Statement with valid data")
	@Test
	final void testAddStatementValid() {

		when(adminService.addStatement(1, "1234 4567 2345"))
				.thenReturn(new ResponseEntity<String>("Statement added successfully", HttpStatus.OK));

		ResponseEntity<String> response = adminController.addStatement(1, "1234 4567 2345");

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("Statement added successfully");
	}

	@DisplayName("Add Statement with invalid data")
	@Test
	final void testAddStatementInvalid() {

		when(adminService.addStatement(-1, ""))
				.thenReturn(new ResponseEntity<String>("Customer doesn't exist", HttpStatus.NOT_FOUND));

		ResponseEntity<String> response = adminController.addStatement(-1, "");

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isEqualTo("Customer doesn't exist");
	}

}
