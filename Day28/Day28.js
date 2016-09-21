$(document).ready(function() {

  var resultMessage = $(".result");
  resultMessage.hide();

  function valueCheck() {
      if (document.getElementById('number').value > 8) {
      $("#number").addClass("invalid");
    } else {
      $("#number").removeClass("invalid");
    }

       if ((Number(document.getElementById('number').value) % 2) === 0) {
      $("#number").addClass("divisibleByTwo");
    } else {
      $("#number").removeClass("divisibleByTwo");
    }
      
      var str = document.getElementById('number').value;
               $("#number").removeClass("sevenInIt");
      for (i = 0; i< str.length; i++) {
        if (str.charAt(i) == 7) {
            $("#number").addClass("sevenInIt");
        } 
        }

  }

  function valueCheckRandom() {
      if (document.getElementById('number2').value > 8) {
      $("#number2").addClass("invalid");
    } else {
      $("#number2").removeClass("invalid");
    }

       if ((Number(document.getElementById('number2').value) % 2) === 0) {
      $("#number2").addClass("divisibleByTwo");
    } else {
      $("#number2").removeClass("divisibleByTwo");
    }
      
      var str = document.getElementById('number2').value;
      $("#number2").removeClass("sevenInIt");
      for (i = 0; i< str.length; i++) {
        if (str.charAt(i) == 7) {
            $("#number2").addClass("sevenInIt");
        } 
        }
      
      if (document.getElementById('number2').value == 0) {
        resultMessage.text("You hit zero!").show();
      }
      else {
        resultMessage.hide();
      }

  }

  function incrementValueCheckUser() {
      incrementValue = Number(document.getElementById('userChoice').value);
}
  function incrementValueCheckRandom() {
      incrementValue2 = Math.floor(Math.random() * 21) -10;
      document.getElementById('userChoice2').value = incrementValue2;
  }
  $("#increment-button").click(function() {
    incrementValueCheckUser();
    document.getElementById('number').value = Number(document.getElementById('number').value) + incrementValue;
     valueCheck();
});
  $("#decrement-button").click(function() {
    incrementValueCheckUser();
    document.getElementById('number').value = Number(document.getElementById('number').value) - incrementValue;
    valueCheck();
});
  $("#reset-button").click(function() {
    document.getElementById('number').value = 0;
    valueCheck();
});


  $("#increment-button2").click(function() {
    incrementValueCheckRandom();
    document.getElementById('number2').value = Number(document.getElementById('number2').value) + incrementValue2;
     valueCheckRandom();
});
  $("#decrement-button2").click(function() {
    incrementValueCheckRandom();
    document.getElementById('number2').value = Number(document.getElementById('number2').value) - incrementValue2;
    valueCheckRandom();
});
  $("#reset-button2").click(function() {
    document.getElementById('number2').value = 0;
    valueCheckRandom();
});
});