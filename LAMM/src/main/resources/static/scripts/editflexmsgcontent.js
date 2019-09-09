var tmpselectnum;
var flexinfo;
var sendlist = [];
var flexuid;
var condslist;

function createflexdetaileditor(createtype, empindex, senduid) {
	if (createtype == '0') {
		tmpselectnum = empindex;
		flexinfo = undefined;
		sendlist = [];
		flexuid = undefined;
		createbase('0');
	} else {
		flexuid = senduid;
		$.get('getflexbyuid', {UID: senduid}, function(result) {
            if (result != null && result != '') {
            	flexinfo = JSON.parse(result);
            	
            	tmpselectnum = flexinfo.FLEXMOD;
            	
            	sendlist = [];
            	
            	createbase('1');
            	createSendFollowers('1');
            } else {
            	alert('此訊息不可使用!!');
            	flexmsgcancel();
            }	
        });
	}
}

function createbase(ctype) {
var editstr = '<div class="demoarea">';
	
	$('body').append('<input type="hidden" id="classnum"/>');
	$('body').append('<div class="contenttypeselector" style="top: 25px; left: 45vw;" onclick="textcomp()"><i class="fas fa-align-left"></i></div>');
	$('body').append('<div class="contenttypeselector" style="top: calc(5vw + 45px); left: 45vw;" onclick="imagecomp()"><i class="fas fa-image"></i></div>');
	
	if (ctype == '0') {
		$('body').append('<div class="flextitleoutterdiv"><input type="text" id="flextitleeditbox" class="form-control" placeholder="請填寫主旨" /></div>');
	} else {
		$('body').append('<div class="flextitleoutterdiv"><input type="text" id="flextitleeditbox" class="form-control" value="' + flexinfo.TITLE + '" disabled style="color: #000;" /></div>');
	}
	
	editstr += '<div class="contentviewer">';
	
	switch (tmpselectnum) {
	    case '1':
	    	if (ctype == '0') {
	    	    $('body').append('<div class="blockselector" style="top: 25px; left: 5px;" onclick="blockedit(\'flexcontent1\')" onmouseover="bmover(\'flexcontent1\', this)" onmouseout="bmout(\'flexcontent1\', this)"><span><i class="fas fa-th-large"></i>1</span></div>');
		    	editstr += '<div class="flexoutter1"><div id="flexcontent1" class="flexblock1" onclick="openlinkwindow(this)"></div></div>';
		    	editstr += '<input type="file" id="tmpimgupper_1" accept="image/*" style="display: none;" onchange="initimage(this)" />';
	    	} else {
	    		var c1arr = flexinfo.DETAILS;
	    			    		
    			editstr += '<div class="flexoutter1"><div id="flexcontent1" class="flexblock1" onclick="openlinkwindow(this)" ';
    			if (c1arr[0].BLOCKLINK != '') {
    				editstr += 'data-url="' + c1arr[0].BLOCKLINK + '" ';
    			}  			
	    		if (c1arr[0].BLOCKTYPE == 'text') {
	    			editstr += '>' + c1arr[0].BLOCKCONTENT;
	    		} else {
	    			editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c1arr[0].BLOCKCONTENT + '.png" />';
	    		}	    		
	    		editstr += '</div></div>';
	    	}
	    	break;
	    case '2':
	    	if (ctype == '0') {
	    	    $('body').append('<div class="blockselector" style="top: 25px; left: 5px;" onclick="blockedit(\'flexcontent1\')" onmouseover="bmover(\'flexcontent1\', this)" onmouseout="bmout(\'flexcontent1\', this)"><span><i class="fas fa-th-large"></i>1</span></div>');
	    	    $('body').append('<div class="blockselector" style="top: calc(3vw + 35px); left: 5px;" onclick="blockedit(\'flexcontent2\')" onmouseover="bmover(\'flexcontent2\', this)" onmouseout="bmout(\'flexcontent2\', this)"><span><i class="fas fa-th-large"></i>2</span></div>');
		    	editstr += '<div class="flexoutter2"><div id="flexcontent1" class="flexblock2" onclick="openlinkwindow(this)"></div>';
		    	editstr += '<div id="flexcontent2" class="flexblock2" onclick="openlinkwindow(this)"></div></div>';
		    	editstr += '<input type="file" id="tmpimgupper_1" accept="image/*" style="display: none;" onchange="initimage(this)" />';
		    	editstr += '<input type="file" id="tmpimgupper_2" accept="image/*" style="display: none;" onchange="initimage(this)" />';
	    	} else {
	    		var c2arr = flexinfo.DETAILS;
	    		
		    	editstr += '<div class="flexoutter2">';
		    	editstr += '<div id="flexcontent1" class="flexblock2" onclick="openlinkwindow(this)" ';
		    	if (c2arr[0].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c2arr[0].BLOCKLINK + '" ';
		    	}
		    	if (c2arr[0].BLOCKTYPE == 'text') {
		    		editstr += '>' + c2arr[0].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c2arr[0].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div>';
		    	editstr += '<div id="flexcontent2" class="flexblock2" onclick="openlinkwindow(this)" ';
		    	if (c2arr[1].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c2arr[1].BLOCKLINK + '" ';
		    	}
		    	if (c2arr[1].BLOCKTYPE == 'text') {
		    		editstr += '>' + c2arr[1].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c2arr[1].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div></div>';
	    	}
	    	break;
	    case '3':
	    	if (ctype == '0') {
	    	    $('body').append('<div class="blockselector" style="top: 25px; left: 5px;" onclick="blockedit(\'flexcontent1\')" onmouseover="bmover(\'flexcontent1\', this)" onmouseout="bmout(\'flexcontent1\', this)"><span><i class="fas fa-th-large"></i>1</span></div>');
	    	    $('body').append('<div class="blockselector" style="top: calc(3vw + 35px); left: 5px;" onclick="blockedit(\'flexcontent2\')" onmouseover="bmover(\'flexcontent2\', this)" onmouseout="bmout(\'flexcontent2\', this)"><span><i class="fas fa-th-large"></i>2</span></div>');
		    	editstr += '<div class="flexoutter1"><div id="flexcontent1" class="flexblock1" onclick="openlinkwindow(this)"></div>';
		    	editstr += '<div id="flexcontent2" class="flexblock1" onclick="openlinkwindow(this)"></div></div>';
		    	editstr += '<input type="file" id="tmpimgupper_1" accept="image/*" style="display: none;" onchange="initimage(this)" />';
		    	editstr += '<input type="file" id="tmpimgupper_2" accept="image/*" style="display: none;" onchange="initimage(this)" />';
	    	} else {
	    		var c3arr = flexinfo.DETAILS;
	    		
	    		editstr += '<div class="flexoutter1">';
	    		editstr += '<div id="flexcontent1" class="flexblock1" onclick="openlinkwindow(this)" ';
	    		if (c3arr[0].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c3arr[0].BLOCKLINK + '" ';
		    	}
		    	if (c3arr[0].BLOCKTYPE == 'text') {
		    		editstr += '>' + c3arr[0].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c3arr[0].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div>';
		    	editstr += '<div id="flexcontent2" class="flexblock1" onclick="openlinkwindow(this)" ';
		    	if (c3arr[1].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c3arr[1].BLOCKLINK + '" ';
		    	}
		    	if (c3arr[1].BLOCKTYPE == 'text') {
		    		editstr += '>' + c3arr[1].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c3arr[1].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div></div>';
	    	}
	    	break;
	    case '4':
	    	if (ctype == '0') {
	    	    $('body').append('<div class="blockselector" style="top: 25px; left: 5px;" onclick="blockedit(\'flexcontent1\')" onmouseover="bmover(\'flexcontent1\', this)" onmouseout="bmout(\'flexcontent1\', this)"><span><i class="fas fa-th-large"></i>1</span></div>');
	    	    $('body').append('<div class="blockselector" style="top: calc(3vw + 35px); left: 5px;" onclick="blockedit(\'flexcontent2\')" onmouseover="bmover(\'flexcontent2\', this)" onmouseout="bmout(\'flexcontent2\', this)"><span><i class="fas fa-th-large"></i>2</span></div>');
	    	    $('body').append('<div class="blockselector" style="top: calc(6vw + 45px); left: 5px;" onclick="blockedit(\'flexcontent3\')" onmouseover="bmover(\'flexcontent3\', this)" onmouseout="bmout(\'flexcontent3\', this)"><span><i class="fas fa-th-large"></i>3</span></div>');
		    	editstr += '<div class="flexoutter1"><div id="flexcontent1" class="flexblock1" onclick="openlinkwindow(this)"></div>';
		    	editstr += '<div id="flexcontent2" class="flexblock1" onclick="openlinkwindow(this)"></div>';
		    	editstr += '<div id="flexcontent3" class="flexblock1" onclick="openlinkwindow(this)"></div></div>';
		    	editstr += '<input type="file" id="tmpimgupper_1" accept="image/*" style="display: none;" onchange="initimage(this)" />';
		    	editstr += '<input type="file" id="tmpimgupper_2" accept="image/*" style="display: none;" onchange="initimage(this)" />';
		    	editstr += '<input type="file" id="tmpimgupper_3" accept="image/*" style="display: none;" onchange="initimage(this)" />';
	    	} else {
	    		var c4arr = flexinfo.DETAILS;
	    		
	    		editstr += '<div class="flexoutter1">';
	    		editstr += '<div id="flexcontent1" class="flexblock1" onclick="openlinkwindow(this) "';
	    		if (c4arr[0].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c4arr[0].BLOCKLINK + '" ';
		    	}
		    	if (c4arr[0].BLOCKTYPE == 'text') {
		    		editstr += '>' + c4arr[0].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c4arr[0].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div>';
		    	editstr += '<div id="flexcontent2" class="flexblock1" onclick="openlinkwindow(this)" ';
		    	if (c4arr[1].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c4arr[1].BLOCKLINK + '" ';
		    	}
		    	if (c4arr[1].BLOCKTYPE == 'text') {
		    		editstr += '>' + c4arr[1].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c4arr[1].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div>';
		    	editstr += '<div id="flexcontent3" class="flexblock1" onclick="openlinkwindow(this)" ';
		    	if (c4arr[2].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c4arr[2].BLOCKLINK + '" ';
		    	}
		    	if (c4arr[2].BLOCKTYPE == 'text') {
		    		editstr += '>' + c4arr[2].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c4arr[2].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div></div>';
	    	}
	    	break;
	    case '5':
	    	if (ctype == '0') {
	    	    $('body').append('<div class="blockselector" style="top: 25px; left: 5px;" onclick="blockedit(\'flexcontent1\')" onmouseover="bmover(\'flexcontent1\', this)" onmouseout="bmout(\'flexcontent1\', this)"><span><i class="fas fa-th-large"></i>1</span></div>');
	    	    $('body').append('<div class="blockselector" style="top: calc(3vw + 35px); left: 5px;" onclick="blockedit(\'flexcontent2\')" onmouseover="bmover(\'flexcontent2\', this)" onmouseout="bmout(\'flexcontent2\', this)"><span><i class="fas fa-th-large"></i>2</span></div>');
	    	    $('body').append('<div class="blockselector" style="top: calc(6vw + 45px); left: 5px;" onclick="blockedit(\'flexcontent3\')" onmouseover="bmover(\'flexcontent3\', this)" onmouseout="bmout(\'flexcontent3\', this)"><span><i class="fas fa-th-large"></i>3</span></div>');
	    	    $('body').append('<div class="blockselector" style="top: calc(9vw + 55px); left: 5px;" onclick="blockedit(\'flexcontent4\')" onmouseover="bmover(\'flexcontent4\', this)" onmouseout="bmout(\'flexcontent4\', this)"><span><i class="fas fa-th-large"></i>4</span></div>');
		    	editstr += '<div class="flexoutter1"><div class="flexsuboutter"><div id="flexcontent1" class="flexblock2" onclick="openlinkwindow(this)"></div>';
		    	editstr += '<div id="flexcontent2" class="flexblock2" onclick="openlinkwindow(this)"></div></div>';
		    	editstr += '<div class="flexsuboutter"><div id="flexcontent3" class="flexblock2" onclick="openlinkwindow(this)"></div>';
		    	editstr += '<div id="flexcontent4" class="flexblock2" onclick="openlinkwindow(this)"></div></div></div>';
		    	editstr += '<input type="file" id="tmpimgupper_1" accept="image/*" style="display: none;" onchange="initimage(this)" />';
		    	editstr += '<input type="file" id="tmpimgupper_2" accept="image/*" style="display: none;" onchange="initimage(this)" />';
		    	editstr += '<input type="file" id="tmpimgupper_3" accept="image/*" style="display: none;" onchange="initimage(this)" />';
		    	editstr += '<input type="file" id="tmpimgupper_4" accept="image/*" style="display: none;" onchange="initimage(this)" />';
	    	} else {
	    		var c5arr = flexinfo.DETAILS;
	    		
	    		editstr += '<div class="flexoutter1"><div class="flexsuboutter">';
	    		editstr += '<div id="flexcontent1" class="flexblock2" onclick="openlinkwindow(this)" ';
	    		if (c5arr[0].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c5arr[0].BLOCKLINK + '" ';
		    	}
		    	if (c5arr[0].BLOCKTYPE == 'text') {
		    		editstr += '>' + c5arr[0].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c5arr[0].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div>';
		    	editstr += '<div id="flexcontent2" class="flexblock2" onclick="openlinkwindow(this)" ';
		    	if (c5arr[1].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c5arr[1].BLOCKLINK + '" ';
		    	}
		    	if (c5arr[1].BLOCKTYPE == 'text') {
		    		editstr += '>' + c5arr[1].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c5arr[1].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div></div>';
		    	editstr += '<div class="flexsuboutter">';
		    	editstr += '<div id="flexcontent3" class="flexblock2" onclick="openlinkwindow(this)" ';
		    	if (c5arr[2].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c5arr[2].BLOCKLINK + '" ';
		    	}
		    	if (c5arr[2].BLOCKTYPE == 'text') {
		    		editstr += '>' + c5arr[2].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c5arr[2].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div>';
		    	editstr += '<div id="flexcontent4" class="flexblock2" onclick="openlinkwindow(this)" ';
		    	if (c5arr[3].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c5arr[3].BLOCKLINK + '" ';
		    	}
		    	if (c5arr[3].BLOCKTYPE == 'text') {
		    		editstr += '>' + c5arr[3].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c5arr[3].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div></div></div>';
	    	}
	    	break;
	    case '6':
	    	if (ctype == '0') {
	    	    $('body').append('<div class="blockselector" style="top: 25px; left: 5px;" onclick="blockedit(\'flexcontent1\')" onmouseover="bmover(\'flexcontent1\', this)" onmouseout="bmout(\'flexcontent1\', this)"><span><i class="fas fa-th-large"></i>1</span></div>');
	    	    $('body').append('<div class="blockselector" style="top: calc(3vw + 35px); left: 5px;" onclick="blockedit(\'flexcontent2\')" onmouseover="bmover(\'flexcontent2\', this)" onmouseout="bmout(\'flexcontent2\', this)"><span><i class="fas fa-th-large"></i>2</span></div>');
	    	    $('body').append('<div class="blockselector" style="top: calc(6vw + 45px); left: 5px;" onclick="blockedit(\'flexcontent3\')" onmouseover="bmover(\'flexcontent3\', this)" onmouseout="bmout(\'flexcontent3\', this)"><span><i class="fas fa-th-large"></i>3</span></div>');	   
		    	editstr += '<div class="flexoutter1"><div id="flexcontent1" class="flexblock1" onclick="openlinkwindow(this)"></div>';
		    	editstr += '<div class="flexsuboutter"><div id="flexcontent2" class="flexblock2" onclick="openlinkwindow(this)"></div>';
		    	editstr += '<div id="flexcontent3" class="flexblock2" onclick="openlinkwindow(this)"></div></div></div>';
		    	editstr += '<input type="file" id="tmpimgupper_1" accept="image/*" style="display: none;" onchange="initimage(this)" />';
		    	editstr += '<input type="file" id="tmpimgupper_2" accept="image/*" style="display: none;" onchange="initimage(this)" />';
		    	editstr += '<input type="file" id="tmpimgupper_3" accept="image/*" style="display: none;" onchange="initimage(this)" />';
	    	} else {
	    		var c6arr = flexinfo.DETAILS;
	    		
	    		editstr += '<div class="flexoutter1">';
	    		editstr += '<div id="flexcontent1" class="flexblock1" onclick="openlinkwindow(this)" ';
	    		if (c6arr[0].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c6arr[0].BLOCKLINK + '" ';
		    	}
		    	if (c6arr[0].BLOCKTYPE == 'text') {
		    		editstr += '>' + c6arr[0].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c6arr[0].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div>';
		    	editstr += '<div class="flexsuboutter">';
		    	editstr += '<div id="flexcontent2" class="flexblock2" onclick="openlinkwindow(this)" ';
		    	if (c6arr[1].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c6arr[1].BLOCKLINK + '" ';
		    	}
		    	if (c6arr[1].BLOCKTYPE == 'text') {
		    		editstr += '>' + c6arr[1].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c6arr[1].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div>';
		    	editstr += '<div id="flexcontent3" class="flexblock2" onclick="openlinkwindow(this)" ';
		    	if (c6arr[2].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c6arr[2].BLOCKLINK + '" ';
		    	}
		    	if (c6arr[2].BLOCKTYPE == 'text') {
		    		editstr += '>' + c6arr[2].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c6arr[2].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div></div></div>';
	    	}
	    	break;
        case '7':
        	if (ctype == '0') {
        	    $('body').append('<div class="blockselector" style="top: 25px; left: 5px;" onclick="blockedit(\'flexcontent1\')" onmouseover="bmover(\'flexcontent1\', this)" onmouseout="bmout(\'flexcontent1\', this)"><span><i class="fas fa-th-large"></i>1</span></div>');
	    	    $('body').append('<div class="blockselector" style="top: calc(3vw + 35px); left: 5px;" onclick="blockedit(\'flexcontent2\')" onmouseover="bmover(\'flexcontent2\', this)" onmouseout="bmout(\'flexcontent2\', this)"><span><i class="fas fa-th-large"></i>2</span></div>');
	    	    $('body').append('<div class="blockselector" style="top: calc(6vw + 45px); left: 5px;" onclick="blockedit(\'flexcontent3\')" onmouseover="bmover(\'flexcontent3\', this)" onmouseout="bmout(\'flexcontent3\', this)"><span><i class="fas fa-th-large"></i>3</span></div>');
	    	    editstr += '<div class="flexoutter1"><div id="flexcontent1" class="flexblock3" onclick="openlinkwindow(this)"></div>';
		    	editstr += '<div id="flexcontent2" class="flexblock1" onclick="openlinkwindow(this)"></div>';
		    	editstr += '<div id="flexcontent3" class="flexblock1" onclick="openlinkwindow(this)"></div></div>';
		    	editstr += '<input type="file" id="tmpimgupper_1" accept="image/*" style="display: none;" onchange="initimage(this)" />';
		    	editstr += '<input type="file" id="tmpimgupper_2" accept="image/*" style="display: none;" onchange="initimage(this)" />';
		    	editstr += '<input type="file" id="tmpimgupper_3" accept="image/*" style="display: none;" onchange="initimage(this)" />';
        	} else {
        		var c7arr = flexinfo.DETAILS;
        		
        		editstr += '<div class="flexoutter1">';
        		editstr += '<div id="flexcontent1" class="flexblock3" onclick="openlinkwindow(this)" ';
        		if (c7arr[0].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c7arr[0].BLOCKLINK + '" ';
		    	}
		    	if (c7arr[0].BLOCKTYPE == 'text') {
		    		editstr += '>' + c7arr[0].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c7arr[0].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div>';
		    	editstr += '<div id="flexcontent2" class="flexblock1" onclick="openlinkwindow(this)" ';
		    	if (c7arr[1].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c7arr[1].BLOCKLINK + '" ';
		    	}
		    	if (c7arr[1].BLOCKTYPE == 'text') {
		    		editstr += '>' + c7arr[1].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c7arr[1].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div>';
		    	editstr += '<div id="flexcontent3" class="flexblock1" onclick="openlinkwindow(this)" ';
		    	if (c7arr[2].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c7arr[2].BLOCKLINK + '" ';
		    	}
		    	if (c7arr[2].BLOCKTYPE == 'text') {
		    		editstr += '>' + c7arr[2].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c7arr[2].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div></div>';
        	}
	    	break;
	    case '8':
	    	if (ctype == '0') {
	    	    $('body').append('<div class="blockselector" style="top: 25px; left: 5px;" onclick="blockedit(\'flexcontent1\')" onmouseover="bmover(\'flexcontent1\', this)" onmouseout="bmout(\'flexcontent1\', this)"><span><i class="fas fa-th-large"></i>1</span></div>');
	    	    $('body').append('<div class="blockselector" style="top: calc(3vw + 35px); left: 5px;" onclick="blockedit(\'flexcontent2\')" onmouseover="bmover(\'flexcontent2\', this)" onmouseout="bmout(\'flexcontent2\', this)"><span><i class="fas fa-th-large"></i>2</span></div>');
	    	    $('body').append('<div class="blockselector" style="top: calc(6vw + 45px); left: 5px;" onclick="blockedit(\'flexcontent3\')" onmouseover="bmover(\'flexcontent3\', this)" onmouseout="bmout(\'flexcontent3\', this)"><span><i class="fas fa-th-large"></i>3</span></div>');
	    	    $('body').append('<div class="blockselector" style="top: calc(9vw + 55px); left: 5px;" onclick="blockedit(\'flexcontent4\')" onmouseover="bmover(\'flexcontent4\', this)" onmouseout="bmout(\'flexcontent4\', this)"><span><i class="fas fa-th-large"></i>4</span></div>');
	    	    $('body').append('<div class="blockselector" style="top: calc(12vw + 65px); left: 5px;" onclick="blockedit(\'flexcontent5\')" onmouseover="bmover(\'flexcontent5\', this)" onmouseout="bmout(\'flexcontent5\', this)"><span><i class="fas fa-th-large"></i>5</span></div>');
	    	    $('body').append('<div class="blockselector" style="top: calc(15vw + 75px); left: 5px;" onclick="blockedit(\'flexcontent6\')" onmouseover="bmover(\'flexcontent6\', this)" onmouseout="bmout(\'flexcontent6\', this)"><span><i class="fas fa-th-large"></i>6</span></div>');
	    	    editstr += '<div class="flexoutter1"><div class="flexsuboutter"><div id="flexcontent1" class="flexblock4" onclick="openlinkwindow(this)"></div>';
		    	editstr += '<div id="flexcontent2" class="flexblock4" onclick="openlinkwindow(this)"></div>';
		    	editstr += '<div id="flexcontent3" class="flexblock4" onclick="openlinkwindow(this)"></div></div>';
		    	editstr += '<div class="flexsuboutter"><div id="flexcontent4" class="flexblock4" onclick="openlinkwindow(this)"></div>';
		    	editstr += '<div id="flexcontent5" class="flexblock4" onclick="openlinkwindow(this)"></div>';
		    	editstr += '<div id="flexcontent6" class="flexblock4" onclick="openlinkwindow(this)"></div></div></div>';
		    	editstr += '<input type="file" id="tmpimgupper_1" accept="image/*" style="display: none;" onchange="initimage(this)" />';
		    	editstr += '<input type="file" id="tmpimgupper_2" accept="image/*" style="display: none;" onchange="initimage(this)" />';
		    	editstr += '<input type="file" id="tmpimgupper_3" accept="image/*" style="display: none;" onchange="initimage(this)" />';
		    	editstr += '<input type="file" id="tmpimgupper_4" accept="image/*" style="display: none;" onchange="initimage(this)" />';
		    	editstr += '<input type="file" id="tmpimgupper_5" accept="image/*" style="display: none;" onchange="initimage(this)" />';
		    	editstr += '<input type="file" id="tmpimgupper_6" accept="image/*" style="display: none;" onchange="initimage(this)" />';
	    	} else {
	    		var c8arr = flexinfo.DETAILS;
	    		
	    		editstr += '<div class="flexoutter1"><div class="flexsuboutter">';
	    		editstr += '<div id="flexcontent1" class="flexblock4" onclick="openlinkwindow(this)" ';
	    		if (c8arr[0].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c8arr[0].BLOCKLINK + '" ';
		    	}
		    	if (c8arr[0].BLOCKTYPE == 'text') {
		    		editstr += '>' + c8arr[0].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c8arr[0].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div>';
		    	editstr += '<div id="flexcontent2" class="flexblock4" onclick="openlinkwindow(this)" ';
		    	if (c8arr[1].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c8arr[1].BLOCKLINK + '" ';
		    	}
		    	if (c8arr[1].BLOCKTYPE == 'text') {
		    		editstr += '>' + c8arr[1].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c8arr[1].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div>';
		    	editstr += '<div id="flexcontent3" class="flexblock4" onclick="openlinkwindow(this)" ';
		    	if (c8arr[2].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c8arr[2].BLOCKLINK + '" ';
		    	}
		    	if (c8arr[2].BLOCKTYPE == 'text') {
		    		editstr += '>' + c8arr[2].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c8arr[2].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div></div>';
		    	editstr += '<div class="flexsuboutter">';
		    	editstr += '<div id="flexcontent4" class="flexblock4" onclick="openlinkwindow(this)" ';
		    	if (c8arr[3].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c8arr[3].BLOCKLINK + '" ';
		    	}
		    	if (c8arr[3].BLOCKTYPE == 'text') {
		    		editstr += '>' + c8arr[3].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c8arr[3].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div>';
		    	editstr += '<div id="flexcontent5" class="flexblock4" onclick="openlinkwindow(this)" ';
		    	if (c8arr[4].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c8arr[4].BLOCKLINK + '" ';
		    	}
		    	if (c8arr[4].BLOCKTYPE == 'text') {
		    		editstr += '>' + c8arr[4].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c8arr[4].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div>';
		    	editstr += '<div id="flexcontent6" class="flexblock4" onclick="openlinkwindow(this)" ';
		    	if (c8arr[5].BLOCKLINK != '') {
		    		editstr += 'data-url="' + c8arr[5].BLOCKLINK + '" ';
		    	}
		    	if (c8arr[5].BLOCKTYPE == 'text') {
		    		editstr += '>' + c8arr[5].BLOCKCONTENT;
		    	} else {
		    		editstr += '><img class="demoimgstyle" src="/lineResources/fleximgs/' + c8arr[5].BLOCKCONTENT + '.png" />';
		    	}
		    	editstr += '</div></div></div>';
	    	}
	    	break;
	}
	
	if (ctype == '0') {
		$('body').append('<div class="operationflexmsg" style="top: calc(100vh - 40px); left: calc(25vw - 270px);" onclick="flexmsgsaveonly(\'0\')"><span><i class="fas fa-save"></i>存檔</span></div>');
		$('body').append('<div class="operationflexmsg" style="top: calc(100vh - 40px); left: calc(25vw - 130px);" onclick="flexmsgsaveandsend()"><span><i class="fas fa-comment-alt"></i>發送</span></div>');
		$('body').append('<div class="operationflexmsg" style="top: calc(100vh - 40px); left: calc(25vw + 10px);" onclick="flexmsgreselect()"><span><i class="fas fa-retweet"></i>修正</span></div>');
		$('body').append('<div class="operationflexmsg" style="top: calc(100vh - 40px); left: calc(25vw + 150px);" onclick="flexmsgcancel()"><span><i class="fas fa-ban"></i>取消</span></div>');
	}
		
	editstr += '</div><div class="editarea"></div>';
	
	$('body').append(editstr);
	
	$('.contenttypeselector').css('display', 'none');
}

function bmover(blocknum, blockbtn) {
	$('#' + blocknum).css({
		'border-color': '#FF0000',
		'border-style': 'solid'
	});
	$(blockbtn).css({
		'border-color': '#FF0000',
		'color': '#FF0000'
	});
}

function bmout(blocknum, blockbtn) {
	if ($(blockbtn).css('display') != 'none') {
		$('#' + blocknum).css({
			'border-color': '#21BA39',
			'border-style': 'dashed'
		});
	}

	$(blockbtn).css({
		'border-color': '#FFF',
		'color': '#FFF'
	});
}

function blockedit(blocknum) {
	$('.blockselector').css('display', 'none');
	$('.operationflexmsg').css('display', 'none');
	$('#classnum').val(blocknum);
	$('.contenttypeselector').css('display', 'flex');
	$('.' + blocknum).css({
		'border-style': 'solid',
		'border-color': '#FF0000'
	});
}

function textcomp() {
	$('.operationflexmsg').css('display', 'none');
	$('.editarea').html('');
	var classtype;
	var cnval = $('#classnum').val();
	
	if ($('#' + cnval).hasClass('flexblock1') || $('#' + cnval).hasClass('flexblock3')) {
		classtype = 'textareaforflex1';
	} else if ($('#' + cnval).hasClass('flexblock2')) {
		classtype = 'textareaforflex2';
	} else {
		classtype = 'textareaforflex3';
	}
	
	var txtfstr = '<textarea class="form-control ' + classtype + '" name="textareaforflex" style="height: 50vh;" onkeyup="showthetext(this)"/>';
	
	txtfstr += '<input type="text" class="form-control flexlinkurltxt" placeholder="你可以將連結網址輸入於此" ';
	txtfstr += 'onkeyup="bindinglink(this)"/>';
	txtfstr += '<div class="flexbtnoutter"><button type="button" class="btn btn-primary flexqrybtn" onclick="readyflextext()">';
	txtfstr += '<span><i class="fas fa-check"></i>完成</span></button><button type="button" class="btn btn-warning flexqrybtn" ';
	txtfstr += 'onclick="cleartextcontent()" style="margin-left: 20px;">';
	txtfstr += '<span><i class="fas fa-eraser"></i>清除</span></button><button type="button" class="btn btn-danger flexqrybtn" ';
	txtfstr += 'onclick="canceleditor()" style="margin-left: 20px;"><span><i class="fas fa-ban"></i>取消</span></button></div>';
	
	$('.editarea').append(txtfstr);
	
	if (typeof $('#' + cnval).data('type') != 'undefined' && $('#' + cnval).data('type') == 'text') {
		$('textarea[name="textareaforflex"]').val($('#' + cnval).html().replace(/<br \/>/g, '\n'));
	}
	
	if (typeof $('#' + cnval).data('url') != 'undefined' && $('#' + cnval).data('url') != '') {
		$('.flexlinkurltxt').val($('#' + cnval).data('url'));
	}
}

function showthetext(sourcer) {
	var cnval = $('#classnum').val();
	
	$('#' + cnval).css('text-align', 'left');
	$('#' + cnval).html($(sourcer).val().replace(/\n/g, '<br />'));
}

function bindinglink(sourcer) {
	$('#' + $('#classnum').val()).data('url', $(sourcer).val());
}

function openlinkwindow(sourcer) {
	if (typeof $(sourcer).data('url') !== 'undefined' && $(sourcer).data('url') != '') {
		window.open($(sourcer).data('url'));
	}
}

function cleartextcontent() {
	var cnval = $('#classnum').val();
	
	$('textarea[name="textareaforflex"]').val('');
	$('.flexlinkurltxt').val('');
	$('#' + cnval).html('');
	$('#' + cnval).removeAttr('data-url');
}

function canceleditor() {
	var cnval = $('#classnum').val();
	
	$('.editarea').html('');
	$('#' + cnval).css({
		'border-style': 'dashed',
		'border-color': '#21BA39'
	});
	$('.contenttypeselector').css('display', 'none');
	$('.operationflexmsg').css('display', 'flex');
	$('.blockselector').css('display', 'flex');
	
	if (typeof $('#' + cnval).data('type') !== 'undefined' && $('#' + cnval).data('type') !== '') {
		if ($('#' + cnval).data('type') == 'text') {
			$('#' + cnval).css('text-align', 'left');
			$('#' + cnval).html($('#' + cnval).data('oritxt'));
		} else if ($('#' + cnval).data('type') == 'image') {
			if ($('#' + cnval).find('img').length == 0) {
				$('#' + cnval).data('type', '');
				$('#' + cnval).html('');
			}
		} else {
			$('#' + cnval).html('');
		}
	} else {
		$('#' + cnval).html('');
	}
}

function imagecomp() {
	$('.operationflexmsg').css('display', 'none');
	$('.editarea').html('');
	var cnval = $('#classnum').val();
	var classtype;
	
	if ($('#' + cnval).hasClass('flexblock1') || $('#' + cnval).hasClass('flexblock3')) {
		classtype = 'imgforflex1';
	} else if ($('#' + cnval).hasClass('flexblock2')) {
		classtype = 'imgforflex2';
	} else {
		classtype = 'imgforflex3';
	}
	
    var imgfstr = '<div class="' + classtype + '" onclick="openimgselect()"><img src="medias/uploadimg.png" class="fleximgstyle" /></div>';
    
    imgfstr += '<input type="text" class="form-control flexlinkurltxt" placeholder="你可以將連結網址輸入於此" ';
    imgfstr += 'onkeyup="bindinglink(this)"/>';
    imgfstr += '<div class="flexbtnoutter"><button type="button" class="btn btn-primary flexqrybtn" onclick="readyfleximg()">';
    imgfstr += '<span><i class="fas fa-check"></i>完成</span></button><button type="button" class="btn btn-danger flexqrybtn" ';
    imgfstr += 'onclick="canceleditor()" style="margin-left: 20px;"><span><i class="fas fa-ban"></i>取消</span></button></div>';
	
	$('.editarea').append(imgfstr);
	
	if (typeof $('#' + cnval).data('type') !== 'undefined' && $('#' + cnval).data('type') == 'image') {
		$('.fleximgstyle').attr('src', $('#' + cnval).find('img').attr('src'));
	}
	
	if (typeof $('#' + cnval).data('url') != 'undefined' && $('#' + cnval).data('url') != '') {
		$('.flexlinkurltxt').val($('#' + cnval).data('url'));
	}
}

function readyflextext() {
	var cnval = $('#classnum').val();
	
	if ($('textarea[name="textareaforflex"]').val() == '') {
		alert('沒有填入內容!');
	} else {
		$('#' + cnval).data('type', 'text');
		$('#' + cnval).data('oritxt', $('textarea[name="textareaforflex"]').val());
		$('#' + cnval).css({
			'border-style': 'dashed',
			'border-color': '#21BA39'
		});
		$('.contenttypeselector').css('display', 'none');
		$('.editarea').html('');
		$('.operationflexmsg').css('display', 'flex');
		$('.blockselector').css('display', 'flex');
	}
}

function openimgselect() {
	var cnval = $('#classnum').val();
	var blockint = cnval[cnval.length -1];
	$('#tmpimgupper_' + blockint).trigger('click');
}

function readyfleximg() {
	var cnval = $('#classnum').val();
	
	if ($('.fleximgstyle').attr('src') == 'medias/uploadimg.png') {
		alert('未選擇圖檔!');
	} else {		
		$('#' + cnval).data('type', 'image');
		$('#' + cnval).css({
			'border-style': 'dashed',
			'border-color': '#21BA39'
		});
		$('.contenttypeselector').css('display', 'none');
		$('.editarea').html('');
		$('.operationflexmsg').css('display', 'flex');
		$('.blockselector').css('display', 'flex');
	}
}

function clearimgcontent(classnum) {
	var cnval = $('#classnum').val();
	
	$('#' + cnval).html('');
	$('#' + cnval).removeAttr('data-url');
	$('.fleximgstyle').attr('src', 'medias/uploadimg.png');
	$('.flexlinkurltxt').val('');
}

function initimage(imgselector) {	
	if (imgselector.files && imgselector.files[0]) {
		var cnval = $('#classnum').val();
        var imgreader = new FileReader();

        imgreader.onload = function (e) {
        	$('#' + cnval).html('');
        	$('#' + cnval).css('text-align', 'center');
            $('.fleximgstyle').attr('src', e.target.result);
            $('#' + cnval).html('');
            $('#' + cnval).append('<img class="demoimgstyle" src="' + e.target.result + '" />');
            $('.editarea').find('.btn-danger').remove();
        };

        imgreader.readAsDataURL(imgselector.files[0]);
    }
}

function flexmsgsaveonly(opetype) {
	if ($('#flextitleeditbox').val() == '') {
		alert('主旨不可為空!!');
		return null;
	} else {
		var flexdata = new FormData();
		flexuid = generateGuid();
		
		flexdata.append('flexuid', flexuid);
		flexdata.append('flexmod', tmpselectnum);
		flexdata.append('flextitle', $('#flextitleeditbox').val());

		switch(tmpselectnum) {
		    case '1':
		    	if ($('#flexcontent1').html() == '' && $('#flexcontent1').find('img').length == 0) {
		    		alert('區塊1沒有內容!!');
		    		return null;
		    	} else {
		    		flexdata.append('block1type', $('#flexcontent1').data('type'));
			    	
			    	if ($('#flexcontent1').data('type') == 'text') {
			    		flexdata.append('block1content', $('#flexcontent1').html());
			    	} else {
			    		flexdata.append('block1image', ($('#tmpimgupper_1'))[0].files[0]);
			    		flexdata.append('block1content', flexuid + '_1');
			    	}
			    	
			    	if (typeof $('#flexcontent1').data('url') !== 'undefined' && $('#flexcontent1').data('url') != '') {
			    		flexdata.append('block1link', $('#flexcontent1').data('url'));
			    	} else {
			    		flexdata.append('block1link', '');
			    	}
		    	}
		    	break;
	        case '2':
	        case '3':
	        	if ($('#flexcontent1').html() == '' && $('#flexcontent1').find('img').length == 0) {
		    		alert('區塊1沒有內容!!');
		    		return null;
		    	} else {
		    		if ($('#flexcontent2').html() == '' && $('#flexcontent2').find('img').length == 0) {
			    		alert('區塊2沒有內容!!');
			    		return null;
			    	} else {
			    		flexdata.append('block1type', $('#flexcontent1').data('type'));
			    		flexdata.append('block2type', $('#flexcontent2').data('type'));
				    	
				    	if ($('#flexcontent1').data('type') == 'text') {
				    		flexdata.append('block1content', $('#flexcontent1').html());
				    	} else {
				    		flexdata.append('block1image', ($('#tmpimgupper_1'))[0].files[0]);
				    		flexdata.append('block1content', flexuid + '_1');
				    	}
				    	
				    	if ($('#flexcontent2').data('type') == 'text') {
				    		flexdata.append('block2content', $('#flexcontent2').html());
				    	} else {
				    		flexdata.append('block2image', ($('#tmpimgupper_2'))[0].files[0]);
				    		flexdata.append('block2content', flexuid + '_2');
				    	}
				    	
				    	if (typeof $('#flexcontent1').data('url') !== 'undefined' && $('#flexcontent1').data('url') != '') {
				    		flexdata.append('block1link', $('#flexcontent1').data('url'));
				    	} else {
				    		flexdata.append('block1link', '');
				    	}
				    	
				    	if (typeof $('#flexcontent2').data('url') !== 'undefined' && $('#flexcontent2').data('url') != '') {
				    		flexdata.append('block2link', $('#flexcontent2').data('url'));
				    	} else {
				    		flexdata.append('block2link', '');
				    	}
			    	}
		    	}
		    	break;
	        case '4':
	        case '6':
	        case '7':
	        	if ($('#flexcontent1').html() == '' && $('#flexcontent1').find('img').length == 0) {
		    		alert('區塊1沒有內容!!');
		    		return null;
		    	} else {
		    		if ($('#flexcontent2').html() == '' && $('#flexcontent2').find('img').length == 0) {
			    		alert('區塊2沒有內容!!');
			    		return null;
			    	} else {
			    		if ($('#flexcontent3').html() == '' && $('#flexcontent3').find('img').length == 0) {
				    		alert('區塊3沒有內容!!');
				    		return null;
				    	} else {
				    		flexdata.append('block1type', $('#flexcontent1').data('type'));
				    		flexdata.append('block2type', $('#flexcontent2').data('type'));
				    		flexdata.append('block3type', $('#flexcontent3').data('type'));
					    	
				    		if ($('#flexcontent1').data('type') == 'text') {
					    		flexdata.append('block1content', $('#flexcontent1').html());
					    	} else {
					    		flexdata.append('block1image', ($('#tmpimgupper_1'))[0].files[0]);
					    		flexdata.append('block1content', flexuid + '_1');
					    	}
				    		
					    	if ($('#flexcontent2').data('type') == 'text') {
					    		flexdata.append('block2content', $('#flexcontent2').html());
					    	} else {
					    		flexdata.append('block2image', ($('#tmpimgupper_2'))[0].files[0]);
					    		flexdata.append('block2content', flexuid + '_2');
					    	}
					    	
					    	if ($('#flexcontent3').data('type') == 'text') {
					    		flexdata.append('block3content', $('#flexcontent3').html());
					    	} else {
					    		flexdata.append('block3image', ($('#tmpimgupper_3'))[0].files[0]);
					    		flexdata.append('block3content', flexuid + '_3');
					    	}
					    	
					    	if (typeof $('#flexcontent1').data('url') !== 'undefined' && $('#flexcontent1').data('url') != '') {
					    		flexdata.append('block1link', $('#flexcontent1').data('url'));
					    	} else {
					    		flexdata.append('block1link', '');
					    	}
					    	
					    	if (typeof $('#flexcontent2').data('url') !== 'undefined' && $('#flexcontent2').data('url') != '') {
					    		flexdata.append('block2link', $('#flexcontent2').data('url'));
					    	} else {
					    		flexdata.append('block2link', '');
					    	}
					    	
					    	if (typeof $('#flexcontent3').data('url') !== 'undefined' && $('#flexcontent3').data('url') != '') {
					    		flexdata.append('block3link', $('#flexcontent3').data('url'));
					    	} else {
					    		flexdata.append('block3link', '');
					    	}
				    	}
			    	}
		    	}
		    	break;
	        case '5':
	        	if ($('#flexcontent1').html() == '' && $('#flexcontent1').find('img').length == 0) {
		    		alert('區塊1沒有內容!!');
		    		return null;
		    	} else {
		    		if ($('#flexcontent2').html() == '' && $('#flexcontent2').find('img').length == 0) {
			    		alert('區塊2沒有內容!!');
			    		return null;
			    	} else {
			    		if ($('#flexcontent3').html() == '' && $('#flexcontent3').find('img').length == 0) {
				    		alert('區塊3沒有內容!!');
				    		return null;
				    	} else {
				    		if ($('#flexcontent4').html() == '' && $('#flexcontent4').find('img').length == 0) {
					    		alert('區塊4沒有內容!!');
					    		return null;
					    	} else {
					    		flexdata.append('block1type', $('#flexcontent1').data('type'));
					    		flexdata.append('block2type', $('#flexcontent2').data('type'));
					    		flexdata.append('block3type', $('#flexcontent3').data('type'));
					    		flexdata.append('block4type', $('#flexcontent4').data('type'));
						    	
					    		if ($('#flexcontent1').data('type') == 'text') {
						    		flexdata.append('block1content', $('#flexcontent1').html());
						    	} else {
						    		flexdata.append('block1image', ($('#tmpimgupper_1'))[0].files[0]);
						    		flexdata.append('block1content', flexuid + '_1');
						    	}
					    		
						    	if ($('#flexcontent2').data('type') == 'text') {
						    		flexdata.append('block2content', $('#flexcontent2').html());
						    	} else {
						    		flexdata.append('block2image', ($('#tmpimgupper_2'))[0].files[0]);
						    		flexdata.append('block2content', flexuid + '_2');
						    	}
						    	
						    	if ($('#flexcontent3').data('type') == 'text') {
						    		flexdata.append('block3content', $('#flexcontent3').html());
						    	} else {
						    		flexdata.append('block3image', ($('#tmpimgupper_3'))[0].files[0]);
						    		flexdata.append('block3content', flexuid + '_3');
						    	}
						    	
						    	if ($('#flexcontent4').data('type') == 'text') {
						    		flexdata.append('block4content', $('#flexcontent4').html());
						    	} else {
						    		flexdata.append('block4image', ($('#tmpimgupper_4'))[0].files[0]);
						    		flexdata.append('block4content', flexuid + '_4');
						    	}
						    	
						    	if (typeof $('#flexcontent1').data('url') !== 'undefined' && $('#flexcontent1').data('url') != '') {
						    		flexdata.append('block1link', $('#flexcontent1').data('url'));
						    	} else {
						    		flexdata.append('block1link', '');
						    	}
						    	
						    	if (typeof $('#flexcontent2').data('url') !== 'undefined' && $('#flexcontent2').data('url') != '') {
						    		flexdata.append('block2link', $('#flexcontent2').data('url'));
						    	} else {
						    		flexdata.append('block2link', '');
						    	}
						    	
						    	if (typeof $('#flexcontent3').data('url') !== 'undefined' && $('#flexcontent3').data('url') != '') {
						    		flexdata.append('block3link', $('#flexcontent3').data('url'));
						    	} else {
						    		flexdata.append('block3link', '');
						    	}
						    	
						    	if (typeof $('#flexcontent4').data('url') !== 'undefined' && $('#flexcontent4').data('url') != '') {
						    		flexdata.append('block4link', $('#flexcontent4').data('url'));
						    	} else {
						    		flexdata.append('block4link', '');
						    	}
					    	}
				    	}
			    	}
		    	}
		    	break;
	        case '8':
	        	if ($('#flexcontent1').html() == '' && $('#flexcontent1').find('img').length == 0) {
		    		alert('區塊1沒有內容!!');
		    		return null;
		    	} else {
		    		if ($('#flexcontent2').html() == '' && $('#flexcontent2').find('img').length == 0) {
			    		alert('區塊2沒有內容!!');
			    		return null;
			    	} else {
			    		if ($('#flexcontent3').html() == '' && $('#flexcontent3').find('img').length == 0) {
				    		alert('區塊3沒有內容!!');
				    		return null;
				    	} else {
				    		if ($('#flexcontent4').html() == '' && $('#flexcontent4').find('img').length == 0) {
					    		alert('區塊4沒有內容!!');
					    		return null;
					    	} else {
					    		if ($('#flexcontent5').html() == '' && $('#flexcontent5').find('img').length == 0) {
						    		alert('區塊5沒有內容!!');
						    		return null;
						    	} else {
						    		if ($('#flexcontent6').html() == '' && $('#flexcontent6').find('img').length == 0) {
							    		alert('區塊6沒有內容!!');
							    		return null;
							    	} else {
							    		flexdata.append('block1type', $('#flexcontent1').data('type'));
							    		flexdata.append('block2type', $('#flexcontent2').data('type'));
							    		flexdata.append('block3type', $('#flexcontent3').data('type'));
							    		flexdata.append('block4type', $('#flexcontent4').data('type'));
							    		flexdata.append('block5type', $('#flexcontent5').data('type'));
							    		flexdata.append('block6type', $('#flexcontent6').data('type'));
								    	
							    		if ($('#flexcontent1').data('type') == 'text') {
								    		flexdata.append('block1content', $('#flexcontent1').html());
								    	} else {
								    		flexdata.append('block1image', ($('#tmpimgupper_1'))[0].files[0]);
								    		flexdata.append('block1content', flexuid + '_1');
								    	}
							    		
								    	if ($('#flexcontent2').data('type') == 'text') {
								    		flexdata.append('block2content', $('#flexcontent2').html());
								    	} else {
								    		flexdata.append('block2image', ($('#tmpimgupper_2'))[0].files[0]);
								    		flexdata.append('block2content', flexuid + '_2');
								    	}
								    	
								    	if ($('#flexcontent3').data('type') == 'text') {
								    		flexdata.append('block3content', $('#flexcontent3').html());
								    	} else {
								    		flexdata.append('block3image', ($('#tmpimgupper_3'))[0].files[0]);
								    		flexdata.append('block3content', flexuid + '_3');
								    	}
								    	
								    	if ($('#flexcontent4').data('type') == 'text') {
								    		flexdata.append('block4content', $('#flexcontent4').html());
								    	} else {
								    		flexdata.append('block4image', ($('#tmpimgupper_4'))[0].files[0]);
								    		flexdata.append('block4content', flexuid + '_4');
								    	}
								    	
								    	if ($('#flexcontent5').data('type') == 'text') {
								    		flexdata.append('block5content', $('#flexcontent5').html());
								    	} else {
								    		flexdata.append('block5image', ($('#tmpimgupper_5'))[0].files[0]);
								    		flexdata.append('block5content', flexuid + '_5');
								    	}
								    	
								    	if ($('#flexcontent6').data('type') == 'text') {
								    		flexdata.append('block6content', $('#flexcontent6').html());
								    	} else {
								    		flexdata.append('block6image', ($('#tmpimgupper_6'))[0].files[0]);
								    		flexdata.append('block6content', flexuid + '_6');
								    	}
								    	
								    	if (typeof $('#flexcontent1').data('url') !== 'undefined' && $('#flexcontent1').data('url') != '') {
								    		flexdata.append('block1link', $('#flexcontent1').data('url'));
								    	} else {
								    		flexdata.append('block1link', '');
								    	}
								    	
								    	if (typeof $('#flexcontent2').data('url') !== 'undefined' && $('#flexcontent2').data('url') != '') {
								    		flexdata.append('block2link', $('#flexcontent2').data('url'));
								    	} else {
								    		flexdata.append('block2link', '');
								    	}
								    	
								    	if (typeof $('#flexcontent3').data('url') !== 'undefined' && $('#flexcontent3').data('url') != '') {
								    		flexdata.append('block3link', $('#flexcontent3').data('url'));
								    	} else {
								    		flexdata.append('block3link', '');
								    	}
								    	
								    	if (typeof $('#flexcontent4').data('url') !== 'undefined' && $('#flexcontent4').data('url') != '') {
								    		flexdata.append('block4link', $('#flexcontent4').data('url'));
								    	} else {
								    		flexdata.append('block4link', '');
								    	}
								    	
								    	if (typeof $('#flexcontent5').data('url') !== 'undefined' && $('#flexcontent5').data('url') != '') {
								    		flexdata.append('block5link', $('#flexcontent5').data('url'));
								    	} else {
								    		flexdata.append('block5link', '');
								    	}
								    	
								    	if (typeof $('#flexcontent6').data('url') !== 'undefined' && $('#flexcontent6').data('url') != '') {
								    		flexdata.append('block6link', $('#flexcontent6').data('url'));
								    	} else {
								    		flexdata.append('block6link', '');
								    	}
							    	}
						    	}
					    	}
				    	}
			    	}
		    	}
		    	break;
		}
		
		$('.blockselector').css('display', 'none');
		$('.contenttypeselector').css('display', 'none');
		$('.flextitleoutterdiv').css('display', 'none');
		$('#flextitleeditbox').prop('disabled', true);
		$('.demoarea').css('display', 'none');
		$('.operationflexmsg').css('display', 'none');
		$('.editarea').html('');
		$('.editarea').css('display', 'none');
		$('body').append('<div id="flexprocessgif" style="position: fixed; top: calc(50vh - 50px); left: calc(50vw - 50px); z-index:999;"><img src="medias/process.gif" width="100px" height="100px" /></div>');
		
		$.ajax({
		    url: '/saveflexmessage',
		    type: 'POST',
		    cache: false,
		    data: flexdata,
		    processData: false,
		    contentType: false
		}).done(function(res) {
			$('#flexprocessgif').remove();

			if (res == '') {
				alert('訊息儲存失敗!!');
				flexmsgcancel();
			} else {
				if (opetype == '1') {
					alert('儲存完成，此筆訊息目前不可再進行修改!!');
					$('.flextitleoutterdiv').css('display', '');
					$('.demoarea').css('display', 'flex');
					$('.editarea').css('display', 'flex');
					
					flexinfo = JSON.parse(res);
					
					createSendFollowers('0');
				} else {
					alert('訊息儲存完成!!');
					flexmsgcancel();
				}
			}
		}).fail(function(res) {
			$('#flexprocessgif').remove();
			alert('訊息儲存失敗!!');
			flexmsgcancel();
		});
	}
}

function flexmsgsaveandsend() {
	var saveresult = flexmsgsaveonly('1');
}

function createSendFollowers(ftype) {
	var sflstr = '<div id="sendopeoutterdiv"><div class="flssearchdiv btn-group"><button id="recflsbtn" class="btn btn-info" onclick="showslsselector()">';
	
	sflstr += '<span><i class="far fa-user-circle"></i>搜尋聯絡人</span></button>';
	sflstr += '<button id="postgrouplistbtn" class="btn btn-primary" onclick="showgrouplist()">';
	sflstr += '<span><i class="fas fa-clipboard-list"></i>選擇發送名單</span></button>';
	sflstr += '</div><div id="recflsdiv"></div>';
	sflstr += '<div class="btn-group flsopedndiv"><button id="sendflsbtn" class="btn btn-success flexqrybtn leftbuttongrp" onclick="sendflexmessage()"><span>';
	sflstr += '<i class="far fa-paper-plane"></i>送出</span></button>';
	sflstr += '<button id="deleteflsbtn" class="btn btn-danger flexqrybtn" onclick="deletecontactinlist()"><span>';
	sflstr += '<i class="fas fa-trash-alt"></i>刪除</span></button>';
	sflstr += '<button id="canflsbtn" class="btn btn-warning flexqrybtn" onclick="flexmsgcancel()">';
	sflstr += '<span><i class="fas fa-ban"></i>取消</span></button></div></div>';
	
	$('.editarea').append(sflstr);
}

function createlinecontactssel() {
    var flsstr = '<div id="fwlseloutterdiv" style="width: 0; opacity: 0;">';
	
    flsstr += '<div class="flssearchdiv">';
    flsstr += '<select id="flsconsel1" class="form-control flsconsel"></select>';
	flsstr += '<input type="text" id="flskeytxt1" class="form-control" placeholder="Please Enter The Keyword" />';
	flsstr += '</div >';
	flsstr += '<div class="flssearchdiv">';
    flsstr += '<select id="flsconsel2" class="form-control flsconsel"></select>';
	flsstr += '<input type="text" id="flskeytxt2" class="form-control" placeholder="Please Enter The Keyword" />';
	flsstr += '</div >';
	flsstr += '<div class="flssearchdiv">';
    flsstr += '<select id="flsconsel3" class="form-control flsconsel"></select>';
	flsstr += '<input type="text" id="flskeytxt3" class="form-control" placeholder="Please Enter The Keyword" />';
	flsstr += '</div >';
	flsstr += '<div class="btn-group flsopeupdiv">';
	flsstr += '<button id="recflsbtn" class="btn btn-info flexqrybtn" style="margin-left: -1px;" onclick="querylinecontacts()">';
	flsstr += '<span><i class="fas fa-search"></i>查詢</span></button></button>';
	flsstr += '<button id="sendflsbtn" class="btn btn-success flexqrybtn" onclick="addlinecontacts()"><span>';
	flsstr += '<i class="fas fa-check"></i>確認</span></button>';
	flsstr += '<button id="canflsbtn" class="btn btn-warning flexqrybtn" onclick="flsquerycancel(\'fwlseloutterdiv\')">';
	flsstr += '<span><i class="fas fa-ban"></i>取消</span></button></div>';
	flsstr += '<div id="qryflsdiv"></div></div>';
	flsstr += '<img src="medias/load.gif" width="300px" height="300px" id="contactsqryloadimg" style="display: none; opacity: 0;"/>';
		
	$('.editarea').append(flsstr);
	
	$('#flsconsel1').change(function() {
		var orival = $('#flsconsel1').attr('data-orivalue');
		var oritxt = $('#flsconsel1').attr('data-oritext');
		var newval = $('#flsconsel1').find(':selected').val();
		var newtxt = $('#flsconsel1').find(':selected').text();

		$('#flsconsel2').find('option').each(function() {
	    	if (this.value == newval) {
	    		$(this).remove();
	    		return false;
	    	}
	    });
	    $('#flsconsel3').find('option').each(function() {
	    	if (this.value == newval) {
	    		$(this).remove();
	    		return false;
	    	}
	    });
	    $('#flsconsel2').append($('<option></option>').attr('value', orival).text(oritxt));
	    $('#flsconsel3').append($('<option></option>').attr('value', orival).text(oritxt));
	    
	    $('#flsconsel1').attr('orivalue', newval);
	    $('#flsconsel1').attr('oritext', newtxt);
	});
	
    $('#flsconsel2').change(function() {
    	var orival = $('#flsconsel2').attr('data-orivalue');
		var oritxt = $('#flsconsel2').attr('data-oritext');
		var newval = $('#flsconsel2').find(':selected').val();
		var newtxt = $('#flsconsel2').find(':selected').text();
		
		$('#flsconsel1').find('option').each(function() {
	    	if (this.value == newval) {
	    		$(this).remove();
	    		return false;
	    	}
	    });
	    $('#flsconsel3').find('option').each(function() {
	    	if (this.value == newval) {
	    		$(this).remove();
	    		return false;
	    	}
	    });
	    $('#flsconsel1').append($('<option></option>').attr('value', orival).text(oritxt));
	    $('#flsconsel3').append($('<option></option>').attr('value', orival).text(oritxt));
	    
	    $('#flsconsel2').attr('orivalue', newval);
	    $('#flsconsel2').attr('oritext', newtxt);
	});
    
    $('#flsconsel3').change(function() {
    	var orival = $('#flsconsel3').attr('data-orivalue');
		var oritxt = $('#flsconsel3').attr('data-oritext');
		var newval = $('#flsconsel3').find(':selected').val();
		var newtxt = $('#flsconsel3').find(':selected').text();
		
		$('#flsconsel1').find('option').each(function() {
	    	if (this.value == newval) {
	    		$(this).remove();
	    		return false;
	    	}
	    });
	    $('#flsconsel2').find('option').each(function() {
	    	if (this.value == newval) {
	    		$(this).remove();
	    		return false;
	    	}
	    });
	    $('#flsconsel1').append($('<option></option>').attr('value', orival).text(oritxt));
	    $('#flsconsel2').append($('<option></option>').attr('value', orival).text(oritxt));
	    
	    $('#flsconsel3').attr('orivalue', newval);
	    $('#flsconsel3').attr('oritext', newtxt);
	});
	
	$.get('getcontactsconds', function(result) {
        if (result != null && result != '') {
        	if (condslist === undefined || condslist.length == 0) {
            	condslist = JSON.parse(result);
        	}
        	
        	if (condslist.length > 0) {
            	for (var d = 0; d < condslist.length; d++) {
            		if (d != 1 && d != 2) {
            			$('#flsconsel1').append('<option value="' + condslist[d].VALUE + '">' + condslist[d].DISPLAY + '</option>');
            			if (d == 0) {
            				$('#flsconsel1').attr('data-oritext', condslist[d].DISPLAY);
            				$('#flsconsel1').attr('data-orivalue', condslist[d].VALUE);
            			}
            		}
            		
            		if (d != 0 && d != 2) {
            			$('#flsconsel2').append('<option value="' + condslist[d].VALUE + '">' + condslist[d].DISPLAY + '</option>');
            			if (d == 1) {
            				$('#flsconsel2').attr('data-oritext', condslist[d].DISPLAY);
            				$('#flsconsel2').attr('data-orivalue', condslist[d].VALUE);
            			}
            		}
            		
                    if (d != 0 && d != 1) {
                    	$('#flsconsel3').append('<option value="' + condslist[d].VALUE + '">' + condslist[d].DISPLAY + '</option>');
                    	if (d == 2) {
                    		$('#flsconsel3').attr('data-oritext', condslist[d].DISPLAY);
            				$('#flsconsel3').attr('data-orivalue', condslist[d].VALUE);
            			}
            		}
            	}
        	}
        }
    });
}

function createlistselector() {
    var lssstr = '<div id="lssoroutterdiv" style="width: 0; opacity: 0;">';
	
    lssstr += '<div class="lssoearchdiv">';
    lssstr += '<label>群組類別</label><select id="lssogrptpsel" class="form-control"></select>';
    lssstr += '</div><div class="lssoearchdiv">';
    lssstr += '<label>群組名稱</label><select id="lssogrpnasel" class="form-control"></select>';
	lssstr += '</div><div id="lssoflsdiv"></div>';
	lssstr += '<div class="btn-group lssopeupdiv">';
	lssstr += '<button id="sendlssbtn" class="btn btn-success flexqrybtn" onclick="addlistcontacts()"><span>';
	lssstr += '<i class="fas fa-check"></i>確認</span></button>';
	lssstr += '<button id="canlssbtn" class="btn btn-warning flexqrybtn" onclick="flsquerycancel(\'lssoroutterdiv\')">';
	lssstr += '<span><i class="fas fa-ban"></i>取消</span></button></div></div>';
	lssstr += '<img src="medias/load.gif" width="300px" height="300px" id="lssoqryloadimg" style="display: none; opacity: 0;"/>';
		
	$('.editarea').append(lssstr);
	
	$('#lssogrptpsel').append('<option value="NONE">請選擇群組類別</option>');
	$('#lssogrptpsel').append('<option value="ALL">所有類別</option>');
		
	$('#lssogrptpsel').change(function() {
		if ($('#lssogrptpsel').val() === 'NONE') {
			$('#lssogrpnasel').empty();
			$('#lssoflsdiv').html('');
		} else {
			$('#lssoroutterdiv').animate({
				'opacity': '0',
				'width': '0'
			}, 300, function() {
				$('#lssoroutterdiv').css('display', 'none');
				$('#lssoqryloadimg').css('display', '');
				
				$('#lssoqryloadimg').animate({
			    	'opacity': '1'
			    }, 300, function() {
			    	var typesel;
			    	
			    	if ($('#lssogrptpsel').val() === undefined) {
			    		alert('您未選擇名單類型!!');
			    	} else {
			    		if ($('#lssogrptpsel').val() === 'ALL') {
		    				typesel = '';
		    			} else {
		    				typesel = $('#lssogrptpsel').val();
		    			}
		    			
		    			$.get('getpostlist', {TYPE: typesel}, function(result) {
							
					        if (result != null && result != '') {
					        	var listlist = JSON.parse(result);
					        	
					        	if (listlist.length > 0) {
					        		$('#lssogrpnasel').append('<option value="none">請選擇群組</option>');
					        		
					            	for (var t = 0; t < listlist.length; t++) {
					            		$('#lssogrpnasel').append('<option value="' + listlist[t].ID + '">' + listlist[t].NAME + '</option>');
					            	}
					        	}
					        }
					        
					        $('#lssoqryloadimg').animate({
					        	'opacity': '0'
					        }, 300, function() {
					        	$('#lssoqryloadimg').css('display', 'none');
					        	$('#lssoroutterdiv').css('display', '');
					        	
							    $('#lssoroutterdiv').animate({
									'opacity': '1',
									'width': '40vw'
								}, 300);
					        });
					    });
			    	}
			    });
			});
		}
	});
	
    $('#lssogrpnasel').change(function() {
    	if ($('#lssogrpnasel').val() === 'NONE') {
			$('#lssoflsdiv').html('');
		} else {
			$('#lssoroutterdiv').animate({
				'opacity': '0',
				'width': '0'
			}, 300, function() {
				$('#lssoroutterdiv').css('display', 'none');
				$('#lssoqryloadimg').css('display', '');
				
				$('#lssoqryloadimg').animate({
			    	'opacity': '1'
			    }, 300, function() {
			    	var listsel;
			    	
			    	if ($('#lssogrpnasel').val() === undefined) {
			    		alert('您未選擇名單!!');
			    	} else {
			    		listsel = $('#lssogrpnasel').val();
		    			
		    			$.get('getcontactsinlist', {ID: listsel}, function(result) {
							$('#lssoflsdiv').html('');

					        if (result != null && result != '') {
					        	var contlist = JSON.parse(result);
					        	
					        	if (contlist.length > 0) {
					        		for (var s = 0; s < contlist.length; s++) {
					        			$('#lssoflsdiv').append('<div class="continlistblock" data-lineuid="' + contlist[s].LINE_UID + '"><label>' + contlist[s].COMPANY + '-' + contlist[s].CONTACT_NAME + '</label></div>');
					        		}
					        	}
					        }
					        
					        $('#lssoqryloadimg').animate({
					        	'opacity': '0'
					        }, 300, function() {
					        	$('#lssoqryloadimg').css('display', 'none');
					        	$('#lssoroutterdiv').css('display', '');
					        	
							    $('#lssoroutterdiv').animate({
									'opacity': '1',
									'width': '40vw'
								}, 300);
					        });
					    });
			    	}
			    });
			});
		}
	});
}

function showslsselector() {
	$('#sendopeoutterdiv').animate({
		'opacity': '0',
		'width': '0'
	}, 500, function() {
		$('#sendopeoutterdiv').css('display', 'none');
		createlinecontactssel();
		
		$('#fwlseloutterdiv').animate({
			'opacity': '1',
			'width': '40vw'
		}, 500);
	});
}

function showgrouplist() {
	$('#sendopeoutterdiv').animate({
		'opacity': '0',
		'width': '0'
	}, 300, function() {
		$('#sendopeoutterdiv').css('display', 'none');
	    createlistselector();
	    
	    $('#lssoroutterdiv').css('display', 'none');
	    $('#lssoqryloadimg').css('display', '');
	    
	    $('#lssoqryloadimg').animate({
	    	'opacity': '1'
	    }, 300, function() {
	    	$.get('getpostlisttype', function(result) {
				
		        if (result != null && result != '') {
		        	var typelist = JSON.parse(result);
		        	
		        	if (typelist.length > 0) {
		            	for (var t = 0; t < typelist.length; t++) {
		            		$('#lssogrptpsel').append('<option value="' + typelist[t].TYPE + '">' + typelist[t].TYPE + '</option>');
		            	}
		        	}
		        }
		        
		        $('#lssoqryloadimg').animate({
		        	'opacity': '0'
		        }, 300, function() {
		        	$('#lssoqryloadimg').css('display', 'none');
		        	$('#lssoroutterdiv').css('display', '');
		        	
				    $('#lssoroutterdiv').animate({
						'opacity': '1',
						'width': '40vw'
					}, 300);
		        });
		    });
	    });
	});
}

function addlinecontacts() {	
	if ($('#qryflsdiv input[type="checkbox"]:checked').length > 0) {
		if (sendlist.length > 0) {
			$('#recflsdiv').prepend('<div class="contactsinfoblock"><input type="checkbox" value="ALL" onchange="contsallchange(this)">ALL</div>');
		}
		
		$('#qryflsdiv input[type="checkbox"]:checked').each(function(){
			if ($(this).val() != 'ALL') {
				$(this).prop('checked', false);
				$('#recflsdiv').append($(this).parent());
				sendlist.push($(this).val());
			}
		});
		
		$('#fwlseloutterdiv').animate({
			'opacity': '0',
			'width': '0'
		}, 500, function() {
			$('#fwlseloutterdiv').remove();
			$('#sendopeoutterdiv').css('display', '');
			
			$('#sendopeoutterdiv').animate({
				'opacity': '1',
				'width': '40vw'
			}, 500);
		});
	} else {
		alert('您未選擇任何聯絡人!!');
	}
}

function addlistcontacts() {
	if (sendlist.length > 0) {
		$('#recflsdiv').prepend('<div class="contactsinfoblock"><input type="checkbox" value="ALL" onchange="contsallchange(this)">ALL</div>');
	}
	
	$('#lssoflsdiv div').each(function() {
		$('#recflsdiv').append('<div class="contactsinfoblock"><input type="checkbox" value="' + $(this).attr('data-lineuid') + '">' + $(this).find('label').text() + '</div>');
		sendlist.push($(this).val());
	});
	
	$('#lssoroutterdiv').animate({
		'opacity': '0',
		'width': '0'
	}, 500, function() {
		$('#lssoroutterdiv').remove();
		$('#sendopeoutterdiv').css('display', '');
		
		$('#sendopeoutterdiv').animate({
			'opacity': '1',
			'width': '40vw'
		}, 500);
	});
}

function deletecontactinlist() {
	if ($('#recflsdiv input[type="checkbox"][value!="ALL"]:checked').length > 0) {
		$('#recflsdiv input[type="checkbox"][value!="ALL"]:checked').each(function(){
			for(var i = 0; i < sendlist.length; i++){ 
			    if (sendlist[i] == $(this).val()) {
			        sendlist.splice(i, 1); 
				    break;
			    }
			}
			
			$(this).parent().remove();
			
			if ($('#recflsdiv input[type="checkbox"][value!="ALL"]').length < 2) {
				$('#recflsdiv input[type="checkbox"][value="ALL"]').parent().remove();
			}
		});
	} else {
		alert('您未選擇任何聯絡人!!');
	}
}

function sendflexmessage() {
	if (sendlist.length > 0) {
		var contactsarr = [];
		
		$('#recflsdiv input[type="checkbox"]').each(function(){			
			contactsarr.push($(this).val());
		});
		
		var flexobj = new Object();
		
		flexobj.UID = flexuid;
		flexobj.FLEXOBJSTR = flexinfo.FLEXOBJSTR;
		
		var contarr = [];
		
		for (var t = 0; t < sendlist.length; t++) {
			var contobj = new Object();
			
			contobj.FOLLOWERID = sendlist[t];
			
			contarr.push(contobj);
		}
		
		flexobj.FOLLOWERSLIST = contarr;
		
		$('.flextitleoutterdiv').css('display', 'none');
		$('.demoarea').css('display', 'none');
		$('.editarea').css('display', 'none');
		$('body').append('<div id="flexprocessgif" style="position: fixed; top: calc(50vh - 50px); left: calc(50vw - 50px); z-index:999;"><img src="medias/process.gif" width="100px" height="100px" /></div>');
		
		$.post('sendflexforcontacts', {SENDINFO: JSON.stringify(flexobj)}, function(result) {
			$('#flexprocessgif').remove();
			
			if (result == 'OK') {
				alert('訊息傳送成功!!');
			} else {
				alert('訊息傳送失敗!!');
			}
			
			flexmsgcancel();
		});
	} else {
		alert('您未選擇任何聯絡人!!');
	}
}

function querylinecontacts() {	
	$('#fwlseloutterdiv').animate({
		opacity: 0
	}, 500, function() {
		$('#fwlseloutterdiv').css('display', 'none');
		$('#contactsqryloadimg').css('display', '');
		$('#contactsqryloadimg').animate({
			opacity: 1
		}, 500, function() {
			var pararr = new Array();
			
			if ($('#flskeytxt1').val() != '') {
				var parobj1 = new Object();
				parobj1.column = $('#flsconsel1').find(':selected').val();
				parobj1.value = $('#flskeytxt1').val();
				
				pararr.push(parobj1);
			}
			
			if ($('#flskeytxt2').val() != '') {
				var parobj2 = new Object();
				parobj2.column = $('#flsconsel2').find(':selected').val();
				parobj2.value = $('#flskeytxt2').val();
				
				pararr.push(parobj2);
			}
			
			if ($('#flskeytxt3').val() != '') {
				var parobj3 = new Object();
				parobj3.column = $('#flsconsel3').find(':selected').val();
				parobj3.value = $('#flskeytxt3').val();
				
				pararr.push(parobj3);
			}
			
			$('#qryflsdiv').html('');
			
			$.get('getlunecontacts', {KEYWORD: JSON.stringify(pararr)}, function(result) {
		        if (result != null && result != '') {
		        	var resArr = JSON.parse(result);
		        	
		        	if (resArr.length > 0) {
		            	var contactsList = [];
		            	
		            	if (sendlist.length > 0) {
		            		for (var r = 0; r < resArr.length; r++) {
		            			var reapcnt = 0;
		            			
		                		for (var e = 0; e < sendlist.length; e++) {
		                			if (sendlist[e] == resArr[r].LINE_UID) {
		                				reapcnt = 1;
		                				break;
		                			}
		                		}
		                		
		                		if (reapcnt == 0) {
		                			contactsList.push(resArr[r]);
		                		}
		                	}
		            	} else {
		            		contactsList = resArr;
		            	}           	

		            	if (contactsList.length > 0) {
		                	if (contactsList.length > 1) {
		                        $('#qryflsdiv').append('<div class="contactsinfoblock"><input type="checkbox" value="ALL" onchange="contsallchange(this)">ALL');
		                	}
		                	
		                	for (var c = 0; c < contactsList.length; c++) {
		                		var cinfd = '<div class="contactsinfoblock"><input type="checkbox" value="' + contactsList[c].LINE_UID + '">';
		                		
		                		cinfd += contactsList[c].COMPANY + '-' + contactsList[c].CONTACT_NAME;
		                		
		                		$('#qryflsdiv').append(cinfd);
		                	}
		            	} else {
		            		alert('搜尋不到任何聯絡人!!');
		            	}
		        	} else {
		        		alert('搜尋不到任何聯絡人!!');
		        	}
		        } else {
		        	alert('搜尋不到任何聯絡人!!');
		        }
		        $('#fwlseloutterdiv').css('display', '');
		        $('#contactsqryloadimg').animate({
		        	opacity: 0
		        }, 500, function() {
		        	$('#contactsqryloadimg').css('display', 'none');
		        	$('#fwlseloutterdiv').animate({
		        		opacity: 1
		        	}, 500);
		        });
		    });
		});
	});
}

function contsallchange(allcheck) {
	if ($(allcheck).prop("checked")) {
		$(allcheck).parent().parent().find('input[type="checkbox"][value!="ALL"]').prop('checked', true);
	} else {
		$(allcheck).parent().parent().find('input[type="checkbox"][value!="ALL"]').prop('checked', false);
	}
}

function flsquerycancel(divname) {
	$('#' + divname).animate({
		'opacity': '0',
		'width': '0'
	}, 500, function() {
		$('#' + divname).remove();
		$('#sendopeoutterdiv').css('display', '');
		
		$('#sendopeoutterdiv').animate({
			'opacity': '1',
			'width': '40vw'
		}, 500);
	});
}

function flexmsgreselect() {
	$('#classnum').remove();
	$('.blockselector').remove();
	$('.contenttypeselector').remove();
	$('.flextitleoutterdiv').remove();
	$('.demoarea').remove();
	$('.operationflexmsg').remove();
	$('.editarea').remove();
	$('body').append(createflexmodselect());
}

function flexmsgcancel() {
	$('#classnum').remove();
	$('.blockselector').remove();
	$('.contenttypeselector').remove();
	$('.flextitleoutterdiv').remove();
	$('.demoarea').remove();
	$('.operationflexmsg').remove();
	$('.editarea').remove();
	
	$('#backgrounddiv').animate({
	    opacity: 0,
	    top: $(window).height()
	}, 500);
}