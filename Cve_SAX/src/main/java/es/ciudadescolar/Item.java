package es.ciudadescolar;

public class Item {

	private String name;
	private String seq;
	private String type;
	private String status;
	private String desc;
	
	public Item() {
		
	}
	

	public Item(String name, String seq, String type, String status, String desc) {
		super();
		this.name = name;
		this.seq = seq;
		this.type = type;
		this.status = status;
		this.desc = desc;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return name + "|" + seq +"|" + type + "|" + status + "|" + desc;
	}
	
	
	
	
}
