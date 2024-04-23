/* global jQuery, Coral */
(function ($, Coral) {
  "use strict";

  var registry = $(window).adaptTo("foundation-registry");

  registry.register("foundation.validation.validator", {
    selector: `[data-foundation-validation=nyc-minmax]`,
    validate: function(element) {
      var el = $(element);
      let max=el.data("maximum-items");
            let min=el.data("minimum-items");
            let items=el.children("coral-multifield-item").length;
            let domitems=el.children("coral-multifield-item");

            if(items>max){
              return "Only "+max+" features are allowed to be added"
            }
            if(items<min){
                return "You need to add minimum "+min+" items."
            }
    }
  });

})(jQuery, Coral);
