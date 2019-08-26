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
        	
        	newFrame += '<img src="lineResources/images/' + msgid + '" width="' + shimgw + '" height="' + shimgh + '" />';
	        break;
        case 'sticker':
        	newFrame += 'Line Sticker';
	        break;
        case 'video':
        	newFrame += '<video width="400" controls><source src="lineResources/videos/' + msgid + '"></video>';
	        break;
        case 'audio':
        	newFrame += '<audio controls="controls"><source src="lineResources/audios/' + msgid + '" /></audio>';
	        break;
        default :
        	newFrame += '<span style="color: #FF0000">系統目前暫不支援此格式訊息!</span>';
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
        	
        	newFrame += '<img src="lineResources/images/' + msgid + '" width="' + shimgw + '" height="' + shimgh + '" />';
	        break;
            break;
        case 'sticker':
        	newFrame += 'Line Sticker';
            break;
        case 'video':
        	newFrame += '<video width="400" controls><source src="lineResources/videos/' + msgid + '"></video>';
            break;
        case 'audio':
        	newFrame += '<audio controls="controls"><source src="lineResources/audios/' + msgid + '" /></audio>';
            break;
        default :
        	newFrame += '<span style="color: #FF0000">系統目前暫不支援傳輸此格式訊息!</span>';
            break;
    }
	
	newFrame += '</div></td></tr></table></div>';
	
	return newFrame;
}