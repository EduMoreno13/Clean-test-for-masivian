package eduardo.cleantest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import eduardo.cleantest.model.Roulette;
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
//	 @PutMapping("/openroulette/{id}")
//	 public String openRoulette(@PathVariable String id) {
//		 Roulette roulette = rouletteRepository.findById(id);
//		 if (roulette.getStatus()=="closed"){
//				 roulette.setStatus("open");
//				 return "Succed";
//		 }
//		 else {
//			 return "Denied";
//		 }	 		 
//	 }
	 @PutMapping("/openroulette/{id}")
	 public String openRoulette(@PathVariable String id) {
		 
		 if(rouletteRepository.rouletteExist(id)) {
			 Roulette roulette = rouletteRepository.findById(id);
			 roulette.setStatus("open");
			 rouletteRepository.update(roulette);
			 return "Succed";
		 }
		 else {
			 return "Denied";
		 }
	 }
}
