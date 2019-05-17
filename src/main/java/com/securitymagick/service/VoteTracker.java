package com.securitymagick.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.securitymagick.domain.VoteItem;

import static com.securitymagick.AppConstants.voteCheckUserOnPageLoad;
import static com.securitymagick.AppConstants.voteCheckUserOnSubmit;


@Component("voteTracker")
public class VoteTracker {
	private List<String> usersWhoHaveVoted = new ArrayList<String>();
	private List<VoteItem> voteItems = new ArrayList<VoteItem>();

	/**
	 * Default Constructor
	 */
	public VoteTracker() {

	}
	
	public List<VoteItem> getVotes() {
		return voteItems;
	}

	public boolean canUserVote(String username) {
		if (voteCheckUserOnPageLoad) {
			if (usersWhoHaveVoted.contains(username)) {
				return false;
			}
		}
		return true;
		
	}
	
	public boolean addVote(String username, String voteFor) {
		if (voteCheckUserOnSubmit) {
			if (usersWhoHaveVoted.contains(username)) {
				return false;
			}
		}
		VoteItem vote = new VoteItem(voteFor.toLowerCase());
		if (voteItems.contains(vote)) {
			Integer index = voteItems.indexOf(vote);
			VoteItem voteExisting = voteItems.get(index);
			voteExisting.incrementCount();
			voteItems.set(index, voteExisting);
		} else {
			vote.incrementCount();
			voteItems.add(vote);
		}
		usersWhoHaveVoted.add(username);
		return true;
	}

}
