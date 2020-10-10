package eduardo.cleantest.api;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import eduardo.cleantest.model.Roulette;
import eduardo.cleantest.repository.BetsRepository;
import eduardo.cleantest.repository.RouletteRepository;

@RestController
@CrossOrigin

public class ApiRoulletteController {
	 @Autowired
	 private RouletteRepository rouletteRepository;
	 @Autowired
	 private BetsRepository betsRepository;
	 
	 @PostMapping(path="/buildroulette")
	 public void save(){
		 rouletteRepository.save();
	 }
	 @GetMapping("/roulettes")
	 public List list(){
		 
		 return rouletteRepository.findAll();
	 }

	 @PutMapping("/openroulette/{id}")
	 public String openRoulette(@PathVariable String id) {
		 
		 if(rouletteRepository.rouletteExist(id)) {
			 Roulette roulette = rouletteRepository.findById(id);
			 if (roulette.getStatus().equals("closed")) {
				 roulette.setStatus("open");
				 rouletteRepository.update(roulette);
				 return "Succed";
			 }
			 else {
				 return "Denied";
			 }
		 }
		 else {
			 return "Denied";
		 }
	 }
	 @PutMapping("/closeroulette/{id}")
	 public Map<String,Float> closeRoulette(@PathVariable String id) {
		 Random r= new Random(System.currentTimeMillis());
		 int win= r.nextInt(37);
		 if(rouletteRepository.rouletteExist(id)) {
			 Roulette roulette = rouletteRepository.findById(id);
			 if(roulette.getStatus().equals("open")) {
				 roulette.setStatus("closed");
				 rouletteRepository.update(roulette);
				 return betsRepository.evalBets(id, win);
			 }
		 }
		 return null; 
	 }
}
