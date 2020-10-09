package eduardo.cleantest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import eduardo.cleantest.repository.RouletteRepository;

@RestController
@CrossOrigin

public class ApiRoulletteController {
	 @Autowired
	 private RouletteRepository rouletteRepository;
	 @PostMapping(path="/buildroulette")
	 public void save(){
		 rouletteRepository.save();
	 }
	 @GetMapping("/roulettes")
	 public List list(){
		 
		 return rouletteRepository.findAll();
	 }
}
