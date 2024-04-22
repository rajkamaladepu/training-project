/* global jQuery, Coral */
(function($, Coral) {
	"use strict";
	var registry = $(window).adaptTo("foundation-registry");
	function validateImageAltText(imagePathField, imageAltField, imageDecorativeValue) {
		var imagePathValue = imagePathField.val();
		var imageAltValue = imageAltField.val();
		// If imageDecorativeValue is undefined, assume it's true
		imageDecorativeValue = typeof imageDecorativeValue === "undefined" ? true : imageDecorativeValue;

		if (imageDecorativeValue !== true && imagePathValue && imageAltValue === "") {
			imageAltField.attr("aria-invalid", "Please fill out this field.");
			if(imageAltField.parent().find(".coral-Form-errorlabel").length === 0){
				imageAltField.parent().append('<label class="coral-Form-errorlabel" tabindex="0">Please fill out this field.</label>');
			} else if(imageAltField.parent().find(".coral-Form-errorlabel").length === 2){
				imageAltField.parent().find(".coral-Form-errorlabel")[1].remove();
			} 
			imageAltField.parent().children(".coral-Form-errorlabel").show();
			imageAltField.parent().children(".coral-Form-fielderror").show();
			imageAltField.addClass("is-invalid");
			return "Please fill out this field.";
		} else {
			imageAltField.removeClass("is-invalid");
			imageAltField.parent().children(".coral-Form-fielderror").hide();
			imageAltField.parent().children(".coral-Form-errorlabel").hide();
		}
	}

	registry.register("foundation.validation.validator", {
		selector: "[data-foundation-validation=alt-validator]",
		validate: function(el) {
			var imageAltField = $(el);
			var imagePathField = $("[name='./" + imageAltField.attr("data-imageproperty") + "']");
			var imageDecorativeField = $("[name='./" + imageAltField.attr("data-decorativeProperty") + "']");
			var imageDecorativeValue = imageDecorativeField.is(":checked");

			return validateImageAltText(imagePathField, imageAltField, imageDecorativeValue);
		},
	});

	function fetchImageDescription(path, callback) {
		$.ajax({
			url: "/content/mycity/servlets/fetchimagemetadata.json",
			data: {
				imagePath: path,
			},
			method: "GET",
			dataType: "json",
			success: function(data) {
				callback(null, data.description, data.isDecorative);
			},
			error: function(xhr, status, error) {
				callback(error);
			},
		});
	}

	$(document).on("dialog-ready", function() {
		Coral.commons.ready(function() {
			var imageAltFields = $("[data-foundation-validation='alt-validator']");

			imageAltFields.each(function() {
				var imageAltField = $(this);
				var imagePathField = $("[name='./" + imageAltField.attr("data-imageproperty") + "']");
				var imageDecorativeField = $("[name='./" + imageAltField.attr("data-decorativeProperty") + "']");
				var imageDecorativeValue = imageDecorativeField.is(":checked");


				// validation when dialog opens
				validateImageAltText(imagePathField, imageAltField, imageDecorativeValue);

				// validation when imagePath value changes
				imagePathField.on("change", function() {
					fetchImageDescription($(this).val(), function(error, description, isDecorative) {
						if (!error) {
							imageAltField.val(description);
							if(isDecorative == "yes"){
								imageDecorativeField.prop("checked", true);
								imageDecorativeValue=true;
							}else {
								imageDecorativeField.prop("checked", false);
								imageDecorativeValue=false;
							}
						}
						validateImageAltText(imagePathField, imageAltField,imageDecorativeValue);
					});
				});

				// validation when imageDecorativeField value changes
				imageDecorativeField.on("change", function() {

					imageDecorativeValue = !imageDecorativeValue;
					validateImageAltText(imagePathField, imageAltField, imageDecorativeValue);
				});

				// validation when imageAltField value changes
				imageAltField.on("change", function() {
					validateImageAltText(imagePathField, imageAltField, imageDecorativeValue);
				});
			});
		});
	});
})(jQuery, Coral);