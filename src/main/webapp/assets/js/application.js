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
	
	// Delete and change visibility as POST requests
	$(".js-change-visibility, .js-delete").on("click", function() {
		var $this = $(this);
		var id = $this.data("prop-id");
		$.post($this.attr("href"), { id: id}, function() {
			window.location.reload();
		});
		return false;
	});
});

