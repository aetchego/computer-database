//On load
$(function() {
    // Default: hide edit mode
    $(".editMode").hide();
    
    // Click on "selectall" box
    $("#selectall").click(function () {
        $('.cb').prop('checked', this.checked);
    });

    // Click on a checkbox
    $(".cb").click(function() {
        if ($(".cb").length == $(".cb:checked").length) {
            $("#selectall").prop("checked", true);
        } else {
            $("#selectall").prop("checked", false);
        }
        if ($(".cb:checked").length != 0) {
            $("#deleteSelected").enable();
        } else {
            $("#deleteSelected").disable();
        }
    });

});


// Function setCheckboxValues
(function ( $ ) {

    $.fn.setCheckboxValues = function(formFieldName, checkboxFieldName) {

        var str = $('.' + checkboxFieldName + ':checked').map(function() {
            return this.value;
        }).get().join();
        
        $(this).attr('value',str);
        
        return this;
    };

}( jQuery ));

// Function toggleEditMode
(function ( $ ) {

    $.fn.toggleEditMode = function() {
    	var edit = "Edit";
    	var view = "View";
    	if ($(language).text() == "Français") {
    		edit = "Mode édition";
    		view = "Mode lecture";
    	}
        if($(".editMode").is(":visible")) {
            $(".editMode").hide();
            $("#editComputer").text(edit);
        }
        else {
            $(".editMode").show();
            $("#editComputer").text(view);
        }
        return this;
    };

}( jQuery ));


// Function delete selected: Asks for confirmation to delete selected computers, then submits it to the deleteForm
(function ( $ ) {
	if ($(language).text() == "Français")
		msg = "Êtes-vous sûr de vouloir supprimer les ordinateurs sélectionnés ?"
	else
		var msg = "Are you sure you want to delete the selected computers?";
    $.fn.deleteSelected = function() {
        if (confirm(msg)) { 
            $('#deleteForm input[name=selection]').setCheckboxValues('selection','cb');
            $('#deleteForm').submit();
        }
    };
}( jQuery ));



//Event handling
//Onkeydown
$(document).keydown(function(e) {

    switch (e.keyCode) {
        //DEL key
        case 46:
            if($(".editMode").is(":visible") && $(".cb:checked").length != 0) {
                $.fn.deleteSelected();
            }   
            break;
        //E key (CTRL+E will switch to edit mode)
        case 69:
            if(e.ctrlKey) {
                $.fn.toggleEditMode();
            }
            break;
    }
});

$("#inputGroupSelect01").click(function() {
	if ($("#inputGroupSelect01").val() == "computer.id")
		$("#order").hide();
	else
		$("#order").show();
});
