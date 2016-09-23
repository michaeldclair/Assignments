$(document).ready(function() {

var token = getSlackToken();
var channelId = new Array();
var channelIdPicked = new Array();
var groupId = new Array();
var groupIdPicked = new Array();
var channel;

  // List public channels
  url = "https://slack.com/api/channels.list?token=" + token;
  $.ajax(url, {
    "method": "GET"}).then(function(result) {

      for (i=0; i<result.channels.length; i++) {
        channelId[i] = result.channels[i].id;
        var channelName = result.channels[i].name;
        var item = $('<input type="checkbox" name="oneonly" id="' + i + 'button" value="' + channelName + '"> ' + channelName + '<br>');
        $("#channels").append(item); 
      console.log(result.channels[i].name);
    }
})

    // List private channels
      url = "https://slack.com/api/groups.list?token=" + token;
  $.ajax(url, {
    "method": "GET"}).then(function(result) {

      for (i=0; i<result.groups.length; i++) {
        groupId[i] = result.groups[i].id;
        var groupName = result.groups[i].name;
        var groupItem = $('<input type="checkbox" name="oneonly" id="' + i + 'groupbutton" value="' + groupName + '"> ' + groupName + '<br>');
        $("#channels2").append(groupItem); 
      console.log(groupName);
    }
})

$("#postButton").click(function() {
var text = $("#message").val();
checkArray();
for (i=0;i<channelIdPicked.length;i++) {
  channel = channelIdPicked[i];
  postMessage(text)
}
for (i=0;i<groupIdPicked.length;i++) {
  channel = groupIdPicked[i];
  postMessage(text)
}

//postMessage(text);
});



$("#toggleButton").click(function(){
    $("#channels2").toggle(); 

  if (document.getElementById("toggleButton").value == "Show Private Channels") {
    document.getElementById("aToggle").text= "Hide Private Channels";
   document.getElementById("toggleButton").value = "Hide Private Channels";}
  else {
    document.getElementById("aToggle").text = "Show Private Channels";
    document.getElementById("toggleButton").value = "Show Private Channels";}
});

$("a#aToggle").click(function() {
  $("#channels2").toggle(); 

  if (document.getElementById("aToggle").text == "Show Private Channels") {
   document.getElementById("aToggle").text= "Hide Private Channels";
   document.getElementById("toggleButton").value = "Hide Private Channels";}
  else {
    document.getElementById("aToggle").text = "Show Private Channels";
    document.getElementById("toggleButton").value = "Show Private Channels";}
});


function checkArray() {
       for (i=0; i<channelId.length;i++) {
        var buttonName = i + "button";
        if (document.getElementById(buttonName).checked == true){
    //    channel = channelId[i];
     //   channelIdPicked[i] = channel;
        channelIdPicked[i] = channelId[i];
    }
  }
  
      for (i=0; i<groupId.length;i++) {
        var buttonName = i + "groupbutton";
        if (document.getElementById(buttonName).checked == true){
  //      channel = groupId[i];
  //      groupIdPicked[i] = channel;
        groupIdPicked[i] = groupId[i];
    }
  }

}   


function postMessage(MessageIn) {
$.ajax("https://slack.com/api/chat.postMessage?", {
    data: {
      "token": token,
      "channel": channel,
      "text": MessageIn
          },
    "method": "POST"
    })
 }




});
