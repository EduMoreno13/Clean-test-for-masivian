package eduardo.cleantest.model;

import java.io.Serializable;
import java.util.UUID;

public class Roulette implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String status;
	
	public Roulette() {
		this.id = UUID.randomUUID().toString();
		this.status = "closed";
	}
	public String getId() {
		
		return id;
	}
	public String getStatus() {
		
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
