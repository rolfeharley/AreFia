function createRecieveFrame(userId, type, msg, time, msgid, imgw, imgh, stadw) {
	var newFrame = '<div class="arefiaSrcFrame"><table><tr>';
	
	newFrame += '<td style="text-align: right;"><div class="arefiaSrcFimg" style="background-image: url(' + $('#' + userId + '_hicon').data('imgurl') + ');"></div></td>';
	newFrame += '<td style="text-align: left;"><div class="arefiaSrcMsgBox">';                       		

	switch (type) {
        case 'text':
        	newFrame += msg;
	        break;
        case 'image':
        	var shimgw, shimgh;
        	
        	if (imgw > stadw) {
        		shimgw = stadw;
        		shimgh = imgh * (stadw / imgw);
        	} else {
        		shimgw = imgw;
        		shimgh = imgh;
        	}
        	
        	newFrame += '<img src="lineResources/images/' + msgid + '.png" width="' + shimgw + '" height="' + shimgh + '" />';
	        break;
        case 'sticker':
        	newFrame += 'Line Sticker';
	        break;
        case 'video':
        	newFrame += '';
	        break;
        case 'audio':
        	newFrame += '';
	        break;
        default :
        	newFrame += '<span style="color: #FF0000">This Message Type Is Not Surpported!</span>';
	        break;
    }
	
	newFrame += '</div></td><td style="text-align: left;"><div class="arefiaConTimes">' + time + '</div></td></tr></table></div>';
	
	return newFrame;
}

function createPushFrame(type, msg, time, msgid, imgw, imgh, stadw) {
	var newFrame = '<div class="arefiaMsfFrame" align="right"><table><tr>';
	newFrame += '<td style="text-align: right;"><div class="arefiaConTimes">' + time + '</div></td>';
	newFrame += '<td style="text-align: left;"><div class="arefiaMsfMsgBox">';                       		

	switch (type) {
        case 'text':
        	newFrame += msg;
            break;
        case 'image':
            var shimgw, shimgh;
        	
        	if (imgw > stadw) {
        		shimgw = stadw;
        		shimgh = imgh * (stadw / imgw);
        	} else {
        		shimgw = imgw;
        		shimgh = imgh;
        	}
        	
        	newFrame += '<img src="lineResources/images/' + msgid + '.png" width="' + shimgw + '" height="' + shimgh + '" />';
	        break;
            break;
        case 'sticker':
        	newFrame += 'Line Sticker';
            break;
        case 'video':
        	newFrame += '';
            break;
        case 'audio':
        	newFrame += '';
            break;
        default :
        	newFrame += '<span style="color: #FF0000">This Message Type Is Not Surpported!</span>';
            break;
    }
	
	newFrame += '</div></td></tr></table></div>';
	
	return newFrame;
}