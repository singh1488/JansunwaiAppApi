package in.nic.igrs.data.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.nic.igrs.data.service.PublicAppService;
import in.nic.igrs.data.util.ListContainer;
import in.nic.igrs.data.validation.CommonValidation;
import in.nic.igrs.data.validation.ValidationFailedException;

@RestController
public class PublicAppController {
	
	@Autowired
	PublicAppService publicAppService;

	@GetMapping("/get-app-details")
	public ResponseEntity<ListContainer> getAppDetails(HttpServletRequest request) throws IOException{
		return new ResponseEntity<ListContainer>(
				publicAppService.getAppDetails(request), HttpStatus.OK);
	}
	
	
	@GetMapping("/get-user-details/{mobileNo}/{langauge}")
	public ResponseEntity<ListContainer> getUserDetails(@PathVariable String mobileNo, @PathVariable String langauge,HttpServletRequest request){
		
		if (!CommonValidation.isValidMobile(mobileNo)) {
			throw new ValidationFailedException("Invalid Mobile Number");
		}
		
		if (langauge == null || langauge.isEmpty() || langauge.equals("")) {
			throw new ValidationFailedException("Invalid langauge");
		}
		
		return new ResponseEntity<ListContainer>(
				publicAppService.getUserDetails(mobileNo,langauge,request), HttpStatus.OK);
	}
	
	
}
