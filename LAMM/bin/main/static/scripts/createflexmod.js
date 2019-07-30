var curselnum;

function createflexmodselect() {
	var modstr = '<div id="flexmoddiv"><div class="container-fluid"><div class="row">';
	
	modstr += '<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12"><center style="margin-bottom: 20px;">請選擇版型</center></div>';
	modstr += '</div><div class="row">';
	
	modstr += '<div class="col-6 col-sm-6 col-md-3 col-lg-3 col-xl-3"><div class="flexmodselector">';
	modstr += '<div class="flexmodtable" onclick="triggerselect(\'1\')"><table><tr><td style="height: 30vh;"></td></tr></table></div>';
	modstr += '<input type="radio" name="templatechoice" value="1" onchange="afterselecttemp(this)"></div></div>';
	
	modstr += '<div class="col-6 col-sm-6 col-md-3 col-lg-3 col-xl-3"><div class="flexmodselector">';
	modstr += '<div class="flexmodtable" onclick="triggerselect(\'2\')"><table><tr><td style="height: 30vh; width: 50%; border-right: 1px solid #000;">';
	modstr += '</td><td style="width: 50%;"></td></tr></table></div>';
	modstr += '<input type="radio" name="templatechoice" value="2" onchange="afterselecttemp(this)"></div></div>';
	
	modstr += '<div class="col-6 col-sm-6 col-md-3 col-lg-3 col-xl-3"><div class="flexmodselector">';
	modstr += '<div class="flexmodtable" onclick="triggerselect(\'3\')"><table><tr><td style="height: 15vh; border-bottom: 1px solid #000;"></td></tr><tr>';
	modstr += '<td style="height: 15vh;"></td></tr></table></div>';
	modstr += '<input type="radio" name="templatechoice" value="3" onchange="afterselecttemp(this)"></div></div>';
	
	modstr += '<div class="col-6 col-sm-6 col-md-3 col-lg-3 col-xl-3"><div class="flexmodselector">';
	modstr += '<div class="flexmodtable" onclick="triggerselect(\'4\')"><table><tr><td style="height: 10vh; border-bottom: 1px solid #000;"></td></tr><tr>';
	modstr += '<td style="height: 10vh; border-bottom: 1px solid #000;"></td></tr><tr><td style="height: 10vh;"></td></tr></table></div>';
	modstr += '<input type="radio" name="templatechoice" value="4" onchange="afterselecttemp(this)"></div></div>';
	
	modstr += '<div class="col-6 col-sm-6 col-md-3 col-lg-3 col-xl-3"><div class="flexmodselector">';
	modstr += '<div class="flexmodtable" onclick="triggerselect(\'5\')"><table><tr><td style="height: 15vh; width: 50%; border-right: 1px solid #000; ';
	modstr += 'border-bottom: 1px solid #000;"></td><td style="width: 50%; border-bottom: 1px solid #000;"></td></tr><tr>';
	modstr += '<td style="height: 15vh; width: 50%; border-right: 1px solid #000;"></td><td style="width: 50%;"></td></tr></table></div>';
	modstr += '<input type="radio" name="templatechoice" value="5" onchange="afterselecttemp(this)"></div></div>';
	
	modstr += '<div class="col-6 col-sm-6 col-md-3 col-lg-3 col-xl-3"><div class="flexmodselector">';
	modstr += '<div class="flexmodtable" onclick="triggerselect(\'6\')"><table><tr><td style="height: 15vh; border-bottom: 1px solid #000;" colspan="2">';
	modstr += '</td></tr><tr><td style="width: 50%; height: 15vh; border-right: 1px solid #000;"></td><td style="width: 50%;"></td></tr></table></div>';
	modstr += '<input type="radio" name="templatechoice" value="6" onchange="afterselecttemp(this)"></div></div>'
	
	modstr += '<div class="col-6 col-sm-6 col-md-3 col-lg-3 col-xl-3"><div class="flexmodselector">';
	modstr += '<div class="flexmodtable" onclick="triggerselect(\'7\')"><table><tr><td style="height: 15vh; border-bottom: 1px solid #000;"></td></tr><tr>';
	modstr += '<td style="height: 7.5vh; border-bottom: 1px solid #000;"></td></tr><tr><td style="height: 7.5vh;"></td></tr></table></div>';
	modstr += '<input type="radio" name="templatechoice" value="7" onchange="afterselecttemp(this)"></div></div>';
	
	modstr += '<div class="col-6 col-sm-6 col-md-3 col-lg-3 col-xl-3"><div class="flexmodselector">';	
	modstr += '<div class="flexmodtable" onclick="triggerselect(\'8\')"><table><tr><td style="height: 15vh; width: 33%; border-right: 1px solid #000; border-bottom: 1px solid #000;">';
	modstr += '<td style="width: 33%; border-right: 1px solid #000; border-bottom: 1px solid #000;"><td style="width: 34%; ';
	modstr += 'border-bottom: 1px solid #000;"></td></tr><tr><td style="height: 15vh; width: 33%; border-right: 1px solid #000;">';
	modstr += '<td style="width: 33%; border-right: 1px solid #000;"><td style="width: 34%;"></td></tr></table></div>';
	modstr += '<input type="radio" name="templatechoice" value="8" onchange="afterselecttemp(this)"></div></div>';
	
	modstr += '<div class="row"><div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12"><div class="templateexebtn">';
	modstr += '<button type="button" class="btn btn-primary flexqrybtn" style="margin-right: 10px; margin-top: 20px;" onclick="editflexmessage()">';
	modstr += '<span><i class="fas fa-cog"></i>執行</span></button>';
	modstr += '<button type="button" class="btn btn-danger flexqrybtn" style="margin-top: 20px;" onclick="canselecttemp()">';
	modstr += '<span><i class="fas fa-ban"></i>取消</span></button></div></div></div>';
	
	modstr += '</div></div></div>';
	
	return modstr;
}

function afterselecttemp(checkeditem) {
	$('.flexmodtable').css('background', 'transparent');
	$(checkeditem).parent().find('div').css('background', '#FFFF00');
	curselnum = $(checkeditem).val();
}

function canselecttemp() {
	$('#flexmoddiv').remove();
	
	$('#backgrounddiv').animate({
	    opacity: 0,
	    top: $(window).height()
	}, 500);
}

function editflexmessage() {
	if (curselnum == null) {
		alert('您未選取任何樣板!!');
	} else {
		var tmpnumtmp = curselnum;
		curselnum = null;
		$('#flexmoddiv').remove();
		createflexdetaileditor('0', tmpnumtmp, null);
	}
}

function triggerselect(tarnum) {
	$('input[value="'+ tarnum +'"]').trigger('click');
}