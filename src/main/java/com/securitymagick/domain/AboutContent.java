package com.securitymagick.domain;

import static com.securitymagick.AppConstants.FoundedYear;
import static com.securitymagick.AppConstants.FounderFName;
import static com.securitymagick.AppConstants.FounderLName;
import static com.securitymagick.AppConstants.SiteName;
import static com.securitymagick.AppConstants.FounderSpecial;
import static com.securitymagick.AppConstants.FamousOther;
import static com.securitymagick.AppConstants.FamousName;
import static com.securitymagick.AppConstants.FamousProfessional;
import static com.securitymagick.AppConstants.FounderPossessive;
import static com.securitymagick.AppConstants.FamousPossessive;
import static com.securitymagick.AppConstants.FounderPronoun;
import static com.securitymagick.AppConstants.FounderYoungAge;
import static com.securitymagick.AppConstants.Location;
import static com.securitymagick.AppConstants.DescriptivePlace;
import static com.securitymagick.AppConstants.DescriptiveToSubject;
import static com.securitymagick.AppConstants.BadResultOfAdversisty;
import static com.securitymagick.AppConstants.Adversity;
import static com.securitymagick.AppConstants.SubjectOfSite;
import static com.securitymagick.AppConstants.FounderStudied;
import static com.securitymagick.AppConstants.SelfHelp;

public class AboutContent {
	private String firstSentence;
	private String middleSentences;
	private String lastSentences;
	private Integer layout;
	
	public AboutContent(Integer layout) {
		this.layout = layout;
		createFirstSentence();
		createMiddleSentences();
		createlastSentence();
	}
	
	private void createlastSentence() {
		lastSentences = "This website is to connect everyone who loves or wants to discover " + SiteName;
		if (layout.equals(1)) {
			lastSentences = "As a side project " + FounderFName + " like to share " + FounderPossessive + " love of " + SiteName + " with the world through this website.";
		}
		if (layout.equals(2)) {
			lastSentences = FounderFName + "'s side project is sharing this site to help other discover " + SiteName + ".";
		}
		if (layout.equals(3)) {
			lastSentences = FounderFName + " started this site to help and connect others who need " + SiteName + ".";
		}
		
	}

	private void createMiddleSentences() {
		String start = "";
		String middle = "";
		String end="";
		String other="";
		String help = "";
		if (!FamousOther.isEmpty() && SelfHelp.isEmpty()){
			
			start = FounderFName + " accompanied " + FounderPossessive + " "+ FamousOther + " -- " + FamousName + " the well known " + FamousProfessional + " -- on one of " + FamousPossessive + " trips to " + Location + " when " + FounderPronoun + " was just " + FounderYoungAge + " years old.";
			middle = "  On that first trip " + FounderFName + " rescued a " + DescriptiveToSubject + " " +  FounderSpecial + " " + BadResultOfAdversisty + "  by " + Adversity + " .";
			end = "  This spurred " + FounderPossessive + " love of " + FounderStudied + "  and of " + SubjectOfSite + ".";

			if (layout.equals(1) || layout.equals(2)) {
				start = FounderFName + " was an orphan raised by " + FounderPossessive+ " " + FamousOther + ", " + FamousName + " who is well known for " + FamousPossessive + " work as a" + FamousProfessional + ".";
				middle = "  Traveling with " + FounderPossessive + " " + FamousOther + " is how " + FounderFName + " learned about the " + Adversity + "  of " + FounderSpecial + " " + BadResultOfAdversisty + "  that should just be " + DescriptiveToSubject + " "+  SubjectOfSite + " .";
				String capitilizedPronoun = FounderPronoun.substring(0, 1).toUpperCase() + FounderPronoun.substring(1);
				end = "  " + capitilizedPronoun + " is training as many " + DescriptiveToSubject + " " +  FounderSpecial + "  as " + FounderPronoun + " can in being proper " + SubjectOfSite + ".";
			}
		} else if (!SelfHelp.isEmpty()) {
			start = FounderFName + " started this website to help others with " + Adversity + " through shared love of " +  FounderSpecial +  ".  "; 
			middle = FounderFName + " at the age of " + FounderYoungAge + " suffered from such bad " + Adversity + " that " + FounderPronoun + " " + BadResultOfAdversisty + " until " + FounderPronoun + " was found " +  DescriptiveToSubject + "  through " + SubjectOfSite + ".";
			end = "  This spurred " + FounderPossessive + " love of " + FounderStudied + "  and of " + SubjectOfSite + ".";

			if (layout.equals(0) || layout.equals(2)) {
				start = FounderFName + " who had suffered from " + Adversity + " at young age, started this site to help other sufferers through their common interest in " +  FounderSpecial +  ".  ";
				middle = FounderFName + "'s " + Adversity + " was so bad that at the age of " + FounderYoungAge + " " + FounderPronoun + BadResultOfAdversisty + "."; 
				end =  "  Finally, " + FounderPronoun + " discovered " +  DescriptiveToSubject + "  through " + SubjectOfSite + ", and studying " + FounderStudied + ".";
			}
				
			
			help = "  " + FounderFName + " advises taking things one day at a time.";
			if (layout.equals(1)) {
				help = "  " + FounderFName + " advises remembering no matter how bleak things look there is always a way forward.";
			}
			if (layout.equals(2)) {
				help = "  " + FounderFName + " always says that failure is often the first step in success.";
			}
			if (layout.equals(3)) {
				help = "  " + FounderFName + " always says that it's always darkest just before the dawn.";
			}
		} else {
			other = "  " + FounderFName + " currently hold a doctrate in " + FounderStudied + "  and is the founder of the " + FounderFName + " " + FounderLName + " Foundation, which focuses on stopping " + Adversity + "  in the " + DescriptivePlace + ".";  
			if (layout.equals(3) || layout.equals(2)) {
				other = "  " + FounderFName + " currently holds a PhD in " + FounderStudied + "  and is recreating the " + DescriptiveToSubject + " " + DescriptivePlace + "  near " + FounderPossessive + " home in " + Location + ".";
			}
		}
		middleSentences = start + middle + end + help + other;
	}

	private void createFirstSentence() {
		firstSentence = "In " + FoundedYear + " " + FounderFName + " " + FounderLName + " founded " + SiteName + ".";
		if (layout.equals(1)) {
			firstSentence = SiteName + " was founded by " + FounderFName + " " + FounderLName + " in " + FoundedYear + ".";
		}
		if (layout.equals(2)) {
			firstSentence =  FounderFName + " " + FounderLName + " started " + SiteName + " in " + FoundedYear + ".";
		}
		if (layout.equals(3)) {
			firstSentence = "In " + FoundedYear + " " + SiteName + " was started by " + FounderFName + " " + FounderLName + ".";
		}
	}

	/**
	 * @return the firstSentence
	 */
	public final String getFirstSentence() {
		return firstSentence;
	}

	/**
	 * @param firstSentence the firstSentence to set
	 */
	public final void setFirstSentence(String firstSentence) {
		this.firstSentence = firstSentence;
	}

	/**
	 * @return the middleSentences
	 */
	public final String getMiddleSentences() {
		return middleSentences;
	}

	/**
	 * @param middleSentences the middleSentences to set
	 */
	public final void setMiddleSentences(String middleSentences) {
		this.middleSentences = middleSentences;
	}

	/**
	 * @return the lastSentences
	 */
	public final String getLastSentences() {
		return lastSentences;
	}

	/**
	 * @param lastSentences the lastSentences to set
	 */
	public final void setLastSentences(String lastSentences) {
		this.lastSentences = lastSentences;
	}
	

}
