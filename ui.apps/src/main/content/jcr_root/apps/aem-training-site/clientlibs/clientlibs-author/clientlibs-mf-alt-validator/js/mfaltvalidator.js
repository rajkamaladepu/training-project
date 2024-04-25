/* global jQuery, Coral */
(function($, Coral) {
	"use strict";

	var registry = $(window).adaptTo("foundation-registry");

	function validateImageAltText(imagePathField, imageAltField, imageDecorativeValue) {
		var imagePathValue = imagePathField.val();
		var imageAltValue = imageAltField.val();
		imageDecorativeValue = typeof imageDecorativeValue === "undefined" ? true : imageDecorativeValue;

		if (imageDecorativeValue !== true && imagePathValue && imageAltValue === "") {
			imageAltField.attr("aria-invalid", "Please fill out this field.");
			$(".coral-Form-fielderror").html("Please fill out this field.").show();
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

	function showError(field, message) {
		field.attr("aria-invalid", "true").addClass("is-invalid");
		let parent = field.parent();
		let errorElement = parent.find(".coral-Form-errorlabel");
		if (errorElement.length === 0) {
			// If the error label element doesn't exist, create and add a new one.
			parent.append('<label class="coral-Form-errorlabel" tabindex="0">' + message + '</label>');
		} else {
			// If the error label element exists, update its content with the error message.
			errorElement.html(message);
		}
		errorElement.show();
	}

	function hideError(field) {
		field.removeClass("is-invalid").parent().children(".coral-Form-fielderror, .coral-Form-errorlabel").hide();
	}

	function createValidator(selector, validationFunction) {
		registry.register("foundation.validation.validator", {
			selector: `[data-foundation-validation='${selector}']`,
			validate: validationFunction,
		});
	}

	function getField(nameAttrValue) {
		return $("[name='" + nameAttrValue + "']");
	}

	createValidator("alt-multifield", function(el) {
		var multifield = $(el);
		var imageAltField = multifield.find("[name$='./imageAltText']:last");
		var nameParts = imageAltField.attr("name").split("/./");
		var imagePathField = getField(`${nameParts[0]}/./${imageAltField.attr("data-imageproperty")}`);
		var imageDecorativeField = getField(`${nameParts[0]}/./${imageAltField.attr("data-decorativeProperty")}`);
		var imageDecorativeValue = imageDecorativeField.is(":checked");
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
				validateImageAltText(imagePathField, imageAltField, imageDecorativeValue);
			});
		});
	});

	createValidator("alt-validator", function(el) {
		var imageAltField = $(el);
		var nameParts = imageAltField.attr("name").split("/./");
		var imagePathField = getField(`${nameParts[0]}/./${imageAltField.attr("data-imageproperty")}`);
		var imageDecorativeField = getField(`${nameParts[0]}/./${imageAltField.attr("data-decorativeProperty")}`);
		var imageDecorativeValue = imageDecorativeField.is(":checked");
		return validateImageAltText(imagePathField, imageAltField, imageDecorativeValue);
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
				var nameParts = imageAltField.attr("name").split("/./");
				var imagePathField = getField(`${nameParts[0]}/./${imageAltField.attr("data-imageproperty")}`);
				var imageDecorativeField = getField(`${nameParts[0]}/./${imageAltField.attr("data-decorativeProperty")}`);
				var imageDecorativeValue = imageDecorativeField.is(":checked");

				validateImageAltText(imagePathField, imageAltField, imageDecorativeValue);

				imagePathField.on("change", function() {
					fetchImageDescription($(this).val(), function(error, description, isDecorative) {
						if (!error) {
							imageAltField.val(description);
							if(isDecorative == "yes"){
								imageDecorativeField.prop("checked", true);
							}else {
								imageDecorativeField.prop("checked", false);
							}
						}
						validateImageAltText(imagePathField, imageAltField, imageDecorativeValue);
					});
				});

				imageDecorativeField.on("change", function() {
					imageDecorativeValue = !imageDecorativeValue;
					validateImageAltText(imagePathField, imageAltField, imageDecorativeValue);
				});

				imageAltField.on("change", function() {
					validateImageAltText(imagePathField, imageAltField, imageDecorativeValue);
				});
			});
		});
	});
})(jQuery, Coral);