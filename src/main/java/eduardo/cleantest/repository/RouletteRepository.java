package eduardo.cleantest.repository;
import java.util.List;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import eduardo.cleantest.model.Roulette;

@Repository
public class RouletteRepository {
	
	    public static final String ROULETTE_KEY = "ROULETTE";
	    private HashOperations hashOperations;
	    private RedisTemplate redisTemplate;
	 
	    public RouletteRepository(RedisTemplate redisTemplate){
	        this.redisTemplate = redisTemplate;
	        this.hashOperations = this.redisTemplate.opsForHash();
	    }
	    public void save() {
	    	Roulette roulette= new Roulette();
	        hashOperations.put(ROULETTE_KEY, roulette.getId(), roulette);
	    }
	    public List findAll(){
	    	
	        return hashOperations.values(ROULETTE_KEY);
	    }
	    public boolean rouletteExist(String Id) {
	    	
	    	return hashOperations.hasKey(ROULETTE_KEY, Id);
	    }
	    public Roulette findById(String Id) {
	    	
	    	return (Roulette) hashOperations.get(ROULETTE_KEY, Id);
	    }
	    public void update(Roulette roulette) {
	    	hashOperations.put(ROULETTE_KEY, roulette.getId(), roulette);
	    }
}
