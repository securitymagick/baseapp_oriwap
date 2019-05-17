package com.securitymagick.domain;

public class VoteItem {
	private String voteFor;
	private Integer count=0;

	
	/**
	 * Default Constructor
	 */
	public VoteItem() {
	}


	/**
	 * @param voteFor
	 */
	public VoteItem(String voteFor) {
		this.voteFor = voteFor;
	}


	/**
	 * @return the voteFor
	 */
	public final String getVoteFor() {
		return voteFor;
	}


	/**
	 * @param voteFor the voteFor to set
	 */
	public final void setVoteFor(String voteFor) {
		this.voteFor = voteFor;
	}


	/**
	 * @return the count
	 */
	public final Integer getCount() {
		return count;
	}


	/**
	 * @param count the count to set
	 */
	public final void incrementCount() {
		this.count = this.count + 1;
	}


	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VoteItem [voteFor=" + voteFor + ", count=" + count + "]";
	}


	/** 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((voteFor == null) ? 0 : voteFor.hashCode());
		return result;
	}


	/** 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof VoteItem)) {
			return false;
		}
		VoteItem other = (VoteItem) obj;
		if (voteFor == null) {
			if (other.voteFor != null) {
				return false;
			}
		} else if (!voteFor.equals(other.voteFor)) {
			return false;
		}
		return true;
	}


	
	
	
}
