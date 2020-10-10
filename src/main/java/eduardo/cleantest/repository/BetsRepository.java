package eduardo.cleantest.repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import eduardo.cleantest.model.Bet;
import eduardo.cleantest.model.Roulette;

@Repository
public class BetsRepository {

    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;
    
 
    public BetsRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }
 
    public String save(Bet bet, String userId) {
    	if(bet.validateBet() && hashOperations.hasKey(RouletteRepository.ROULETTE_KEY, bet.getRouletteId())) {
    		Roulette roulette = (Roulette) hashOperations.get(RouletteRepository.ROULETTE_KEY,bet.getRouletteId());
    		if(roulette.getStatus().equals("open")) {
    			hashOperations.put(bet.getRouletteId(), userId, bet);
        		return "succed";
    		}
    		else {
    			return "denied";
    		}
    	}
    	else {
    		return "denied";
    	}
    }
 
    public Map<String,Bet> findAll(String rouletteId){
        return hashOperations.entries(rouletteId);
    }
 
    public void delete(Bet bet, String userId) {
        hashOperations.delete(bet.getRouletteId(),userId);
    }
    public Map<String,Float> evalBets(String rouletteId, int win) {
    	
    	Map<String, Bet> bets = findAll(rouletteId);
    	Map<String, Float> results = new HashMap<String,Float>();
    	Iterator<String> it = bets.keySet().iterator();
    	while(it.hasNext()){
    		  String key = it.next();
    		  Bet bet = bets.get(key);
    		  bet.evalBet(win);
    		  delete(bet,key);
    		  results.put(key, bet.getMoney());
    	}
    	results.put("numero ganador", (float)(win));
    	return results;	
    }

}
