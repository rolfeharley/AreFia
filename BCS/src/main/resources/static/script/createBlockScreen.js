function createblock(afterfuntion) {
	var blockmodel = '<div id="arefiablockdiv" style="position: fixed; top: 101vh; left: 0; width: 100vw; height: 100vh; background: #FFF; opacity: 0.5; z-index: 998;"></div>';
	
	$('body').append(blockmodel);
	
	$('#arefiablockdiv').animate({
	    top: 0
	}, 500, afterfuntion);
}

function deleteblock() {
	$('#arefiablockdiv').animate({
	    top: 2500
	}, 500, function() {
		$('#arefiablockdiv').remove();
	});
}