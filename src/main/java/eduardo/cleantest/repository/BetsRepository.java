package eduardo.cleantest.repository;

import java.util.Map;

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

}
