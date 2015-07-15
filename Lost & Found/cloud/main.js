
// Use Parse.Cloud.define to define as many cloud functions as you want.
// For example:
Parse.Cloud.define("hello", function(request, response) {
  response.success("Hello world!");
});



Parse.Cloud.afterSave("Items", function(request) {
  query = new Parse.Query("Item");
  if(request.user != null){

  
}
});
