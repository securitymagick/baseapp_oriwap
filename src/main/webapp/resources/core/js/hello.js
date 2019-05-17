function testText(text, documentPlace, submitButton)
{
		var intScore   = 1;
		var strVerdict = "";
		var strFont     = "text-success";
		
		

		
	    // check for common ways to start java
		if (text.match(/(<script|<SCRIPT|<img src="javascript:|<img src=javascript:)/)){
			intScore=0;
		} 
		
		if(intScore < 1)
		{
		   strVerdict  = "We love you making your posts and comments look good with tags, but for security reason can't allow javascript.";
		   strFont="text-danger";
		   submitButton.setAttribute('disabled', true); 	   
		}
		else {
			submitButton.removeAttribute('disabled');
			if (text.length >23)
			{
			   strVerdict = "Awesome Zoo Baby!  Awesome Thoughts!";
			   strFont="text-success";
			}
		} 
		
		document.getElementById(documentPlace).innerHTML = "<div class=\"text-center " + strFont +  "\">"  + strVerdict + "</div>"
}

/* ************************************************************
Password Strength Meter and Other useful functions

************************************************************ */
function testPassword(passwd, documentPlace)
{
		var intScore   = 0
		var strVerdict = "weak"
		var	strFont="text-danger";
		
		
		if (passwd.length>7)// get 1 point for each character after 8
		{
			intScore = passwd.length
		}	
		
		// LETTERS 
		if (passwd.match(/[a-z]/))                              // at least one lower case letter
		{
			intScore = (intScore+1)
		}
		
		if (passwd.match(/[A-Z]/))                              // at least one upper case letter
		{
			intScore = (intScore+1)
		}
		
		// NUMBERS
		if (passwd.match(/\d+/))                                 // at least one number
		{
			intScore = (intScore+1)
		}
		
		if (passwd.match(/(.*[0-9].*[0-9].*[0-9])/))             // at least three numbers
		{
			intScore = (intScore+3)
		}
		
		
		// SPECIAL CHAR
		if (passwd.match(/[^a-zA-Z0-9]/))            // at least one special character
		{
			intScore = (intScore+2)
		}
		
									 // [verified] at least two special characters
		if (passwd.match(/(.*[^a-zA-Z0-9].*[^a-zA-Z0-9])/))
		{
			intScore = (intScore+3)
		}
	    // check for common passwords ignore case
		var lowerCasePass = passwd.toLowerCase();
		if (lowerCasePass.match(/^(passw(o|0)rd1?|123456789?|trustno1|football|iloveyou|welcome|whatever|jordan23)$/)){
			intScore=0;
		}
		// check for common passwords
		if (lowerCasePass.match(/^(1qaz2wsx|12341234|corvette|computer|blahblah|matthew|mercedes)$/)){
			intScore=0;
		}		
		// check for common passwords
		if (lowerCasePass.match(/^(admin1234?|michelle|sunshine|zaq1zaq1|jennifer|maverick|starwars)$/)){
			intScore=0;
		}			
	
		if(intScore < 8)
		{
		   strVerdict = "Try a longer password or add a special character..."
		   strFont="text-danger";
		}
		else if (intScore >= 8 && intScore < 14)
		{
		   strVerdict = "Pretty good choice if I do say so myself ..."
		   strFont = "text-info"
		}
		else if (intScore >= 14)
		{
		   strVerdict = "That's my favorite password!!! Yipee!!"
		   strFont="text-success";
		}
		document.getElementById(documentPlace).innerHTML = "<div class=\"text-center " + strFont +  "\">" + strVerdict + "</div>"
}

function testPasswordMatch(passwd, confirmPasswd, documentPlace) {
	var strVerdict = "Passwords Do Not Match!"
	var strFont="text-danger";
	
	if (passwd === confirmPasswd) {
		strVerdict = "Passwords Match ... Great Job!"
		strFont="text-success";
	}
		
	document.getElementById(documentPlace).innerHTML = "<div class=\"text-center " + strFont +  "\">" + strVerdict + "</div>"	
}

function testUsername(user, documentPlace, submitButton)
{
		var intScore   = 0
		var strVerdict = "choose again"
		var strFont="text-danger";
		
		
		if (user.length>2 && user.length<33)// get 1 point for length greater than 2
		{
			intScore = user.length
		}	
		
		// LETTERS 
		if (user.match(/[a-z]/))                              // at least one lower case letter
		{
			intScore = (intScore+1)
		}
		
		if (user.match(/[A-Z]/))                              // at least one upper case letter
		{
			intScore = (intScore+1)
		}
		
		// NUMBERS
		if (user.match(/\d+/))                                 // at least one number
		{
			intScore = (intScore+1)
		}
		
		if (user.match(/(.*[0-9].*[0-9].*[0-9])/))             // at least three numbers
		{
			intScore = (intScore+3)
		}
		
		
		// SPECIAL CHAR
		if (user.match(/[^a-zA-Z0-9]/))            // at least one special character
		{
			intScore = 0
		}
		
	    // check for common users
		if (user.match(/^(RickPotter|Elephantus|lions4ever|Bob|admin|root|superuser)$/)){
			intScore=0;
		}
		
		if(intScore < 4)
		{
		   strVerdict = "Choose something different!"
		   strFont="text-danger";
		   submitButton.setAttribute('disabled', true); 
		}
		else if (intScore >= 4)
		{
		   strVerdict = "Good Choice!"
		   strFont="text-success";
		   submitButton.removeAttribute('disabled');	  	   
		}
		document.getElementById(documentPlace).innerHTML = "<div class=\"text-center " + strFont +  "\">" + strVerdict + "</div>"
}

