/**
 * 
 */
package ca.est.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.est.service.AppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * @author Estevam Meneses
 */
@RestController
//@RequestMapping("/api")
public class UserController {


	@Autowired
	private AppService appService;


	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EDITOR')")
	@GetMapping("/api/users/find")
	public ResponseEntity<?> findUser() {
		return appService.findAllUsers().build();
	}
	

	@PreAuthorize("hasRole('ROLE_EDITOR')")
	@GetMapping("/api/users/test")
	public ResponseEntity<?> test() {
		return appService.findAllUsers().build();
	}
	
	/**
	 * Create token adding name
	 * @param name
	 * @return
	 */
	@Operation(summary = "Create token by name")
	@GetMapping("/generate/token/{username}")
	
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Token created", 
			    content = { @Content(mediaType = "application/json") }),
			  @ApiResponse(responseCode = "403", description = "User not found", 
			    content = @Content)})
	@ResponseBody
	public ResponseEntity<?> token(@PathVariable(value="username") String username) {
		return appService.createUserAndToken(username).build();
	}
}