/* global jQuery, Coral */
(function ($, Coral) {
  "use strict";

  console.log(" --------INPUTFIELD CLIENTLIBS LOADED------- ");

  var registry = $(window).adaptTo("foundation-registry");

  function validateInputText(inputField) {
    var inputFieldValue = inputField.val();
    var errorMessage = inputField.attr('data-error-message');

    if (inputFieldValue === "") {
      inputField.attr(
        "aria-invalid",
        errorMessage
      );
      $(".coral-Form-fielderror")
        .html(errorMessage)
        .show();
      inputField.parent().children(".coral-Form-errorlabel").show();
      inputField.parent().children(".coral-Form-fielderror").show();
      inputField.parent().children(".coral-Form-fielderror").css("display", "none");  
      inputField.addClass("is-invalid");
      return errorMessage;
    } else {
      inputField.removeClass("is-invalid");
      inputField.parent().children(".coral-Form-fielderror").hide();
      inputField.parent().children(".coral-Form-errorlabel").hide();
    }
  }

  registry.register("foundation.validation.validator", {
    selector: "[data-foundation-validation=input-validator]",
    validate: function (el) {
      var inputField = $(el);

      return validateInputText(inputField);
    },
  });

  $(document).on("dialog-ready", function () {
    Coral.commons.ready(function () {
      var inputField = $("[data-foundation-validation='input-validator']");

        // validation when dialog opens
        validateInputText(inputField);

        // validation when imageAltField value changes
        inputField.on("change", function () {
         validateInputText(inputField);
        });
      });

    });

})(jQuery, Coral);

