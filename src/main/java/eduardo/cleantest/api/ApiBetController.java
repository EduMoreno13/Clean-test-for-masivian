package eduardo.cleantest.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import eduardo.cleantest.model.Bet;
import eduardo.cleantest.repository.BetsRepository;

@RestController
public class ApiBetController {
	 @Autowired
	 private BetsRepository betsRepository;
	 
	 @PostMapping(path="/Betting")
	 public String save(@RequestBody Bet bet,@RequestHeader("Id-user") String user){
	        return betsRepository.save(bet,user);
	 }
	 
	 @GetMapping("/consultarbets/{id}")
	 public Map<String,Bet> list(@PathVariable String id){
	        return betsRepository.findAll(id);
	 }	 
}
