package gov.nih.nci.evs.compare.searchservice.model;

public class DeferredStatus {
	public static enum Status{TRUE, FALSE, EXPIRED}
	
	public Status status;

	public DeferredStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


}
