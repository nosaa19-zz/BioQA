$(document).ready(function() {
    // SUBMIT FORM
    $("#searchForm").submit(function(event) {
      // Prevent the form from submitting via the browser.
      event.preventDefault();
      doPostQuery();
    });

    function doPostQuery(){
        
        // PREPARE FORM DATA
        var query = $("#search").val();
        
        // DO POST
           $.ajax({
          type : "POST",
          cache: false,
          contentType : "application/json",
          url: "/api/_search/question",
          data : JSON.stringify(query),
          dataType : 'json',
       }).done(
         function (data) { 	console.log("AYAAAAM");  		});}
  });

