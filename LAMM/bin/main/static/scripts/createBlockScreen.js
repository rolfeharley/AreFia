function createblock() {
	var blockmodel = '<div id="arefiablockdiv" style="position: fixed; top: 101vh; left: 0; width: 100vw; height: 100vh; background: #000; opacity: 0.5; z-index: 998;"></div>';
	
	$('body').append(blockmodel);
	
	$('#arefiablockdiv').animate({
	    top: 0
	}, 1000);
}

function deleteblock() {
	$('#arefiablockdiv').animate({
	    bottom: 0
	}, 1000, function() {
		$('#arefiablockdiv').remove();
	});
}