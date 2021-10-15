package gov.nih.nci.evs.compare.searchservice.service;

import org.springframework.web.context.request.async.DeferredResult;

public class TimedDeferredResultWrapper {

	public TimedDeferredResultWrapper(boolean stale, DeferredResult<byte[]> result, long deferredTimeOut) {

		super();
		this.result = result;
		this.deferredTimeOut = deferredTimeOut + System.currentTimeMillis();
		
	}

	private DeferredResult<byte[]> result;
	private long deferredTimeOut;

	public boolean isStale() {

		if (result.hasResult() && deferredTimeOut < System.currentTimeMillis()) {
			return true;
		} else {
			return false;
		}
	}

	public void setStale(boolean stale) {
	}

	public DeferredResult<byte[]> getResult() {
		return result;
	}

	public void setResult(DeferredResult<byte[]> result) {
		this.result = result;
	}

	public long getDeferredTimeOut() {
		return deferredTimeOut;
	}

	public void setDeferredTimeOut(int deferredTimeOut) {
		this.deferredTimeOut = deferredTimeOut + System.currentTimeMillis();
	}


}
