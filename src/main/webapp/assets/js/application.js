$(document).ready(function(){
	$('input, textarea').placeholder();
	$('.tabs').button();
	$('.tabs').click(function(e) {
		e.preventDefault();
	});
	
	$('.servicesDrop li, .ambientsDrop li').click(function(event){
        event.stopPropagation();
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
	
	
	$("#js-advanced-search").on("click", function() {
		$(".subnav").toggleClass("subnav-expanded");
		$(".js-secondary-navbar").toggle();
	});
	
	
	$(".js-toggle-usertype").on("change", function() {	
		var $this = $(this);
		
		if ($this.val() == "User") {
			$(".agency-control").hide();
		} else {
			$(".agency-control").show();
		}
	});	
	
	// Most horrible js ever START
	var oldHtmlToClone = '<div class="controls js-roomlist"><h6>Ambiente</h6><br/> <input type="text" class="input-xlarge" name="rooms[0].size" placeholder="Tama&ntilde;o del ambiente (metros cuadrados)"/><br/><br/><span>Tipo:</span><select  name="rooms[0].type"><option value="BATHROOM">Ba&ntilde;o</option><option value="DORM">Dormitorio</option><option value="KITCHEN">Cocina</option><option value="LIVING">Living</option><option value="PLAYROOM">Playroom</option></select><a href="javascript:;" class="btn btn-danger js-del-room">Eliminar</a><hr/></div>';
	
	$(".js-add-room").live("click", function() {
		var $this = $(this);
		
		if ($(".js-roomlist").length > 0) {
			var htmlToClone = $(".js-roomlist:last")[0].outerHTML;
			
			var nameToUpdate = $(htmlToClone).find("input[type=text]").attr("name");
				
			$(htmlToClone).insertAfter(".js-roomlist:last");
			
			var newElement = $(".js-roomlist:last");
			
			var nextIndex = parseInt(nameToUpdate.substring(6,7)) + 1;
			
			var nextName = nameToUpdate.substring(0,6) + nextIndex.toString() + nameToUpdate.substring(7);
			
			$(newElement).find("input[type=text]").attr("name", nextName);
			
			nameToUpdate = $(newElement).find("select").attr("name");
			
			nextName = nameToUpdate.substring(0,6) + nextIndex.toString() + nameToUpdate.substring(7);
			
			$(newElement).find("select").attr("name", nextName);
		} else {
			$(oldHtmlToClone).insertAfter(".js-roomlist-init");
		}
	});
	
	$(".js-del-room").live("click", function() {
		$(this).parents(".js-roomlist:first").remove();
	});
	// Most horrible js ever END
});

