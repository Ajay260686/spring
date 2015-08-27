package com.nuance.explorer.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.nuance.explorer.AbstractProfileTest;


public class FileSystemControllerTest extends AbstractProfileTest{
	
	@Test
	public void testFile() throws Exception {
	    this.mockMvc.perform(get("/fileexplorer/filesAndDirectories?directoryPath=/home/rohit/test").accept(MediaType.APPLICATION_JSON))   
	            .andExpect(status().isOk())
	            .andExpect(model().size(3));
	}
 
}
