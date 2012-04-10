$(document).ready(function(){
	$('input, textarea').placeholder();
	$('.tabs').button();
	$('.tabs').click(function(e) {
		e.preventDefault();
	});
	$("#remember_session").click(function() {
		$("#remember").val("session");
	});
	$("#remember_name").click(function() {
		$("#remember").val("name");
	});
});

