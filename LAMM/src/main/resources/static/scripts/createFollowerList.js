function addNewLine(userObj, tarcomp, addtype) {
	var followerdiv = '<div id="' + userObj.USERID + '_outcon" class="arefiafollowercontainer" onclick="showmessagecontent(\'' + userObj.USERID + '\')">';
	
	followerdiv += '<div id="' + userObj.USERID + '_hicon" class="arefiafollowericon" data-imgurl="' + userObj.PICTUREURL + '" style="background-image: url(' + userObj.PICTUREURL + ');"></div>';
	followerdiv += '<div class="arefiafollowerinfo">';
	followerdiv += '<div id="' + userObj.USERID + '_udna" class="arefiafollowername">' + userObj.DISPLAYNAME + '</div>';
	followerdiv += '<div id="' + userObj.USERID + '_lstmsg" class="arefialastmessage">';
	
	switch(userObj.MSGTYPE) {
	    case 'text':
	    	var textmsg = userObj.MSG;
	    	
	    	textmsg.substring(0, textmsg.indexOf('\r\n'));
	    	
	    	if (textmsg.length > 25) {
		    	followerdiv += textmsg.substr(0, 25) + '&#8228;&#8228;&#8228;';
	    	} else {
		    	followerdiv += textmsg;
	    	}
	    	break;
	    case 'image':
	    	if (userObj.MSGFROM == 'PUSH') {
		    	followerdiv += 'Image';
	    	} else {
	    		followerdiv += 'Image';
	    	}
	    	break;
	    case 'video':
	    	if (userObj.MSGFROM == 'PUSH') {
		    	followerdiv += 'Video';
	    	} else {
	    		followerdiv += 'Video';
	    	}
	    	break;
	    case 'audio':
	    	if (userObj.MSGFROM == 'PUSH') {
		    	followerdiv += 'Audio';
	    	} else {
	    		followerdiv += 'Audio';
	    	}
	    	break;
	    case 'sticker':
	    	if (userObj.MSGFROM == 'PUSH') {
		    	followerdiv += 'Sticker';
	    	} else {
	    		followerdiv += 'Sticker';
	    	}
	    	break;
	    default:
	    	followerdiv += 'Unsupported';
	        break;
	}
	                		
	followerdiv += '</div></div>';
	followerdiv += '<div id="' + userObj.USERID + '_mgtime" class="arefiamessagetime">' + userObj.LASTDATE + '<br/>' + userObj.LASTTIME;
	followerdiv += '</div><div id="' + userObj.USERID + '_unrcnt" class="arefiaunrmsgcount"';

	if (Number(userObj.UNREADCOUNT) > 0) {
		if (Number(userObj.UNREADCOUNT) > 999) {
			followerdiv += '>999+';
		} else {
			followerdiv += '>' + userObj.UNREADCOUNT;
		}
	} else {
		followerdiv += ' style="display: none;">';
	}
	
	followerdiv += '</div></div>'
	
	if (addtype == 'initial') {
		$(tarcomp).append(followerdiv);
	} else {
		$(tarcomp).prepend(followerdiv);
	}
}

function addNewUserList(userObj, tarcomp) {
    var contsdiv = '<div id="' + userObj.USERID + '_outulist" class="arefiafollowercontainer" onclick="showcontactcontent(\'' + userObj.USERID + '\')">';
	
    contsdiv += '<div id="' + userObj.USERID + '_ulicon" class="arefiafollowericon" data-imgurl="' + userObj.PICTUREURL + '" style="background-image: url(' + userObj.PICTUREURL + ');"></div>';
    contsdiv += '<div class="arefiafollowerinfo">';
    contsdiv += '<div id="' + userObj.USERID + '_ulsnam" class="arefiaconlstname">' + userObj.DISPLAYNAME + '</div></div>';
    
    $(tarcomp).append(contsdiv);
}